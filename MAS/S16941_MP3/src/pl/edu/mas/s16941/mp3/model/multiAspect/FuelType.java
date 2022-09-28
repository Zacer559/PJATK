package pl.edu.mas.s16941.mp3.model.multiAspect;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public abstract class FuelType {
    private Vehicle vehicle;

    public FuelType(Vehicle vehicle) throws ModelCreationException {
        setVehicle(vehicle);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) throws ModelCreationException {
        if (vehicle == null) throw new ModelCreationException("Vehicle cannot be null");
        this.vehicle = vehicle;
        vehicle.setFuelTypeInternal(this);
    }

}
