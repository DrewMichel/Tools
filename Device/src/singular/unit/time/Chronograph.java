package singular.unit.time;

/**
 * Created by Andrew Michel on 12/20/2017.
 *
 * Measures time using milliseconds as a base
 */
public class Chronograph extends Chrono
{
    public Chronograph()
    {
        super(System.currentTimeMillis());
    }

    public Chronograph(long startTime)
    {
        super(startTime);
    }

    public Chronograph(long startTime, long endTime)
    {
        super(startTime, endTime);
    }

    @Override
    public void updateStartTime()
    {
        setStartTime(System.currentTimeMillis());
    }

    @Override
    public void updateEndTime()
    {
        setEndTime(System.currentTimeMillis());
    }

    @Override
    public long getTotalSeconds()
    {
        return getElapsedTime() / Chrono.MILLISECONDS_IN_SECOND;
    }

    @Override
    public long getTotalMinutes()
    {
        return getElapsedTime() / Chrono.MILLISECONDS_IN_MINUTE;
    }

    @Override
    public long getTotalHours()
    {
        return getElapsedTime() / Chrono.MILLISECONDS_IN_HOUR;
    }

    @Override
    public long getTotalDays()
    {
        return getElapsedTime() / Chrono.MILLISECONDS_IN_DAY;
    }

    @Override
    public String toString()
    {
        return String.format("Time Elapsed: %d Days %d Hours %d Minutes %d Seconds %d Milliseconds", getTotalDays(), getTotalHours() % Chrono.HOURS_IN_DAY, getTotalMinutes() % Chrono.MINUTES_IN_HOUR, getTotalSeconds() % Chrono.SECONDS_IN_MINUTE, getElapsedTime() % Chrono.MILLISECONDS_IN_SECOND);
    }
}
