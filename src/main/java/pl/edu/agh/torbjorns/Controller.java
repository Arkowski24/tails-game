package pl.edu.agh.torbjorns;

import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import pl.edu.agh.torbjorns.board.Board;
import pl.edu.agh.torbjorns.board.BoardFactory;
import pl.edu.agh.torbjorns.board.BufferZone;
import pl.edu.agh.torbjorns.board.CardStack;
import pl.edu.agh.torbjorns.board.deck.DeckFactory;
import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.view.CardControl;
import pl.edu.agh.torbjorns.view.CardPlaceholderControl;
import pl.edu.agh.torbjorns.view.FinishedCardStackControl;
import pl.edu.agh.torbjorns.view.WorkingCardStackControl;

public class Controller {

    public static final double BASE_WIDTH = 1280.0;
    public static final double BASE_HEIGHT = 720.0;

    @FXML
    private GridPane mainGrid;

    @Inject
    private DeckFactory deckFactory;

    @Inject
    private BoardFactory boardFactory;

    private Board board;

    private ObjectProperty<CardControl> selectedCardControl;

    public void lateInitialize() {
        var deck = deckFactory.createDeck();
        board = boardFactory.createBoard(deck);

        initializeSelectedCardControl();
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
            mainGrid.add(stackControl, column, 1, 1, 4);
            column++;
        }
    }

    private void initializeBufferZone() {
        for (var i = 0; i < BufferZone.SIZE; i++) {
            var column = (i % 2) + 8;
            var row = i / 2;
            mainGrid.add(new CardPlaceholderControl(this), column, row);
        }
    }

    private void initializeSelectedCardControl() {
        this.selectedCardControl = new SimpleObjectProperty<>(null);
    }

    public void clickedOnCard(CardControl cardControl) {
        selectedCardControl.setValue(cardControl);
    }

    public void clickedOnFinishedCardStack(FinishedCardStackControl stackControl) {
        CardStack stack = stackControl.getCardStack();

        putCardIntoStack(stack);
        setPositionOfCard(stackControl.getLayoutX(), stackControl.getLayoutY());
    }

    public void clickedOnWorkingCardStack(WorkingCardStackControl stackControl) {
        CardStack stack = stackControl.getCardStack();

        putCardIntoStack(stack);
        setPositionOfCard(stackControl.getLayoutX(), stackControl.getLayoutY());
    }

    private void putCardIntoStack(CardStack stack) {
        if (this.selectedCardControl.get() == null) {
            return;
        }

        Card selectedCard = this.selectedCardControl.get().getCard();
        stack.putCard(selectedCard);
        this.selectedCardControl.setValue(null);
    }

    private void setPositionOfCard(double x, double y) {
        if (this.selectedCardControl.get() == null) {
            return;
        }

        selectedCardControl.get().relocate(x, y);
    }

    public Card getSelectedCard() {
        return selectedCardControl.get().getCard();
    }

    public ObjectProperty<CardControl> selectedCardControlProperty() {
        return selectedCardControl;
    }
}
