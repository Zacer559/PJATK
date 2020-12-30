// The Person class has multiple clients, but all of the clients are in 
// one file for convenience.  Imagine them as non-test methods in separate 
// client classes.

import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class PersonClientsTest {

    @Test
    public void testClients() throws IOException {
        Person bobSmith = new Person("Smith", "Bob", null);
        Person jennyJJones = new Person("Jones", "Jenny", "J");

        StringWriter out = new StringWriter();
        Client1.printPerson(out, bobSmith);
        assertEquals("Bob Smith", out.toString());


        out = new StringWriter();
        Client1.printPerson(out, jennyJJones);
        assertEquals("Jenny J Jones", out.toString());

        assertEquals("Smith, Bob", Client2.formatPerson(bobSmith));
        assertEquals("Jones, Jenny J", Client2.formatPerson(jennyJJones));

        out = new StringWriter();
        Client3.display(out, bobSmith);
        assertEquals("Smith, Bob", out.toString());

        out = new StringWriter();
        Client3.display(out, jennyJJones);
        assertEquals("Jones, Jenny J", out.toString());

        assertEquals("Smith, Bob", Client4.toString(bobSmith));
        assertEquals("Jones, Jenny J", Client4.toString(jennyJJones));


        //Added to check that our new methods are working as it should
        assertEquals("Bob Smith", bobSmith.toString());
        assertEquals("Jenny J Jones", jennyJJones.toString());
        assertEquals("Smith, Bob", bobSmith.toStringWithComa());
        assertEquals("Jones, Jenny J", jennyJJones.toStringWithComa());

    }
}
