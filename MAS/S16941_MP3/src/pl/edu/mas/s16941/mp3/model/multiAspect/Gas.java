package pl.edu.mas.s16941.mp3.model.multiAspect;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public class Gas extends FuelType {
    GasType type;
    String fuelConsumption;

    public Gas(Vehicle vehicle, GasType type, String fuelConsumption) throws ModelCreationException {
        super(vehicle);
        setType(type);
        setFuelConsumption(fuelConsumption);
    }

    public GasType getType() {
        return type;
    }

    public void setType(GasType type) throws ModelCreationException {
        if (type == null) throw new ModelCreationException("type cannot be null");
        this.type = type;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) throws ModelCreationException {
        if (fuelConsumption == null || fuelConsumption.isEmpty())
            throw new ModelCreationException("Fuel consumption cannot be null or empty");
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {
        return "Gas{" +
                "type=" + type +
                ", fuelConsumption='" + fuelConsumption + '\'' +
                '}';
    }
}
