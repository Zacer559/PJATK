import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();

        threads.add(new Thread(EchoServer::echoServer));
        threads.add(new Thread(AddingServer::addingServer));
        for (Thread thread : threads) {
            thread.start();
        }


    }
}
