import java.io.IOException;
import java.io.Writer;


public class Client3 {

    public static void display(Writer out, Person person) throws IOException {
        out.write(person.last);
        out.write(", ");
        out.write(person.first);
        if (person.middle != null) {
            out.write(" ");
            out.write(person.middle);
        }
    }

}
