package pl.edu.agh.torbjorns.board;

import org.junit.jupiter.api.Test;
import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.card.Rank;
import pl.edu.agh.torbjorns.card.Suit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WorkingCardStackTests {

    @Test
    void canPutCardTestSmallStack(){

        //  Given
        WorkingCardStack workingCardStackSmall = new WorkingCardStack();

        //  When
        workingCardStackSmall.putCard(new Card(Rank.KING, Suit.SPADES));

        //  Then
        assertThat(workingCardStackSmall.canPutCard(new Card(Rank.QUEEN, Suit.HEARTS))).isEqualTo(true);
        assertThat(workingCardStackSmall.canPutCard(new Card(Rank.QUEEN, Suit.DIAMONDS))).isEqualTo(true);
        assertThat(workingCardStackSmall.canPutCard(new Card(Rank.QUEEN, Suit.SPADES))).isEqualTo(false);
        assertThat(workingCardStackSmall.canPutCard(new Card(Rank.JACK, Suit.DIAMONDS))).isEqualTo(false);
    }

    @Test
    void canPutCardTestBigStack(){

        //  Given
        WorkingCardStack workingCardStackBig = new WorkingCardStack();

        //  When
        workingCardStackBig.putCard(new Card(Rank.KING, Suit.DIAMONDS));
        workingCardStackBig.putCard(new Card(Rank.QUEEN, Suit.CLUBS));
        workingCardStackBig.putCard(new Card(Rank.JACK, Suit.DIAMONDS));
        workingCardStackBig.putCard(new Card(Rank.TEN, Suit.CLUBS));
        workingCardStackBig.putCard(new Card(Rank.NINE, Suit.DIAMONDS));
        workingCardStackBig.putCard(new Card(Rank.EIGHT, Suit.CLUBS));
        workingCardStackBig.putCard(new Card(Rank.SEVEN, Suit.DIAMONDS));
        workingCardStackBig.putCard(new Card(Rank.SIX, Suit.CLUBS));
        workingCardStackBig.putCard(new Card(Rank.FIVE, Suit.DIAMONDS));

        //  Then
        assertThat(workingCardStackBig.canPutCard(new Card(Rank.FOUR, Suit.SPADES))).isEqualTo(true);
        assertThat(workingCardStackBig.canPutCard(new Card(Rank.FOUR, Suit.CLUBS))).isEqualTo(true);
        assertThat(workingCardStackBig.canPutCard(new Card(Rank.SIX, Suit.SPADES))).isEqualTo(false);
        assertThat(workingCardStackBig.canPutCard(new Card(Rank.SIX, Suit.CLUBS))).isEqualTo(false);
        assertThat(workingCardStackBig.canPutCard(new Card(Rank.FIVE, Suit.DIAMONDS))).isEqualTo(false);
        assertThat(workingCardStackBig.canPutCard(new Card(Rank.FIVE, Suit.CLUBS))).isEqualTo(false);
        assertThat(workingCardStackBig.canPutCard(new Card(Rank.ACE, Suit.DIAMONDS))).isEqualTo(false);
        assertThat(workingCardStackBig.canPutCard(new Card(Rank.KING, Suit.HEARTS))).isEqualTo(false);
    }
}
