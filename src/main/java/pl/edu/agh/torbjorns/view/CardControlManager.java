package pl.edu.agh.torbjorns.view;

public interface CardControlManager {
    void addCard(CardControl cardControl);

    void removeCard(CardControl cardControl);

    CardControl getTopCard();

    boolean canPutCard(CardControl cardControl);
}
