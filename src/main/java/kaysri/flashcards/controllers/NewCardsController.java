package kaysri.flashcards.controllers;

import kaysri.flashcards.model.FlashCard;
import kaysri.flashcards.repositories.FlashCardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/newcards")

public class NewCardsController {

   private final FlashCardRepository flashCardRepository;

   public NewCardsController(FlashCardRepository flashCardRepository) {
      this.flashCardRepository = flashCardRepository;
   }

   @GetMapping
   public String newCardForm(Model model){
      model.addAttribute("flashcard", new FlashCard());
      return "newcards";
   }


   @PostMapping
   public String processNewCard(@ModelAttribute FlashCard flashCard, Errors errors, Model model){

      if(errors.hasErrors()){
         return "newcards";
      }

      model.addAttribute("flashcard", flashCard);

      flashCardRepository.save(flashCard);

      return "index";
   }
}
