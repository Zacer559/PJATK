public class Main {

    public static void main(String[] args) {

        CareTaker careTaker = new CareTaker();
        Originator originator = new Originator();
        originator.setState("This is first state");
        careTaker.saveMemento(originator.createMemento());
        originator.setState("This is second state");
        careTaker.saveMemento(originator.createMemento());
        originator.setState("This is third state");
        careTaker.saveMemento(originator.createMemento());
        originator.restore(careTaker.getMemento(0));
        System.out.println(originator.getState());
        originator.restore(careTaker.getMemento(1));
        System.out.println(originator.getState());
        originator.restore(careTaker.getMemento(2));
        System.out.println(originator.getState());
    }

}


