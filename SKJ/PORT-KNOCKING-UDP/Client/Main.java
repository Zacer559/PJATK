import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static List<Integer> ports = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Too many arguments");
        } else {
            String adress = args[0];

            for (int i = 1; i < args.length; i++) {

                ports.add(Integer.valueOf(args[i]));

            }
            System.out.println("Trying to authenticate....");
            CreateClients.create(ports, adress);
        }

    }



}