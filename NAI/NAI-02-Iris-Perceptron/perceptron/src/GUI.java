import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {
    IrisTypes.IrisType one;
    IrisTypes.IrisType two;
    IrisTypes.IrisType three;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button btn = new Button();
        btn.setText("Test dataset");
        btn.setOnAction(event -> {
            Perceptron p = new Perceptron(one, two);
            p.readAndTrain();
            System.out.println(one + " " + two);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, ("Accuracy was: " + Math.round(p.accuracy(p.ClassifyList(p.getTestList())) * 100) + "%"), ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

        });
        Button btn2 = new Button();
        btn2.setText("Input one iris");
        btn2.setOnAction(event -> {
            VBox root2 = new VBox();
            Button btn3 = new Button();
            btn3.setText("OK");

            root2.getChildren().add(btn3);
            root2.setAlignment(Pos.CENTER);
            TextField a = new TextField();
            TextField b = new TextField();
            TextField c = new TextField();
            TextField d = new TextField();
            root2.getChildren().add(a);
            root2.getChildren().add(b);
            root2.getChildren().add(c);
            root2.getChildren().add(d);
            btn3.setOnAction(event2 -> {
                Perceptron p = new Perceptron(one, two);
                p.readAndTrain();
                Iris i = new Iris();
                i.setInitialType(three);
                List<Double> ddd = new ArrayList<>();
                ddd.add(Double.parseDouble(a.getText()));
                ddd.add(Double.parseDouble(b.getText()));
                ddd.add(Double.parseDouble(c.getText()));
                ddd.add(Double.parseDouble(d.getText()));
                i.setInputs(ddd);
                String correct;
                if (p.Classify(i).getRecognizedType() == three) {
                    correct = "CORRECT";
                } else {
                    correct = "NOT CORRECT";
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION, ("recognized as " + p.Classify(i).getRecognizedType() + "  " + correct), ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.show();
            });
            ChoiceBox iriss = new ChoiceBox(FXCollections.observableArrayList(
                    "SETOSA", "VIRGINICA", "VERSICOLOR")
            );
            iriss.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> {
                System.out.println(new_value);
                switch ((Integer) new_value) {
                    case 0:
                        three = IrisTypes.IrisType.SETOSA;
                        break;
                    case 1:
                        three = IrisTypes.IrisType.VIRGINICA;
                        break;
                    case 2:
                        three = IrisTypes.IrisType.VERSICOLOR;
                        break;
                }


            });
            root2.getChildren().add(iriss);
            Scene scene2 = new Scene(root2, 300, 250);

            primaryStage.setTitle("Iris perceptron");
            primaryStage.setScene(scene2);
            primaryStage.show();

        });
        ChoiceBox irisone = new ChoiceBox(FXCollections.observableArrayList(
                "SETOSA", "VIRGINICA", "VERSICOLOR")
        );
        irisone.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> {

            switch ((Integer) new_value) {
                case 0:
                    one = IrisTypes.IrisType.SETOSA;
                    break;
                case 1:
                    one = IrisTypes.IrisType.VIRGINICA;
                    break;
                case 2:
                    one = IrisTypes.IrisType.VERSICOLOR;
                    break;
            }

        });

        ChoiceBox iristwo = new ChoiceBox(FXCollections.observableArrayList(
                "SETOSA", "VIRGINICA", "VERSICOLOR")
        );
        iristwo.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> {

            switch ((Integer) new_value) {
                case 0:
                    two = IrisTypes.IrisType.SETOSA;
                    break;
                case 1:
                    two = IrisTypes.IrisType.VIRGINICA;
                    break;
                case 2:
                    two = IrisTypes.IrisType.VERSICOLOR;
                    break;
            }

        });
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        root.getChildren().add(irisone);
        root.getChildren().add(iristwo);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Iris perceptron");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}