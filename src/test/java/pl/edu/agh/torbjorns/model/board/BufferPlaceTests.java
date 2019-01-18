package pl.edu.agh.torbjorns.model.board;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.agh.torbjorns.model.card.Card;

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
