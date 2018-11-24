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
            return card.getSuit().getColor() != topCard.getSuit().getColor()
                    && card.getRank().isFollowedBy(topCard.getRank());
        }
    }

}
