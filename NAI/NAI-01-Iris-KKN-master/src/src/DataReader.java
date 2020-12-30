import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class DataReader {

    public static void readDataset(String filepath) {
        try {

            File file = new File(filepath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] tempArr;
            br.readLine();
            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");

                if (tempArr.length == 6) {
                    IrisType irisType;

                    switch (tempArr[5]) {
                        case "\"setosa\"":
                            irisType = IrisType.SETOSA;
                            break;
                        case "\"versicolor\"":
                            irisType = IrisType.VERSICOLOR;
                            break;
                        case "\"virginica\"":
                        default:
                            irisType = IrisType.VIRGINICA;
                            break;
                    }
                    IrisContainers.irisesDataset.add(new Iris(Double.parseDouble(tempArr[1]), Double.parseDouble(tempArr[2]), Double.parseDouble(tempArr[3]), Double.parseDouble(tempArr[4]), irisType));
                    IrisContainers.irisesCorrect.add(new Iris(Double.parseDouble(tempArr[1]), Double.parseDouble(tempArr[2]), Double.parseDouble(tempArr[3]), Double.parseDouble(tempArr[4]), irisType));
                }
            }


            br.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readToCheck(String filepath) {
        try {
            File file = new File(filepath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] tempArr;
            br.readLine();
            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");

                if (tempArr.length == 6) {
                    IrisType irisType;

                    switch (tempArr[5]) {
                        case "\"setosa\"":
                            irisType = IrisType.SETOSA;
                            break;
                        case "\"versicolor\"":
                            irisType = IrisType.VERSICOLOR;
                            break;
                        case "\"virginica\"":
                        default:
                            irisType = IrisType.VIRGINICA;
                            break;
                    }
                    IrisContainers.irisesToCheck.add(new Iris(Double.parseDouble(tempArr[1]), Double.parseDouble(tempArr[2]), Double.parseDouble(tempArr[3]), Double.parseDouble(tempArr[4]), null));
                    IrisContainers.irisesCorrect.add(new Iris(Double.parseDouble(tempArr[1]), Double.parseDouble(tempArr[2]), Double.parseDouble(tempArr[3]), Double.parseDouble(tempArr[4]), irisType));
                }
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}



