import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Perceptron implements DataReader {


    private List<Weight> WeightList;
    private IrisTypes.IrisType typeOne;
    private IrisTypes.IrisType typeTwo;

    private List<Iris> trainList;


    private List<Iris> testList;

    private double theta = Math.random() * 5;
    private double alpha = 0.4;


    public Perceptron(IrisTypes.IrisType typeOne, IrisTypes.IrisType typeTwo) {

        this.typeOne = typeOne;
        this.typeTwo = typeTwo;

        trainList = new ArrayList<>();
        testList = new ArrayList<>();
    }


    public void readAndTrain() {

        try {

            WeightList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File("iristrain.csv")));
            reader.readLine();
            DataReader.read(typeOne, typeTwo, reader, trainList);
            BufferedReader reader2 = new BufferedReader(new FileReader(new File("iristest.csv")));
            reader2.readLine();
            DataReader.read(typeOne, typeTwo, reader2, testList);
            for (int i = 0; i < trainList.get(0).getInputs().size(); i++) {
                Weight weight = new Weight();
                WeightList.add(weight);
            }
            ClassifyList(trainList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Iris Classify(Iris vector) {

        double y;

        double sum = 0;

        double d;

        for (int i = 0; i < WeightList.size(); i++) {


            sum = sum + (WeightList.get(i).getWeight() * vector.getInputs().get(i));
        }

        if (sum >= theta) {

            vector.setRecognizedType(typeOne);

            y = 1;

            if (vector.getInitialType() != null) {

                if (vector.getInitialType() == typeOne) {
                    d = 1;

                } else {

                    d = 0;
                }

            } else {


                return vector;
            }


        } else {

            vector.setRecognizedType(typeTwo);

            y = 0;
            if (vector.getInitialType() != null) {
                if (vector.getInitialType() == typeTwo) {
                    d = 0;

                } else {

                    d = 1;
                }


            } else {


                return vector;
            }


        }

        for (int i = 0; i < WeightList.size(); i++) {


            WeightList.get(i).setWeight(WeightList.get(i).getWeight() + (d - y) * alpha * vector.getInputs().get(i));


        }

        theta = theta - (d - y) * alpha;

        return vector;

    }

    public List<Iris> ClassifyList(List<Iris> list) {
        List<Iris> lista = new ArrayList<>();
        for (Iris vector : list) {
            lista.add(Classify(vector));
        }
        return lista;
    }


    public double accuracy(List<Iris> list) {
        double correct = 0;
        for (Iris iris : list) {
            if (iris.getInitialType() == iris.getRecognizedType()) {
                correct++;
            }
        }
        return correct / list.size();
    }
    public List<Iris> getTestList() {
        return testList;
    }

}
