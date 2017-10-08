package singular.sequence.sum;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class contains static methods to the sum value of
 * a positive or negative whole number using a calculation
 *
 * Calculation: (sum of n) = ((n * (n + 1)) / 2)
 * Example: 55 = ((10 * (10 + 1)) / 2)
 */
public final class SumCalculation
{
    // Private constructor
    private SumCalculation(){}

    /**
     * Wrapper method which returns the sum of n
     *
     * @param n value used to get the sum of n
     * @return the sum of n
     */
    public static long findSum(long n)
    {
        int modifier = 1;

        if(0 > n)
        {
            modifier = -modifier;
        }

        return modifier * sum(Math.abs(n));
    }

    /**
     * Wrapper method which returns the sum of n
     *
     * @param n value used to get the sum of n
     * @return the sum of n
     */
    public static int findSum(int n)
    {
        int modifier = 1;

        if(0 > n)
        {
            modifier = -modifier;
        }

        return modifier * sum(Math.abs(n));
    }

    /**
     * Finds the sum of n using the calculation
     *
     * @param n value used to get the sum of n
     * @return the sum of n
     */
    private static long sum(long n)
    {
        return ((n * (n + 1)) / 2);
    }

    /**
     * Finds the sum of n using the calculation
     *
     * @param n value used to get the sum of n
     * @return the sum of n
     */
    private static int sum(int n)
    {
        return ((n * (n + 1)) / 2);
    }
}
