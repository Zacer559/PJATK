package pl.edu.mas.s16941.mp3.model.multi;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

import java.util.Calendar;

public class CityCarImpl extends Car implements CityCar {
    private char vehicleSegment;

    public CityCarImpl(String mark, String model, String serialNumber, Calendar productionDate, Double fuelConsumption, char vehicleSegment) throws ModelCreationException {
        super(mark, model, serialNumber, productionDate, fuelConsumption);
        setVehicleSegment(vehicleSegment);
    }

    @Override
    public char getVehicleSegment() {
        return vehicleSegment;
    }

    @Override
    public void setVehicleSegment(char vehicleSegment) throws ModelCreationException {
        if (vehicleSegment == '\u0000') throw new ModelCreationException("Vehicle segment cannot be empty");
        this.vehicleSegment = vehicleSegment;
    }
}
