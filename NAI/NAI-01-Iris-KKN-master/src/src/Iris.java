public class Iris {

    double sepalLength;
    double sepalWidth;
    double petalLength;
    double petalWidth;
    IrisType type;

    public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, IrisType type) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.type = type;
    }

    public double[] getAllSize() {

        return new double[]{this.sepalLength, this.sepalWidth, this.petalWidth, this.petalWidth};
    }

    public double getSepalLength() {
        return sepalLength;
    }

    public double getSepalWidth() {
        return sepalWidth;
    }

    public double getPetalLength() {
        return petalLength;
    }

    public double getPetalWidth() {
        return petalWidth;
    }

    public IrisType getIrisType() {
        return type;
    }

    @Override
    public String toString() {
        return "Iris{" +
                "sepalLength=" + sepalLength +
                ", sepalWidth=" + sepalWidth +
                ", petalLength=" + petalLength +
                ", petalWidth=" + petalWidth +
                ", type='" + type + '\'' +
                '}';
    }

    public void setIrisType(IrisType type) {
        this.type = type;
    }
}
