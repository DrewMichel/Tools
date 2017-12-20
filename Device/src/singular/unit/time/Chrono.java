package singular.unit.time;

/**
 * Created by Andrew Michel on 12/20/2017.
 */
public abstract class Chrono
{
    // Move all or specific constants to sub classes?

    // HOURS IN DAY = 2.4e+1
    public static final long HOURS_IN_DAY = 24;

    // MINUTES IN HOUR = 6e+1, MINUTES IN DAY = 1.44e+3
    public static final long MINUTES_IN_HOUR = 60, MINUTES_IN_DAY = MINUTES_IN_HOUR * HOURS_IN_DAY;

    // SECONDS IN MINUTE = 6e+1, SECONDS_IN_HOUR = 3.6e+3, SECONDS IN DAY = 8.64e+4
    public static final long SECONDS_IN_MINUTE = 60, SECONDS_IN_HOUR = SECONDS_IN_MINUTE * MINUTES_IN_HOUR, SECONDS_IN_DAY = SECONDS_IN_HOUR * HOURS_IN_DAY;

    // MILLISECONDS IN SECOND = 1e+3, MILLISECONDS IN MINUTE = 6e+4, MILLISECONDS IN HOUR = 3.6e+6,
    // MILLISECONDS IN DAY = 8.64+7
    public static final long MILLISECONDS_IN_SECOND = 1000, MILLISECONDS_IN_MINUTE = MILLISECONDS_IN_SECOND * SECONDS_IN_MINUTE,
            MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUTE * MINUTES_IN_HOUR, MILLISECONDS_IN_DAY = MILLISECONDS_IN_HOUR * HOURS_IN_DAY;

    // NANOSECONDS IN MILLISECOND = 1e+6, NANOSECONDS IN SECOND = 1e+9, NANOSECONDS IN MINUTE = 6e+10,
    // NANOSECONDS IN HOUR = 3.6e+12, NANOSECONDS IN DAY = 8.64e+13
    public static final long NANOSECONDS_IN_MILLISECOND = 1000000, NANOSECONDS_IN_SECOND = NANOSECONDS_IN_MILLISECOND * MILLISECONDS_IN_SECOND,
            NANOSECONDS_IN_MINUTE = NANOSECONDS_IN_SECOND * SECONDS_IN_MINUTE, NANOSECONDS_IN_HOUR = NANOSECONDS_IN_MINUTE * MINUTES_IN_HOUR,
            NANOSECONDS_IN_DAY = NANOSECONDS_IN_HOUR * HOURS_IN_DAY;

    private long startTime;
    private long endTime;

    public Chrono()
    {
        startTime = 0;
        endTime = startTime;
    }

    public Chrono(long startTime)
    {
        this.startTime = startTime;
        endTime = this.startTime;
    }

    public Chrono(long startTime, long endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public abstract void updateStartTime();

    public abstract void updateEndTime();

    // public abstract long getTotalNanoseconds();

    //public abstract long getTotalMilliseconds();

    public abstract long getTotalSeconds();

    public abstract long getTotalMinutes();

    public abstract long getTotalHours();

    public abstract long getTotalDays();

    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime)
    {
        this.endTime = endTime;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public long getEndTime()
    {
        return endTime;
    }

    public long getElapsedTime()
    {
        return endTime - startTime;
    }
}
