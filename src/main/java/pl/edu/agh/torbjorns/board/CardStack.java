package pl.edu.agh.torbjorns.board;

import javafx.beans.binding.Binding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.card.Card;

import java.util.Collection;

import static javafx.beans.binding.Bindings.*;

public abstract class CardStack implements CardHolder {

    private final ObservableList<Card> cards = FXCollections.observableArrayList();

    @Override
    public abstract boolean canPutCard(Card card);

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public ObservableList<Card> getCards() {
        return cards;
    }

    public Binding<@Nullable Card> topCardProperty() {
        return createObjectBinding(this::peekTopCard, cards);
    }

    @Override
    public @Nullable Card peekTopCard() {
        if (isEmpty()) {
            return null;
        } else {
            return getTopCard();
        }
    }

    public Card getTopCard() {
        requireNotEmpty();
        return cards.get(cards.size() - 1);
    }

    public void setCards(Collection<Card> cards) {
        this.cards.clear();
        this.cards.addAll(cards);
        this.cards.forEach(card -> card.setHolder(this));
    }

    @Override
    public void putCard(Card card) {
        requireCanPutCard(card);
        cards.add(card);
        card.setHolder(this);
    }

    @Override
    public Card takeCard() {
        requireNotEmpty();
        var card = cards.remove(cards.size() - 1);
        card.setHolder(null);
        return card;
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
