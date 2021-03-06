package pl.edu.agh.torbjorns.view;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.model.board.FinishedCardStack;
import pl.edu.agh.torbjorns.model.card.Card;

import static pl.edu.agh.torbjorns.view.util.ControlUtils.*;
import static pl.edu.agh.torbjorns.view.util.ObservableUtils.*;

public class FinishedCardStackControl extends StackPane {

    private final static double PADDING = 0.05 * CardControl.CARD_WIDTH;

    private final FinishedCardStack cardStack;
    private final Controller controller;

    @SuppressWarnings("FieldCanBeLocal")
    private BooleanBinding isTargetBinding;

    @SuppressWarnings("FieldCanBeLocal")
    private ObjectBinding<@Nullable Card> topCardBinding;

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
        topCardBinding = cardStack.topCardBinding();
        observe(topCardBinding, this::setCard);

        isTargetBinding = createIsTargetBinding(controller, cardStack);
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
