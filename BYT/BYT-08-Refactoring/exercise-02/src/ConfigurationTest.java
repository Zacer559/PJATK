import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static org.junit.Assert.*;

public class ConfigurationTest {

    @Test
    public void testGoodInput() throws IOException {
        String data = "interval = 10\nduration = 100\ndeparture = 200\n";

        Properties input = loadInput(data);

        Configuration props = new Configuration();
        try {
            props.load(input);
        } catch (ConfigurationException e) {
            //changed to "fail()" method
            fail();
            return;
        }

        assertEquals(props.interval, 10);
        assertEquals(props.duration, 100);
        assertEquals(props.departure, 200);
    }

    @Test
    public void testNegativeValues() throws IOException {
        processBadInput("interval = -10\nduration = 100\ndeparture = 200\n");
        processBadInput("interval = 10\nduration = -100\ndeparture = 200\n");
        processBadInput("interval = 10\nduration = 100\ndeparture = -200\n");
    }

    @Test
    public void testInvalidDuration() throws IOException {
        processBadInput("interval = 10\nduration = 99\ndeparture = 200\n");
    }

    @Test
    public void testInvalidDeparture() throws IOException {
        processBadInput("interval = 10\nduration = 100\ndeparture = 199\n");
    }

    //removed @Test
    private void processBadInput(String data) throws IOException {
        Properties input = loadInput(data);

        boolean failed = false;
        Configuration props = new Configuration();
        try {
            props.load(input);
        } catch (ConfigurationException e) {
            failed = true;
        }

        assertTrue(failed);
    }

    //removed @Test
    private Properties loadInput(String data) throws IOException {
        // changed from new StringBufferInputStream(data) because its deprecated (its not always converting properly string to byte array)
        InputStream is = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));

        Properties input = new Properties();
        input.load(is);
        is.close();

        return input;
    }
}
