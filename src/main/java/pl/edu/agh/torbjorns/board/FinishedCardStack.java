package pl.edu.agh.torbjorns.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.card.Rank;
import pl.edu.agh.torbjorns.card.Suit;

@RequiredArgsConstructor
public class FinishedCardStack extends CardStack {

    @Getter
    private final Suit suit;

    @Override
    public boolean canPutCard(Card card) {
        if (card.getSuit() != suit) {
            return false;
        }

        if (isEmpty()) {
            return card.getRank() == Rank.ACE;
        } else {
            return card.getRank().isPrecededBy(getTopCard().getRank());
        }
    }

}
