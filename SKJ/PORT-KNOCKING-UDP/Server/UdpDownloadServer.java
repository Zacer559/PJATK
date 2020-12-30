import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.util.Arrays;


public class UdpDownloadServer extends Thread {
    private InetAddress address;
    private int clientPort;
    private String filename = "AndroidNotesForProfessionals.pdf";
    private DatagramSocket socket;
    private boolean running;
    private byte[] buff = new byte[2048];

    public UdpDownloadServer(int port, InetAddress address, int clientPort) throws InterruptedException {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
            CreateServer.serverClose();
        }
        this.address = address;
        this.clientPort = clientPort;

    }

    public void run() {
        running = true;
        System.out.println("Autchentication successful from " + address + "\n Lauching server on new port " + socket.getLocalPort() + " and sending the file");
        buff = filename.getBytes();
        while (running) {
            DatagramPacket packet = new DatagramPacket(buff, buff.length, address, clientPort);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }


            File copy = new File(filename);

            try {
                buff = Files.readAllBytes(copy.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] senddata = buff;
            byte[] len = String.valueOf(buff.length).getBytes();


            try {
                packet = new DatagramPacket(len, len.length, address, clientPort);
                socket.send(packet);
                int to = buff.length;
                if (to >= 2048) {
                    for (int i = 0; i * 2048 < buff.length; i = i + 1) {
                        //   System.out.println(i);
                        Thread.sleep(1);
                        packet = new DatagramPacket(Arrays.copyOfRange(buff, i * 2048, (i + 1) * 2048), Arrays.copyOfRange(buff, i * 2048, (i + 1) * 2048).length, address, clientPort);
                        socket.send(packet);
                    }


                } else {
                    packet = new DatagramPacket(senddata, senddata.length, address, clientPort);
                    socket.send(packet);

                }
                System.out.println("Sending to: " + address + " successful");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            running = false;




        }

    }
}