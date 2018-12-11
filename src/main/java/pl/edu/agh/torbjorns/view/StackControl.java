package pl.edu.agh.torbjorns.view;

import pl.edu.agh.torbjorns.board.CardStack;

public interface StackControl extends ManageCardControl {

    CardControl getTopCardControl();

    CardStack getCardStack();
}
