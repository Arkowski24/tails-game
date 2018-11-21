package pl.edu.agh.torbjorns.board;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.agh.torbjorns.card.Card;

import static org.assertj.core.api.Assertions.assertThat;

public class BufferZoneTests {

    @Test
    public void addCardsTest() {
        BufferZone bufferZone = new BufferZone();
        for (int i = 0; i < BufferZone.SIZE; i++) {
            Card card = Mockito.mock(Card.class);
            bufferZone.addCard(card, i);

            Card retrievedCard = bufferZone.getCard(i);
            assertThat(retrievedCard).isEqualTo(card);
        }
    }
    
}
