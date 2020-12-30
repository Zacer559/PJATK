import java.util.ArrayList;
import java.util.List;

public abstract class FindBestK {
    public static void findBestK(int howMany) {
        List<Integer> percentages = new ArrayList<>();
        double bestPercentage = 0;
        for (int i = 1; i <= howMany; i++) {
            double correctAnswers = 0;
            double badAnswers = 0;
            int j = 0;
            for (Iris iris : IrisContainers.irisesToCheck) {
                iris.setIrisType(Knn.getIrisType(i, IrisContainers.irisesDataset, iris));
                if (iris.getIrisType().equals(IrisContainers.irisesCorrect.get(howMany + j).getIrisType())) {
                    correctAnswers++;
                } else {
                    badAnswers++;
                }
                j++;
            }
            double correctness = (correctAnswers / (correctAnswers + badAnswers) * 100);
            if (correctness > bestPercentage) {
                bestPercentage = correctness;
                percentages.clear();
                percentages.add(i);
            } else if (correctness == bestPercentage) {
                percentages.add(i);
            }


        }
        System.out.println("The biggest accuracy(" + bestPercentage + "%) was for the following number of closest plants: ");
        for (Integer percent : percentages) {
            System.out.print(percent + ",");
        }
        System.out.println();
    }


}
