package pl.edu.mas.s16941.mp3.model.multiAspect;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public class Electric extends FuelType {
    String batterySize;
    String powerConsumption;

    public Electric(Vehicle vehicle, String batterySize, String powerConsumption) throws ModelCreationException {
        super(vehicle);
        setBatterySize(batterySize);
        setPowerConsumption(powerConsumption);
    }

    public String getBatterySize() {
        return batterySize;
    }

    public void setBatterySize(String batterySize) throws ModelCreationException {
        if (batterySize == null || batterySize.isEmpty())
            throw new ModelCreationException("Battery size cannot be empty or null");
        this.batterySize = batterySize;
    }

    public String getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(String powerConsumption) throws ModelCreationException {
        if (powerConsumption == null || powerConsumption.isEmpty())
            throw new ModelCreationException("Power consumption cannot be empty or null");
        this.powerConsumption = powerConsumption;
    }

    @Override
    public String toString() {
        return "Electric{" +
                "batterySize='" + batterySize + '\'' +
                ", powerConsumption='" + powerConsumption + '\'' +
                '}';
    }
}
