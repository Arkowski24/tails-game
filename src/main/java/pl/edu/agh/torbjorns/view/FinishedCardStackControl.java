package pl.edu.agh.torbjorns.view;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.board.FinishedCardStack;
import pl.edu.agh.torbjorns.card.Card;

import static pl.edu.agh.torbjorns.view.util.ControlUtils.*;
import static pl.edu.agh.torbjorns.view.util.ObservableUtils.*;

public class FinishedCardStackControl extends StackPane {

    private final static double PADDING = 0.05 * CardControl.CARD_WIDTH;

    private final FinishedCardStack cardStack;
    private final Controller controller;

    @FXML private Label suitLabel;

    public FinishedCardStackControl(FinishedCardStack cardStack, Controller controller) {
        this.cardStack = cardStack;
        this.controller = controller;

        loadFxml(this);
        initializeDimensions();
        initializeSuitLabel();
        initializeBindings();
        setOnMouseClicked(event -> onMouseClicked());
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

    private void initializeBindings() {
        observe(cardStack.topCardProperty(), this::setCard);

        var isTargetBinding = createIsTargetBinding(controller, cardStack);
        observe(isTargetBinding, this::setIsTarget);
    }

    private void setCard(@Nullable Card card) {
        getChildren().removeIf(children -> children instanceof CardControl);

        if (card != null) {
            var cardControl = new CardControl(controller);
            cardControl.setCard(card);
            getChildren().add(cardControl);
        }
    }

    private void setIsTarget(boolean isTarget) {
        setHasStyleClass(this, "target", isTarget);
    }

    private void onMouseClicked() {
        controller.onCardHolderClicked(cardStack);
    }

}
