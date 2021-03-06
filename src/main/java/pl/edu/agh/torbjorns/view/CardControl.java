package pl.edu.agh.torbjorns.view;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Nullable;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.model.card.Card;

import static javafx.beans.binding.Bindings.*;
import static pl.edu.agh.torbjorns.view.util.ControlUtils.*;
import static pl.edu.agh.torbjorns.view.util.ObservableUtils.*;

public class CardControl extends AnchorPane {

    public final static double CARD_WIDTH = 110;
    public final static double CARD_HEIGHT = 1.5 * CARD_WIDTH;

    private final Property<@Nullable Card> cardProperty = new SimpleObjectProperty<>(null);
    private final Controller controller;

    @SuppressWarnings("FieldCanBeLocal")
    private BooleanBinding isSelectedBinding;

    @FXML private Label topLeftLabel;
    @FXML private Label centerLabel;
    @FXML private Label bottomRightLabel;

    public CardControl(Controller controller) {
        this.controller = controller;

        loadFxml(this);
        initializeDimensions();
        initializeLabels();
        initializeBindings();
    }

    public Property<@Nullable Card> cardProperty() {
        return cardProperty;
    }

    public @Nullable Card getCard() {
        return cardProperty.getValue();
    }

    public void setCard(@Nullable Card card) {
        cardProperty.setValue(card);
    }

    private void initializeDimensions() {
        setMinWidth(CARD_WIDTH);
        setMaxWidth(CARD_WIDTH);
        setMinHeight(CARD_HEIGHT);
        setMaxHeight(CARD_HEIGHT);
    }

    private void initializeLabels() {
        var labelColorBinding = createObjectBinding(this::getLabelColor, cardProperty);

        topLeftLabel.textProperty().bind(createStringBinding(this::getTopLeftLabelText, cardProperty));
        topLeftLabel.textFillProperty().bind(labelColorBinding);

        centerLabel.textProperty().bind(createStringBinding(this::getCenterLabelText, cardProperty));
        centerLabel.textFillProperty().bind(labelColorBinding);

        bottomRightLabel.textProperty().bind(createStringBinding(this::getBottomRightLabelText, cardProperty));
        bottomRightLabel.textFillProperty().bind(labelColorBinding);
    }

    private String getTopLeftLabelText() {
        var card = getCard();
        if (card == null) return "";
        return card.getRank().getShortText() + " " + card.getSuit().getSymbolText();
    }

    private String getCenterLabelText() {
        var card = getCard();
        if (card == null) return "";
        return card.getSuit().getSymbolText();
    }

    private String getBottomRightLabelText() {
        var card = getCard();
        if (card == null) return "";
        return card.getSuit().getSymbolText() + " " + card.getRank().getShortText();
    }

    private Color getLabelColor() {
        var card = getCard();
        if (card == null) return null;
        return card.getColor().getFxColor();
    }

    private void initializeBindings() {
        isSelectedBinding = createBooleanBinding(
                () -> cardProperty.getValue() == controller.selectedCardProperty().getValue(),
                cardProperty, controller.selectedCardProperty());

        observe(isSelectedBinding, this::setIsSelected);
    }

    private void setIsSelected(boolean isSelected) {
        setHasStyleClass(this, "selected", isSelected);
    }

}
