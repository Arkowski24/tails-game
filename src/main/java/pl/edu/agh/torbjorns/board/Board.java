package pl.edu.agh.torbjorns.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.edu.agh.torbjorns.card.Suit;

import java.util.List;

@RequiredArgsConstructor
public class Board {

    public static final int WORKING_CARD_STACK_COUNT = 8;
    public static final Suit[] FINISHED_CARD_STACK_SUITS = {
            Suit.HEARTS, Suit.DIAMONDS, Suit.SPADES, Suit.CLUBS,
            Suit.HEARTS, Suit.DIAMONDS, Suit.SPADES, Suit.CLUBS
    };

    @Getter
    private final List<CardStack> finishedCardStacks;

    @Getter
    private final List<CardStack> workingCardStacks;

    @Getter
    private final BufferZone bufferZone;

}
