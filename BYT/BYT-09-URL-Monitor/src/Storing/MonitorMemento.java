package Storing;

/* Memento class which stores information about our state */
public class MonitorMemento implements IMemento {

    private MonitorState _state;

    /**
     * Constructor
     *
     * @param state of memento
     */
    public MonitorMemento(MonitorState state) {
        this._state = state;
    }

    /**
     * Returns state of memento
     *
     * @return state of memento
     */
    @Override
    public MonitorState getState() {
        return _state;
    }

    /**
     * Sets state of memento
     *
     * @param state of memento
     */
    @Override
    public void setState(MonitorState state) {
        _state = state;
    }
}
