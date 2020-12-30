package Memento;


import Setup.TestSetup;
import Storing.IMemento;
import Storing.MonitorMemento;
import Storing.MonitorState;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class MementoTest extends TestSetup {
    /* Test of setting memento state. */
    @Test
    public void setMementoTest() {
        MonitorState beforeState = monitor.getMemento().getState();
        Assert.assertNotEquals(0, beforeState.getLastModified().size());
        Assert.assertNotEquals(0, beforeState.getObservers().size());

        MonitorState newState = new MonitorState(new HashMap<>(), new HashMap<>());
        monitor.setMemento(new MonitorMemento(newState));
        Assert.assertEquals(0, monitor.getMemento().getState().getLastModified().size());
        Assert.assertEquals(0, monitor.getMemento().getState().getObservers().size());
    }

    /* Test of saving memento to a File and reading it. */
    @Test
    public void saveStateTest() throws Exception {
        MonitorMemento oldMemento = monitor.getMemento();
        IMemento.saveMemento(oldMemento);

        MonitorMemento newMemento = IMemento.readMemento();

        Assert.assertEquals(oldMemento.getState().getObservers().size(), newMemento.getState().getObservers().size());
        Assert.assertEquals(oldMemento.getState().getLastModified().size(), newMemento.getState().getLastModified().size());
    }


}