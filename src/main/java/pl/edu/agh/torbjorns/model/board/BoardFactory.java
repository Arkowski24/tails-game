package pl.edu.agh.torbjorns.model.board;

import pl.edu.agh.torbjorns.model.card.Suit;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class BoardFactory {

    private static final int WORKING_CARD_STACK_COUNT = 8;
    private static final Suit[] FINISHED_CARD_STACK_SUITS = {
            Suit.HEARTS, Suit.DIAMONDS, Suit.SPADES, Suit.CLUBS,
            Suit.HEARTS, Suit.DIAMONDS, Suit.SPADES, Suit.CLUBS
    };

    public Board createBoard() {
        var finishedCardStacks =
                Stream.of(FINISHED_CARD_STACK_SUITS)
                        .map(FinishedCardStack::new)
                        .collect(toUnmodifiableList());

        List<CardStack> workingCardStacks =
                Stream.generate(WorkingCardStack::new)
                        .limit(WORKING_CARD_STACK_COUNT)
                        .collect(toUnmodifiableList());

        var bufferPlaces =
                Stream.generate(BufferPlace::new)
                        .limit(BufferZone.SIZE)
                        .collect(toUnmodifiableList());

        var bufferZone = new BufferZone(bufferPlaces);

        return new Board(finishedCardStacks, workingCardStacks, bufferZone);
    }

}
