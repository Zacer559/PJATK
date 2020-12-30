
import java.util.ArrayList;
import java.util.List;

public class Main {


    private static List<Integer> ports = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("No enough arguments");
        } else {
            for (int i = 0; i < args.length; i++) {
                ports.add(Integer.valueOf(args[i]));

            }
            CreateServer.create(ports);
        }


    }




}

