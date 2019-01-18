package pl.edu.agh.torbjorns.view;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.model.board.CardStack;
import pl.edu.agh.torbjorns.model.card.Card;

import java.util.List;

import static javafx.beans.binding.Bindings.*;
import static pl.edu.agh.torbjorns.view.util.ControlUtils.*;
import static pl.edu.agh.torbjorns.view.util.ObservableUtils.*;

public class WorkingCardStackControl extends VBox {

    private final static double MAX_SPACING = -0.85 * CardControl.CARD_HEIGHT;
    private final static double PADDING_VERTICAL = 0.05 * CardControl.CARD_WIDTH;

    private final CardStack cardStack;
    private final Controller controller;

    @SuppressWarnings("FieldCanBeLocal")
    private BooleanBinding isTargetBinding;

    public WorkingCardStackControl(CardStack cardStack, Controller controller) {
        this.cardStack = cardStack;
        this.controller = controller;

        getStyleClass().add("working-card-stack");
        initializeLayout();
        initializeBindings();
        setOnMouseClicked(event -> onMouseClicked());
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

    private void initializeBindings() {
        observe(cardStack.getCards(), this::setCards);

        isTargetBinding = createIsTargetBinding(controller, cardStack);
        observe(isTargetBinding, this::setIsTarget);
    }

    private void setCards(List<Card> cards) {
        getChildren().removeIf(children -> children instanceof CardControl);

        cards.stream()
                .map(card -> {
                    var control = new CardControl(controller);
                    control.setCard(card);
                    return control;
                })
                .forEachOrdered(getChildren()::add);
    }

    private void setIsTarget(boolean isTarget) {
        setHasStyleClass(this, "target", isTarget);
    }

    private void onMouseClicked() {
        controller.onCardHolderClicked(cardStack);
    }

}
