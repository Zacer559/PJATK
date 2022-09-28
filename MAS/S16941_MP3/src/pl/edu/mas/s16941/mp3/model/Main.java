package pl.edu.mas.s16941.mp3.model;

import pl.edu.mas.s16941.mp3.exception.ClientTypeException;
import pl.edu.mas.s16941.mp3.exception.GasTypeException;
import pl.edu.mas.s16941.mp3.exception.ModelCreationException;
import pl.edu.mas.s16941.mp3.model._abstract.Person;
import pl.edu.mas.s16941.mp3.model._abstract.StandardPerson;
import pl.edu.mas.s16941.mp3.model._abstract.VIPPerson;
import pl.edu.mas.s16941.mp3.model.dynamic.Client;
import pl.edu.mas.s16941.mp3.model.dynamic.ClientType;
import pl.edu.mas.s16941.mp3.model.multi.CityCar;
import pl.edu.mas.s16941.mp3.model.multi.ElectricCar;
import pl.edu.mas.s16941.mp3.model.multi.SportsCar;
import pl.edu.mas.s16941.mp3.model.multiAspect.Gas;
import pl.edu.mas.s16941.mp3.model.multiAspect.GasType;
import pl.edu.mas.s16941.mp3.model.multiAspect.Vehicle;
import pl.edu.mas.s16941.mp3.model.multiAspect2.FuelType;
import pl.edu.mas.s16941.mp3.model.overlapping.Tire;
import pl.edu.mas.s16941.mp3.model.overlapping.TireType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ModelCreationException, ClientTypeException, GasTypeException {
        //MULTI
        List<CityCar> allCityCar = new ArrayList<>();
        allCityCar.add(new ElectricCar("aa", "bb", "123", Calendar.getInstance(), 0.0, false, 'c'));
        allCityCar.add(new ElectricCar("aa", "bb", "123", Calendar.getInstance(), 0.0, false, 'c'));
        List<SportsCar> allSportsCar = new ArrayList<>();
        allSportsCar.add(new ElectricCar("cc", "dd", "123", Calendar.getInstance(), 0.0, true, 'c'));
        allSportsCar.add(new ElectricCar("ee", "ff", "123", Calendar.getInstance(), 0.0, true, 'c'));
        for (SportsCar a : allSportsCar) {
            System.out.println(a.isCabrio());
        }
        for (CityCar d : allCityCar) {
            System.out.println(d.getVehicleSegment());
        }
        // OVERLAPPING
        Tire tire1 = new Tire("Pirelli P0", 225, 65, 20, EnumSet.allOf(TireType.class), "A", "B");
        Tire tire2 = new Tire("Michelin Pilot Sport", 250, 30, 21, EnumSet.of(TireType.SUMMER), "A", "B");
        System.out.println(tire1);
        System.out.println(tire2);
        // DYNAMIC
        Client c1 = new Client("Adam", "Kowalski", ClientType.VIP, 1, "A");
        System.out.println(c1);
        c1.changeToStandard(1);
        System.out.println(c1);
        c1.changeToVIP("B");
        System.out.println(c1);
        // ABSTRACT CLASS AND POLYMORPHIC METHOD INVOCATION
        Person p1 = new VIPPerson("Ala", "Kakao");
        Person p2 = new StandardPerson("Franek", "Kimono");
        System.out.println(p1.applyDiscount(200));
        System.out.println(p2.applyDiscount(200));
        //MULTI ASPECT INHERITANCE
        Vehicle veh1 = new Vehicle("BMW", "s111", Calendar.getInstance());
        pl.edu.mas.s16941.mp3.model.multiAspect.FuelType t = new Gas(veh1, GasType.PETROL, "12.0L/100km");
        System.out.println(veh1.getFuelType());
        System.out.println(t.getVehicle());
        //MULTI ASPECT INHERITANCE SECOND METHOD
        pl.edu.mas.s16941.mp3.model.multiAspect2.Vehicle veh2 = new pl.edu.mas.s16941.mp3.model.multiAspect2.Vehicle("Audi", "222", Calendar.getInstance(), FuelType.GAS, GasType.DIESEL, "12.0L/100km", "100kwh", "12kwh/100km");
        System.out.println(veh2);
    }
}
