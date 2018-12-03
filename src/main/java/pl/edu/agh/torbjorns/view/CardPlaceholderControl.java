package pl.edu.agh.torbjorns.view;

import javafx.scene.layout.StackPane;
import pl.edu.agh.torbjorns.view.util.ControlUtils;

public class CardPlaceholderControl extends StackPane {

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

}
