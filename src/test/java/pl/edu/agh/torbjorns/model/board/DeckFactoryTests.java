package pl.edu.agh.torbjorns.model.board;

import org.junit.jupiter.api.Test;
import pl.edu.agh.torbjorns.model.board.deck.Deck;
import pl.edu.agh.torbjorns.model.board.deck.DeckFactory;
import pl.edu.agh.torbjorns.model.card.Card;
import pl.edu.agh.torbjorns.model.card.Rank;
import pl.edu.agh.torbjorns.model.card.Suit;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckFactoryTests {

    @Test
    void createDeckTest(){

        //  Given
        DeckFactory deckFactory = new DeckFactory();

        //  When
        Deck deck = deckFactory.createDeck();

        //  Then
        int noCards[][] = new int[Rank.values().length][Suit.values().length];
        while(!deck.isEmpty()){
            Card card = deck.takeCard();
            Rank cardRank = card.getRank();
            Suit cardSuit = card.getSuit();
            noCards[cardRank.ordinal()][cardSuit.ordinal()]++;
        }
        for(int i = 0; i < Rank.values().length; i++){
            for(int j = 0; j < Suit.values().length; j++){
                assertThat(noCards[i][j]).isEqualTo(2);
            }
        }
    }
}
