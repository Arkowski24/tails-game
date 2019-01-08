package pl.edu.agh.torbjorns.board;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.card.Card;

public class BufferPlace implements CardHolder {

    private final Property<@Nullable Card> cardProperty = new SimpleObjectProperty<>(null);

    public Property<@Nullable Card> cardProperty() {
        return cardProperty;
    }

    @Override
    public boolean isEmpty() {
        return cardProperty.getValue() == null;
    }

    @Override
    public @Nullable Card peekTopCard() {
        return cardProperty.getValue();
    }

    @Override
    public boolean canPutCard(Card card) {
        return isEmpty();
    }

    @Override
    public boolean canTakeCard() {
        return !isEmpty();
    }

    @Override
    public void putCard(Card card) {
        requireEmpty();
        cardProperty.setValue(card);
        card.setHolder(this);
    }

    @Override
    public Card takeCard() {
        requireNotEmpty();

        var card = cardProperty.getValue();
        assert card != null;
        cardProperty.setValue(null);
        card.setHolder(null);

        return card;
    }


    private void requireEmpty() {
        if (!isEmpty())
            throw new IllegalStateException("Place is not empty");
    }

    private void requireNotEmpty() {
        if (isEmpty())
            throw new IllegalStateException("Place is empty");
    }
}
