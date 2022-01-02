package kaysri.flashcards.controllers;

import kaysri.flashcards.model.FlashCard;
import kaysri.flashcards.model.FlashCardCopy;
import kaysri.flashcards.repositories.FlashCardCopyRepository;
import kaysri.flashcards.repositories.FlashCardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/test")
public class TestController {

   private final FlashCardRepository flashCardRepository;
   private final FlashCardCopyRepository flashCardCopyRepository;

   public TestController(FlashCardRepository flashCardRepository, FlashCardCopyRepository flashCardCopyRepository) {
      this.flashCardRepository = flashCardRepository;
      this.flashCardCopyRepository = flashCardCopyRepository;
   }

   private void populateCopyRepo() {
      List<FlashCard> flashCards = new ArrayList<>();
      flashCardRepository.findAll().forEach(flashCards::add);

      for (FlashCard flcrd : flashCards) {
         String question = flcrd.getQuestion();
         String answer = flcrd.getAnswer();
         FlashCardCopy flashCardCopy = new FlashCardCopy();
         flashCardCopy.setAnswer(answer);
         flashCardCopy.setQuestion(question);
         flashCardCopyRepository.save(flashCardCopy);
      }
   }

   private FlashCardCopy getRandomFlashcard(List<FlashCardCopy> tempList) {
      Random random = new Random();
      return tempList.get(random.nextInt(tempList.size()));
   }

   private void removeRandomFromDB(FlashCardCopy flashCardCopy) {
      flashCardCopyRepository.delete(flashCardCopy);
   }


   @GetMapping
   public String getTest(Model model) {
      List<FlashCardCopy> flashCardCopies = new ArrayList<>();
      flashCardCopyRepository.findAll().forEach(flashCardCopies::add);
      if (flashCardCopies.isEmpty()) {
         populateCopyRepo();
         flashCardCopyRepository.findAll().forEach(flashCardCopies::add);
      }

      if (flashCardCopies.size() == 1) {
         FlashCardCopy flashCardCopy = flashCardCopies.get(0);
         flashCardCopyRepository.delete(flashCardCopy);
         return "redirect:index";
      } else {
         FlashCardCopy flashcardToShow = getRandomFlashcard(flashCardCopies);
         model.addAttribute("flashcard", flashcardToShow);
         removeRandomFromDB(flashcardToShow);
         return "test";
      }
   }
}

