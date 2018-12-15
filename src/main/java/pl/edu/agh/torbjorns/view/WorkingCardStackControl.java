package pl.edu.agh.torbjorns.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.board.CardStack;

import static javafx.beans.binding.Bindings.*;
import static pl.edu.agh.torbjorns.view.util.ControlUtils.*;
import static pl.edu.agh.torbjorns.view.util.ObservableUtils.*;

public class WorkingCardStackControl extends VBox {

    private final static double MAX_SPACING = -0.85 * CardControl.CARD_HEIGHT;
    private final static double PADDING_VERTICAL = 0.05 * CardControl.CARD_WIDTH;

    private final CardStack cardStack;
    private final Controller controller;

    public WorkingCardStackControl(CardStack cardStack, Controller controller) {
        this.cardStack = cardStack;
        this.controller = controller;

        getStyleClass().add("working-card-stack");
        initializeLayout();
        initializeCards();
        initializeIsTarget();
        setOnMouseClicked(this::onMouseClicked);
    }

    private void initializeLayout() {
        setMinHeight(0);
        setPadding(new Insets(PADDING_VERTICAL, 0, PADDING_VERTICAL, 0));
        setAlignment(Pos.TOP_CENTER);
        spacingProperty().bind(
                createDoubleBinding(this::calculateSpacing, getChildren(), heightProperty()));
    }

    private double calculateSpacing() {
        var cardCount = getChildren().size();
        double freeSpace = getHeight() - 2 * PADDING_VERTICAL - cardCount * CardControl.CARD_HEIGHT;
        if (freeSpace >= 0) {
            return MAX_SPACING;
        } else {
            double spacing = freeSpace / (cardCount - 1);
            return Math.min(spacing, MAX_SPACING);
        }
    }

    private void initializeCards() {
        observe(cardStack.getCards(), cards -> {
            getChildren().removeIf(children -> children instanceof CardControl);

            for (var card : cards) {
                var cardControl = new CardControl(controller);
                cardControl.setCard(card);
                getChildren().add(cardControl);
            }
        });
    }

    private void initializeIsTarget() {
        var isTargetBinding = isTargetBinding(controller, cardStack);

        observe(isTargetBinding, isTarget -> {
            setHasStyleClass(this, "target", isTarget);
        });
    }

    private void onMouseClicked(MouseEvent event) {
        controller.onCardManagerClicked(cardStack);
    }

}
