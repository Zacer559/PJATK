package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PersonServiceI {

    @WebMethod
    boolean addPerson(Person p);

    @WebMethod
    boolean deletePerson(int id);

    @WebMethod
    Person getPersonByIndex(int id);

    @WebMethod
    Person[] getAllPersons();

    @WebMethod
    Person[] getPersonByLastName(String lastName);

    @WebMethod
    Person[] getPersonByBirthDate(Date date);
}
