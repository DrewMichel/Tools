package singular.sequence.factorial;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class contains static methods to find the factorial
 * value of a positive whole number n using iteration
 *
 * Example: n = 5 would return 120
 */
public final class FactorialIteration
{
    // Private constructor
    private FactorialIteration() {}

    /**
     * Wrapper class for factorial method
     * @param n desired factorial position
     * @return The factorial value of n
     * @throws InvalidFactorialException if a negative value is entered
     */
    public static final long findFactorial(long n) throws InvalidFactorialException
    {
        if(0 > n)
        {
            throw new InvalidFactorialException();
        }
        else
        {
            return factorial(n);
        }
    }

    /**
     * Finds the factorial value of n using iteration
     *
     * @param n desired factorial position
     * @return the factorial value of n
     */
    private static final long factorial(long n)
    {
        long factorial = 1;

        for(long i = 2; i <= n; i++)
        {
            factorial *= i;
        }

        return factorial;
    }
}
