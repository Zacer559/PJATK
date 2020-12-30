import java.awt.*;

public abstract class Shapes {
    public Shapes() {
    }

    public static boolean IsInside(int width, int height, int xEnd, int yEnd) {
        return xEnd + 50 < width && yEnd + 50 < height && xEnd - 50 > 0 && yEnd - 50 > 0;


    }

    public static int ShapeType() {
        return (int) (Math.random() * 3);


    }

    public abstract void drawShape(Graphics g);

    public int generateSize(int a, int b) {
        return (int) Math.sqrt((Math.random() * ((a * a + b * b) / 2) + 50));

    }

    public int MyRandom(int size) {
        return (int) (Math.random() * size);
    }

    public Color randomColor() {
        return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    public abstract void update(double ratiow, double ratioh);

}