package pl.edu.agh.torbjorns.board.deck;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.torbjorns.card.Card;

import java.util.List;

@RequiredArgsConstructor
public class Deck {

    private final List<Card> cards;

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card takeCard() {
        if (isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.remove(cards.size() - 1);
    }

}
