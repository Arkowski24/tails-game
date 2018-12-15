package pl.edu.agh.torbjorns.board;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import lombok.NonNull;
import pl.edu.agh.torbjorns.card.Card;

import java.util.Optional;

public class BufferPlace implements CardManager {

    private final Property<Card> card = new SimpleObjectProperty<>(null);

    public Property<Card> cardProperty() {
        return card;
    }

    @Override
    public Optional<Card> peekCard() {
        return Optional.ofNullable(card.getValue());
    }

    @Override
    public boolean canPutCard(@NonNull Card card) {
        return isEmpty();
    }

    @Override
    public void putCard(@NonNull Card card) {
        requireEmpty();
        this.card.setValue(card);
    }

    @Override
    public Card takeCard() {
        requireNotEmpty();

        var card = this.card.getValue();
        this.card.setValue(null);

        return card;
    }

    public boolean isEmpty() {
        return card.getValue() == null;
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
