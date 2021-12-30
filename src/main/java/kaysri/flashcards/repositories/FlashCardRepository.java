package kaysri.flashcards.repositories;

import kaysri.flashcards.model.FlashCard;
import org.springframework.data.repository.CrudRepository;

public interface FlashCardRepository extends CrudRepository<FlashCard, Long> {
}
