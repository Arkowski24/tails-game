package pl.edu.agh.torbjorns.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {
    private final Rank rank;
    private final Suit suit;
}
