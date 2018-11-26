package pl.edu.agh.torbjorns.board;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.torbjorns.board.deck.Deck;
import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.card.Rank;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class BoardFactory {

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

        new Placer(board, deck)
                .placeCards();

        return board;
    }

    @RequiredArgsConstructor
    private class Placer {
        private final Board board;
        private final Deck deck;

        private int currentStackIndex = 0;
        private Deque<Card> currentStack = new ArrayDeque<>();

        public void placeCards() {
            var firstKingPut = false;

            while (!deck.isEmpty()) {
                var card = deck.takeCard();

                if (card.getRank() == Rank.KING) {
                    if (!firstKingPut) {
                        currentStack.addFirst(card);
                        firstKingPut = true;
                    } else {
                        completeCurrentStack();
                        currentStack.addLast(card);
                    }
                } else {
                    currentStack.addLast(card);
                }
            }

            completeCurrentStack();
        }

        public void completeCurrentStack() {
            board.getWorkingCardStacks().get(currentStackIndex).setCards(currentStack);
            currentStackIndex++;
            currentStack = new ArrayDeque<>();
        }
    }

}
