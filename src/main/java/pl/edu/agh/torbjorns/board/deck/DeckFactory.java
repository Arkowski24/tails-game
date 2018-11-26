package pl.edu.agh.torbjorns.board.deck;

import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.card.Rank;
import pl.edu.agh.torbjorns.card.Suit;

import java.util.Collections;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class DeckFactory {

    public Deck createDeck() {
        var cards = Stream.concat(createSingleDeck(), createSingleDeck())
                .collect(toList());

        Collections.shuffle(cards);

        return new Deck(cards);
    }

    private Stream<Card> createSingleDeck() {
        return Stream.of(Rank.values())
                .flatMap(rank ->
                        Stream.of(Suit.values())
                                .map(suit -> new Card(rank, suit)));
    }

}
