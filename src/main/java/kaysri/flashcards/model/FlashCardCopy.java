package kaysri.flashcards.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class FlashCardCopy {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NonNull
   private String question;

   @NonNull
   private String answer;

   public FlashCardCopy() {

   }
}
