import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface DataReader {
    static void read(IrisTypes.IrisType typeOne, IrisTypes.IrisType typeTwo, BufferedReader reader, List<Iris> testList) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] inputs = line.split(",");
            Iris iris = new Iris();
            for (int i = 1; i < inputs.length - 1; i++) {

                iris.getInputs().add(Double.parseDouble(inputs[i]));
            }
            String type = (inputs[inputs.length - 1].substring(1, inputs[inputs.length - 1].length() - 1));
            switch (type) {
                case "setosa":
                    iris.setInitialType(IrisTypes.IrisType.SETOSA);
                    break;
                case "virginica":
                    iris.setInitialType(IrisTypes.IrisType.VIRGINICA);
                    break;
                case "versicolor":
                    iris.setInitialType(IrisTypes.IrisType.VERSICOLOR);
                    break;
            }
            if (iris.getInitialType() == typeOne || iris.getInitialType() == typeTwo) {
                testList.add(iris);
            }
        }
    }
}
