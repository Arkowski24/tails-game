package pl.edu.agh.torbjorns.model.board;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.torbjorns.model.board.deck.Deck;
import pl.edu.agh.torbjorns.model.card.Card;
import pl.edu.agh.torbjorns.model.card.Rank;

import java.util.ArrayDeque;
import java.util.Deque;

public class Dealer {

    public void dealCards(Board board, Deck deck) {
        new Deal(board, deck).perform();
    }

    @RequiredArgsConstructor
    private class Deal {
        private final Board board;
        private final Deck deck;

        private int currentStackIndex = 0;
        private Deque<Card> currentStack = new ArrayDeque<>();

        public void perform() {
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

        private void completeCurrentStack() {
            currentStack.forEach(board.getWorkingCardStacks().get(currentStackIndex)::putCardForcibly);
            currentStackIndex++;
            currentStack = new ArrayDeque<>();
        }
    }

}
