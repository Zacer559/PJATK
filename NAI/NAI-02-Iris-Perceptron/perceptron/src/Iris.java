import java.util.ArrayList;
import java.util.List;

public class Iris {


    private IrisTypes.IrisType initialType;
    private List<Double> inputs;

    private IrisTypes.IrisType recognizedType;


    public Iris() {


        inputs = new ArrayList<>();
    }


    public List<Double> getInputs() {
        return inputs;
    }


    public void setInputs(List<Double> inputs) {
        this.inputs = inputs;
    }


    public IrisTypes.IrisType getInitialType() {
        return initialType;
    }


    public void setInitialType(IrisTypes.IrisType initialType) {
        this.initialType = initialType;
    }


    public IrisTypes.IrisType getRecognizedType() {
        return recognizedType;
    }

    public void setRecognizedType(IrisTypes.IrisType recognizedType) {
        this.recognizedType = recognizedType;
    }

    @Override
    public String toString() {
        return "Vector [cname=" + initialType + ", cpname=" + recognizedType + "]";
    }


}

