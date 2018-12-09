package pl.edu.agh.torbjorns.board;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.agh.torbjorns.board.deck.Deck;
import pl.edu.agh.torbjorns.board.deck.DeckFactory;
import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.card.Rank;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTests {

    @Test
    void dealCardsTest(){

        //  Given
        Dealer dealer = new Dealer();
        Deck deck = new DeckFactory().createDeck();
        Board board = new BoardFactory(dealer).createBoard(deck);

        //  When
        dealer.dealCards(board, deck);

        System.out.println(board.getWorkingCardStacks().size());

        //  Then
        for(int i = 0; i < board.getWorkingCardStacks().size(); i++){
            System.out.println(board.getWorkingCardStacks().get(i).isEmpty());
            //Card topCard = board.getWorkingCardStacks().get(i).getTopCard();
            //assertThat(topCard.getRank()).isEqualTo(Rank.KING);
        }
    }

}
