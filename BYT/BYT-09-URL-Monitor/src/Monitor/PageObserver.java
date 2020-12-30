package Monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * PageObserver is a class which is our Observer.
 */
public class PageObserver implements IObserver, Serializable {

    private final String id; /* id of our Observer */
    private Date lastModified; /* lastModified date of the page  */

    /**
     * Constructor
     *
     * @param id is id of our Observer.
     */
    public PageObserver(String id) {
        this.id = id;
    }

    /**
     * Updates lastModified date to newer one and prints information obout that.
     *
     * @param newLastModified is new Date which will be stored as lastModified field of this class.
     */
    @Override
    public void update(Date newLastModified) {
        lastModified = newLastModified;
        System.out.printf("Observer number: #%-2s observed that new date is: %25s%n", id, lastModified.toString()); /* Prints info about change. */
    }

    /**
     * Returns lastModified date.
     *
     * @return actual lastModified date.
     */
    @Override
    public Date getLastModified() {
        return lastModified;
    }
}