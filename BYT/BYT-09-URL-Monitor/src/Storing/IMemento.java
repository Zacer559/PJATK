package Storing;

import java.io.*;

public interface IMemento {

    /**
     * Static method which is saving serialized Memento to a file
     *
     * @param memento Memento which we want to save in file
     */
    static void saveMemento(MonitorMemento memento) throws IOException {
        File file = new File("./Serialized");
        ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file));
        MonitorState oldState = memento.getState();
        outStream.writeObject(oldState);
        outStream.close();
    }

    /**
     * Static method which is reading serialized Memento from file
     *
     * @return Read memento
     */
    static MonitorMemento readMemento() throws IOException, ClassNotFoundException {
        File file = new File("./Serialized");
        ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
        MonitorState newState = (MonitorState) inStream.readObject();
        return new MonitorMemento(newState);
    }

    MonitorState getState();

    void setState(MonitorState state);
}
