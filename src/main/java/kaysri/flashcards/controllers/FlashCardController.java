package kaysri.flashcards.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/home", "/index"})
public class FlashCardController {

   @GetMapping
   public String goHome(){
      return "index";
   }

}
