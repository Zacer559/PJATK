import Chain.*;
import DTO.Parser;
import DTO.Request;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // scanning
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Provide the operation (for example "2 + 2"):""");
        String input = scanner.nextLine();
        scanner.close();
        Request request = Parser.parse(input);
        // creating the chain
        if (request != null) {
            Chain chainAdd = new Add();
            Chain chainSub = new Subtract();
            Chain chainMul = new Multiply();
            Chain chainDiv = new Divide();
            chainAdd.setNextChain(chainSub);
            chainSub.setNextChain(chainMul);
            chainMul.setNextChain(chainDiv);
            // calculating
            chainAdd.calculate(request);
            System.out.println(request.getResponse().getResult());
        }
    }
}
