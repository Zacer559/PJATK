import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Knn {

    private static double getDistance(double[] p, double[] k) {
        int dimension = p.length;
        double distance = 0;

        for (int i = 0; i < dimension; i++) {
            distance += Math.pow(Math.abs(p[i] - k[i]), 2);
        }

        return Math.sqrt(distance);
    }

    private static IrisType getResult(Map<IrisType, Integer> irisTypes) {
        int max = Integer.MIN_VALUE;
        if (irisTypes.values().stream().max(Integer::compare).isPresent()) {
            max = irisTypes.values().stream().max(Integer::compare).get();
        }
        IrisType result = null;
        for (Map.Entry<IrisType, Integer> value : irisTypes.entrySet()) {
            if (value.getValue() == max) {
                result = value.getKey();
            }
        }
        return result;
    }

    public static IrisType getIrisType(int k, List<Iris> dataset, Iris newIris) {
        Map<Double, Iris> neighbourDistance = new TreeMap<>();

        for (Iris iris : dataset) {
            neighbourDistance.put(getDistance(newIris.getAllSize(), iris.getAllSize()), iris);
        }

        int counter = 0;
        Map<IrisType, Integer> irisTypes = new TreeMap<>();

        for (Map.Entry<Double, Iris> neighbour : neighbourDistance.entrySet()) {
            if (counter == k) break;

            irisTypes.put(neighbour.getValue().getIrisType(), (irisTypes.get(neighbour.getValue().getIrisType()) == null ? 0 : irisTypes.get(neighbour.getValue().getIrisType())) + 1);

            counter++;
        }

        return getResult(irisTypes);
    }
}
