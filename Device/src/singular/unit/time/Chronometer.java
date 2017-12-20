package singular.unit.time;

/**
 * Created by Andrew Michel on 12/20/2017.
 *
 * Measures time using nanoseconds as a base
 */
public class Chronometer extends Chrono
{
    public Chronometer()
    {
        super(System.nanoTime());
    }

    public Chronometer(long startTime)
    {
        super(startTime);
    }

    public Chronometer(long startTime, long endTime)
    {
        super(startTime, endTime);
    }

    @Override
    public void updateStartTime()
    {
        setStartTime(System.nanoTime());
    }

    @Override
    public void updateEndTime()
    {
        setEndTime(System.nanoTime());
    }

    public long getTotalMilliseconds()
    {
        return getElapsedTime() / NANOSECONDS_IN_MILLISECOND;
    }

    @Override
    public long getTotalSeconds()
    {
        return getElapsedTime() / NANOSECONDS_IN_SECOND;
    }

    @Override
    public long getTotalMinutes()
    {
        return getElapsedTime() / NANOSECONDS_IN_MINUTE;
    }

    @Override
    public long getTotalHours()
    {
        return getElapsedTime() / NANOSECONDS_IN_HOUR;
    }

    @Override
    public long getTotalDays()
    {
        return getElapsedTime() / NANOSECONDS_IN_DAY;
    }

    @Override
    public String toString()
    {
        return String.format("Time Elapsed: %d Days %d Hours %d Minutes %d Seconds %d Milliseconds %d Nanoseconds", getTotalDays(), getTotalHours() % Chrono.HOURS_IN_DAY, getTotalMinutes() % Chrono.MINUTES_IN_HOUR, getTotalSeconds() % Chrono.SECONDS_IN_MINUTE, getTotalMilliseconds() % Chrono.MILLISECONDS_IN_SECOND, getElapsedTime() % NANOSECONDS_IN_MILLISECOND);
    }
}
