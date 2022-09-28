package pl.edu.mas.s16941.mp3.model.multi;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public interface SportsCar {
    boolean isCabrio();

    void setCabrio(boolean isCabrio) throws ModelCreationException;
}
