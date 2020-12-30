import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ReadWrite {
    ArrayList<Shapes> F;
    ArrayList<Shapes> E;

    public ReadWrite(ArrayList<Shapes> F, ArrayList<Shapes> E) {
        this.F = F;
        this.E = E;
    }


    public ArrayList<Shapes> save() {

        try {
            File file = new File("data.txt");


            BufferedWriter out = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < F.size(); i++) {
                out.write(F.get(i).toString() + "\n");

            }
            out.close();
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName());
        }


        try {
            File file = new File("data.txt");

            BufferedReader in = new BufferedReader(new FileReader(file));

            String st;
            while ((st = in.readLine()) != null) {

                String[] input = st.split("\\s");
                Color c;
                switch (input[0]) {
                    case "Line":
                        c = new Color(Integer.parseInt(input[5]), Integer.parseInt(input[6]), Integer.parseInt(input[7]));
                        E.add(new Line(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4]), c));
                        break;
                    case "Rectangle":
                        c = new Color(Integer.parseInt(input[5]), Integer.parseInt(input[6]), Integer.parseInt(input[7]));
                        E.add(new Rectangle(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4]), c));
                        break;
                    case "Oval":
                        c = new Color(Integer.parseInt(input[5]), Integer.parseInt(input[6]), Integer.parseInt(input[7]));
                        E.add(new Oval(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4]), c));
                        break;
                }

            }
            in.close();
            file.delete();
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName());
        }

        return E;


    }


}
