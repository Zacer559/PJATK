package pl.edu.mas.s16941.mp3.model.multi;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public interface CityCar {
    char getVehicleSegment();

    void setVehicleSegment(char vehicleSegment) throws ModelCreationException;
}
