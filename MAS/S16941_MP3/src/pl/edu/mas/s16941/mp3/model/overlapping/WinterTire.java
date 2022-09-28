package pl.edu.mas.s16941.mp3.model.overlapping;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;
import pl.edu.mas.s16941.mp3.exception.TireTypeException;

public interface WinterTire {
    String getStoppingOnSnowIndex() throws TireTypeException;

    void setStoppingOnSnowIndex(String stoppingOnSnowIndex) throws ModelCreationException;
}
