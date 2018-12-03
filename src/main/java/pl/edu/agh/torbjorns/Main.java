package pl.edu.agh.torbjorns;

import com.google.inject.Guice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        var loader = new FXMLLoader(getClass().getResource("/pl/edu/agh/torbjorns/application.fxml"));

        Parent root = loader.load();

        primaryStage.setTitle("Tornbjorns' Ogonki");
        primaryStage.setScene(new Scene(root, Controller.BASE_WIDTH, Controller.BASE_HEIGHT));
        primaryStage.show();

        Controller controller = loader.getController();
        Guice.createInjector().injectMembers(controller);
        controller.lateInitialize();
    }

}
