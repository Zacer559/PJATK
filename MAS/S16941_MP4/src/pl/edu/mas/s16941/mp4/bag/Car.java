package pl.edu.mas.s16941.mp4.bag;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Car {
    private String mark;
    private String model;
    private Set<Repair> repairs = new HashSet<>();

    public Car(String mark, String model) throws ModelValidationException {
        setMark(mark);
        setModel(model);
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) throws ModelValidationException {
        if (mark == null || mark.isEmpty()) throw new ModelValidationException("Mark of car cannot be null");
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws ModelValidationException {
        if (model == null || model.isEmpty()) throw new ModelValidationException("Model of car cannot be null");
        this.model = model;
    }

    public Set<Repair> getRepairs() {
        return Collections.unmodifiableSet(repairs);
    }

    public void addRepair(Repair rep) throws ModelValidationException {
        if (rep == null) throw new ModelValidationException("Repair cannot be null");
        repairs.add(rep);
    }

    @Override
    public String toString() {
        return "Car{" +
                "mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", repairs=" + repairs +
                '}';
    }
}
