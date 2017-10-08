package singular.sequence.sum;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class contains static methods to the sum value of
 * a positive or negative whole number using recursion
 */
public final class SumRecursion
{
    // Private constructor
    private SumRecursion(){}

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
     * Finds the sum of n using recursion
     *
     * @param n value used to get the sum of n
     * @return the sum of n
     */
    private static long sum(long n)
    {
        if(n == 0)
        {
            return 0;
        }
        else
        {
            return n + sum(n - 1);
        }
    }

    /**
     * Finds the sum of n using recursion
     *
     * @param n value used to get the sum of n
     * @return the sum of n
     */
    private static int sum(int n)
    {
        if(n == 0)
        {
            return 0;
        }
        else
        {
            return n + sum(n - 1);
        }
    }
}
