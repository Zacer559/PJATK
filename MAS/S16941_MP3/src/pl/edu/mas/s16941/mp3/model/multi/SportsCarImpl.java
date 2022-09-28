package pl.edu.mas.s16941.mp3.model.multi;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

import java.util.Calendar;

public class SportsCarImpl extends Car implements SportsCar {
    private boolean isCabrio;

    public SportsCarImpl(String mark, String model, String serialNumber, Calendar productionDate, Double fuelConsumption, boolean isCabrio) throws ModelCreationException {
        super(mark, model, serialNumber, productionDate, fuelConsumption);
        setCabrio(isCabrio);
    }

    @Override
    public boolean isCabrio() {
        return isCabrio;
    }

    @Override
    public void setCabrio(boolean isCabrio) {
        this.isCabrio = isCabrio;
    }
}
