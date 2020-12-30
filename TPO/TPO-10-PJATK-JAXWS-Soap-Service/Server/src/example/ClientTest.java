package example;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientTest {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final String urlString = "http://localhost:8888/ws/person?wsdl";
    private static final String localPart = "PersonServiceImplService";
    private static final String namespace = "http://example/";
    //People data
    private static final int id1 = 1;
    private static final int id2 = 2;
    private static final String firstName1 = "Jacek";
    private static final String firstName2 = "Franek";
    private static final String lastName1 = "Kowalski";
    private static final String lastName2 = "Nowak";
    private static final String birtDate1 = "1980-12-20";
    private static final String birtDate2 = "1970-11-11";

    private PersonServiceI initialize() throws MalformedURLException {
        URL wsdlURL = new URL(urlString);
        QName qname = new QName(namespace, localPart);
        Service service = Service.create(wsdlURL, qname);
        return service.getPort(PersonServiceI.class);
    }


    @org.junit.jupiter.api.BeforeEach
    void setUp() throws MalformedURLException, ParseException {
        PersonServiceI personService = initialize();
        //create person one
        Person p1 = new Person();
        p1.setId(id1);
        p1.setFirstName(firstName1);
        p1.setLastName(lastName1);
        p1.setBirthDate(formatter.parse(birtDate1));
        //create person two
        Person p2 = new Person();
        p2.setId(id2);
        p2.setFirstName(firstName2);
        p2.setLastName(lastName2);
        p2.setBirthDate(formatter.parse(birtDate2));
        //adding persons
        assertTrue(personService.addPerson(p1));
        assertTrue(personService.addPerson(p2));


    }

    @org.junit.jupiter.api.Test
    void main() throws MalformedURLException, ParseException {
        PersonServiceI personService = initialize();

        //checking that adding was successful
        // checking that we have two people
        assertEquals(2, personService.getAllPersons().length);
        //person 1
        Person p1 = personService.getPersonByIndex(1);
        assertEquals(id1, p1.getId());
        assertEquals(firstName1, p1.getFirstName());
        assertEquals(lastName1, p1.getLastName());
        assertEquals(formatter.parse(birtDate1), p1.getBirthDate());
        //person 2
        Person p2 = personService.getPersonByIndex(2);
        assertEquals(id2, p2.getId());
        assertEquals(firstName2, p2.getFirstName());
        assertEquals(lastName2, p2.getLastName());
        assertEquals(formatter.parse(birtDate2), p2.getBirthDate());

        //checking getByLastName
        Person[] getByLastName = personService.getPersonByLastName(lastName1);
        assertEquals(1, getByLastName.length);
        assertEquals(id1, getByLastName[0].getId());
        assertEquals(firstName1, getByLastName[0].getFirstName());
        assertEquals(lastName1, getByLastName[0].getLastName());
        assertEquals(formatter.parse(birtDate1), getByLastName[0].getBirthDate());

        //checking by birthdate
        Person[] getByBirthdate = personService.getPersonByBirthDate(formatter.parse(birtDate2));
        assertEquals(1, getByBirthdate.length);
        assertEquals(id2, getByBirthdate[0].getId());
        assertEquals(firstName2, getByBirthdate[0].getFirstName());
        assertEquals(lastName2, getByBirthdate[0].getLastName());
        assertEquals(formatter.parse(birtDate2), getByBirthdate[0].getBirthDate());


    }

    @org.junit.jupiter.api.AfterEach
    void setDown() throws MalformedURLException {
        PersonServiceI personService = initialize();

        // deleting persons
        assertTrue(personService.deletePerson(1));
        assertTrue(personService.deletePerson(2));
        assertEquals(0, personService.getAllPersons().length);

    }
}