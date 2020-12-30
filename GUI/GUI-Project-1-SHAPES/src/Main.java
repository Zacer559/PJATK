import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class Main extends Thread {

    public static void main(String[] args) {
        JFrame application = new JFrame();
        ArrayList<Shapes> F = new ArrayList();
        ArrayList<Shapes> E = new ArrayList();
        int width = 800;
        int height = 640;
        application.setSize(width, height);
        application.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        application.setResizable(true);
        for (int i = 0; i < 1; i++) {
            int shape = Shapes.ShapeType();

            switch (shape) {
                case 0:
                    F.add(new Line(application.getHeight(), application.getWidth()));
                    break;
                case 1:
                    F.add(new Rectangle(application.getHeight(), application.getWidth()));
                    break;
                default:
                    F.add(new Oval(application.getHeight(), application.getWidth()));
                    break;


            }
        }

        application.add(new Draw(F));
        application.setVisible(true);
        Thread thread = new Thread() {
            public void run() {
                for (int i = 0; i < 9; i++) {
                    int shape = Shapes.ShapeType();

                    switch (shape) {
                        case 0:
                            F.add(new Line(application.getHeight(), application.getWidth()));
                            break;
                        case 1:
                            F.add(new Rectangle(application.getHeight(), application.getWidth()));
                            break;
                        default:
                            F.add(new Oval(application.getHeight(), application.getWidth()));


                    }
                    application.repaint();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                }


            }
        };
        thread.start();

        application.addComponentListener(new ComponentAdapter() {
            int width2 = application.getWidth();
            int height2 = application.getHeight();

            public void componentResized(ComponentEvent e) {
                Component c = (Component) e.getSource();

                int width3;
                int height3;
                width3 = c.getWidth();
                height3 = c.getHeight();
                double ratioh;
                double ratiow;
                ratiow = ((double) width3 / (double) width2);
                ratioh = ((double) height3 / (double) height2);
                width2 = width3;
                height2 = height3;

                for (int i = 0; i < F.size(); i++) {
                    F.get(i).update(ratiow, ratioh);

                }
                application.repaint();


            }
        });

        ReadWrite m = new ReadWrite(F, E);


        application.addComponentListener(new ComponentAdapter() {

            public void componentHidden(ComponentEvent e) {
                Component c = (Component) e.getSource();
                JFrame application2 = new JFrame();
                application2.setSize(application.getWidth(), application.getHeight());
                application.dispose();
                Draw draw2 = new Draw(m.save());
                application2.add(draw2);
                application2.setResizable(true);
                application2.setVisible(true);
                application2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                application2.addComponentListener(new ComponentAdapter() {

                    int width2 = application2.getWidth();
                    int height2 = application2.getHeight();

                    public void componentResized(ComponentEvent e) {
                        Component c = (Component) e.getSource();

                        int width3;
                        int height3;
                        width3 = c.getWidth();
                        height3 = c.getHeight();
                        double ratioh;
                        double ratiow;
                        ratiow = ((double) width3 / (double) width2);
                        ratioh = ((double) height3 / (double) height2);
                        width2 = width3;
                        height2 = height3;

                        for (int i = 0; i < E.size(); i++) {
                            E.get(i).update(ratiow, ratioh);
                        }
                        application2.repaint();

                    }
                });
            }


        });

    }


}