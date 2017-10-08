package singular.factor.lcm;

import singular.factor.gcd.GreatestCommonDivisor;

/**
 * Created by Andrew Michel on 10/7/2017.
 */
public final class LeastCommonMultiple
{
    // Private constructor
    private LeastCommonMultiple(){}

    /**
     * Determines and returns the least common multiple of two whole numbers
     *
     * @param firstDen first parameter
     * @param secondDen second parameter
     * @return the absolute value of the least common multiple of the two parameters
     */
    public static long findLCM(long firstDen, long secondDen)
    {
        return Math.abs(firstDen * (secondDen / GreatestCommonDivisor.findGCD(firstDen, secondDen)));
    }

    /**
     * Determines and returns the least common multiple of two whole numbers
     *
     * @param firstDen first parameter
     * @param secondDen second parameter
     * @return the absolute value of the least common multiple of the two parameters
     */
    public static int findLCM(int firstDen, int secondDen)
    {
        return Math.abs(firstDen * (secondDen / GreatestCommonDivisor.findGCD(firstDen, secondDen)));
    }

    /**
     * Determines and returns the least common multiple of a set of numbers
     *
     * @param denominators array containing values to find the least common multiple
     * @return the least common multiple of the values in parameter
     */
    public static long findLCM(long[] denominators)
    {
        int size = denominators.length;

        long lcm = denominators[0];

        for(int i = 1; i < size; i++)
        {
            lcm = findLCM(lcm, denominators[i]);
        }

        return lcm;
    }

    /**
     * Determines and returns the least common multiple of a set of numbers
     *
     * @param denominators array containing values to find the least common multiple
     * @return the least common multiple of the values in parameter
     */
    public static int findLCM(int[] denominators)
    {
        int size = denominators.length;

        int lcm = denominators[0];

        for(int i = 1; i < size; i++)
        {
            lcm = findLCM(lcm, denominators[i]);
        }

        return lcm;
    }
}
