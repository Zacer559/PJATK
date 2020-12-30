package sample;

import java.util.concurrent.TimeUnit;


public class Timee {
    private long time;
    private String timeString;
    private long minutes;
    private long seconds;
    private long millis;

    public Timee() {
        this.time = 0;
        this.timeString = "00:00:00";
        this.minutes = 0;
        this.seconds = 0;
        this.millis = 0;

    }

    public Timee(int minutes, int seconds, int millis) {
        this.time = 0;
        timeString = String.format("%02d:%02d:%d", minutes, seconds, millis);
        this.minutes = minutes;
        this.seconds = seconds;
        this.millis = millis;
    }


    public void updateTime() {

        minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        seconds = TimeUnit.MILLISECONDS.toSeconds(time) - minutes * 60;
        millis = (time - seconds * 1000 - minutes * 60000) / 100;


        time += 100;
    }

    public void setZero() {
        this.minutes = 0;
        this.seconds = 0;
        this.time = 0;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long intTime() {
        return millis + seconds * 100 + minutes * 100 * 60;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }
}
