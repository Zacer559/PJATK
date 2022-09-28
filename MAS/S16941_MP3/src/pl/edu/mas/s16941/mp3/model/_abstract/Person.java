package pl.edu.mas.s16941.mp3.model._abstract;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public abstract class Person {
    private String name, surname;

    public Person(String name, String surname) throws ModelCreationException {
        setName(name);
        setSurname(surname);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelCreationException {
        if (name == null || name.isEmpty()) throw new ModelCreationException("Name of person cannot be null or empty.");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws ModelCreationException {
        if (surname == null || surname.isEmpty())
            throw new ModelCreationException("Surname of person cannot be null or empty.");
        this.surname = surname;
    }

    public double applyDiscount(double amount) {
        return amount;
    }
}
