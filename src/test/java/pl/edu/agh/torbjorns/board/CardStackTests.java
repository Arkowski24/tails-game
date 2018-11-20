package pl.edu.agh.torbjorns.board;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.agh.torbjorns.card.Card;

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
}
