package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Highscore implements Initializable {
    public static Result[] scoretable;
    @FXML
    private Label x1;
    @FXML
    private Label x2;
    @FXML
    private Label x3;
    @FXML
    private Label x4;
    @FXML
    private Label x5;
    @FXML
    private Label x6;
    @FXML
    private Label x7;
    @FXML
    private Label x8;
    @FXML
    private Label x9;
    @FXML
    private Label x10;


    public static int isHigh(Result a) {
        for (int i = 0; i < 5; i++) {
            if (scoretable[i].getTime().intTime() > a.getTime().intTime()) {
                for (int j = 4; j > i; j--) {
                    scoretable[j] = scoretable[j - 1];


                }
                scoretable[i] = a;
                return i;
            }


        }
        return -1;

    }


    public static void saveScores() {
        try {
            File file = new File("scores.txt");


            BufferedWriter out = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < 5; i++) {
                out.write(scoretable[i].getTime().getMinutes() + " " + scoretable[i].getTime().getSeconds() + " " + scoretable[i].getTime().getMillis() + " " + scoretable[i].getMoves() + " " + "\n");
            }

            out.close();
        } catch (IOException e) {

        }


    }


    public static void loadScores() {
        int i = 0;
        scoretable = new Result[5];
        try {

            File file = new File("scores.txt");
            if (file.exists()) {
                BufferedReader in = new BufferedReader(new FileReader(file));

                String st;
                while (i < 5) {

                    if ((st = in.readLine()) != null) {
                        String[] input = st.split("\\s");
                        Timee tmp = new Timee(Integer.valueOf(input[0]), Integer.valueOf(input[1]), Integer.valueOf(input[2]));
                        scoretable[i] = new Result(Integer.valueOf(input[3]), tmp);
                    } else {
                        Timee tmp = new Timee(99, 59, 99);
                        scoretable[i] = new Result(0, tmp);
                    }
                    i++;
                }
                in.close();
            } else {
                while (i < 5) {
                    Timee tmp = new Timee(99, 59, 99);
                    scoretable[i] = new Result(0, tmp);
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("LoadScore error");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGrid();
    }

    public void menu(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }

    public void reset(ActionEvent actionEvent) throws Exception {
        for (int i = 0; i < 5; i++) {
            Timee tmp = new Timee(99, 59, 99);
            scoretable[i] = new Result(0, tmp);


        }
        setGrid();
        saveScores();
    }


    public void setGrid() {
        x1.setText(scoretable[0].getTime().getTimeString());
        x2.setText(String.valueOf(scoretable[0].getMoves()));
        x3.setText(scoretable[1].getTime().getTimeString());
        x4.setText(String.valueOf(scoretable[1].getMoves()));
        x5.setText(scoretable[2].getTime().getTimeString());
        x6.setText(String.valueOf(scoretable[2].getMoves()));
        x7.setText(scoretable[3].getTime().getTimeString());
        x8.setText(String.valueOf(scoretable[3].getMoves()));
        x9.setText(scoretable[4].getTime().getTimeString());
        x10.setText(String.valueOf(scoretable[4].getMoves()));


    }


}
