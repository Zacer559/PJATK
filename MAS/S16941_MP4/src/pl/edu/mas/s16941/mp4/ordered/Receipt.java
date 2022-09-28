package pl.edu.mas.s16941.mp4.ordered;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

public class Receipt {
    double cost;
    Client client;

    public Receipt(double cost, Client client) throws ModelValidationException {
       setCost(cost);
       setClient(client);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) throws ModelValidationException {
        if(cost<0) throw new ModelValidationException("Cost cannot be lower than 0") ;
        this.cost = cost;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) throws ModelValidationException {
        if (client == null) throw new ModelValidationException("Client cannot be null");
        if(this.client!=null) this.client.removeReceipt(this);
        this.client = client;
        client.addReceipt(this);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "cost=" + cost +
                ", client=" + client.getName() + " " + client.getSurname() +
                '}';
    }
}
