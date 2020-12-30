import java.util.Properties;

public class Configuration {
    public int interval;

    public int duration;

    public int departure;

    // dividing big method to 3 smaller
    // method 1 interval
    public int checkInterval(Properties props) throws ConfigurationException {
        //assigning value to string at the beginning
        String valueOfString = props.getProperty("interval");
        int value;

        if (valueOfString == null) {
            //removed "monitor" from exception string
            throw new ConfigurationException("interval");
        }
        value = Integer.parseInt(valueOfString);
        if (value <= 0) {
            //changed exception string to correct one (earlier monitor interval > 0)
            throw new ConfigurationException("interval <= 0");
        }

        return value;
    }

    // method 2 duration
    public int checkDuration(Properties props) throws ConfigurationException {
        //assigning value to string at the beginning
        String valueOfString = props.getProperty("duration");
        int value;

        if (valueOfString == null) {
            throw new ConfigurationException("duration");
        }
        value = Integer.parseInt(valueOfString);
        if (value <= 0) {
            //changed exception string from duration > 0 to correct.
            throw new ConfigurationException("duration <= 0");
        } else if ((value % interval) != 0) {
            throw new ConfigurationException("duration % interval");
        }

        return value;
    }

    //method 3 departure
    public int checkDeparture(Properties props) throws ConfigurationException {
        //assigning value to string at the beginning
        String valueOfString = props.getProperty("departure");
        int value;

        if (valueOfString == null) {
            //changed from departure offset to departure to get more monolithic errors.
            throw new ConfigurationException("departure");
        }
        value = Integer.parseInt(valueOfString);
        if (value <= 0) {
            //changed exception string from duration > 0 to correct.
            throw new ConfigurationException("departure <= 0");
        } else if ((value % interval) != 0) {
            throw new ConfigurationException("departure % interval");
        }

        return value;
    }

    // calling all "smaller" methods
    public void load(Properties props) throws ConfigurationException {
        interval = checkInterval(props);
        duration = checkDuration(props);
        departure = checkDeparture(props);
    }
}
