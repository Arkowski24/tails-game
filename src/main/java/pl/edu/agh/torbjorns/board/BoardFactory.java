package pl.edu.agh.torbjorns.board;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.torbjorns.board.deck.Deck;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class BoardFactory {

    private final Dealer dealer;

    public Board createBoard(Deck deck) {
        List<CardStack> finishedCardStacks =
                Stream.of(Board.FINISHED_CARD_STACK_SUITS)
                        .map(FinishedCardStack::new)
                        .collect(toUnmodifiableList());

        List<CardStack> workingCardStacks =
                Stream.generate(WorkingCardStack::new)
                        .limit(Board.WORKING_CARD_STACK_COUNT)
                        .collect(toUnmodifiableList());

        var bufferZone = new BufferZone();

        var board = new Board(finishedCardStacks, workingCardStacks, bufferZone);

        dealer.dealCards(board, deck);

        return board;
    }

}
