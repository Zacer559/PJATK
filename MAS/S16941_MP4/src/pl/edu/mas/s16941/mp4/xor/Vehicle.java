package pl.edu.mas.s16941.mp4.xor;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

public abstract class Vehicle {
    String name;

    public Vehicle(String name) throws ModelValidationException {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelValidationException {
        if (name == null || name.isEmpty())
            throw new ModelValidationException("Name of vehicle cannot be null or empty");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                '}';
    }
}
