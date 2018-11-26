package pl.edu.agh.torbjorns.card;

public enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    public boolean isPrecededBy(Rank rank) {
        return rank.ordinal() == this.ordinal() - 1;
    }

    public boolean isFollowedBy(Rank rank) {
        return rank.ordinal() == this.ordinal() + 1;
    }
}
