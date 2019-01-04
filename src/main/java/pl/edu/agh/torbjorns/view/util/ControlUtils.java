package pl.edu.agh.torbjorns.view.util;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ControlUtils {

    public static void loadFxml(Object control) {
        var clazz = control.getClass();
        var url = clazz.getResource(clazz.getSimpleName() + ".fxml");

        var loader = new FXMLLoader(url);
        loader.setRoot(control);
        loader.setController(control);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
