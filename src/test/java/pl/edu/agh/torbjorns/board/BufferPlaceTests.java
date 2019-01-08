package pl.edu.agh.torbjorns.board;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.agh.torbjorns.card.Card;

import static org.assertj.core.api.Assertions.*;

public class BufferPlaceTests {

    @Test
    public void addCardsTest() {
        var bufferPlace = new BufferPlace();
        var card = Mockito.mock(Card.class);
        bufferPlace.putCard(card);

        var retrievedCard = bufferPlace.takeCard();
        assertThat(retrievedCard).isEqualTo(card);
    }

}
