import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AddingResponseObject extends UnicastRemoteObject implements IAdding {

    public AddingResponseObject() throws RemoteException {
        super();
    }

    @Override
    public AddingResponse sum(AddingRequest addingRequest) {
        System.out.println("Received numbers to add are: " + addingRequest.get_num1() + " and " + addingRequest.get_num2());
        return new AddingResponse(addingRequest.get_num1(), addingRequest.get_num2());
    }
}