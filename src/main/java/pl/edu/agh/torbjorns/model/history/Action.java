package pl.edu.agh.torbjorns.model.history;

public interface Action {
    void perform();

    void undo();
}
