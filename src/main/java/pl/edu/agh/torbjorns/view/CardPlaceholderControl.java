package pl.edu.agh.torbjorns.view;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.view.util.ControlUtils;

public class CardPlaceholderControl extends StackPane {
    private Controller controller;

    public CardPlaceholderControl(Controller controller) {
        ControlUtils.loadFxml(this);
        initializeDimensions();

        this.controller = controller;
        this.setOnMouseClicked(this::onClickAction);
    }

    private void initializeDimensions() {
        setMinWidth(CardControl.CARD_WIDTH);
        setMaxWidth(CardControl.CARD_WIDTH);
        setMinHeight(CardControl.CARD_HEIGHT);
        setMaxHeight(CardControl.CARD_HEIGHT);
    }

    private void onClickAction(MouseEvent event) {
    }
}
