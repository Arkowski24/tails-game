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
import pl.edu.agh.torbjorns.board.deck.DeckFactory;
import pl.edu.agh.torbjorns.view.*;

public class Controller {

    public static final double BASE_WIDTH = 1280.0;
    public static final double BASE_HEIGHT = 720.0;
    private final ObjectProperty<CardControl> selectedCardControl;
    @FXML
    private GridPane mainGrid;
    @Inject
    private DeckFactory deckFactory;
    @Inject
    private BoardFactory boardFactory;
    private Board board;

    public Controller() {
        this.selectedCardControl = new SimpleObjectProperty<>(null);
    }

    public void lateInitialize() {
        var deck = deckFactory.createDeck();
        board = boardFactory.createBoard(deck);

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
            mainGrid.add(new CardPlaceholderControl(), column, row);
        }
    }

    public void clickedOnCardStack(StackControl stackControl) {
        if (stackControl.getTopCardControl() == selectedCardControl.get()) {
            return;
        }

        if (selectedCardControl.get() == null) {
            var topCardControl = stackControl.getTopCardControl();
            selectedCardControl.setValue(topCardControl);
            topCardControl.setSelected();
        } else {
            if (stackControl.getCardStack().canPutCard(selectedCardControl.get().getCard())) {
                moveSelectedCard(stackControl);
            } else {
                selectedCardControl.get().setUnselected();
                selectedCardControl.setValue(null);
            }

        }
    }

    public void clickedOnBufferZone(CardPlaceholderControl placeholder) {

    }

    private void moveSelectedCard(StackControl newStack) {
        if (this.selectedCardControl.get() == null) {
            return;
        }

        var selectedCardControl = this.selectedCardControl.get();
        var oldStack = (StackControl) this.selectedCardControl.get().getParent();

        oldStack.removeCard(selectedCardControl);
        newStack.addCard(selectedCardControl);
        this.selectedCardControl.setValue(null);
    }

    public ObjectProperty<CardControl> selectedCardControlProperty() {
        return selectedCardControl;
    }
}
