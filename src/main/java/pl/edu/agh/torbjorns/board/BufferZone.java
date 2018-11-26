package pl.edu.agh.torbjorns.board;

import lombok.NonNull;
import pl.edu.agh.torbjorns.card.Card;

import java.util.Optional;

public class BufferZone {
    public final static int SIZE = 8;
    private final Card[] cards = new Card[SIZE];

    public boolean isPlaceEmpty(int place) {
        return cards[place] == null;
    }

    public Optional<Card> peekCard(int place) {
        return Optional.of(cards[place]);
    }

    public void putCard(@NonNull Card card, int place) {
        requirePlaceEmpty(place);

        cards[place] = card;
    }

    public Card takeCard(int place) {
        requirePlaceNotEmpty(place);

        var card = cards[place];
        cards[place] = null;

        return card;
    }

    private void requirePlaceEmpty(int place) {
        if (!isPlaceEmpty(place))
            throw new IllegalStateException("Place is not empty");
    }

    private void requirePlaceNotEmpty(int place) {
        if (isPlaceEmpty(place))
            throw new IllegalStateException("Place is empty");
    }

}
