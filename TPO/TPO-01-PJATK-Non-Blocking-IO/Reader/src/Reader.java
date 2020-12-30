import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Reader implements DataContainer {


    public static void main(String[] args) {
        try {

            int[] array = new int[3];
            System.out.println(ANSI_YELLOW + "Yellow - Reader" + ANSI_RESET);
            RandomAccessFile file = new RandomAccessFile(filename, "rw");
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 16);


            while (true) {
                Thread.sleep(2000);

                if (buffer.getInt() == lastActionSend) {
                    for (int i = 0; i < 3; i++) {
                        array[i] = buffer.getInt();
                    }

                    System.out.println(ANSI_YELLOW + array[0] + " + " + array[1] + " + " + array[2] + " = " + (array[0] + array[1] + array[2]) + ANSI_RESET);
                    buffer.flip();
                    buffer.putInt(lastActionReceive);
                    buffer.rewind();
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Reading error");
            e.printStackTrace();
        }
    }
}

