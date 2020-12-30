import java.util.Scanner;

public class Main extends FindBestK {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DataReader.readDataset("iristrain.csv");
        DataReader.readToCheck("iristest.csv");
        System.out.println("Irises total size: " + IrisContainers.irisesCorrect.size());
        System.out.println("Irises train size: " + IrisContainers.irisesDataset.size());
        System.out.println("Irises to check size: " + IrisContainers.irisesToCheck.size());
        System.out.println(" Would you like to use iristest dataset? (y/n) \n If no then you have to input your values for plant");
        String answer = scan.nextLine();
        if (answer.equals("n")) {
            System.out.println(" Input data in format: double sepalLength double sepalWidth double petalLength double petalWidth \n for example: 1.54 2.57 3.76 8.22");
            double sepalLength = scan.nextDouble();
            double sepalWidth = scan.nextDouble();
            double petalLength = scan.nextDouble();
            double petalWidth = scan.nextDouble();
            Iris inputIris = new Iris(sepalLength, sepalWidth, petalLength, petalWidth, null);
            System.out.println("How many closest plants would You like to check?");
            int k = scan.nextInt();
            inputIris.setIrisType(Knn.getIrisType(k, IrisContainers.irisesDataset, inputIris));
            System.out.println(" For the given values : " +
                    "\n sepalLength: " + sepalLength +
                    "\n sepalWidth: " + sepalWidth +
                    "\n petalLength: " + petalLength +
                    "\n petalWidth: " + petalWidth);
            System.out.println("Iris was recognized as: " + inputIris.getIrisType());

        } else {
            System.out.println("Would you like to check for which value of closest plants the result is most accurate?(y/n)");
            answer = scan.nextLine();
            if (answer.equals("y")) {
                findBestK(IrisContainers.irisesDataset.size());
            } else if (!answer.equals("n")) {
                System.out.println("Bad answer,skipping this step");
            }

            System.out.println("How many closest plants would You like to check? ");
            int howMany = scan.nextInt();
            scan.close();
            for (Iris iris : IrisContainers.irisesToCheck) {
                iris.setIrisType(Knn.getIrisType(howMany, IrisContainers.irisesDataset, iris));
            }
            int i = 0;
            double correctAnswers = 0;
            double badAnswers = 0;
            for (Iris iris : IrisContainers.irisesToCheck) {
                if (iris.getIrisType().equals(IrisContainers.irisesCorrect.get(100 + i).getIrisType())) {
                    answer = "CORRECT";
                    correctAnswers++;
                } else {
                    answer = "NOT CORRECT";
                    badAnswers++;
                }
                System.out.println("Iris number " + (100 + i) + " was recognized as " + iris.getIrisType() + " which is " + answer);
                i++;
            }
            System.out.println("Total correctness of the algorithm was: " + (correctAnswers / (correctAnswers + badAnswers) * 100 + "%"));
        }

    }
}
