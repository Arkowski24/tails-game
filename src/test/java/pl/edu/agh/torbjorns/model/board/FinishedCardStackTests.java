package pl.edu.agh.torbjorns.model.board;

import org.junit.jupiter.api.Test;
import pl.edu.agh.torbjorns.model.card.Card;
import pl.edu.agh.torbjorns.model.card.Rank;
import pl.edu.agh.torbjorns.model.card.Suit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FinishedCardStackTests {

    @Test
    void canPutCardTestSmallStack(){

        //  Given
        FinishedCardStack finishedCardStackSmall = new FinishedCardStack(Suit.DIAMONDS);

        //  When
        finishedCardStackSmall.putCard(new Card(Rank.ACE, Suit.DIAMONDS));

        //  Then
        assertThat(finishedCardStackSmall.canPutCard(new Card(Rank.TWO, Suit.DIAMONDS))).isEqualTo(true);
        assertThat(finishedCardStackSmall.canPutCard(new Card(Rank.THREE, Suit.DIAMONDS))).isEqualTo(false);
        assertThat(finishedCardStackSmall.canPutCard(new Card(Rank.TWO, Suit.CLUBS))).isEqualTo(false);
    }

    @Test
    void canPutCardTestBigStack(){

        //  Given
        FinishedCardStack finishedCardStackBig = new FinishedCardStack(Suit.CLUBS);

        //  When
        finishedCardStackBig.putCard(new Card(Rank.ACE, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.TWO, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.THREE, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.FOUR, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.FIVE, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.SIX, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.SEVEN, Suit.CLUBS));
        finishedCardStackBig.putCard(new Card(Rank.EIGHT, Suit.CLUBS));

        //  Then
        assertThat(finishedCardStackBig.canPutCard(new Card(Rank.NINE, Suit.CLUBS))).isEqualTo(true);
        assertThat(finishedCardStackBig.canPutCard(new Card(Rank.SIX, Suit.CLUBS))).isEqualTo(false);
        assertThat(finishedCardStackBig.canPutCard(new Card(Rank.TWO, Suit.CLUBS))).isEqualTo(false);
        assertThat(finishedCardStackBig.canPutCard(new Card(Rank.KING, Suit.HEARTS))).isEqualTo(false);
        assertThat(finishedCardStackBig.canPutCard(new Card(Rank.JACK, Suit.SPADES))).isEqualTo(false);
    }
}
