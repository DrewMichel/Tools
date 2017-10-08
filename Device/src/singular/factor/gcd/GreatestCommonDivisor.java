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
     * @return greatest common divisor of the numerator and denominator
     */
    public static long findGCD(long numerator, long denominator)
    {
        long gcd, swap;

        while(numerator != 0 && denominator != 0)
        {
            swap = denominator;
            denominator = numerator % denominator;
            numerator = swap;
        }

        gcd = numerator + denominator;

        return gcd;
    }

    /**
     * Determines and returns the gcd of the two parameters
     *
     * @param numerator parameter value
     * @param denominator parameter value
     * @return greatest common divisor of the numerator and denominator
     */
    public static int findGCD(int numerator, int denominator)
    {
        int gcd, swap;

        while(numerator != 0 && denominator != 0)
        {
            swap = denominator;
            denominator = numerator % denominator;
            numerator = swap;
        }

        gcd = numerator + denominator;

        return gcd;
    }
}
