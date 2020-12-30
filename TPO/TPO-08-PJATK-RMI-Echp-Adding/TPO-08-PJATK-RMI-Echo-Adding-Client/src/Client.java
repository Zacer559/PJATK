import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "rmi-client.policy");
            IEcho echoServer = (IEcho) Naming.lookup("rmi://localhost:1099/EchoServer");
            IAdding addingServer = (IAdding) Naming.lookup("rmi://localhost:1099/AddingServer");

            String msg = "Hello!";
            EchoRequest echoMsg = new EchoRequest(msg);
            EchoResponse respMsg = echoServer.echo(echoMsg);

            int num1 = 555;
            int num2 = 666;
            AddingRequest addingNums = new AddingRequest(num1, num2);
            AddingResponse respNums = addingServer.sum(addingNums);

            System.out.println("ECHO: \nClient message: " + msg);
            System.out.println("Server message: " + respMsg.get_msg() + "\n");
            System.out.println("ADDING: \nClient message: " + num1 + " + " + num2);
            System.out.println("Server message: " + respNums.get_sum());
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}
