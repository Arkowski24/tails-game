package pl.edu.agh.torbjorns.board;

import lombok.NonNull;
import pl.edu.agh.torbjorns.card.Card;

import java.util.Optional;

public class BufferZone {
    public final static int SIZE = 8;
    private final Card[] cards = new Card[SIZE];

    public Optional<Card> peekCard(int place) {
        checkPlaceIndex(place);

        return Optional.of(cards[place]);
    }

    public void putCard(@NonNull Card card, int place) {
        checkPlaceIndex(place);
        requirePlaceEmpty(place);

        cards[place] = card;
    }

    public Card takeCard(int place) {
        checkPlaceIndex(place);
        requirePlaceNotEmpty(place);

        var card = cards[place];
        cards[place] = null;

        return card;
    }

    private void checkPlaceIndex(int place) {
        if (place < 0 || place > SIZE) {
            throw new IndexOutOfBoundsException("Invalid place");
        }
    }

    private void requirePlaceEmpty(int place) {
        if (cards[place] != null) {
            throw new IllegalStateException("Place is not empty");
        }
    }

    private void requirePlaceNotEmpty(int place) {
        if (cards[place] == null) {
            throw new IllegalStateException("Place is empty");
        }
    }

}
