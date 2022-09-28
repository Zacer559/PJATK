package pl.edu.mas.s16941.mp4.unique;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

import java.util.HashSet;
import java.util.Set;

public class Car {
    private String mark;
    private String model;
    private String serialNumber; //unique
    private static Set<String> serialNumbers = new HashSet<String>();

    public Car(String mark, String model, String serialNumber) throws ModelValidationException {
        setMark(mark);
        setModel(model);
        setSerialNumber(serialNumber);
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) throws ModelValidationException {
        if (mark == null || mark.isEmpty()) throw new ModelValidationException("Mark of car cannot be null");
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws ModelValidationException {
        if (model == null || model.isEmpty()) throw new ModelValidationException("Model of car cannot be null");
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    private void setSerialNumber(String serialNumber) throws ModelValidationException {
        if (serialNumber == null || serialNumber.isEmpty()) throw new ModelValidationException("Serial number of car cannot be null");
        if (isAlreadyInSerialNumber(serialNumber))  throw new ModelValidationException("Car with specified serial number already exists");
        this.serialNumber = serialNumber;
        serialNumbers.add(serialNumber);
    }
    public void changeSerialNumber(String serialNumber) throws ModelValidationException {
        if (serialNumber == null || serialNumber.isEmpty()) throw new ModelValidationException("Serial number of car cannot be null");
        if (isAlreadyInSerialNumber(serialNumber))  throw new ModelValidationException("Car with specified serial number already exists");
        serialNumbers.remove(this.serialNumber);
        this.serialNumber = serialNumber;
        serialNumbers.add(serialNumber);
    }

    private static boolean isAlreadyInSerialNumber(String serialNumber){
        return serialNumbers.contains(serialNumber);
    }
}
