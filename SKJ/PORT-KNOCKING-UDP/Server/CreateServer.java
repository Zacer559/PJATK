import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class CreateServer {
    private static HashMap<String, Integer> map = new HashMap<>();
    private static int size;
    private static List<Integer> order = new ArrayList<>();
    private static List<UdpServer> servers = new ArrayList<>();

    public static int sizze() {
        return size;
    }

    public static int properOrder(int d) {
        return order.get(d);

    }


    public static synchronized void create(List<Integer> ports) {
        size = ports.size();
        order = ports;
        final int[] i = {1};

        ports.forEach((n) -> {


            try {
                if (!ports.subList(0, i[0] - 1).contains(n)) {


                    servers.add(new UdpServer(n));
                    servers.get(i[0] - 1).start();
                    i[0]++;
                }
            } catch (SocketException e) {
               System.err.println("Cannot create socket on port " + n + "\n Exiting...");

                CreateServer.serverClose();
            }
        });
        System.out.println("Server has started correctly on specified ports");
    }



    public static synchronized int addIP(String ip) {
        if (map.containsKey(ip)) {
            int i = map.get(ip);
            i = i + 1;
            // System.out.println(i);
            map.replace(ip, i);
        } else {
            map.put(ip, 1);
        }
        // System.out.println(map);
        return map.get(ip);

    }

    public static synchronized void remove(String ip) {
        map.remove(ip);
    }


    @SuppressWarnings("deprecation")
    public static void serverClose()  {
        for (UdpServer n : servers) {
            n.stop();
            System.exit(0);
        }
    }
}