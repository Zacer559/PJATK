import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.stream.IntStream;


public class UdpServer extends Thread {
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[2048];


    public UdpServer(int port) throws SocketException {
        socket = new DatagramSocket(port);

    }

    public void run() {
        running = true;

        while (running) {

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            int random = (int) (1024 + Math.random() * 64511);

            int num = CreateServer.addIP((packet.getAddress().toString() + (packet.getPort())));




            if (!(CreateServer.properOrder(num-1)==socket.getLocalPort()))
            {
                System.out.println("Authentication failed from " + address + " (wrong ports sequence)");
                CreateServer.remove((packet.getAddress().toString() + (packet.getPort())));
                num=0;
            }

            if (num == CreateServer.sizze()) {
                try {

                    buf = Integer.toString(random).getBytes();

                    DatagramPacket packet2 = new DatagramPacket(buf, buf.length, address, port);

                    socket.send(packet2);
                    new UdpDownloadServer(random, address, port).start();

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }


}