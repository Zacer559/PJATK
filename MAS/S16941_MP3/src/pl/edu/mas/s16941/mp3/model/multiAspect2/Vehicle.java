package pl.edu.mas.s16941.mp3.model.multiAspect2;

import pl.edu.mas.s16941.mp3.exception.GasTypeException;
import pl.edu.mas.s16941.mp3.exception.ModelCreationException;
import pl.edu.mas.s16941.mp3.model.multiAspect.GasType;

import java.util.Calendar;

public class Vehicle {
    //PETROL
    GasType gasType;
    String fuelConsumption;
    // ELECTRIC
    String batterySize;
    String powerConsumption;
    private FuelType fuelType;
    private String name, serialNumber;
    private Calendar productionDate;

    public Vehicle(String name, String serialNumber, Calendar productionDate, FuelType fuelType, GasType gasType, String fuelConsumption, String batterySize, String powerConsumption) throws ModelCreationException, GasTypeException {
        setName(name);
        setSerialNumber(serialNumber);
        setProductionDate(productionDate);
        setPetrolType(fuelType);
        if (isPetrol()) {
            setGasType(gasType);
            setFuelConsumption(fuelConsumption);
        } else {
            setBatterySize(batterySize);
            setPowerConsumption(powerConsumption);
        }
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


    public FuelType getPetrolType() {
        return fuelType;
    }

    public void setPetrolType(FuelType fuelType) throws ModelCreationException {
        if (fuelType == null) throw new ModelCreationException("Petrol type cannot be null");
        this.fuelType = fuelType;
    }

    public GasType getGasType() throws GasTypeException {
        if (!isPetrol()) throw new GasTypeException("Cannot return gasType for non petrol vehicle.");
        return gasType;
    }

    public void setGasType(GasType gasType) throws ModelCreationException, GasTypeException {
        if (!isPetrol()) throw new GasTypeException("Cannot set gasType for non petrol vehicle.");
        if (gasType == null) throw new ModelCreationException("Gas type cannot be null");
        this.gasType = gasType;
    }

    public String getFuelConsumption() throws GasTypeException {
        if (!isPetrol()) throw new GasTypeException("Cannot return fuelConsumption for non petrol vehicle.");
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) throws ModelCreationException, GasTypeException {
        if (!isPetrol()) throw new GasTypeException("Cannot set fuel consumption for non petrol vehicle.");
        if (fuelConsumption == null || fuelConsumption.isEmpty())
            throw new ModelCreationException("Fuel consumption cannot be null or empty.");
        this.fuelConsumption = fuelConsumption;
    }

    public String getBatterySize() throws GasTypeException {
        if (isPetrol()) throw new GasTypeException("Cannot return batterySize for non electric vehicle.");
        return batterySize;
    }

    public void setBatterySize(String batterySize) throws ModelCreationException, GasTypeException {
        if (isPetrol()) throw new GasTypeException("Cannot set battery size for non electric vehicle.");
        if (batterySize == null || batterySize.isEmpty())
            throw new ModelCreationException("batterySize cannot be null or empty.");
        this.batterySize = batterySize;
    }

    public String getPowerConsumption() throws GasTypeException {
        if (isPetrol()) throw new GasTypeException("Cannot return power consumption for non electric vehicle.");
        return powerConsumption;
    }

    public void setPowerConsumption(String powerConsumption) throws ModelCreationException, GasTypeException {
        if (isPetrol()) throw new GasTypeException("Cannot set gasType for non electric vehicle.");
        if (powerConsumption == null || powerConsumption.isEmpty())
            throw new ModelCreationException("Power consumption cannot be null or empty.");
        this.powerConsumption = powerConsumption;
    }

    public boolean isPetrol() {
        return fuelType.equals(FuelType.GAS);
    }

    @Override
    public String toString() {
        if (isPetrol()) {
            return "Vehicle{" +
                    "fuelType=" + fuelType +
                    ", name='" + name + '\'' +
                    ", serialNumber='" + serialNumber + '\'' +
                    ", productionDate=" + productionDate.getTime() +
                    ", gasType=" + gasType +
                    ", fuelConsumption='" + fuelConsumption + '\'' +
                    '}';
        } else {
            return "Vehicle{" +
                    "fuelType=" + fuelType +
                    ", name='" + name + '\'' +
                    ", serialNumber='" + serialNumber + '\'' +
                    ", productionDate=" + productionDate.getTime() +
                    ", batterySize='" + batterySize + '\'' +
                    ", powerConsumption='" + powerConsumption + '\'' +
                    '}';
        }
    }
}
