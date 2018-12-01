package pl.edu.agh.torbjorns;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import pl.edu.agh.torbjorns.board.FinishedCardStack;

import java.io.IOException;

public class FinishedCardStackControl extends StackPane {

    private static final double WIDTH = 1.08 * CardControl.CARD_WIDTH;
    private static final double HEIGHT = CardControl.CARD_HEIGHT + 0.08 * CardControl.CARD_WIDTH;

    private final FinishedCardStack cardStack;

    @FXML
    private Label suitLabel;

    public FinishedCardStackControl(FinishedCardStack cardStack) {
        this.cardStack = cardStack;

        loadFxml();
        initializeDimensions();
        initializeSuitLabel();
    }

    private void loadFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("finished-card-stack.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void initializeDimensions() {
        setMinWidth(WIDTH);
        setMaxWidth(WIDTH);
        setMinHeight(HEIGHT);
        setMaxHeight(HEIGHT);
    }

    private void initializeSuitLabel() {
        var suit = cardStack.getSuit();
        var color = suit.getColor();
        suitLabel.setText(suit.getSymbolText());
        suitLabel.setTextFill(color.getFxColor());
    }

}
