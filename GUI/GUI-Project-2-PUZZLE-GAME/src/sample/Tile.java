package sample;

import javafx.scene.shape.Rectangle;

import java.awt.image.BufferedImage;

public class Tile extends Rectangle {
    private BufferedImage part;
    private int num;


    Tile(double width, double height, BufferedImage part, int num) {
        super(width, height);
        this.part = part;
        this.num = num;
    }

    public BufferedImage getPart() {
        return part;
    }

    public void setPart(BufferedImage part) {
        this.part = part;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


}