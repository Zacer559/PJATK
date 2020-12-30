import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;


public class Request2 implements Runnable {
    Socket clientSocket;
    BufferedReader proxyToClientBr;
    BufferedWriter proxyToClientBw;

    public Request2(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            proxyToClientBr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            proxyToClientBw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        // Get Request from client
        String requestString;
        try {
            requestString = proxyToClientBr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading request from client");
            return;
        }
        if (requestString != null) {
            System.out.println("Request Received " + requestString);

            String request = requestString.substring(0, requestString.indexOf(' '));
            String urlString = requestString.substring(requestString.indexOf(' ') + 1);
            urlString = urlString.substring(0, urlString.indexOf(' '));
            if (!urlString.substring(0, 4).equals("http")) {
                String temp = "http://";
                urlString = temp + urlString;
            }

            if (request.equals("GET")) {

                System.out.println("HTTP GET for : " + urlString + "\n");
                sendToClient(urlString);


            }


        }


    }

    private void sendToClient(String urlString) {

        try {

            // Compute a logical file name as per schema
            // This allows the files on stored on disk to resemble that of the URL it was taken from
            URL remoteURL = new URL(urlString);
            HttpURLConnection proxyToServerCon = (HttpURLConnection) remoteURL.openConnection();
            try {
                byte[] buffer = new byte[4096];
                int read;
                do {
                    read = proxyToServerCon.getInputStream().read(buffer);

                    if (read > 0) {
                        clientSocket.getOutputStream().write(buffer, 0, read);
                        if (proxyToServerCon.getInputStream().available() < 1) {
                            clientSocket.getOutputStream().flush();
                        }
                    }
                } while (read >= 0);
            } catch (SocketTimeoutException e) {

            }

            if (proxyToClientBw != null) {
                proxyToClientBw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
