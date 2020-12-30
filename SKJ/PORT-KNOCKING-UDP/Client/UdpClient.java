import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class UdpClient extends Thread {
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf="".getBytes();;

    public UdpClient(String address) throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        this.address = InetAddress.getByName(address);
    }

    public void run(int port, boolean last) throws IOException, InterruptedException {
        Thread.sleep(100);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);
        if (last) {
            byte[] buf2 = new byte[2048];
            DatagramPacket packet2 = new DatagramPacket(buf2, buf2.length,packet.getAddress(),packet.getPort());
            socket.receive(packet2);
            String received = new String(packet2.getData(), 0, packet2.getLength());
            System.out.println("Authentication sucessful \nServer is connecting from port :  "+ received);
            try {
                socket.receive(packet2);
                received = new String(packet2.getData(), 0, packet2.getLength());
                System.out.print("The following file will be downloaded: " + received);
                socket.receive(packet2);
                File file = new File(received);
                received = new String(packet2.getData(), 0, packet2.getLength());
                System.out.println( " with size equals " + received + " bytes");

                int to = Integer.valueOf(received);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                if (to >= 2048) {
                    for (int i = 0; i * 2048 < to; i++) {
                        socket.receive(packet2);

                        outputStream.write(packet2.getData());
                    }

                    Files.write(Paths.get(file.getAbsolutePath()), outputStream.toByteArray());
                } else {

                    socket.receive(packet2);
                    byte[] buff = Arrays.copyOfRange(packet2.getData(), 0, to);
                    Files.write(Paths.get(file.getAbsolutePath()), buff);
                }
                System.out.println("Download successful!\n Exiting...");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void close() {
        socket.close();
    }
}
