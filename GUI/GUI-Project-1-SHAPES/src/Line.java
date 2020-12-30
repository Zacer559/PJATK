import java.awt.*;

public class Line extends Shapes {
    int xFrom;
    int yFrom;
    int xEnd;
    int yEnd;
    Color color;

    public Line(int height, int width) {

        color = randomColor();
        int xxEnd;
        int yyEnd;
        double angle;
        int size;
        do {
            xFrom = MyRandom(width) / 2;
            yFrom = MyRandom(height) / 2;
            angle = Math.random() * Math.PI;
            size = generateSize(height, width);
            xxEnd = (int) (xFrom + Math.sin(angle) * size);
            yyEnd = (int) (yFrom + Math.cos(angle) * size);
        } while (!IsInside(width, height, xxEnd, yyEnd));
        xEnd = xxEnd;
        yEnd = yyEnd;

    }

    public Line(int xFrom, int yFrom, int xEnd, int yEnd, Color color) {
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
    }

    @Override
    public void drawShape(Graphics g) {

        g.setColor(color);
        g.drawLine(xFrom, yFrom, xEnd, yEnd);


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
        return "Line " + xFrom + " " + yFrom + " " + xEnd + " " + yEnd + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue();
    }


}
