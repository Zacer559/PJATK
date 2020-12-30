import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EchoResponseObject extends UnicastRemoteObject implements IEcho {

    public EchoResponseObject() throws RemoteException {
        super();
    }

    @Override
    public EchoResponse echo(EchoRequest echoRequest) {
        if (echoRequest.get_msg() != null) {
            System.out.println("Received echo request message is: " + echoRequest.get_msg());
            return new EchoResponse(echoRequest.get_msg());
        }
        return new EchoResponse("EMPTY REQUEST ");
    }
}