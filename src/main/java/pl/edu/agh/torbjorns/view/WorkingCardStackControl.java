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

public class WorkingCardStackControl extends VBox implements StackControl {

    private final static double SPACING = -0.85 * CardControl.CARD_HEIGHT;
    private final static double PADDING_TOP = 0.05 * CardControl.CARD_WIDTH;

    private final CardStack cardStack;

    private final Controller controller;

    public WorkingCardStackControl(CardStack cardStack, Controller controller) {
        this.cardStack = cardStack;

        setAlignment(Pos.TOP_CENTER);
        setSpacing(SPACING);
        setPadding(new Insets(PADDING_TOP, 0, 0, 0));

        this.controller = controller;
        initializeCards();
        attachListener();
        this.setOnMouseClicked(this::onClickAction);
    }

    private void onClickAction(MouseEvent event) {
        controller.clickedOnCardStack(this);
    }

    @Override
    public CardControl getTopCardControl() {
        return (CardControl) getChildren().get(getChildren().size() - 1);
    }

    @Override
    public CardStack getCardStack() {
        return cardStack;
    }

    private void initializeCards() {
        for (Card card : cardStack.getCards()) {
            createCardControlOnTop(card);
        }
    }

    private void createCardControlOnTop(Card card) {
        CardControl cardControl = new CardControl();
        cardControl.setCard(card);
        getChildren().add(cardControl);
    }

    private void attachListener() {
        ObjectProperty<CardControl> cardControl = this.controller.selectedCardControlProperty();
        cardControl.addListener((obj, oldCard, newCard) -> updateOnSelect(newCard));
    }

    private void updateOnSelect(CardControl newCardControl) {
        if (newCardControl != null && cardStack.canPutCard(newCardControl.getCard())) {
            this.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        } else {
            this.setBorder(null);
        }
    }

    @Override
    public void addCard(CardControl cardControl) {
        Card card = cardControl.getCard();
        createCardControlOnTop(card);
        cardStack.putCard(card);
    }

    @Override
    public void removeCard(CardControl cardControl) {
        getChildren().remove(cardControl);
        cardStack.removeCard();
    }
}
