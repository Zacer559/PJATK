import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.sin;

public class Main {
    static void masochisticSinus(double n2, int iterations) {
        BigDecimal n, denominator, sinx, sinval, accuracy;

        n = BigDecimal.valueOf(n2);


        n2 = n2 * (Math.PI / 180);
        n = n.multiply(BigDecimal.valueOf(Math.PI).divide(BigDecimal.valueOf(180), RoundingMode.HALF_UP));
        BigDecimal x1 = n;
        sinx = n;
        int i = 1;
        sinval = BigDecimal.valueOf(sin(n2));
        do {
            denominator = BigDecimal.valueOf(2 * i * (2 * i + 1));
            x1 = x1.multiply(BigDecimal.valueOf(-1)).multiply(n).multiply(n).divide(denominator, RoundingMode.HALF_UP);
            sinx = sinx.add(x1);
            i = i + 1;
            accuracy = sinx.subtract(sinval).abs();
            System.out.println("Value of sinus calculeted for " + (i - 1) + " iterations:     " + sinx + "       Accuracy is: " + accuracy);
        } while (i <= iterations);
        System.out.println("Value of sinus calculated by Math.sin function: " + sinval);
    }

    static void sinus(double n, int iterations) {
        double denominator, sinx, accuracy;


        n = n * (Math.PI / 180);

        double x1 = n;


        sinx = n;


        double sinval = sin(n);
        int i = 1;
        do {
            denominator = (2 * i * (2 * i + 1));
            x1 = -x1 * n * n / denominator;
            sinx = sinx + x1;
            i = i + 1;
            accuracy = abs(sinx - sinval);
            System.out.println("Value of sinus calculeted for " + (i - 1) + " iterations:     " + sinx + "       Accuracy is: " + accuracy);
        } while (i <= iterations);

        System.out.println("Value of sinus calculated by Math.sin function: " + sinval);
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Provide angle in degrees");
        int angle = scan.nextInt();
        if (angle >= 360) {
            angle = angle % 360;
        }
        if (angle == 180) {
            angle = 0;
        }
        System.out.println("Provide number of iterations");
        int iterations = scan.nextInt();
        scan.nextLine();
        System.out.println("Would You like to use masochistic mode? (y/n) \n !!!Warning!!! \n The accuracy will be not availible");
        String answer = scan.nextLine();

        if (answer.equals("y")) {


            masochisticSinus(angle, iterations);


        } else if (answer.equals("n")) {

            sinus(angle, iterations);


        } else {
            System.out.println("Bad answer");
        }
    }
}
