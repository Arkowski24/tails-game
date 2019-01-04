package pl.edu.agh.torbjorns.board;

import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.card.Card;

public interface CardHolder {
    boolean isEmpty();

    @Nullable Card peekTopCard();

    boolean canPutCard(Card card);

    void putCard(Card card);

    Card takeCard();
}
