package pl.edu.mas.s16941.mp4.bag;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Worker {
    private String name, surname;
    private Set<Repair> repairs = new HashSet<>();

    public Worker(String name, String surname) throws ModelValidationException {
        setName(name);
        setSurname(surname);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelValidationException {
        if (name == null || name.isEmpty())
            throw new ModelValidationException("Name of person cannot be null or empty");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws ModelValidationException {
        if (surname == null || surname.isEmpty())
            throw new ModelValidationException("Surname of person cannot be null or empty");
        this.surname = surname;
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
        return "Worker{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", repairs=" + repairs +
                '}';
    }
}
