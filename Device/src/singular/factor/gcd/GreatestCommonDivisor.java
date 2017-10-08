package singular.factor.gcd;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class contains static methods to locate and return
 * the greatest common divisor of two whole numbers
 */
public final class GreatestCommonDivisor
{
    // Private constructor
    private GreatestCommonDivisor(){}

    /**
     * Determines and returns the gcd of the two parameters
     *
     * @param numerator parameter value
     * @param denominator parameter value
     * @return the absolute value of the greatest common divisor of the numerator and denominator
     */
    public static long findGCD(long numerator, long denominator)
    {
        long swap;

        while(numerator != 0 && denominator != 0)
        {
            swap = denominator;
            denominator = numerator % denominator;
            numerator = swap;
        }

        return Math.abs(numerator + denominator);
    }

    /**
     * Determines and returns the gcd of the two parameters
     *
     * @param numerator parameter value
     * @param denominator parameter value
     * @return the absolute value of the greatest common divisor of the numerator and denominator
     */
    public static int findGCD(int numerator, int denominator)
    {
        int swap;

        while(numerator != 0 && denominator != 0)
        {
            swap = denominator;
            denominator = numerator % denominator;
            numerator = swap;
        }

        return Math.abs(numerator + denominator);
    }
}
