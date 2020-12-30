package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSpliter {
    private File image;
    private int width;
    private int height;
    private int wtiles;
    private int htiles;
    private ObservableList<Tile> tileList = FXCollections.observableArrayList();

    public ImageSpliter(File image, int width, int height, int wtiles, int htiles) {
        this.image = image;
        this.width = width;
        this.wtiles = wtiles;
        this.htiles = htiles;
        this.height = height;

    }

    public static BufferedImage update(BufferedImage a, double ratio) {
        BufferedImage buff = a;
        if (buff.getHeight() != (a.getHeight() * ratio) || buff.getWidth() != (a.getHeight() * ratio)) {
            Image temp = buff.getScaledInstance((int) (a.getHeight() * ratio), (int) (a.getHeight() * ratio), Image.SCALE_SMOOTH);
            BufferedImage img = new BufferedImage((int) (a.getHeight() * ratio), (int) (a.getHeight() * ratio), BufferedImage.TYPE_4BYTE_ABGR);

            Graphics2D graphics = img.createGraphics();
            graphics.drawImage(temp, 0, 0, null);
            graphics.dispose();
            return img;


        } else return a;
    }


    private BufferedImage Scale() {
        try {
            BufferedImage buff = ImageIO.read(image);
            if (buff.getHeight() != height || buff.getWidth() != width) {
                Image temp = buff.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

                Graphics2D graphics = img.createGraphics();
                graphics.drawImage(temp, 0, 0, null);
                graphics.dispose();
                return img;
            } else return buff;


        } catch (IOException e) {
            System.err.println("Cannot read image from path");
        }


        return null;
    }

    public ObservableList<Tile> spliter() {
        BufferedImage image = Scale();
        int z = 0;
        if (image != null) {
            System.out.println("Image size: " + image.getWidth() + " " + image.getHeight());


            for (int i = 0; i < htiles; i++) {
                for (int j = 0; j < wtiles; j++) {

                    BufferedImage part = image.getSubimage(
                            j * (width / wtiles),
                            i * (height / htiles),
                            width / wtiles,
                            height / htiles
                    );


                    Tile tile = new Tile(part.getWidth(), part.getHeight(), part, i * wtiles + j);
                    z++;
                    tile.setLayoutX(j * (tile.getWidth()) + j * 5);
                    tile.setLayoutY(i * (tile.getHeight()) + i * 5);

                    tile.setFill(new ImagePattern(SwingFXUtils.toFXImage(tile.getPart(), null)));
                    tileList.add(tile);
                }
            }
        } else System.out.println("Image is null");
        tileList.get(tileList.size() - 1).setFill(Color.DARKGRAY);
        tileList.get(tileList.size() - 1).setNum(-1);
        return tileList;
    }
}
