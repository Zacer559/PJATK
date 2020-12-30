import java.util.ArrayList;
import java.util.List;

public class CareTaker {

    private final List<Memento> mementoList = new ArrayList<>();

    public void saveMemento(Memento m) {
        mementoList.add(m);
    }

    public Memento getMemento(int index) {
        return mementoList.get(index);
    }
}
