import java.util.ArrayList;
import java.util.List;

public class User {

    private static int idCounter = 0;
    private final List<SomeMysteriousObject> ListOfUsers;
    private final int id;

    public User() {
        this.ListOfUsers = new ArrayList<>();
        this.id = ++idCounter;
    }

    public void takeFromPool(ObjectPool op) {

        ListOfUsers.add(op.takeObject());
        if (ListOfUsers.get(ListOfUsers.size() - 1) == null) {
            ListOfUsers.remove(ListOfUsers.size() - 1);
            System.out.println("User " + this.id + " object limit is reached (10).");
        }

    }

    public void returnToPool(ObjectPool op) {
        if (!ListOfUsers.isEmpty()) {
            op.addObject(ListOfUsers.get(ListOfUsers.size() - 1));
            ListOfUsers.remove(ListOfUsers.size() - 1);
        } else {
            System.out.println("User " + this.id + " that user has no objects\n");
        }
    }

    public int getListSize() {
        return ListOfUsers.size();
    }

}
