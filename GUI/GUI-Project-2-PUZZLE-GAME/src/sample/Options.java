package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class Options implements Initializable {
    static int wtiles = 3;
    static int htiles = 3;
    static int width = 600;
    static int height = 600;
    static int timelimit = 2;
    @FXML
    private TextField heightt;
    @FXML
    private TextField widtht;
    @FXML
    private TextField tilesw;
    @FXML
    private TextField tilesh;
    @FXML
    private Button apply;
    @FXML
    private TextField timelimitt;

    public static void loadOptions() {

        try {
            File file = new File("options.txt");

            BufferedReader in = new BufferedReader(new FileReader(file));

            String st;
            while ((st = in.readLine()) != null) {

                String[] input = st.split("\\s");
                wtiles = parseInt(input[0]);
                htiles = parseInt(input[1]);
                width = parseInt(input[2]);
                height = parseInt(input[3]);
                timelimit = parseInt(input[4]);

                in.close();
            }
        } catch (IOException e) {

        }


    }

    public static void saveOptions() {
        try {
            File file = new File("options.txt");


            BufferedWriter out = new BufferedWriter(new FileWriter(file));


            out.write(wtiles + " " + htiles + " " + width + " " + height + " " + timelimit + " ");


            out.close();
        } catch (IOException e) {

        }


    }

    public static int getWtiles() {
        return wtiles;
    }

    public static int getTimelimit() {
        return timelimit;
    }

    public static int getHtiles() {
        return htiles;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        heightt.setText(String.valueOf(height));
        widtht.setText(String.valueOf(width));
        tilesw.setText(String.valueOf(wtiles));
        tilesh.setText(String.valueOf(htiles));
        timelimitt.setText(String.valueOf(timelimit));

    }

    public void apply(ActionEvent actionEvent) throws Exception {
        wtiles = parseInt(tilesw.getCharacters().toString());
        htiles = parseInt(tilesh.getCharacters().toString());
        width = parseInt(widtht.getCharacters().toString());
        height = parseInt(heightt.getCharacters().toString());
        timelimit = parseInt(timelimitt.getCharacters().toString());

        saveOptions();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();


    }

    public void menu(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }


}
