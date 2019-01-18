package pl.edu.agh.torbjorns.model.board;

import org.junit.jupiter.api.Test;
import pl.edu.agh.torbjorns.model.board.deck.Deck;
import pl.edu.agh.torbjorns.model.board.deck.DeckFactory;
import pl.edu.agh.torbjorns.model.card.Card;
import pl.edu.agh.torbjorns.model.card.Rank;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTests {

    @Test
    void dealCardsTest(){
        //  Given
        Deck deck = new DeckFactory().createDeck();
        Board board = new BoardFactory().createBoard();
        Dealer dealer = new Dealer();

        //  When
        dealer.dealCards(board, deck);

        //  Then
        for(int i = 0; i < board.getWorkingCardStacks().size(); i++){
            Card bottomCard = board.getWorkingCardStacks().get(i).getCards().get(0);
            assertThat(bottomCard.getRank()).isEqualTo(Rank.KING);
        }
    }

}
