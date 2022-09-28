package pl.edu.mas.s16941.mp3.model._abstract;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public class StandardPerson extends Person {
    public StandardPerson(String name, String surname) throws ModelCreationException {
        super(name, surname);
    }

    @Override
    public double applyDiscount(double amount) {
        return amount;
    }

}
