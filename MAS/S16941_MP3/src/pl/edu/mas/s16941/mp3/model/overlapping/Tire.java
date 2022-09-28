package pl.edu.mas.s16941.mp3.model.overlapping;

import pl.edu.mas.s16941.mp3.exception.ModelCreationException;
import pl.edu.mas.s16941.mp3.exception.TireTypeException;

import java.util.Collections;
import java.util.EnumSet;

public class Tire implements SummerTire, WinterTire {
    private EnumSet<TireType> types;
    private String name;
    private int width, ratioHeightToWidth, diameter;

    private String stoppingOnWetIndex; // for summer
    private String stoppingOnSnowIndex; // for winter

    public Tire(String name, int width, int ratioHeightToWidth, int diameter, EnumSet<TireType> types, String stoppingOnWetIndex, String stoppingOnSnowIndex) throws ModelCreationException {
        setName(name);
        setWidth(width);
        setRatioHeightToWidth(ratioHeightToWidth);
        setDiameter(diameter);
        setTypes(types);
        if (isSummerTire()) setStoppingOnWetIndex(stoppingOnWetIndex);
        if (isWinterTire()) setStoppingOnSnowIndex(stoppingOnSnowIndex);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelCreationException {
        if (name == null || name.isEmpty()) throw new ModelCreationException("Name of tire cannot be null or empty.");
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) throws ModelCreationException {
        if (width <= 0) throw new ModelCreationException("Width of tire cannot be lower or equal 0");
        this.width = width;
    }

    public int getRatioHeightToWidth() {
        return ratioHeightToWidth;
    }

    public void setRatioHeightToWidth(int ratioHeightToWidth) throws ModelCreationException {
        if (ratioHeightToWidth <= 0) throw new ModelCreationException("ratioHeightToWidth cannot be lower or equal 0");
        this.ratioHeightToWidth = ratioHeightToWidth;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) throws ModelCreationException {
        if (diameter <= 0) throw new ModelCreationException("diameter cannot be lower or equal 0");
        this.diameter = diameter;
    }

    @Override
    public String getStoppingOnWetIndex() throws TireTypeException {
        if (!isSummerTire()) throw new TireTypeException("This parameter is not apropriete for not summer tire.");
        return stoppingOnWetIndex;
    }

    @Override
    public void setStoppingOnWetIndex(String stoppingOnWetIndex) throws ModelCreationException {
        if (stoppingOnWetIndex == null || stoppingOnWetIndex.isEmpty())
            throw new ModelCreationException("Stopping on wet cannot be null or empty for summer tire.");
        if (!isSummerTire()) throw new ModelCreationException("Cannot set stopping on wet for not summer tire.");
        this.stoppingOnWetIndex = stoppingOnWetIndex;
    }

    @Override
    public String getStoppingOnSnowIndex() throws TireTypeException {
        if (!isWinterTire()) throw new TireTypeException("This parameter is not apropriete for not winter tire.");
        return stoppingOnSnowIndex;
    }

    @Override
    public void setStoppingOnSnowIndex(String stoppingOnSnowIndex) throws ModelCreationException {
        if (stoppingOnSnowIndex == null || stoppingOnSnowIndex.isEmpty())
            throw new ModelCreationException("Stopping on snow cannot not be null or empty for winter tire.");
        if (!isWinterTire()) throw new ModelCreationException("Cannot set stopping on winter for not winter tire.");
        this.stoppingOnSnowIndex = stoppingOnSnowIndex;
    }

    public EnumSet<TireType> getTypes() {
        return (EnumSet<TireType>) Collections.unmodifiableSet(types);
    }

    private void setTypes(EnumSet<TireType> types) throws ModelCreationException {
        if (types == null || types.isEmpty())
            throw new ModelCreationException("Types of tires cannot be null or empty");
        this.types = types;
    }

    public boolean isSummerTire() {
        return this.types.contains(TireType.SUMMER);
    }

    public boolean isWinterTire() {
        return this.types.contains(TireType.WINTER);
    }

    @Override
    public String toString() {
        if (isWinterTire() && isSummerTire()) {
            return "Tire{" +
                    "types=" + types +
                    ", name='" + name + '\'' +
                    ", width=" + width +
                    ", ratioHeightToWidth=" + ratioHeightToWidth +
                    ", diameter=" + diameter +
                    ", stoppingOnWetIndex='" + stoppingOnWetIndex + '\'' +
                    ", stoppingOnSnowIndex='" + stoppingOnSnowIndex + '\'' +
                    '}';
        } else if (isSummerTire()) {
            return "Tire{" +
                    "types=" + types +
                    ", name='" + name + '\'' +
                    ", width=" + width +
                    ", ratioHeightToWidth=" + ratioHeightToWidth +
                    ", diameter=" + diameter +
                    ", stoppingOnWetIndex='" + stoppingOnWetIndex + '\'' +
                    '}';
        } else if (isWinterTire()) {
            return "Tire{" +
                    "types=" + types +
                    ", name='" + name + '\'' +
                    ", width=" + width +
                    ", ratioHeightToWidth=" + ratioHeightToWidth +
                    ", diameter=" + diameter +
                    ", stoppingOnSnowIndex='" + stoppingOnSnowIndex + '\'' +
                    '}';
        } else {
            return "Tire{" +
                    "types=" + types +
                    ", name='" + name + '\'' +
                    ", width=" + width +
                    ", ratioHeightToWidth=" + ratioHeightToWidth +
                    ", diameter=" + diameter +
                    '}';
        }
    }
}
