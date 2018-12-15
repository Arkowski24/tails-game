package pl.edu.agh.torbjorns.view;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.board.BufferPlace;

import static pl.edu.agh.torbjorns.view.util.ControlUtils.*;
import static pl.edu.agh.torbjorns.view.util.ObservableUtils.*;

public class BufferPlaceControl extends StackPane {

    private final BufferPlace place;
    private final Controller controller;

    public BufferPlaceControl(BufferPlace place, Controller controller) {
        this.place = place;
        this.controller = controller;

        loadFxml(this);
        initializeDimensions();
        initializeCard();
        initializeIsTarget();
        setOnMouseClicked(this::onMouseClicked);
    }

    private void initializeDimensions() {
        setMinWidth(CardControl.CARD_WIDTH);
        setMaxWidth(CardControl.CARD_WIDTH);
        setMinHeight(CardControl.CARD_HEIGHT);
        setMaxHeight(CardControl.CARD_HEIGHT);
    }

    private void initializeCard() {
        observe(place.cardProperty(), card -> {
            getChildren().removeIf(children -> children instanceof CardControl);

            if (card != null) {
                var cardControl = new CardControl(controller);
                cardControl.setCard(card);
                getChildren().add(cardControl);
            }
        });
    }

    private void initializeIsTarget() {
        var isTargetBinding = isTargetBinding(controller, place);

        observe(isTargetBinding, isTarget -> {
            System.out.println("xd");
            setHasStyleClass(this, "target", isTarget);
        });
    }

    private void onMouseClicked(MouseEvent event) {
        controller.onCardManagerClicked(place);
    }

}
