package pl.edu.agh.torbjorns;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Setter;
import pl.edu.agh.torbjorns.board.Board;
import pl.edu.agh.torbjorns.board.BoardFactory;
import pl.edu.agh.torbjorns.board.deck.DeckFactory;
import pl.edu.agh.torbjorns.view.FinishedCardStackControl;
import pl.edu.agh.torbjorns.view.WorkingCardStackControl;

import javax.inject.Inject;

public class Controller {

    public static final double BASE_WIDTH = 1280.0;
    public static final double BASE_HEIGHT = 720.0;

    @FXML
    private GridPane mainGrid;

    @FXML
    private GridPane leftGrid;

    @FXML
    private GridPane rightGrid;

    @Setter
    private Stage stage;

    @Inject
    private DeckFactory deckFactory;

    @Inject
    private BoardFactory boardFactory;

    private Board board;

    public void lateInitialize() {
        var deck = deckFactory.createDeck();
        board = boardFactory.createBoard(deck);

        initializeFinishedCardStacks();
        initializeWorkingCardStacks();
    }

    private void initializeFinishedCardStacks() {
        var column = 0;
        for (var stack : board.getFinishedCardStacks()) {
            var stackControl = new FinishedCardStackControl(stack);
            leftGrid.add(stackControl, column, 0);
            column++;
        }
    }

    private void initializeWorkingCardStacks() {
        var column = 0;
        for (var stack : board.getWorkingCardStacks()) {
            var stackControl = new WorkingCardStackControl(stack);
            leftGrid.add(stackControl, column, 1);
            column++;
        }
    }

}
