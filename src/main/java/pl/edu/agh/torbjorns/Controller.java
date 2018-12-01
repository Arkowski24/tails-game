package pl.edu.agh.torbjorns;

import com.google.inject.Guice;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pl.edu.agh.torbjorns.board.Board;
import pl.edu.agh.torbjorns.board.BoardFactory;
import pl.edu.agh.torbjorns.board.deck.DeckFactory;
import pl.edu.agh.torbjorns.card.Card;

import javax.inject.Inject;

public class Controller {

    @FXML
    GridPane mainGrid;

    @FXML
    GridPane bufferGrid;

    @Inject
    private DeckFactory deckFactory;

    @Inject
    private BoardFactory boardFactory;

    private Board board;

    public void initialize() {
        Guice.createInjector().injectMembers(this);

        var deck = deckFactory.createDeck();
        board = boardFactory.createBoard(deck);

        initializeFinishedCardStacks();
        initializeWorkingCardStacks();
    }

    private void initializeFinishedCardStacks() {
        var column = 0;
        for (var stack : board.getFinishedCardStacks()) {

            var label = new Label();
            label.setText(stack.getSuit().toString());

            mainGrid.add(label, column, 0);

            column++;
        }
    }

    private void initializeWorkingCardStacks() {
        // TODO(pjanczyk): clean up dimensions

        var column = 0;
        for (var stack : board.getWorkingCardStacks()) {
            var vbox = new VBox(-180);

            for (Card card : stack.getCards()) {
                CardControl cardControl = new CardControl();
                cardControl.setCard(card);
                cardControl.setMinHeight(200);
                VBox.setMargin(cardControl, new Insets(5));
                vbox.getChildren().add(cardControl);
            }

            mainGrid.add(vbox, column, 1);

            column++;
        }
    }

}
