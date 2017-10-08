package singular.sequence.fibonacci;

/**
 * Created by Andrew Michel on 10/8/2017.
 *
 * This class contains static methods to location an nth fibonacci number
 * using iteration
 */
public final class FibonacciIteration
{
    // Private constructor
    private FibonacciIteration() {}

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

        return modifier * fibonacci(n);
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

        return modifier * fibonacci(n);
    }

    /**
     * Acquires the nth fibonacci number
     *
     * @param n the nth number in the fibonacci sequence
     * @return the fibonacci number based on initial nth number
     */
    private static long fibonacci(long n)
    {
        long currentFib = 1, previousFib = 0, swap;

        for(long i = 1; i < n; i++)
        {
            swap = currentFib;
            currentFib = currentFib + previousFib;
            previousFib = swap;
        }

        return currentFib;
    }

    /**
     * Acquires the nth fibonacci number
     *
     * @param n the nth number in the fibonacci sequence
     * @return the fibonacci number based on initial nth number
     */
    private static int fibonacci(int n)
    {
        int currentFib = 1, previousFib = 0, swap;

        for(long i = 1; i < n; i++)
        {
            swap = currentFib;
            currentFib = currentFib + previousFib;
            previousFib = swap;
        }

        return currentFib;
    }
}
