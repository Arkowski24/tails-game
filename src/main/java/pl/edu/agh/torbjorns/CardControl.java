package pl.edu.agh.torbjorns;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import pl.edu.agh.torbjorns.card.Card;
import pl.edu.agh.torbjorns.card.Color;
import pl.edu.agh.torbjorns.card.Rank;
import pl.edu.agh.torbjorns.card.Suit;

import java.io.IOException;
import java.util.Map;

import static javafx.beans.binding.Bindings.*;

public class CardControl extends AnchorPane {

    private static final Map<Suit, String> SUIT_TEXTS = Map.of(
            Suit.SPADES, "♠",
            Suit.HEARTS, "♥",
            Suit.DIAMONDS, "♦",
            Suit.CLUBS, "♣"
    );

    private static final Map<Rank, String> RANK_TEXTS = Map.ofEntries(
            Map.entry(Rank.ACE, "A"),
            Map.entry(Rank.TWO, "2"),
            Map.entry(Rank.THREE, "3"),
            Map.entry(Rank.FOUR, "4"),
            Map.entry(Rank.FIVE, "5"),
            Map.entry(Rank.SIX, "6"),
            Map.entry(Rank.SEVEN, "7"),
            Map.entry(Rank.EIGHT, "8"),
            Map.entry(Rank.NINE, "9"),
            Map.entry(Rank.TEN, "10"),
            Map.entry(Rank.JACK, "J"),
            Map.entry(Rank.QUEEN, "Q"),
            Map.entry(Rank.KING, "K")
    );

    private final Property<Card> cardProperty = new SimpleObjectProperty<>();

    @FXML
    private Label label;

    public CardControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        label.textProperty()
                .bind(createStringBinding(
                        () -> computeLabelText(getCard()),
                        cardProperty));

        label.textFillProperty()
                .bind(createObjectBinding(
                        () -> computeLabelTextFill(getCard()),
                        cardProperty));
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

    private String computeLabelText(Card card) {
        if (card != null) {
            return RANK_TEXTS.get(card.getRank()) + " " + SUIT_TEXTS.get(card.getSuit());
        } else {
            return "";
        }
    }

    private javafx.scene.paint.Color computeLabelTextFill(Card card) {
        if (card != null && card.getColor() == Color.RED) {
            return javafx.scene.paint.Color.RED;
        } else {
            return javafx.scene.paint.Color.BLACK;
        }
    }

}
