package singular.sequence.fibonacci;

/**
 * Created by Andrew Michel on 10/8/2017.
 *
 * This class contains static methods to location an nth fibonacci number
 * using recursion
 */
public final class FibonacciRecursion
{
    // Private constructor
    private FibonacciRecursion(){}


    /**
     * Wrapper method that returns the nth fibonacci number
     *
     * @param n used to acquire the nth fibonacci number
     * @return the nth fibonacci number
     */
    public static long findFibonacci(long n)
    {
        int modifier = 1;

        if(n == 0)
        {
            return 0;
        }
        else if(0 > n && n % 2 == 0)
        {
            modifier = -modifier;
        }
        else if(n == 1 || n == 2)
        {
            return 1;
        }

        n = Math.abs(n);

        return modifier * fibonacci(n, 1, 0);
    }


    /**
     * Wrapper method that returns the nth fibonacci number
     *
     * @param n used to acquire the nth fibonacci number
     * @return the nth fibonacci number
     */
    public static int findFibonacci(int n)
    {
        int modifier = 1;

        if(n == 0)
        {
            return 0;
        }
        else if(0 > n && n % 2 == 0)
        {
            modifier = -modifier;
        }
        else if(n == 1 || n == 2)
        {
            return 1;
        }

        n = Math.abs(n);

        return modifier * fibonacci(n, 1, 0);
    }

    /**
     * Acquires the fibonacci value after nth iterations
     *
     * @param iterations the nth number
     * @param current fibonacci number
     * @param previous fibonacci number
     * @return the fibonacci number based on initial nth number
     */
    private static long fibonacci(long iterations, long current, long previous)
    {
        if(iterations <= 1)
        {
            return current;
        }
        else
        {
            return fibonacci(iterations - 1, current + previous, current);
        }
    }

    /**
     * Acquires the fibonacci value after nth iterations
     *
     * @param iterations the nth number
     * @param current fibonacci number
     * @param previous fibonacci number
     * @return the fibonacci number based on initial nth number
     */
    private static int fibonacci(int iterations, int current, int previous)
    {
        if(iterations <= 1)
        {
            return current;
        }
        else
        {
            return fibonacci(iterations - 1, current + previous, current);
        }
    }
}
