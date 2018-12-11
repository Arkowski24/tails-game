package pl.edu.agh.torbjorns.view;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import pl.edu.agh.torbjorns.view.util.ControlUtils;

public class CardPlaceholderControl extends StackPane implements ManageCardControl {

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

    private void onClickAction(MouseEvent event) {
        // controller.clickedOnBufferZone(this);
    }

    public boolean canPutCard() {
        return (getChildren().size() == 0);
    }

    public void addCard(CardControl cardControl) {
        getChildren().add(cardControl);
    }

    public void removeCard(CardControl cardControl) {
        getChildren().remove(cardControl);
    }

    public CardControl getCard() {
        if (!canPutCard()) {
            return (CardControl) getChildren().get(getChildren().size() - 1);
        } else {
            return null;
        }
    }
}
