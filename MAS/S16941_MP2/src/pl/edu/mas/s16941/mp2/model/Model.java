package pl.edu.mas.s16941.mp2.model;

import pl.edu.mas.s16941.mp2.exceptions.ModelCreationException;
import pl.edu.mas.s16941.mp2.exceptions.TireNotFound;

import java.util.*;

public class Model {
    private static Map<String, List<Tire>> tires = new HashMap<>(); // qualified association
    private static List<Model> models = new ArrayList<>();
    String name, serialNumber;
    Calendar productionDate;
    Mark mark; //composition

    public Model(String serialNumber, Mark mark, String name, Calendar productionDate) throws ModelCreationException {
        setSerialNumber(serialNumber);
        setMark(mark);
        setName(name);
        setProductionDate(productionDate);
        setMark(mark);
        models.add(this);
    }

    public static void removeModel(Model model) throws Exception {
        if (model != null && model.getMark().hasModel(model) && models.contains(model)) {
            models.remove(model);
            model.getMark().removeModel(model);
        }
    }

    public static List<Model> getModels() {
        return models;
    }

    public static List<Tire> getTires(String name) throws Exception {
        if (!tires.containsKey(name)) {
            throw new TireNotFound("Tire not found:" + name);
        }
        return tires.get(name);
    }

    public Mark getMark() {
        return this.mark;
    }

    //composition service
    public void setMark(Mark mark) throws ModelCreationException {
        if (mark == null)
            throw new ModelCreationException("Mark cannot be null");
        this.mark = mark;
        mark.addModel(this);
    }

    public void setName(String name) throws ModelCreationException {
        if (name == null || name.isEmpty())
            throw new ModelCreationException("name cannot be null or empty");
        this.name = name;
    }

    public void setSerialNumber(String serialNumber) throws ModelCreationException {
        if (serialNumber == null || serialNumber.isEmpty())
            throw new ModelCreationException("Serial number cannot be null or empty");
        this.serialNumber = serialNumber;
    }

    // qualified association service

    public void setProductionDate(Calendar productionDate) throws ModelCreationException {
        if (serialNumber == null)
            throw new ModelCreationException("Production Date cannot be null");
        this.serialNumber = serialNumber;
    }

    public void addTire(Tire tire) {
        if (!tires.containsKey(tire.getName())) {
            List<Tire> a = new ArrayList<Tire>();
            a.add(tire);
            tires.put(tire.name, a);
        } else {
            tires.get(tire.getName()).add(tire);
        }
        tire.setModelFromModel(this);

    }

    public void removeTire(Tire tire) {
        if (tires.containsKey(tire.getName())) {
            if (tires.get(tire.name).size() < 2) {
                tires.remove(tire.getName());
                tire.setModel(null);
            } else {
                tires.get(tire.name).remove(tire);
            }
        }
    }

    public String getProductionDateString() {
        return String.format("%1$tY", this.productionDate);
    }


    public String toString() {
        return "Model:\tserial number: " + serialNumber + ", mark: " + mark.getName() + ", name: " + name + ", production date: " + getProductionDateString();
    }

}
