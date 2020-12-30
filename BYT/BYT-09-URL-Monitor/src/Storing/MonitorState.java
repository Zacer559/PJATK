package Storing;

import Monitor.IObserver;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class MonitorState implements Serializable { /* Implements serializable to be able to save memento to a file */
    /* Fields containing our data to store */
    private Map<URL, Date> lastModified;
    private Map<URL, Set<IObserver>> observers;

    /**
     * Constructor
     *
     * @param lastModified is our Map of URL and last dates that we want to store.
     * @param observers    is our Map of URL and Observers that we want to store.
     */
    public MonitorState(Map<URL, Date> lastModified, Map<URL, Set<IObserver>> observers) {
        this.lastModified = lastModified;
        this.observers = observers;
    }

    /**
     * Returns lastModified Map
     *
     * @return Map of last modified dates.
     */
    public Map<URL, Date> getLastModified() {
        return lastModified;
    }

    /**
     * Sets our lastModified Map
     *
     * @param lastModified Map of last modified dates.
     */
    public void setLastModified(Map<URL, Date> lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Returns Observers Map
     *
     * @return Map of Observers.
     */
    public Map<URL, Set<IObserver>> getObservers() {
        return observers;
    }

    /**
     * Sets our observers Map
     *
     * @param observers Map of observers.
     */
    public void setObservers(Map<URL, Set<IObserver>> observers) {
        this.observers = observers;
    }
}