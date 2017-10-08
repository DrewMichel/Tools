package singular.sequence.sum;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class contains static methods to the sum value of
 * a positive or negative whole number using iteration
 */
public final class SumIteration
{
    // Private constructor
    private SumIteration(){}

    /**
     * Finds the sum of n using iteration
     *
     * @param n value used to get the sum of n
     * @return the sum of n
     */
    public static long findSum(long n)
    {
        long sum = 0;
        long abs = Math.abs(n);

        int modifier = 1;

        if(0 > n)
        {
            modifier = -modifier;
        }

        for(long i = 1; i <= abs; i++)
        {
            sum += i;
        }

        return modifier * sum;
    }

    /**
     * Finds the sum of n using iteration
     *
     * @param n value used to get the sum of n
     * @return the sum of n
     */
    public static int findSum(int n)
    {
        int sum = 0;
        int abs = Math.abs(n);

        int modifier = 1;

        if(0 > n)
        {
            modifier = -modifier;
        }

        for(long i = 1; i <= abs; i++)
        {
            sum += i;
        }

        return modifier * sum;
    }
}
