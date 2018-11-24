package pl.edu.agh.torbjorns.board;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.torbjorns.board.deck.Deck;
import pl.edu.agh.torbjorns.card.Suit;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class BoardFactory {

    private static final int WORKING_CARD_STACK_COUNT = 8;
    private static final Suit[] FINISHED_CARD_STACK_SUITS = {
            Suit.HEARTS, Suit.DIAMONDS, Suit.SPADES, Suit.CLUBS,
            Suit.HEARTS, Suit.DIAMONDS, Suit.SPADES, Suit.CLUBS
    };

    private final Dealer dealer;

    public Board createBoard(Deck deck) {
        var finishedCardStacks =
                Stream.of(FINISHED_CARD_STACK_SUITS)
                        .map(FinishedCardStack::new)
                        .collect(toUnmodifiableList());

        List<CardStack> workingCardStacks =
                Stream.generate(WorkingCardStack::new)
                        .limit(WORKING_CARD_STACK_COUNT)
                        .collect(toUnmodifiableList());

        var bufferZone = new BufferZone();

        var board = new Board(finishedCardStacks, workingCardStacks, bufferZone);

        dealer.dealCards(board, deck);

        return board;
    }

}
