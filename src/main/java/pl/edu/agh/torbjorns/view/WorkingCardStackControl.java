package pl.edu.agh.torbjorns.view;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.board.CardStack;
import pl.edu.agh.torbjorns.card.Card;

import static javafx.beans.binding.Bindings.*;

public class WorkingCardStackControl extends VBox implements CardControlManager {

    private final static double MAX_SPACING = -0.85 * CardControl.CARD_HEIGHT;
    private final static double PADDING_VERTICAL = 0.05 * CardControl.CARD_WIDTH;

    private final CardStack cardStack;

    private final Controller controller;

    public WorkingCardStackControl(CardStack cardStack, Controller controller) {
        this.cardStack = cardStack;
        this.controller = controller;

        getStyleClass().add("working-card-stack");
        setMinHeight(0);
        setPadding(new Insets(PADDING_VERTICAL, 0, PADDING_VERTICAL, 0));
        setAlignment(Pos.TOP_CENTER);
        spacingProperty().bind(
                createDoubleBinding(this::calculateSpacing, getChildren(), heightProperty()));

        initializeCards();
        attachListener();
        this.setOnMouseClicked(this::onClickAction);
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

    private void onClickAction(MouseEvent event) {
        controller.clickedOnCardManager(this);
    }

    private void initializeCards() {
        for (Card card : cardStack.getCards()) {
            CardControl cardControl = new CardControl();
            cardControl.setCard(card);
            getChildren().add(cardControl);
        }
    }

    private void attachListener() {
        ObjectProperty<CardControl> cardControl = this.controller.selectedCardControlProperty();
        cardControl.addListener((obj, oldCard, newCard) -> updateOnSelect(newCard));
    }

    private void updateOnSelect(CardControl newCardControl) {
        if (newCardControl != null && cardStack.canPutCard(newCardControl.getCard())) {
            getStyleClass().add("target");
        } else {
            getStyleClass().remove("target");
        }
    }

    @Override
    public void addCard(CardControl cardControl) {
        Card card = cardControl.getCard();
        getChildren().add(cardControl);
        cardStack.putCard(card);
    }

    @Override
    public void removeCard(CardControl cardControl) {
        getChildren().remove(cardControl);
        cardStack.removeCard();
    }

    @Override
    public CardControl getTopCard() {
        if (getChildren().size() == 0) {
            return null;
        }
        return (CardControl) getChildren().get(getChildren().size() - 1);
    }

    @Override
    public boolean canPutCard(CardControl cardControl) {
        if (cardControl == null) {
            return false;
        }

        var card = cardControl.getCard();
        return cardStack.canPutCard(card);
    }
}
