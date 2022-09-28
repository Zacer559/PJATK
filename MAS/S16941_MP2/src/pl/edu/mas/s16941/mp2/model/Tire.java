package pl.edu.mas.s16941.mp2.model;

import pl.edu.mas.s16941.mp2.exceptions.ModelCreationException;

public class Tire {

    String serialNumber, name;
    private Model model;

    public Tire(String serialNumber, String name) throws ModelCreationException {
        setName(name);
        setSerialNumber(serialNumber);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) throws ModelCreationException {
        if (serialNumber == null || serialNumber.isEmpty())
            throw new ModelCreationException("Serial number cannot be null or empty");
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelCreationException {
        if (name == null || name.isEmpty()) throw new ModelCreationException("Serial number cannot be null or empty");
        this.name = name;
    }

    public Model getModel() {
        return this.model;
    }

    public void setModel(Model model) {
        if (this.model == model) return;
        if (this.model != null) {
            this.model.removeTire(this);
        }
        this.model = model;
        if (model != null) {
            model.addTire(this);
        }
    }

    public void setModelFromModel(Model model) {
        if (this.model == model) return;
        if (this.model != null) {
            this.model.removeTire(this);
        }
        this.model = model;
    }

    public String toString() {
        return "Tire: serial number: " + serialNumber + " name: " + name;
    }
}