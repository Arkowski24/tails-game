package pl.edu.agh.torbjorns;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import pl.edu.agh.torbjorns.card.Card;

import java.io.IOException;

import static javafx.beans.binding.Bindings.*;

public class CardControl extends AnchorPane {

    public final static double CARD_WIDTH = 110;
    public final static double CARD_HEIGHT = 1.5 * CARD_WIDTH;

    private final Property<Card> cardProperty = new SimpleObjectProperty<>();

    @FXML
    private Label topLeftLabel;

    @FXML
    private Label centerLabel;

    @FXML
    private Label bottomRightLabel;

    public CardControl() {
        loadFxml();
        initializeDimensions();
        initializeLabels();
    }

    private void loadFxml() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("card.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
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

    public Property<Card> cardProperty() {
        return cardProperty;
    }

    public Card getCard() {
        return cardProperty.getValue();
    }

    public void setCard(Card card) {
        cardProperty.setValue(card);
    }

}
