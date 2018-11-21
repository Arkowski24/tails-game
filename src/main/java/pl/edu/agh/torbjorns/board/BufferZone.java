package pl.edu.agh.torbjorns.board;

import pl.edu.agh.torbjorns.card.Card;

public class BufferZone {
    public final static int SIZE = 8;
    private Card[] cards;

    public BufferZone() {
        this.cards = new Card[SIZE];
    }

    public void addCard(Card card, int place) {

    }

    public Card getCard(int place) {
        return null;
    }
}
