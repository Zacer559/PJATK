package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class Controller {

    public void newgame(ActionEvent actionEvent) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/sample/Game.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Puzzle Game");
        stage.setScene(new Scene(root, Options.getWidth() + 5 * (Options.getWtiles() - 1) + 2, 60 + Options.getHeight() + 5 * (Options.getHtiles() - 1) + 2));
        stage.show();
        stage.setResizable(false);

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    root2.getStyleClass().add("demo.css");
                    Stage stage2 = new Stage();
                    stage2.setTitle("Puzzle Game");
                    stage2.setScene(new Scene(root2, 800, 600));
                    stage2.getScene().getStylesheets().add(
                            getClass().getResource("demo.css").toExternalForm()
                    );
                    stage2.show();
                    stage2.setResizable(false);
                } catch (IOException e) {
                    System.out.println("error");
                }
            }
        });


    }

    public void options(ActionEvent actionEvent) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/sample/Options.fxml"));
        root.getStyleClass().add("demo.css");
        Stage stage = new Stage();
        stage.setTitle("Options");
        stage.setScene(new Scene(root, 800, 600));
        stage.getScene().getStylesheets().add(
                getClass().getResource("demo.css").toExternalForm()
        );
        stage.show();
        stage.setResizable(false);
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    root2.getStyleClass().add("demo.css");
                    Stage stage2 = new Stage();
                    stage2.setTitle("Puzzle Game");
                    stage2.setScene(new Scene(root2, 800, 600));
                    stage2.getScene().getStylesheets().add(
                            getClass().getResource("demo.css").toExternalForm()
                    );
                    stage2.show();
                    stage2.setResizable(false);
                } catch (IOException e) {
                    System.out.println("error");
                }
            }
        });


    }

    public void exit(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void highscore(ActionEvent actionEvent) throws Exception {
        Parent root;

        root = FXMLLoader.load(getClass().getResource("/sample/Highscore.fxml"));
        root.getStyleClass().add("demo.css");
        Stage stage = new Stage();
        stage.setTitle("Highscore");
        stage.setScene(new Scene(root, 800, 600));
        stage.getScene().getStylesheets().add(
                getClass().getResource("demo.css").toExternalForm()
        );
        stage.show();
        stage.setResizable(false);
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    root2.getStyleClass().add("demo.css");
                    Stage stage2 = new Stage();
                    stage2.setTitle("Puzzle Game");
                    stage2.setScene(new Scene(root2, 800, 600));
                    stage2.getScene().getStylesheets().add(
                            getClass().getResource("demo.css").toExternalForm()
                    );
                    stage2.show();
                    stage2.setResizable(false);
                } catch (IOException e) {
                    System.out.println("error");
                }
            }
        });

    }
}





