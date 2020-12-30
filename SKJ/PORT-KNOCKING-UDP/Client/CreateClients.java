import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class CreateClients {
    

    public static void create(List<Integer> ports, String adress) throws  IOException {
       
        int last = ports.size() - 1;
      

         // ports.set(0,ports.get(4));
        //     ports.set(4,temp);

        UdpClient client2 = new UdpClient(adress);
        //   UdpClient client3 = new UdpClient(ports.get(0));
        for (int i = 0; i < ports.size(); i++) {
            int port = ports.get(i);
            //   System.out.println(n);
            try {
                if (last == i) {

                    client2.run(port, true);
                    //  client3.run(n, true);
                } else {
                    client2.run(port, false);
                    //client3.run(n, false);
                }
            } catch (Exception e ){
                System.err.println("No response from server. (Timeout 5 seconds) \n Exiting...");
            }

        }
        client2.close();
    }

}

