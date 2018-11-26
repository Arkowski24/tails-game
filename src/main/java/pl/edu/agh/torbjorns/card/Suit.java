package pl.edu.agh.torbjorns.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Suit {
    CLUBS(Color.BLACK),
    DIAMONDS(Color.RED),
    HEARTS(Color.RED),
    SPADES(Color.BLACK);

    @Getter
    private final Color color;
}
