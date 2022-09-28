package pl.edu.mas.s16941.mp2.tests;

import org.junit.jupiter.api.BeforeEach;
import pl.edu.mas.s16941.mp2.exceptions.ModelCreationException;
import pl.edu.mas.s16941.mp2.model.*;

import java.util.Calendar;

class TireServiceTest {
    Calendar cal1, cal2, cal3;
    Receipt receipt1, receipt2, receipt3;
    TireChange tireChange1, tireChange2, tireChange3;
    Worker worker1, worker2, worker3;
    Mark mark1, mark2, mark3;
    Model model1, model2, model3, model4, model5;

    @BeforeEach
    public void before() throws ModelCreationException {
        cal1 = Calendar.getInstance();
        cal2 = Calendar.getInstance();
        cal3 = Calendar.getInstance();
        cal1.set(2021, Calendar.FEBRUARY, 3);
        cal2.set(2021, Calendar.DECEMBER, 8);
        cal3.set(2015, Calendar.JUNE, 76);
        System.out.println(cal1.getTime());
        receipt1 = new Receipt(cal1);
        receipt2 = new Receipt(cal2);
        receipt3 = new Receipt(cal3);
        this.tireChange1 = new TireChange(cal1);
        tireChange2 = new TireChange();
        tireChange3 = new TireChange();


        cal1.set(Calendar.YEAR, 2014);
        cal1.set(Calendar.MONTH, 5);
        cal1.set(Calendar.DAY_OF_MONTH, 15);
        worker1 = new Worker("John", "Smith", cal1);
        cal1.set(Calendar.YEAR, 2018);
        cal1.set(Calendar.MONTH, 7);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        worker2 = new Worker("Alex", "Kowalski", cal1);
        cal1.set(Calendar.YEAR, 2001);
        cal1.set(Calendar.MONTH, 2);
        cal1.set(Calendar.DAY_OF_MONTH, 27);
        worker3 = new Worker("Kate", "Middleton", cal1);

        cal1.set(Calendar.YEAR, 2013);
        mark1 = new Mark("BMW");

        mark3 = new Mark("Audi");

        model1 = new Model("1", mark1, "320d", cal1);
        model3 = new Model("3", mark1, "520d", cal1);
        model4 = new Model("4", mark1, "750d", cal1);
        model5 = new Model("5", mark3, "R8", cal1);

    }


    @org.junit.jupiter.api.Test
    public void mainTest() throws Exception {


        //"Basic" Association       receipt 1 - * tireChanges
        System.out.println("###Basic Association###");
        // Many tire changes to one receipt
        tireChange1.setReceipt(receipt1);
        tireChange2.setReceipt(receipt1);
        tireChange3.setReceipt(receipt2);
        // Try to associate two receipts to one tireChange
        tireChange3.setReceipt(receipt2);
        tireChange3.setReceipt(receipt3);
        for (Receipt receipt : Receipt.getReceipts()) {
            System.out.println(receipt);
        }


        //Association with an attribute       tireChange 1 - * workers
        System.out.println("###Association with an attribute###");
        Worker[] arr1 = {worker1, worker2};
        Worker[] arr2 = {worker3};
        WorkerTireChange workerTireChange1 = new WorkerTireChange(cal1, arr1, tireChange1);
        WorkerTireChange workerTireChange2 = new WorkerTireChange(cal2, arr2, tireChange2);
        System.out.println(workerTireChange1);
        System.out.println(workerTireChange2);


        // Qualified association: 1 model - * Tires
        System.out.println("Qualified association");
        Tire cz1 = new Tire("111", "Pirelli P0");
        Tire cz2 = new Tire("222", "Michelin Pilot Sport Cup 2");
        model1.addTire(cz1);
        model1.addTire(cz1);
        model1.addTire(cz2);
        System.out.println(Model.getTires("Pirelli P0"));
        model1.removeTire(cz1);
        System.out.println(Model.getTires("Pirelli P0").get(0).getModel());
        System.out.println(Model.getTires("Michelin Pilot Sport Cup 2"));


        // Composition: 1 mark - * models
        System.out.println("###Composition###");
        model2 = new Model("8", mark3, "A8", cal1);
        // Removing mark - tire should be removed too
        System.out.println("Before removing mark");
        for (Model model : Model.getModels()) {
            System.out.println(model.toString());
        }
        Mark.removeMark(mark3);
        System.out.println("After removing mark");
        for (Model model : Model.getModels()) {
            System.out.println(model.toString());
        }

    }


}
