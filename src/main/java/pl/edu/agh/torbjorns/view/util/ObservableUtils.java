package pl.edu.agh.torbjorns.view.util;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.torbjorns.Controller;
import pl.edu.agh.torbjorns.board.CardManager;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public static <T, U> void map(ObservableValue<T> source1, Function<T, U> mapper) {
        createObjectBinding(() -> mapper.apply(source1.getValue()), source1);
    }

    public static BooleanBinding isTargetBinding(Controller controller, CardManager cardManager) {
        return createBooleanBinding(
                () -> {
                    var selectedCard = controller.selectedCardProperty().getValue();
                    return selectedCard != null && cardManager.canPutCard(selectedCard);
                },
                controller.selectedCardProperty());
    }

}
