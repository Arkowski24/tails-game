package pl.edu.agh.torbjorns.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import pl.edu.agh.torbjorns.board.FinishedCardStack;

import java.io.IOException;

public class FinishedCardStackControl extends StackPane {

    private final static double PADDING = 0.05 * CardControl.CARD_WIDTH;

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
        setPadding(new Insets(PADDING));
    }

    private void initializeSuitLabel() {
        var suit = cardStack.getSuit();
        var color = suit.getColor();
        suitLabel.setText(suit.getSymbolText());
        suitLabel.setTextFill(color.getFxColor());
    }

}
