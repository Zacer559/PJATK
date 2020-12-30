package Setup;

import Monitor.IObserver;
import Monitor.PageMonitor;
import Monitor.PageObserver;
import org.junit.After;
import org.junit.Before;

import java.net.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSetup {
    protected PageMonitor monitor;
    protected List<IObserver> observers;
    protected List<URL> urls;
    protected Long[] responses;

    @Before
    public void setup() {
        int howManyUrl = 10;
        int howManyObserversPerUrl = 2;
        int howManyResponsesPerUrl = 10;
        String urlPattern = "http://wp%d.pl";
        /* Creating responses times */
        responses = LongStream.range(System.currentTimeMillis(), System.currentTimeMillis() + howManyResponsesPerUrl)
                .boxed()
                .toArray(Long[]::new);
        /* Creating observers */
        observers = IntStream.range(0, howManyUrl * howManyObserversPerUrl)
                .mapToObj(i -> new PageObserver(String.valueOf(i)))
                .collect(Collectors.toList());
        /* Creating Urls */
        urls = LongStream.range(1, 1 + howManyUrl)
                .mapToObj(i -> String.format(urlPattern, i))
                .map(this::mockURL)
                .collect(Collectors.toList());
        /* Assigning observers to urls */
        monitor = new PageMonitor();
        urls.forEach(monitor::addMonitoredPage);
        for (int i = 0; i < urls.size(); i++) {
            monitor.addObserver(urls.get(i), observers.get(i * 2));
            monitor.addObserver(urls.get(i), observers.get(i * 2 + 1));
        }
    }

    @After
    public void after() {
        observers = null;
        responses = null;
        urls = null;
        monitor = null;
    }

    private URL mockURL(String urlString) {
        /* Mocking URL using URLStreamHandler because URL class is final so it cannot be mocked. */
        HttpURLConnection mockedConnection = mock(HttpURLConnection.class);
        when(mockedConnection.getLastModified()).thenReturn(responses[0], responses);

        URLStreamHandler stubURLStreamHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL u) {
                return mockedConnection;
            }
        };
        /* Returns mocked URL. */
        try {
            return new URL(null, urlString, stubURLStreamHandler);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
