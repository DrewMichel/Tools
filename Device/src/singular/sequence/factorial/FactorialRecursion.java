package singular.sequence.factorial;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class contains static methods to find the factorial
 * value of a positive whole number n using recursion
 *
 * Example: n = 5 would return 120
 */
public final class FactorialRecursion
{
    // Private constructor
    private FactorialRecursion(){}

    /**
     * Wrapper class for factorial method
     * @param n desired factorial position
     * @return The factorial value of n
     * @throws InvalidFactorialException if a negative value is entered
     */
    public static long findFactorial(long n) throws InvalidFactorialException
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
     * Finds the factorial value of n using recursion
     *
     * @param n desired factorial position
     * @return the factorial value of n
     */
    private static long factorial(long n)
    {
        if(2 > n)
        {
            return 1;
        }
        else
        {
            return n * factorial(n - 1);
        }
    }
}
