package Monitor;

import Exceptions.PageMonitorException;
import Storing.MonitorMemento;
import Storing.MonitorState;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PageMonitor {
    private Map<URL, Date> lastModified = new HashMap<>(); //Map which stores lastModified date for each of our URL
    private Map<URL, Set<IObserver>> observers = new HashMap<>(); //Map which stores all Observers for each of our URL


    /**
     * Checks last modified date of page each refreshInterval
     *
     * @param refreshInterval interval between each checking of page in milliseconds
     */
    public void monitor(int refreshInterval) {
        while (true) {
            try {
                // Iterating each page in our lastModified Map
                lastModified.keySet().forEach(this::updateLastModified);
                // Sleeps for our refreshInterval time
                Thread.sleep(refreshInterval);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    /**
     * @param url url for which we want to check lastModified date
     * @return Date when page was last time modified.
     */
    private Date getLastModified(URL url) {
        try {
            return new Date(url.openConnection().getLastModified());
        } catch (IOException e) {
            throw new PageMonitorException("Cannot load last update date.", e);
        }
    }

    /**
     * Updates last modified date for the passed url and notifies attached observers
     *
     * @param url url for which we want to update our lastModified date.
     */
    private void updateLastModified(URL url) {
        /* Getting last modified date */
        Date currLastModified = getLastModified(url);
        /* If that date is not the last that we had in our Map
        we are putting that date to our Map and notify Observers */
        if (!lastModified.get(url).equals(currLastModified)) {
            lastModified.put(url, currLastModified);
            notifyObservers(url, currLastModified);
        }
    }

    /**
     * @param url             url for which observers should be notified
     * @param newLastModified new lastModified date which we are passing to each interested Observer
     */
    private void notifyObservers(URL url, Date newLastModified) {
        observers.get(url).forEach(o -> o.update(newLastModified));
    }

    /**
     * Adds URL to map of monitored urls
     *
     * @param url url of page that we want to be monitored and observed
     */
    public void addMonitoredPage(URL url) {
        lastModified.putIfAbsent(url, getLastModified(url));
        observers.putIfAbsent(url, new HashSet<>());
    }

    /**
     * Removes URL from map of monitored urls
     *
     * @param url url of page that we want to be no longer monitored and observed
     */
    public void removeMonitoredPage(URL url) {
        lastModified.remove(url);
        observers.remove(url);
    }

    /**
     * Attaches the observer to the url and adds url to monitored if already not monitored.
     *
     * @param url      url which will be observed
     * @param observer observer that will be observing to the specified url
     */
    public void addObserver(URL url, IObserver observer) {
        addMonitoredPage(url);
        observers.get(url).add(observer);
        observer.update(lastModified.get(url));
    }

    /**
     * @param url      url which will be no longer observed
     * @param observer observer that will be no longer observing
     */
    public void removeObserver(URL url, IObserver observer) {
        if (observers.containsKey(url)) {
            observers.get(url).remove(observer);
        }
    }

    /**
     * Creates new memento
     *
     * @return new memento of both of our maps
     */
    public MonitorMemento getMemento() {
        return new MonitorMemento(new MonitorState(lastModified, observers));
    }

    /**
     * Restores states from memento.
     *
     * @param memento which we want to "restore".
     */
    public void setMemento(MonitorMemento memento) {
        observers = memento.getState().getObservers();
        lastModified = memento.getState().getLastModified();
    }
}

