public class Person {
    public final String last;

    public final String first;

    public final String middle;

    public Person(String last, String first, String middle) {
        this.last = last;
        this.first = first;
        this.middle = middle;
    }

    //All client classes are doing the almost same so they are almost useless
    //overriding toString and creating toStringWithComaMethod to cover all formats we are using
    //eventually we can create IPerson interface with toString(boolean withComa) method
    //and implement that in class Person using if statement as below:
         // @Override
         // public String toString(boolean withComa) {
         // if (withComa)
              //  return ((last == null ? "" : (last + ", ")) + (first == null ? "" : (first)) + (middle == null ? "" : " " + middle));
         // else
             //    return ((first == null ? "" : (first + " ")) + (middle == null ? "" : (middle + " ")) + (last == null ? "" : last));
         // }

    @Override
    public String toString() {
        return ((first == null ? "" : (first + " ")) + (middle == null ? "" : (middle + " ")) + (last == null ? "" : last));
    }

    public String toStringWithComa() {
        return ((last == null ? "" : (last + ", ")) + (first == null ? "" : (first)) + (middle == null ? "" : " " + middle));
    }
}