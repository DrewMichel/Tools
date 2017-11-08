package singular.unit.round;

/**
 * Created by Andrew Michel on 11/8/2017.
 */
public final class Round
{
    private Round(){}

    public static double round(double value, long places)
    {
        double modifier = Math.pow(10, places);

        return Math.round(value * modifier) / modifier; // multiplies value, rounds, divides back
    }
}
