package pl.edu.mas.s16941.mp2.model;

import pl.edu.mas.s16941.mp2.exceptions.ModelCreationException;

import java.util.ArrayList;
import java.util.Calendar;

public class Worker {
    static int salary = 4000;
    private static int recordsNo = 0;
    private static ArrayList<Worker> workers = new ArrayList<>();
    int id = 0;
    String name, surname, phoneNumber;
    Calendar employmentDate;
    WorkerTireChange workerTireChange;

    public Worker(String name, String surname, Calendar employmentDate) throws ModelCreationException {
        setName(name);
        setSurname(surname);
        setEmploymentDate(employmentDate);
        id = ++recordsNo;
        workers.add(this);
    }

    public void setName(String name) throws ModelCreationException {
        if (name == null || name.isEmpty()) throw new ModelCreationException("Name cannot be null or empty");

        this.name = name;
    }

    public void setSurname(String surname) throws ModelCreationException {
        if (surname == null || surname.isEmpty()) throw new ModelCreationException("Surname cannot be null or empty");
        this.surname = surname;
    }

    public void setEmploymentDate(Calendar employmentDate) throws ModelCreationException {
        if (employmentDate == null) throw new ModelCreationException("emplotment date cannot be null");
        this.employmentDate = employmentDate;
    }

    public void setTireChange(WorkerTireChange workerTireChange) {
        if (this.workerTireChange == workerTireChange) return;
        if (this.workerTireChange != null) {
            this.workerTireChange.removeWorker(this);
        }
        this.workerTireChange = workerTireChange;
        if (workerTireChange != null) {
            workerTireChange.addWorker(this);
        }
    }

    public String getEmploymentDateString() {
        return String.format("%1$te.%1$tm.%1$tY", this.employmentDate);
    }


    @Override
    public String toString() {
        return "Worker:\t" + id + ": " + name + " " + surname + " - employment date: " + this.getEmploymentDateString() + ", phone number: " + phoneNumber + ", salary " + salary;
    }
}