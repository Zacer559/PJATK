package pl.edu.mas.s16941.mp2.model;

import pl.edu.mas.s16941.mp2.exceptions.ModelCreationException;
import pl.edu.mas.s16941.mp2.exceptions.ModelDeleteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mark {


    private static List<Mark> marks = new ArrayList<>();
    // composition - model cannot exist without mark
    private String name;
    private List<Model> models = new ArrayList<>(); // composition  *

    public Mark(String name) throws ModelCreationException {
        setName(name);
        marks.add(this);
    }

    public static void removeMark(Mark mark) throws Exception {
        if (!marks.contains(mark)) throw new ModelDeleteException("Cannot remove null or not existing Mark");
        List<Model> copyOfModel = new ArrayList<Model>(mark.getModels());
        for (Model model : copyOfModel) {
            mark.removeModel(model);
        }
        marks.remove(mark);
    }

    public static List<Mark> getMarks() {
        return Collections.unmodifiableList(marks);
    }

    // composition service
    public List<Model> getModels() {
        return Collections.unmodifiableList(this.models);
    }

    public void addModel(Model model) throws ModelCreationException {
        if (!models.contains(model) && model != null) {
            models.add(model);
            model.setMark(this);
        }
    }

    public void removeModel(Model model) throws Exception {
        if (model == null || !hasModel(model)) {
            throw new ModelDeleteException("Cannot remove null or not existing Model");
        }
        Model.removeModel(model);
        this.models.remove(model);
    }

    public boolean hasModel(Model model) {
        return this.models.contains(model);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelCreationException {
        if (name == null || name.isEmpty())
            throw new ModelCreationException("name cannot be null or empty");
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Mark:\t " + name + "\n");
        for (Model model : models) result.append(model).append("\n");
        return result.toString();
    }
}