package pl.edu.agh.torbjorns.board;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.torbjorns.card.Card;

import java.util.Collection;
import java.util.Optional;

public abstract class CardStack implements CardManager {

    private final ObservableList<Card> cards = FXCollections.observableArrayList();

    @Override
    public abstract boolean canPutCard(Card card);

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public ObservableList<Card> getCards() {
        return cards;
    }

    public Binding<Card> topCardProperty() {
        return Bindings.createObjectBinding(() -> peekCard().orElse(null), cards);
    }

    @Override
    public Optional<Card> peekCard() {
        if (isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(getTopCard());
        }
    }

    public Card getTopCard() {
        requireNotEmpty();
        return cards.get(cards.size() - 1);
    }

    public void setCards(Collection<Card> cards) {
        this.cards.clear();
        this.cards.addAll(cards);
    }

    @Override
    public void putCard(Card card) {
        requireCanPutCard(card);
        cards.add(card);
    }

    @Override
    public Card takeCard() {
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
