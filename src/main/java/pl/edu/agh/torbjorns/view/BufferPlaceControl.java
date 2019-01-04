package pl.edu.agh.torbjorns.view;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.board.BufferPlace;
import pl.edu.agh.torbjorns.card.Card;

import static pl.edu.agh.torbjorns.view.util.ControlUtils.*;
import static pl.edu.agh.torbjorns.view.util.ObservableUtils.*;

public class BufferPlaceControl extends StackPane {

    private final BufferPlace place;
    private final Controller controller;

    @SuppressWarnings("FieldCanBeLocal")
    private BooleanBinding isTargetBinding;

    public BufferPlaceControl(BufferPlace place, Controller controller) {
        this.place = place;
        this.controller = controller;

        loadFxml(this);
        initializeDimensions();
        initializeBindings();
        setOnMouseClicked(event -> onMouseClicked());
    }

    private void initializeDimensions() {
        setMinWidth(CardControl.CARD_WIDTH);
        setMaxWidth(CardControl.CARD_WIDTH);
        setMinHeight(CardControl.CARD_HEIGHT);
        setMaxHeight(CardControl.CARD_HEIGHT);
    }

    private void initializeBindings() {
        observe(place.cardProperty(), this::setCard);

        isTargetBinding = createIsTargetBinding(controller, place);
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
        controller.onCardHolderClicked(place);
    }

}
