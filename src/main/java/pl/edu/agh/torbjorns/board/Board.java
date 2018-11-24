package pl.edu.agh.torbjorns.board;

import lombok.Getter;
import pl.edu.agh.torbjorns.card.Suit;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Board {

    public static final int WORKING_CARD_STACK_COUNT = 8;
    private static final Suit[] FINISHED_CARD_STACK_SUITS = {
            Suit.HEARTS, Suit.DIAMONDS, Suit.SPADES, Suit.CLUBS,
            Suit.HEARTS, Suit.DIAMONDS, Suit.SPADES, Suit.CLUBS
    };

    private final List<CardStack> finishedCardStacks =
            Stream.of(FINISHED_CARD_STACK_SUITS)
                    .map(FinishedCardStack::new)
                    .collect(toList());

    private final List<CardStack> workingCardStacks =
            Stream.generate(WorkingCardStack::new)
                    .limit(WORKING_CARD_STACK_COUNT)
                    .collect(toList());

    @Getter
    private final BufferZone bufferZone = new BufferZone();

    public List<CardStack> getFinishedCardStacks() {
        return Collections.unmodifiableList(finishedCardStacks);
    }

    public List<CardStack> getWorkingCardStacks() {
        return Collections.unmodifiableList(workingCardStacks);
    }

}
