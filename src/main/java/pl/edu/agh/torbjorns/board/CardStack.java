package pl.edu.agh.torbjorns.board;

import pl.edu.agh.torbjorns.card.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class CardStack {

    private final List<Card> cards = new ArrayList<>();

    public abstract boolean canPutCard(Card card);

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getTopCard() {
        requireNotEmpty();
        return cards.get(cards.size() - 1);
    }

    public void setCards(Collection<Card> cards) {
        this.cards.clear();
        this.cards.addAll(cards);
    }

    public void putCard(Card card) {
        requireCanPutCard(card);
        cards.add(card);
    }

    public Card removeCard() {
        requireNotEmpty();
        return cards.remove(cards.size() - 1);
    }

    private void requireNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
    }

    private void requireCanPutCard(Card card) {
        if (!canPutCard(card)) {
            throw new IllegalStateException("Given card cannot be put on the stack");
        }
    }

}
