package pl.edu.agh.torbjorns.model.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Rank {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String shortText;

    public boolean isPrecededBy(Rank rank) {
        return rank.ordinal() == this.ordinal() - 1;
    }

    public boolean isFollowedBy(Rank rank) {
        return rank.ordinal() == this.ordinal() + 1;
    }
}
