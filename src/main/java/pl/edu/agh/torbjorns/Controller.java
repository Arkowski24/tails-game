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
import pl.edu.agh.torbjorns.view.*;

public class Controller {

    public static final double BASE_WIDTH = 1280.0;
    public static final double BASE_HEIGHT = 720.0;

    private final ObjectProperty<CardControl> selectedCardControl;
    private CardControlManager selectedCardControlManager;

    @FXML
    private GridPane mainGrid;
    @Inject
    private DeckFactory deckFactory;
    @Inject
    private BoardFactory boardFactory;
    @Inject
    private Dealer dealer;
    private Board board;

    public Controller() {
        this.selectedCardControl = new SimpleObjectProperty<>(null);
    }

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
            var column = (i % 2) + 8;
            var row = i / 2;
            var placeholder = new CardPlaceholderControl();
            mainGrid.add(placeholder, column, row);
            placeholder.setController(this);
        }
    }

    public void clickedOnCardManager(CardControlManager cardManager) {
        if (cardManager == selectedCardControlManager) {
            return;
        }

        if (selectedCardControl.get() == null) {
            var topCardControl = cardManager.getTopCard();
            selectedCardControl.setValue(topCardControl);
            selectedCardControlManager = cardManager;
            topCardControl.setSelected();
        } else {
            var selectedCard = selectedCardControl.get();
            if (cardManager.canPutCard(selectedCard)) {
                selectedCardControlManager.removeCard(selectedCard);
                cardManager.addCard(selectedCard);
            }
            deselectSelectedCard();
        }
    }

    public ObjectProperty<CardControl> selectedCardControlProperty() {
        return selectedCardControl;
    }

    private void deselectSelectedCard() {
        CardControl cardControl = this.selectedCardControl.get();
        cardControl.setUnselected();
        selectedCardControl.setValue(null);
        selectedCardControlManager = null;
    }
}
