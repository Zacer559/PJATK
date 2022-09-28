import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;

public class Proxy implements Runnable {


    static ArrayList<Thread> servicingThreads;
    private ServerSocket serverSocket;
    private volatile boolean running = true;

    public Proxy(int port) {
        servicingThreads = new ArrayList<>();
        new Thread(this).start();

        try {
            // Create the Server Socket for the Proxy
            serverSocket = new ServerSocket(port);

            // Set the timeout
            //serverSocket.setSoTimeout(100000);    // debug
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..");
            running = true;
        } catch (SocketException se) {
            System.out.println("Problem accessing or creating socket");
            se.printStackTrace();
        } catch (SocketTimeoutException ste) {
            System.out.println("Timeout occured while connecting to client");
        } catch (IOException io) {
            System.out.println("IO exception when connecting to client");
        }


    }

    public void listen() {

        while (running) {
            try {
                // serverSocket.accpet() Blocks until a connection is made
                Socket socket = serverSocket.accept();

                // Create new Thread and pass it Runnable RequestHandler
                Thread thread = new Thread(new Request2(socket));

                // Key a reference to each thread so they can be joined later if necessary
                servicingThreads.add(thread);

                thread.start();
            } catch (SocketException e) {
                // Socket exception is triggered by management system to shut down the proxy
                System.out.println("Server closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeServer() {
        System.out.println("\nClosing Server..");
        running = false;
        try {
            // Close all servicing threads
            for (Thread thread : servicingThreads) {
                if (thread.isAlive()) {
                    System.out.print("Waiting on " + thread.getId() + " to close..");
                    thread.join();
                    System.out.println(" closed");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Terminating Connection");
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Exception closing proxy's server socket");
            e.printStackTrace();
        }


    }


    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        String command;
        while (running) {
            command = scanner.nextLine();
            if (command.equals("close")) {
                running = false;
                closeServer();
            }



        }
        scanner.close();

    }
}