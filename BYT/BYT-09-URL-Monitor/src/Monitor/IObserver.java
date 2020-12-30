package Monitor;

import java.util.Date;

/**
 * IObserver Interface was created for easier usage of Maps.
 * Now we can have more than one type of Observer and all will be still working properly.
 */
public interface IObserver {

    Date getLastModified();

    void update(Date newLastModified);


}

