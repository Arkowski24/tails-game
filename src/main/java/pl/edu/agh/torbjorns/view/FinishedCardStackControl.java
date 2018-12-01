package pl.edu.agh.torbjorns.view;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import pl.edu.agh.torbjorns.board.FinishedCardStack;
import pl.edu.agh.torbjorns.view.util.ControlUtils;

public class FinishedCardStackControl extends StackPane {

    private final static double PADDING = 0.05 * CardControl.CARD_WIDTH;

    private final FinishedCardStack cardStack;

    @FXML
    private Label suitLabel;

    public FinishedCardStackControl(FinishedCardStack cardStack) {
        this.cardStack = cardStack;

        ControlUtils.loadFxml(this);
        initializeDimensions();
        initializeSuitLabel();
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
