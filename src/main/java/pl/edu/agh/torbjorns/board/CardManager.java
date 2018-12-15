package pl.edu.agh.torbjorns.board;

import pl.edu.agh.torbjorns.card.Card;

import java.util.Optional;

public interface CardManager {
    Optional<Card> peekCard();

    boolean canPutCard(Card card);

    void putCard(Card card);

    Card takeCard();
}
