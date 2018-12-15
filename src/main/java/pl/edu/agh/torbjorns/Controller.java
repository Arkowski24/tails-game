package pl.edu.agh.torbjorns;

import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import pl.edu.agh.torbjorns.board.Board;
import pl.edu.agh.torbjorns.board.BoardFactory;
import pl.edu.agh.torbjorns.board.BufferZone;
import pl.edu.agh.torbjorns.board.Dealer;
import pl.edu.agh.torbjorns.board.deck.DeckFactory;
import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.view.BufferPlaceControl;
import pl.edu.agh.torbjorns.board.CardManager;
import pl.edu.agh.torbjorns.view.FinishedCardStackControl;
import pl.edu.agh.torbjorns.view.WorkingCardStackControl;

public class Controller {

    public static final double BASE_WIDTH = 1280.0;
    public static final double BASE_HEIGHT = 720.0;

    private final ObjectProperty<Card> selectedCard = new SimpleObjectProperty<>(null);
    private CardManager selectedCardManager;

    @FXML
    private GridPane mainGrid;
    @Inject
    private DeckFactory deckFactory;
    @Inject
    private BoardFactory boardFactory;
    @Inject
    private Dealer dealer;
    private Board board;

    public void lateInitialize() {
        var deck = deckFactory.createDeck();
        board = boardFactory.createBoard();
        dealer.dealCards(board, deck);

        initializeFinishedCardStacks();
        initializeWorkingCardStacks();
        initializeBufferZone();
    }

    private void initializeFinishedCardStacks() {
        var column = 0;
        for (var stack : board.getFinishedCardStacks()) {
            var stackControl = new FinishedCardStackControl(stack, this);
            mainGrid.add(stackControl, column, 0);
            column++;
        }
    }

    private void initializeWorkingCardStacks() {
        var column = 0;
        for (var stack : board.getWorkingCardStacks()) {
            var stackControl = new WorkingCardStackControl(stack, this);
            GridPane.setValignment(stackControl, VPos.TOP);
            GridPane.setVgrow(stackControl, Priority.ALWAYS);
            mainGrid.add(stackControl, column, 1, 1, 4);
            column++;
        }
    }

    private void initializeBufferZone() {
        for (var i = 0; i < BufferZone.SIZE; i++) {
            var place = board.getBufferZone().getPlace(i);
            var placeControl = new BufferPlaceControl(place, this);
            var column = (i % 2) + 8;
            var row = i / 2;
            mainGrid.add(placeControl, column, row);
        }
    }

    public ObjectProperty<Card> selectedCardProperty() {
        return selectedCard;
    }

    public void onCardManagerClicked(CardManager cardManager) {
        if (selectedCardManager == cardManager) {
            selectedCard.setValue(null);
            selectedCardManager = null;
        } else if (selectedCardManager != null) {
            if (cardManager.canPutCard(selectedCard.getValue())) {
                var card = selectedCardManager.takeCard();
                selectedCardManager = null;
                selectedCard.setValue(null);
                cardManager.putCard(card);
            } else {
                selectedCard.setValue(null);
                selectedCardManager = null;
            }
        } else {
            if (cardManager.peekCard().isPresent()) {
                selectedCardManager = cardManager;
                selectedCard.setValue(cardManager.peekCard().get());
            }
        }
    }

}
