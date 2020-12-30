import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAdding extends Remote {
    AddingResponse sum(AddingRequest addingRequest) throws RemoteException;
}
