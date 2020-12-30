public class Client4 {

    public static String toString(Person person) {
        return person.last + ", " + person.first
                + ((person.middle == null) ? "" : " " + person.middle);
    }

}
