package pl.edu.agh.torbjorns;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import pl.edu.agh.torbjorns.board.CardStack;
import pl.edu.agh.torbjorns.card.Card;

public class WorkingCardStackControl extends VBox {

    private final static double SPACING = -0.85 * CardControl.CARD_HEIGHT;
    private final static double PADDING_TOP = 0.05 * CardControl.CARD_WIDTH;

    private final CardStack cardStack;

    public WorkingCardStackControl(CardStack cardStack) {
        this.cardStack = cardStack;

        setAlignment(Pos.TOP_CENTER);
        setSpacing(SPACING);
        setPadding(new Insets(PADDING_TOP, 0, 0, 0));

        initializeCards();
    }

    private void initializeCards() {
        for (Card card : cardStack.getCards()) {
            CardControl cardControl = new CardControl();
            cardControl.setCard(card);
            getChildren().add(cardControl);
        }
    }

}
