package pl.edu.agh.torbjorns.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Suit {
    SPADES(Color.BLACK, "♠"),
    HEARTS(Color.RED, "♥"),
    DIAMONDS(Color.RED, "♦"),
    CLUBS(Color.BLACK, "♣");

    private final Color color;
    private final String symbolText;
}
