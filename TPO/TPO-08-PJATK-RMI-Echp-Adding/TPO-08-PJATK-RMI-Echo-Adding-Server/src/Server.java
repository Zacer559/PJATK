import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "rmi-client.policy");
            LocateRegistry.createRegistry(1099);
            EchoResponseObject echoResponse = new EchoResponseObject();
            AddingResponseObject addingResponse = new AddingResponseObject();
            Naming.rebind("EchoServer", echoResponse);
            Naming.rebind("AddingServer", addingResponse);
            System.out.println("Initialized");

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
