package pl.edu.agh.torbjorns.model.board;

import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.model.card.Card;

public interface CardHolder {
    boolean isEmpty();

    @Nullable Card peekTopCard();

    boolean canPutCard(Card card);

    boolean canTakeCard();

    void putCard(Card card, boolean forcibly);

    Card takeCard(boolean forcibly);

    default void putCard(Card card) { putCard(card, false); }

    default void putCardForcibly(Card card) { putCard(card, true); }

    default Card takeCard() { return takeCard(false); }

    default Card takeCardForcibly() { return takeCard(true); }
}
