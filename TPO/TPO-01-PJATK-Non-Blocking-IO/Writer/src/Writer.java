import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Writer implements DataContainer {

    public static void main(String[] args) {
        try {
            System.out.println(ANSI_GREEN + "Green - Writer" + ANSI_RESET);

            RandomAccessFile file = new RandomAccessFile(filename, "rw");
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 16);
            buffer.putInt(lastActionReceive);
            buffer.rewind();
            while (true) {
                Thread.sleep(2000);
                file.seek(0);
                if (file.readInt() == lastActionReceive) {

                    buffer.putInt(lastActionSend);
                    buffer.putInt((int) (Math.random() * 1000));
                    buffer.putInt((int) (Math.random() * 1000));
                    buffer.putInt((int) (Math.random() * 1000));
                    buffer.flip();
                    System.out.println(ANSI_GREEN + buffer.getInt() + " " + buffer.getInt() + " " + buffer.getInt() + " " + buffer.getInt() + ANSI_RESET);
                    buffer.clear();
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Writing error");
            e.printStackTrace();
        }
    }
}

