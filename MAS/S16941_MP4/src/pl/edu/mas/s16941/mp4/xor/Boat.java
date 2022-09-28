package pl.edu.mas.s16941.mp4.xor;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

public class Boat extends Vehicle{
    double depth;
    public Boat(String name,double depth) throws ModelValidationException {
        super(name);
        setDepth(depth);
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) throws ModelValidationException {
        if(depth<=0) throw new ModelValidationException("Depth of boat cannot be lower or equal 0.");
        this.depth = depth;
    }
}
