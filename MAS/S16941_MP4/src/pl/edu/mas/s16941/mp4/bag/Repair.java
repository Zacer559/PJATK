package pl.edu.mas.s16941.mp4.bag;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

public class Repair {
    private double cost;
    private Car car;
    private Worker worker;

    public Repair(double cost, Car car, Worker worker) throws ModelValidationException {
        setCost(cost);
        setCar(car);
        setWorker(worker);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) throws ModelValidationException {
        if(cost<=0) throw new ModelValidationException("Cost of repair cannot be lower than 0");
        this.cost = cost;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) throws ModelValidationException {
        if(car==null) throw new ModelValidationException("Car cannot be null");
        this.car = car;
        car.addRepair(this);
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) throws ModelValidationException {
        if(worker==null) throw new ModelValidationException("Worker cannot be null");
        this.worker = worker;
        worker.addRepair(this);
    }

    @Override
    public String toString() {
        return "Repair{" +
                "cost=" + cost +
                ", car=" + car.getMark() + car.getModel()+
                ", worker=" + worker.getName() + worker.getSurname()+
                '}';
    }
}
