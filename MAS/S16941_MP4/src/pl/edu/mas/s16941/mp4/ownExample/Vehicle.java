package pl.edu.mas.s16941.mp4.ownExample;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

public class Vehicle {
    private String vin;
    private String mark;
    private String model;
    private final static String VINRegex = "[A-HJ-NPR-Z0-9]{17}";
    public Vehicle(String vin, String mark, String model) throws ModelValidationException {
        setVin(vin);
        setMark(mark);
        setModel(model);
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) throws ModelValidationException {
        if (vin == null || vin.isEmpty()) throw new ModelValidationException("VIN of vehicle cannot be null");
        if(!vin.matches(VINRegex)) throw new ModelValidationException("Not valid VIN provided.");
        this.vin = vin;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) throws ModelValidationException {
        if (mark == null || mark.isEmpty()) throw new ModelValidationException("Mark of vehicle cannot be null");
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws ModelValidationException {
        if (model == null || model.isEmpty()) throw new ModelValidationException("Model of vehicle cannot be null");
        this.model = model;
    }

}
