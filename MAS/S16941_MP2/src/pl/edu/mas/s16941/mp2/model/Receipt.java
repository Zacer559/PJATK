package pl.edu.mas.s16941.mp2.model;

import pl.edu.mas.s16941.mp2.exceptions.ModelCreationException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Receipt {
    private static int recordsNo = 0;
    private static List<Receipt> receipts = new ArrayList<>();
    private int receiptNumber = 0;
    private Calendar dateOfIssue;
    private List<TireChange> tireChanges = new ArrayList<TireChange>();

    public Receipt(Calendar dateOfIssue) throws ModelCreationException {
        setDateOfIssue(dateOfIssue);
        receiptNumber = ++recordsNo;
        receipts.add(this);
    }

    public static List<Receipt> getReceipts() {
        return Collections.unmodifiableList(receipts);
    }

    public void setDateOfIssue(Calendar dateOfIssue) throws ModelCreationException {
        if (dateOfIssue == null) throw new ModelCreationException("date of issue cannot be null");
        this.dateOfIssue = dateOfIssue;
    }

    public void addTireChange(TireChange tireChange) {
        if (!tireChanges.contains(tireChange) && tireChange != null) {
            tireChanges.add(tireChange);
            tireChange.setReceipt(this);
        }
    }

    public void removeTireChange(TireChange tireChange) {
        if (tireChanges.contains(tireChange)) {
            tireChanges.remove(tireChange);
            tireChange.setReceipt(null);
        }
    }

    public String getdateOfIssueString() {
        return String.format("%1$te.%1$tm.%1$tY", this.dateOfIssue);
    }

    public String toString() {
        StringBuilder ret = new StringBuilder("Receipt nr " + receiptNumber + " data of issue: " + this.getdateOfIssueString() + "\n");
        for (TireChange tireChange : tireChanges) {
            ret.append(tireChange.toString()).append("\n");
        }
        return ret.toString();
    }


}
