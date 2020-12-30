import java.awt.*;

public class Oval extends Shapes {
    int xFrom;
    int yFrom;
    int xEnd;
    int yEnd;
    Color color;

    public Oval(int height, int width) {

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

    public Oval(int xFrom, int yFrom, int xEnd, int yEnd, Color color) {
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(color);
        g.fillOval(xFrom, yFrom, xEnd, yEnd);


    }


    @Override
    public void update(double ratiow, double ratioh) {
        xFrom = (int) Math.round((double) xFrom * ratiow);
        yFrom = (int) Math.round((double) yFrom * ratioh);
        xEnd = (int) Math.round((double) xEnd * ratiow);
        yEnd = (int) Math.round((double) yEnd * ratioh);


    }


    @Override
    public String toString() {
        return "Oval " + xFrom + " " + yFrom + " " + xEnd + " " + yEnd + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue();
    }


}
