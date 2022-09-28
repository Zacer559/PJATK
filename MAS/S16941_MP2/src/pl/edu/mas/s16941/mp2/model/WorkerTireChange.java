package pl.edu.mas.s16941.mp2.model;

import pl.edu.mas.s16941.mp2.exceptions.ModelCreationException;

import java.util.*;

public class WorkerTireChange {

    private Calendar startDate;
    private Calendar endDate;

    // association with attribiute: many workers one tire change
    // attriubiute start date
    private List<Worker> workers = new ArrayList<>();
    private TireChange tireChange;

    public WorkerTireChange(Calendar startDate, Worker[] worker, TireChange tireChange) throws ModelCreationException {
       setStartDate(startDate);
       setTireChange(tireChange);
        workers.addAll(Arrays.asList(worker));
    }

    public void setEndDate(Calendar endDate) throws ModelCreationException {
        if (endDate == null) throw new ModelCreationException("enddate cannot be null");
        this.endDate = endDate;
    }
    public void setStartDate(Calendar startDate) throws ModelCreationException {
        if (startDate == null) throw new ModelCreationException("startdate cannot be null");
        this.startDate = startDate;
    }

    public void addWorker(Worker worker) {
        if (!workers.contains(worker)) {
            workers.add(worker);
            worker.setTireChange(this);
        }
    }

    public void removeWorker(Worker worker) {
        if (workers.contains(worker)) {
            workers.remove(worker);
            worker.setTireChange(null);
        }
    }

    public void setTireChange(TireChange tireChange) throws ModelCreationException {
        if(tireChange==null) throw new ModelCreationException("tireChange cannot be null");
        this.tireChange = tireChange;
    }

    public List<Worker> getWorkers() {
        return Collections.unmodifiableList(workers);
    }

    public String getStartDateString() {
        return String.format("%1$te.%1$tm.%1$tY", this.startDate);
    }

    public String toString() {
        StringBuilder ret = new StringBuilder("Workers with started tire change : " + this.getStartDateString() + "\n" + tireChange.toString() + "\n");
        for (Worker worker : this.getWorkers()) {
            ret.append(worker).append("\n");
        }
        return ret.toString();
    }


}
