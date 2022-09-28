package pl.edu.mas.s16941.mp4.ordered;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

import java.util.*;

public class Client {
    private String name, surname;
    private List<Receipt> receipts = new ArrayList<>();

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    public List<Receipt> getReceipts() {
        return Collections.unmodifiableList(receipts);
    }

    public void addReceipt(Receipt receipt) throws ModelValidationException {
        if (receipt == null) throw new ModelValidationException("Receipt cannot be null");
        receipts.add(receipt);
    }
    public void removeReceipt(Receipt receipt) throws ModelValidationException {
        if (receipt == null) throw new ModelValidationException("Receipt cannot be null");
        receipts.remove(receipt);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", receipts=" + receipts +
                '}';
    }
}
