package sample;

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
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.getStyleClass().add("demo.css");
        primaryStage.setTitle("Puzzle Game");
        Options.loadOptions();
        Highscore.loadScores();
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.getScene().getStylesheets().add(
                getClass().getResource("demo.css").toExternalForm()
        );
        primaryStage.show();
        primaryStage.setResizable(false);


    }
}
