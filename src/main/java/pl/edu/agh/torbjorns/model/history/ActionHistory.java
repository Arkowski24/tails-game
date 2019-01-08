package pl.edu.agh.torbjorns.model.history;

import java.util.ArrayDeque;
import java.util.Deque;

public class ActionHistory {
    private final Deque<Action> actions = new ArrayDeque<>();

    public void performAction(Action action) {
        action.perform();
        actions.push(action);
    }

    public boolean canUndoLastAction() {
        return !actions.isEmpty();
    }

    public void undoLastAction() {
        if (!canUndoLastAction())
            throw new IllegalStateException("Cannot undo last action");

        var lastAction = actions.pop();
        lastAction.undo();
    }

}
