package pl.edu.mas.s16941.mp4;

import pl.edu.mas.s16941.mp4.attribiute.Product;
import pl.edu.mas.s16941.mp4.bag.Repair;
import pl.edu.mas.s16941.mp4.ordered.Client;
import pl.edu.mas.s16941.mp4.ordered.Receipt;
import pl.edu.mas.s16941.mp4.ownExample.Vehicle;
import pl.edu.mas.s16941.mp4.subset.Party;
import pl.edu.mas.s16941.mp4.subset.Person;
import pl.edu.mas.s16941.mp4.unique.Car;
import pl.edu.mas.s16941.mp4.xor.Boat;

import static pl.edu.mas.s16941.mp4.subset.Party.roleOrganizedBy;
import static pl.edu.mas.s16941.mp4.subset.Party.roleParticipantsOf;
import static pl.edu.mas.s16941.mp4.subset.Person.roleOrganizes;
import static pl.edu.mas.s16941.mp4.subset.Person.roleParticipatesIn;

public class Main {

    public static void main(String[] args) throws Exception {
        // ATTRIBUTE
        Product p = new Product("Tire", 100, 200);
        //   p.setSellingPrice(1);
        //  p.setBuyingPrice(200);
        p.setSellingPrice(500);

        // UNIQUE
        Car c = new Car("Audi", "RS6", "WBA555");
        // Car c1 = new Car("BMW","M4","WBA555");
        c.changeSerialNumber("WBA556");

        // SUBSET
        Person person = new Person("Jacek", "Sasin");
        Party party = new Party("Super duper party");
        person.addLink(roleParticipatesIn, roleParticipantsOf, party);
        if (person.isLink(roleParticipatesIn, party)) {
            person.addLink(roleOrganizes, roleOrganizedBy, party);
        } else {
            System.out.println("No super link for the role: " + roleParticipatesIn);
        }
        person.showLinks(roleParticipatesIn, System.out);
        person.showLinks(roleOrganizes, System.out);
        //BAG
        var cb1 = new pl.edu.mas.s16941.mp4.bag.Car("BMW","7");
        var wb1 = new pl.edu.mas.s16941.mp4.bag.Worker("Franek","Kimono");
        var rp = new Repair(200,cb1,wb1);
        System.out.println(cb1);
        // ORDERED
        var cl1 = new Client("Janek","kowalski");
        var rc1 = new Receipt(2d,cl1);
        var rc2 = new Receipt(3d,cl1);
        System.out.println(cl1);
        // XOR
        pl.edu.mas.s16941.mp4.xor.Vehicle boat = new Boat("Very good boat",2.0);
        System.out.println(boat);


        // OWN
        Vehicle v = new Vehicle("WBA18273647582950","Mercedes","E klasa");
        //Vehicle v = new Vehicle("fdsfsdf","Mercedes","E klasa");
    }
}

