import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class AddingClient {
    final static int ADDING_PORT = 1234;
    static ByteBuffer buffer = ByteBuffer.allocate(4096);
    static int response;

    public static void adding(String host, String message) {
        try {

            InetSocketAddress socketAddress = new InetSocketAddress(host, ADDING_PORT);
            SocketChannel client = SocketChannel.open(socketAddress);


            buffer = ByteBuffer.wrap(message.getBytes());

            System.out.println("Client is sent:" + message);
            client.write(buffer);
            //dotad to samo co bylo w echo client
            //czycze buffer
            buffer.clear();
            //ustawiam limit do rozmiaru inta
            buffer.limit(4);
            //czytam wiadomosc
            client.read(buffer);
            //ustawiam pozycje na 0
            buffer.rewind();
            //konwertuje odpowiedz na inta
            response = buffer.getInt();
            System.out.println("Server response is: " + response);
            //czycze buffer
            buffer.clear();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}