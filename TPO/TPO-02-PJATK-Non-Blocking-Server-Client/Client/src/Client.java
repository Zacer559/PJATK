import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        String answer;
        System.out.println("Enter the command (\"help\" for help)");
        while (true) {
            Scanner scan = new Scanner(System.in);
            answer = scan.nextLine();
            if (answer.equals("help")) {
                System.out.println(" echo <server adress> <message>        " +
                        "          - to send echo message to the server \n" +
                        " addition <server adress> <number> <number>  " +
                        "    - to get result of adding two numbers");

            } else if (answer.equals("exit")) {
                break;
            } else {
                String[] temp = answer.split(" ");
                if (temp[0].equals("echo")) {
                    StringBuilder message = new StringBuilder();
                    for (int i = 2; i < temp.length; i++) {
                        message.append(" ").append(temp[i]);

                    }
                    EchoClient.echo(temp[1], message.toString());

                } else if (temp[0].equals("addition")) {
                    StringBuilder message = new StringBuilder();
                    for (int i = 2; i < temp.length; i++) {
                        message.append(" ").append(temp[i]);

                    }
                    AddingClient.adding(temp[1], message.toString());

                } else {
                    System.out.println("Bad option");
                }


            }

        }


    }
}

