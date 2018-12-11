package pl.edu.agh.torbjorns.view;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.view.util.ControlUtils;

public class CardPlaceholderControl extends StackPane implements CardControlManager {
    private Controller controller;

    public CardPlaceholderControl() {
        ControlUtils.loadFxml(this);
        initializeDimensions();
    }

    private void initializeDimensions() {
        setMinWidth(CardControl.CARD_WIDTH);
        setMaxWidth(CardControl.CARD_WIDTH);
        setMinHeight(CardControl.CARD_HEIGHT);
        setMaxHeight(CardControl.CARD_HEIGHT);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        attachListener();
        this.setOnMouseClicked(this::onClickAction);
    }

    private void onClickAction(MouseEvent event) {
        controller.clickedOnCardManager(this);
    }

    private void attachListener() {
        ObjectProperty<CardControl> cardControl = this.controller.selectedCardControlProperty();
        cardControl.addListener((obj, oldCard, newCard) -> updateOnSelect(newCard));
    }

    private void updateOnSelect(CardControl cardControl) {
        if (canPutCard(cardControl) && cardControl != null) {
            this.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        } else {
            this.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
    }

    @Override
    public void addCard(CardControl cardControl) {
        getChildren().add(cardControl);
    }

    @Override
    public void removeCard(CardControl cardControl) {
        getChildren().remove(cardControl);
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
        return (getChildren().size() == 0);
    }
}
