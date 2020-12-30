import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public abstract class EchoClient {
    final static int ECHO_PORT = 7777;
    static ByteBuffer buffer = ByteBuffer.allocate(4096);
    static String response;

    public static void echo(String host, String message) {
        try {
            //tworze socket
            InetSocketAddress socketAddress = new InetSocketAddress(host, ECHO_PORT);
            SocketChannel client = SocketChannel.open(socketAddress);
            // zapisuje message do buffera
            buffer = ByteBuffer.wrap(message.getBytes());

            System.out.println("Client is sent: " + message);
            //wysylam wiadomosc
            client.write(ByteBuffer.wrap(message.getBytes()));
            //czyszcze buffer
            buffer.clear();
            //czytam odpowiedz servera
            client.read(buffer);
            // cofam do poprzedniej pozycji i ustawiam limit do tej co byla
            buffer.flip();
            // konwertuje na stringa
            response = new String(buffer.array());
            System.out.println("Server response is: " + response);
            //czycze buffer
            buffer.clear();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }
