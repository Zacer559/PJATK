import java.awt.*;

public class Rectangle extends Shapes {
    int xFrom;
    int yFrom;
    int xEnd;
    int yEnd;
    Color color;

    public Rectangle(int height, int width) {
        color = randomColor();
        int xxEnd;
        int yyEnd;
        do {
            xFrom = MyRandom(width);
            yFrom = MyRandom(height);
            xxEnd = (generateSize(height, width) / 2);
            yyEnd = (generateSize(height, width) / 2);
        } while (!IsInside(width, height, xFrom + xxEnd, yFrom + yyEnd));
        xEnd = xxEnd;
        yEnd = yyEnd;

    }

    public Rectangle(int xFrom, int yFrom, int xEnd, int yEnd, Color color) {
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.fillRect(xFrom, yFrom, xEnd, yEnd);

    }

    @Override
    public String toString() {
        return "Rectangle" + " " + xFrom + " " + yFrom + " " + xEnd + " " + yEnd + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue();
    }

    @Override
    public void update(double ratiow, double ratioh) {
        xFrom = (int) Math.round((double) xFrom * ratiow);
        yFrom = (int) Math.round((double) yFrom * ratioh);
        xEnd = (int) Math.round((double) xEnd * ratiow);
        yEnd = (int) Math.round((double) yEnd * ratioh);

    }
}
