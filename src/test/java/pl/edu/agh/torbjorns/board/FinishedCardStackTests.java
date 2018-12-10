package pl.edu.agh.torbjorns.board;

import org.junit.jupiter.api.Test;
import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.card.Rank;
import pl.edu.agh.torbjorns.card.Suit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FinishedCardStackTests {

    @Test
    void canPutCardTest(){

        //  Given
        FinishedCardStack finishedCardStackSmall = new FinishedCardStack(Suit.DIAMONDS);
        finishedCardStackSmall.putCard(new Card(Rank.ACE, Suit.DIAMONDS));

        FinishedCardStack finishedCardStackBig = new FinishedCardStack(Suit.CLUBS);
        finishedCardStackBig.putCard(new Card(Rank.ACE, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.TWO, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.THREE, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.FOUR, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.FIVE, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.SIX, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.SEVEN, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.EIGHT, Suit.CLUBS));

        //  When

        //  Then
        assertThat(finishedCardStackSmall.canPutCard(new Card(Rank.TWO, Suit.DIAMONDS))).isEqualTo(true);
        assertThat(finishedCardStackSmall.canPutCard(new Card(Rank.THREE, Suit.DIAMONDS))).isEqualTo(false);
        assertThat(finishedCardStackSmall.canPutCard(new Card(Rank.TWO, Suit.CLUBS))).isEqualTo(false);

        assertThat(finishedCardStackBig.canPutCard(new Card(Rank.NINE, Suit.CLUBS))).isEqualTo(true);
        assertThat(finishedCardStackBig.canPutCard(new Card(Rank.SIX, Suit.CLUBS))).isEqualTo(false);
        assertThat(finishedCardStackBig.canPutCard(new Card(Rank.TWO, Suit.CLUBS))).isEqualTo(false);
        assertThat(finishedCardStackBig.canPutCard(new Card(Rank.KING, Suit.HEARTS))).isEqualTo(false);
        assertThat(finishedCardStackBig.canPutCard(new Card(Rank.JACK, Suit.SPADES))).isEqualTo(false);
    }
}
