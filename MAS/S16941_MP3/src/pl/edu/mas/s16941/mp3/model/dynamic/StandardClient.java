package pl.edu.mas.s16941.mp3.model.dynamic;

import pl.edu.mas.s16941.mp3.exception.ClientTypeException;
import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public interface StandardClient {
    int getClientScore() throws ClientTypeException;

    void setClientScore(int clientScore) throws ModelCreationException, ClientTypeException;
}
