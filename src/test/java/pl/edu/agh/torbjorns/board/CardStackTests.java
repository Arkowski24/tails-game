package pl.edu.agh.torbjorns.board;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.agh.torbjorns.card.Card;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardStackTests {

    @Test
    void putCardTest() {
        CardStack cardStack = new CardStack();

        Card card = Mockito.mock(Card.class);
        cardStack.putCard(card);

        Card topCard = cardStack.getTopCard();
        assertThat(topCard).isEqualTo(card);
    }

    @Test
    void removeCardTest() {
        CardStack cardStack = new CardStack();
        Card card = Mockito.mock(Card.class);

        cardStack.putCard(card);
        Card topCard = cardStack.getTopCard();
        assertThat(topCard).isNotNull();

        Card removedCard = cardStack.removeCard();
        assertThat(removedCard).isEqualTo(topCard);
    }

    @Test
    void getCardsInOrderTest() {
        CardStack cardStack = new CardStack();
        Card card1 = Mockito.mock(Card.class);
        Card card2 = Mockito.mock(Card.class);
        Card card3 = Mockito.mock(Card.class);

        cardStack.putCard(card3);
        cardStack.putCard(card2);
        cardStack.putCard(card1);

        List<Card> cardList = cardStack.getCards();
        assertThat(cardList.size()).isEqualTo(3);

        assertThat(cardList.get(0)).isEqualTo(card1);
        assertThat(cardList.get(1)).isEqualTo(card2);
        assertThat(cardList.get(2)).isEqualTo(card3);
    }
}
