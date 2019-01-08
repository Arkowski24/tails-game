package pl.edu.agh.torbjorns.view.util;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.board.CardHolder;

import java.util.List;
import java.util.function.Consumer;

import static javafx.beans.binding.Bindings.*;

public class ObservableUtils {

    public static <T> void observe(ObservableValue<T> observable, Consumer<T> observer) {
        observer.accept(observable.getValue());
        observable.addListener((_observable, _oldValue, newValue) -> observer.accept(newValue));
    }

    public static <T> void observe(ObservableList<T> observable, Consumer<List<T>> observer) {
        observer.accept(observable);
        observable.addListener((ListChangeListener<T>) c -> observer.accept(observable));
    }

    public static BooleanBinding createIsTargetBinding(Controller controller, CardHolder cardHolder) {
        return createBooleanBinding(
                () -> {
                    var selectedCard = controller.selectedCardProperty().getValue();
                    return selectedCard != null && cardHolder.canPutCard(selectedCard);
                },
                controller.selectedCardProperty());
    }

}
