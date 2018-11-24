package pl.edu.agh.torbjorns;

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
        Parent root = FXMLLoader.load(getClass().getResource("/pl/edu/agh/torbjorns/application.fxml"));
        primaryStage.setTitle("Tornbjorns' Ogonki");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

}
