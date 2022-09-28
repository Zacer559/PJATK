package pl.edu.mas.s16941.mp3.model.multi;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

import java.util.Calendar;

public class Car {
    private String mark, model, serialNumber;
    private Calendar productionDate;
    private Double fuelConsumption;

    public Car(String mark, String model, String serialNumber, Calendar productionDate, Double fuelConsumption) throws ModelCreationException {
        setMark(mark);
        setModel(model);
        setSerialNumber(serialNumber);
        setProductionDate(productionDate);
        setFuelConsumption(fuelConsumption);
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) throws ModelCreationException {
        if (mark == null || mark.isEmpty()) throw new ModelCreationException("Mark cannot be empty or null");
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws ModelCreationException {
        if (model == null || model.isEmpty()) throw new ModelCreationException("Model cannot be empty or null");
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) throws ModelCreationException {
        if (serialNumber == null || serialNumber.isEmpty())
            throw new ModelCreationException("Serial number cannot be empty or null");
        this.serialNumber = serialNumber;
    }

    public Calendar getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Calendar productionDate) throws ModelCreationException {
        if (productionDate == null) throw new ModelCreationException("Production date cannot be empty or null");
        this.productionDate = productionDate;
    }

    public Double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Double fuelConsumption) throws ModelCreationException {
        if (fuelConsumption == null || fuelConsumption < 0)
            throw new ModelCreationException("Fuel consumption cannot be null or lower than 0");
        this.fuelConsumption = fuelConsumption;
    }
}
