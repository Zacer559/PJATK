import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Draw extends JPanel {
    ArrayList<Shapes> shapes;

    public Draw(ArrayList<Shapes> shapes) {
        this.shapes = shapes;

    }


    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        drawArray(g, shapes);

    }

    public void drawArray(Graphics g, ArrayList<Shapes> shapes) {
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).drawShape(g);

        }

    }


}

