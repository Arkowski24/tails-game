package pl.edu.agh.torbjorns.view;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.board.FinishedCardStack;
import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.view.util.ControlUtils;

public class FinishedCardStackControl extends StackPane implements CardControlManager {

    private final static double PADDING = 0.05 * CardControl.CARD_WIDTH;

    private final FinishedCardStack cardStack;

    private final Controller controller;

    @FXML
    private Label suitLabel;

    public FinishedCardStackControl(FinishedCardStack cardStack, Controller controller) {
        this.cardStack = cardStack;

        ControlUtils.loadFxml(this);
        initializeDimensions();
        initializeSuitLabel();

        this.controller = controller;
        attachListener();
        this.setOnMouseClicked(this::onClickAction);
    }

    private void onClickAction(MouseEvent event) {
        controller.clickedOnCardManager(this);
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
        this.getChildren().remove(cardControl);
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
