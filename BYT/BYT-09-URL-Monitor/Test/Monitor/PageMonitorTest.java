package Monitor;

import Setup.TestSetup;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.net.URL;


public class PageMonitorTest extends TestSetup {

    @Test
    public void updateLastModifiedTest() throws Exception {
        /* Setting accessibility for the method because it is private. */
        Method updateLastModified = PageMonitor.class.getDeclaredMethod("updateLastModified", URL.class);
        updateLastModified.setAccessible(true);
        /* Checking that for all observers we have set time to null */
        observers.forEach(o -> Assert.assertEquals((long) responses[0], o.getLastModified().getTime()));
        /* Launching monitor for every url  */
        for (URL url : urls) {
            updateLastModified.invoke(monitor, url);
        }
        /* Updating the dates and checking that they are correct
         * Starting from index 3 because mockito returns values from index 3, before we have nulls */
        for (int i = 3; i < responses.length; i++) {
            for (URL url : urls) {
                updateLastModified.invoke(monitor, url);
            }
            for (IObserver o : observers) {
                Assert.assertEquals((long) responses[i], o.getLastModified().getTime());
            }
        }
    }

}