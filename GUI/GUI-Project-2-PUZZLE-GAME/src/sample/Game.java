package sample;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

class NewResizeChangeWidth implements ChangeListener<Number> {
    double ratio = 1;
    private AnchorPane panel;
    private ObservableList<Tile> tileList = FXCollections.observableArrayList();

    public NewResizeChangeWidth(AnchorPane pane, ObservableList<Tile> tileList) {
        this.panel = pane;
        this.tileList = tileList;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        if (number.intValue() != 0 && t1.doubleValue() / number.doubleValue() != ratio) {
            this.ratio = t1.doubleValue() / number.doubleValue();
            System.out.println(number + "   " + t1);
            panel.getChildren().removeAll(tileList);


            for (Tile tile : tileList) {
                BufferedImage a = ImageSpliter.update(tile.getPart(), ratio);
                tile.setPart(a);
                tile.setFill(new ImagePattern(SwingFXUtils.toFXImage(tile.getPart(), null)));
            }
            panel.getChildren().addAll(tileList);
        }
    }

}


public class Game implements Initializable {
    private String path = "Tapety";
    private int countimaage = 0;
    private ArrayList<String> files = new ArrayList<String>();
    private Tile first = null;
    private Tile second = null;
    private int wtiles = Options.getWtiles();
    private int height = Options.getHeight();
    private int width = Options.getWidth();
    private int htiles = Options.getHtiles();
    private boolean isAnimationFinished = true;
    private boolean won = false;
    private Time time;
    private Timeline timeline;
    private int timeLimit = Options.getTimelimit();
    private int movess = 0;
    private boolean isStarted = false;
    private ObservableList<Tile> tileList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane panel;
    @FXML
    private Label moves;
    @FXML
    private Label mytime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listf(path);

        File imgFile = new File(randomImage());
        files.clear();
        ImageSpliter cutter = new ImageSpliter(imgFile, width, height, wtiles, htiles);
        tileList = cutter.spliter();
        shuffle();
        isStarted = true;
        panel.getChildren().addAll(tileList);
        for (Tile tile : tileList) {

            tile.setOnMouseClicked(event -> {
                if (isAnimationFinished && isStarted) {
                    first = (Tile) event.getSource();
                    if (isNeightbour() != -1 && isAnimationFinished) {
                        second = tileList.get(isNeightbour());
                        playAnimation();


                    }
                }
            });
            tile.setOnMouseExited(event -> {
                if (tile != first)
                    tile.setStrokeWidth(0);
            });
            tile.setOnMouseMoved(event -> {
                if (tile != first && (first == null || first != null && isNeightbour() != -1)) {
                    tile.setStrokeWidth(3);
                    tile.setStroke(Color.ORANGERED);
                }
            });
            // panel.heightProperty().addListener(new NewResizeChangeWidth(panel,tileList));


        }
        time = new Time();
        timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                event -> {
                    if (time.getMinutes() == timeLimit) {
                        timeline.stop();
                        isStarted = false;
                        Platform.runLater(() -> {
                            timeline.stop();
                            if (first != null) {
                                first.setStrokeWidth(0);
                                first = null;
                            }

                            second = null;
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("You lose!");
                            alert.setHeaderText("Time is up!");
                            alert.setContentText("Better luck next time.");

                            alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                                @Override
                                public void handle(DialogEvent event) {
                                    panel.getParent().getScene().getWindow().hide();
                                }
                            });
                            alert.showAndWait();


                        });
                    }
                    time.updateTime();
                    mytime.setText("Time: " + time.getTimeString());
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        time.setZero();
        timeline.play();


    }

    public int isNeightbour() {
        int indexone = tileList.indexOf(first);
        int indextwo = indexone + wtiles;
        int indexthree = indexone - wtiles;
        int indexfour = indexone + 1;
        int indexfive = indexone - 1;

        int[] arr = {indextwo, indexthree, indexfour, indexfive};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0 && arr[i] < tileList.size() && tileList.get(arr[i]).getNum() == -1) {
                if (arr[i] == indexfour && indexfour % wtiles == 0) {
                    return -1;
                } else if (arr[i] == indexfive && indexfive % wtiles == wtiles - 1) {
                    return -1;
                } else {
                    return arr[i];
                }
            }
        }
        return -1;
    }


    private void swapPuzzles() {
        int indexFirst = tileList.indexOf(first);
        int indexSecond = tileList.indexOf(second);

        double firstX = first.getLayoutX();
        double firstY = first.getLayoutY();

        first.setLayoutX(second.getLayoutX());
        first.setLayoutY(second.getLayoutY());

        second.setLayoutX(firstX);
        second.setLayoutY(firstY);

        Collections.swap(tileList, indexFirst, indexSecond);
        movess++;
        moves.setText("Moves:  " + movess);
        first = null;
        second = null;
        if (checkWin()) {
            isStarted = false;
            Result a = new Result(movess, new Timee((int) time.getMinutes(), (int) time.getSeconds(), (int) time.getMillis()));
            int ish = Highscore.isHigh(a);
            if (ish != -1) {
                Highscore.saveScores();
                Platform.runLater(() -> {
                    timeline.stop();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Winner");
                    alert.setHeaderText("New HIGHSCORE number " + (ish + 1));
                    alert.setContentText("Your time is: " + time.getTimeString() + "\nMoves: " + movess);
                    alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                        @Override
                        public void handle(DialogEvent event) {
                            panel.getParent().getScene().getWindow().hide();
                        }
                    });
                    alert.showAndWait();
                });


            } else {


                Platform.runLater(() -> {
                    timeline.stop();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Winner");
                    alert.setHeaderText("You win!");
                    alert.setContentText("Your time is: " + time.getTimeString() + "\nMoves: " + movess);
                    alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                        @Override
                        public void handle(DialogEvent event) {
                            panel.getParent().getScene().getWindow().hide();
                        }
                    });
                    alert.showAndWait();


                });
            }
        }

    }


    public void shuffle() {

        Collections.shuffle(tileList);
        for (int i = 0; i < htiles; i++)
            for (int j = 0; j < wtiles; j++) {
                Tile tile = tileList.get(i * wtiles + j);
                tile.setLayoutX(j * tile.getWidth() + j * 5);
                tile.setLayoutY(i * tile.getHeight() + i * 5);

            }

    }

    public void listf(String directoryName) {

        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                if (file.isFile()) {
                    countimaage++;
                    files.add(file.getAbsolutePath());
                } else if (file.isDirectory()) {
                    listf(file.getAbsolutePath());
                }
            }
    }


    public String randomImage() {
        String randompath = files.get((int) (Math.random() * countimaage));
        return randompath;


    }

    private void playAnimation() {
        isAnimationFinished = false;
        first.toFront();
        second.toFront();
        first.setStrokeWidth(5);
        first.setStroke(Color.GOLD);
        second.setStrokeWidth(5);
        second.setStroke(Color.GOLD);

        PathTransition ptr = getPathTransition(first, second);
        PathTransition ptr2 = getPathTransition(second, first);
        ParallelTransition pt = new ParallelTransition(ptr, ptr2);

        pt.setOnFinished(event -> {
            first.setStrokeWidth(0);
            second.setStrokeWidth(0);
            first.setTranslateX(0);
            first.setTranslateY(0);
            second.setTranslateX(0);
            second.setTranslateY(0);
            swapPuzzles();
            isAnimationFinished = true;
            if (won) {
            }

        });


        pt.play();
    }


    private boolean checkWin() {
        for (Tile tile : tileList) {
            if (tile.getNum() != -1) {
                if (tile.getNum() != tileList.indexOf(tile))
                    System.out.println("check");
                    System.out.println("check 2");
                    return false;
            }
        }
        return true;
    }


    private PathTransition getPathTransition(Tile first, Tile second) {
        PathTransition ptr = new PathTransition();
        Path path = new Path();
        path.getElements().clear();
        path.getElements().add(new MoveToAbs(first));
        path.getElements().add(new LineToAbs(first, second.getLayoutX(), second.getLayoutY()));
        ptr.setPath(path);
        ptr.setNode(first);
        return ptr;
    }

    public static class MoveToAbs extends MoveTo {
        MoveToAbs(Node node) {
            super(node.getLayoutBounds().getWidth() / 2,
                    node.getLayoutBounds().getHeight() / 2);
        }
    }

    public static class LineToAbs extends LineTo {
        LineToAbs(Node node, double x, double y) {
            super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2,
                    y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
        }
    }


}
