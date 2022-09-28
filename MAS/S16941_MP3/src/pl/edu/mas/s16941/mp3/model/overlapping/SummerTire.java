package pl.edu.mas.s16941.mp3.model.overlapping;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;
import pl.edu.mas.s16941.mp3.exception.TireTypeException;

public interface SummerTire {
    String getStoppingOnWetIndex() throws TireTypeException;

    void setStoppingOnWetIndex(String stoppingOnWetIndex) throws ModelCreationException;
}
