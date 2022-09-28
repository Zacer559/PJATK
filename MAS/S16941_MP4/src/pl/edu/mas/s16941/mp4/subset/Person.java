package pl.edu.mas.s16941.mp4.subset;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

public class Person extends ObjectPlus4{

    private String name, surname;

    public Person(String name, String surname) throws ModelValidationException {
        super();
        setName(name);
        setSurname(surname);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelValidationException {
        if (name == null || name.isEmpty()) throw new ModelValidationException("Name of person cannot be null or empty");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws ModelValidationException {
        if (surname == null || surname.isEmpty()) throw new ModelValidationException("Surname of person cannot be null or empty");
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public static final String roleParticipatesIn = "participates in";
    public static final String roleOrganizes = "organizes";
}
