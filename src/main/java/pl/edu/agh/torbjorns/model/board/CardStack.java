package pl.edu.agh.torbjorns.model.board;

import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.model.card.Card;

import static javafx.beans.binding.Bindings.*;

public abstract class CardStack implements CardHolder {

    private final ObservableList<Card> cards = FXCollections.observableArrayList();

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public ObservableList<Card> getCards() {
        return cards;
    }

    public ObjectBinding<@Nullable Card> topCardBinding() {
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

    @Override
    public void putCard(Card card, boolean forcibly) {
        if (!forcibly) requireCanPutCard(card);

        cards.add(card);
        card.setHolder(this);
    }

    @Override
    public Card takeCard(boolean forcibly) {
        requireNotEmpty();

        if (!forcibly) requireCanTakeCard();

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

    private void requireCanTakeCard() {
        if (!canTakeCard()) {
            throw new IllegalStateException("Card cannot be taken from the stack");
        }
    }

}
