import java.io.IOException;
import java.io.Writer;

public class Client1 {

    public static void printPerson(Writer out, Person person) throws IOException {
        out.write(person.first);
        out.write(" ");
        if (person.middle != null) {
            out.write(person.middle);
            out.write(" ");
        }
        out.write(person.last);
    }

}
