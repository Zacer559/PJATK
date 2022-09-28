package pl.edu.mas.s16941.mp3.model.dynamic;

import pl.edu.mas.s16941.mp3.exception.ClientTypeException;
import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public interface VIPClient {
    String getVIPScore() throws ClientTypeException;

    void setVIPScore(String VIPScore) throws ModelCreationException, ClientTypeException;
}
