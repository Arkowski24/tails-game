package pl.edu.agh.torbjorns;

import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.model.history.ActionHistory;
import pl.edu.agh.torbjorns.model.history.MoveCardAction;
import pl.edu.agh.torbjorns.model.board.*;
import pl.edu.agh.torbjorns.model.board.deck.DeckFactory;
import pl.edu.agh.torbjorns.model.card.Card;
import pl.edu.agh.torbjorns.view.BufferPlaceControl;
import pl.edu.agh.torbjorns.view.FinishedCardStackControl;
import pl.edu.agh.torbjorns.view.WorkingCardStackControl;

public class Controller {

    public static final double BASE_WIDTH = 1280.0;
    public static final double BASE_HEIGHT = 720.0;

    private final ObjectProperty<@Nullable Card> selectedCardProperty = new SimpleObjectProperty<>(null);

    @FXML private GridPane mainGrid;

    @Inject private DeckFactory deckFactory;
    @Inject private BoardFactory boardFactory;
    @Inject private Dealer dealer;
    @Inject private ActionHistory actionHistory;
    private Board board;

    public void lateInitialize() {
        var deck = deckFactory.createDeck();
        board = boardFactory.createBoard();
        dealer.dealCards(board, deck);

        initializeUndoShortcut();

        initializeFinishedCardStacks();
        initializeWorkingCardStacks();
        initializeBufferZone();
    }

    private void initializeUndoShortcut() {
        var keyCombination = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
        mainGrid.getScene().getAccelerators().put(keyCombination, this::onUndoShortcutPressed);
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

    public ReadOnlyObjectProperty<@Nullable Card> selectedCardProperty() {
        return selectedCardProperty;
    }

    private @Nullable Card getSelectedCard() {
        return selectedCardProperty.get();
    }

    private void selectCard(Card card) {
        selectedCardProperty.setValue(card);
    }

    private void deselectCard() {
        selectedCardProperty.setValue(null);
    }

    public void onCardHolderClicked(CardHolder clickedCardHolder) {
        var selectedCard = getSelectedCard();

        if (selectedCard != null) {
            var selectedCardHolder = selectedCard.getHolder();
            if (clickedCardHolder != selectedCardHolder && clickedCardHolder.canPutCard(selectedCard)) {
                actionHistory.performAction(new MoveCardAction(selectedCardHolder, clickedCardHolder));
            }
            deselectCard();

        } else { // selectedCard == null
            if (clickedCardHolder.canTakeCard()) {
                selectCard(clickedCardHolder.peekTopCard());
            }
        }
    }

    private void onUndoShortcutPressed() {
        deselectCard();
        if (actionHistory.canUndoLastAction()) {
            actionHistory.undoLastAction();
        }
    }

}
