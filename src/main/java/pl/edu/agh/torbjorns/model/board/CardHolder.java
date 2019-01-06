package pl.edu.agh.torbjorns.model.board;

import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.model.card.Card;

public interface CardHolder {
    boolean isEmpty();

    @Nullable Card peekTopCard();

    boolean canPutCard(Card card);

    boolean canTakeCard();

    void putCard(Card card);

    Card takeCard();
}
