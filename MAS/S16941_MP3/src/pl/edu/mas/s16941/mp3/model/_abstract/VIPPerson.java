package pl.edu.mas.s16941.mp3.model._abstract;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public class VIPPerson extends Person {
    Double discount = 0.2;

    public VIPPerson(String name, String surname) throws ModelCreationException {
        super(name, surname);
    }

    @Override
    public double applyDiscount(double amount) {
        return amount * (1 - discount);
    }
}
