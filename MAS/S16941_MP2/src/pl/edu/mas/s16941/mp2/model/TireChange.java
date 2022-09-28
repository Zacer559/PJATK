package pl.edu.mas.s16941.mp2.model;

import pl.edu.mas.s16941.mp2.exceptions.ModelCreationException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TireChange {
    private static int recordsNo = 0;
    private static List<TireChange> tireChanges = new ArrayList<>();
    private int id = 0;
    private Calendar creationDate;
    private Receipt receipt;
    private WorkerTireChange workerTireChange;
    public TireChange() {
        tireChanges.add(this);
        id = ++recordsNo;
    }

    public TireChange(Calendar creationDate) throws ModelCreationException {
        setCreationDate(creationDate);
        tireChanges.add(this);
        id = ++recordsNo;
    }

    public void setCreationDate(Calendar creationDate) throws ModelCreationException {
        if (creationDate == null)
            throw new ModelCreationException("Creation date cannot be null");
        this.creationDate = creationDate;
    }

    public void setReceipt(Receipt receipt) {
        if (this.receipt == receipt) return;
        if (this.receipt != null) {
            this.receipt.removeTireChange(this);
        }
        this.receipt = receipt;
        if (receipt != null) {
            receipt.addTireChange(this);
        }
    }
    public void setTireChange(WorkerTireChange workerTireChange) throws ModelCreationException {
        if (this.workerTireChange == workerTireChange) return;
        if (this.workerTireChange ==null ) throw new ModelCreationException("worker Tire Change cannot be null");
        this.workerTireChange=workerTireChange;
        this.workerTireChange.setTireChange(this);
    }

    public String getCreationDateString() {
        if (creationDate == null) {
            return "none";
        } else {
            return String.format("%1$te.%1$tm.%1$tY", this.creationDate);
        }
    }

    public String toString() {
        return "TireChange:\tid: " + id + ", creation date: " + this.getCreationDateString();
    }

}
