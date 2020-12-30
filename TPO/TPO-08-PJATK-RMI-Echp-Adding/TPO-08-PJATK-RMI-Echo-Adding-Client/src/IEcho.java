import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEcho extends Remote {
    EchoResponse echo(EchoRequest echoRequest) throws RemoteException;
}
