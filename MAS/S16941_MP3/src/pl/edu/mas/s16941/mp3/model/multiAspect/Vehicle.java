package pl.edu.mas.s16941.mp3.model.multiAspect;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

import java.util.Calendar;

public class Vehicle {
    private String name, serialNumber;
    private Calendar productionDate;
    private FuelType fuelType;

    public Vehicle(String name, String serialNumber, Calendar productionDate) throws ModelCreationException {
        setName(name);
        setSerialNumber(serialNumber);
        setProductionDate(productionDate);
    }

    public Vehicle(String name, String serialNumber, Calendar productionDate, FuelType fuelType) throws ModelCreationException {
        setName(name);
        setSerialNumber(serialNumber);
        setProductionDate(productionDate);
        setFuelType(fuelType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelCreationException {
        if (name == null || name.isEmpty())
            throw new ModelCreationException("Name of Vehicle cannot be null or empty.");
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) throws ModelCreationException {
        if (serialNumber == null || serialNumber.isEmpty())
            throw new ModelCreationException("Serial number of vehicle cannot be empty or null");
        this.serialNumber = serialNumber;
    }

    public Calendar getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Calendar productionDate) throws ModelCreationException {
        if (productionDate == null)
            throw new ModelCreationException("Production date of vehicle cannot be empty or null");
        this.productionDate = productionDate;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) throws ModelCreationException {
        if (fuelType == null) throw new ModelCreationException("fuel type cannot be null");
        this.fuelType = fuelType;
        fuelType.setVehicle(this);
    }

    void setFuelTypeInternal(FuelType fuelType) throws ModelCreationException {
        if (fuelType == null) throw new ModelCreationException("fuel type cannot be null");
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", productionDate=" + productionDate.getTime() +
                ", fuelType=" + fuelType +
                '}';
    }
}
