package kaysri.flashcards.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "flashcard")
public class FlashCard {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NonNull
   private String question;

   @NonNull
   private String answer;

   public FlashCard() {

   }
}
