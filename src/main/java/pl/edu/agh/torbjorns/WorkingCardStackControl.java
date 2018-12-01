package pl.edu.agh.torbjorns;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import pl.edu.agh.torbjorns.board.CardStack;
import pl.edu.agh.torbjorns.card.Card;

public class WorkingCardStackControl extends VBox {

    private final CardStack cardStack;

    public WorkingCardStackControl(CardStack cardStack) {
        this.cardStack = cardStack;
        this.setSpacing(-180);
        initializeCards();
    }

    private void initializeCards() {
        // TODO(pjanczyk): clean up dimensions

        for (Card card : cardStack.getCards()) {
            CardControl cardControl = new CardControl();
            cardControl.setCard(card);
            cardControl.setMinHeight(200);
            VBox.setMargin(cardControl, new Insets(5));
            getChildren().add(cardControl);
        }
    }

}
