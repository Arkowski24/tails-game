package pl.edu.agh.torbjorns.model.history;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.torbjorns.model.board.CardHolder;

@RequiredArgsConstructor
public class MoveCardAction implements Action {
    private final CardHolder source;
    private final CardHolder destination;

    public void perform() {
        var movedCard = source.takeCard();
        destination.putCard(movedCard);
    }

    public void undo() {
        var movedCard = destination.takeCardForcibly();
        source.putCardForcibly(movedCard);
    }
}
