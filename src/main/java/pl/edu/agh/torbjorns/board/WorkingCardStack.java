package pl.edu.agh.torbjorns.board;

import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.card.Rank;

public class WorkingCardStack extends CardStack {

    @Override
    public boolean canPutCard(Card card) {
        if (isEmpty()) {
            return card.getRank() == Rank.KING;
        } else {
            var topCard = getTopCard();
            return haveOppositeColors(card, topCard) && haveSubsequentRanks(card, topCard);
        }
    }

    @Override
    public boolean canTakeCard() {
        return !isEmpty();
    }

    private boolean haveOppositeColors(Card card1, Card card2) {
        return card1.getColor() != card2.getColor();
    }

    private boolean haveSubsequentRanks(Card card1, Card card2) {
        return card1.getRank().isFollowedBy(card2.getRank());
    }

}
