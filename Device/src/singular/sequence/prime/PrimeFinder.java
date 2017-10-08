package singular.sequence.prime;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class contains static methods to locate the next prime number
 * after a previous value
 */
public final class PrimeFinder
{
    // Private constructor
    private PrimeFinder(){}

    /**
     * Wrapper method that prepares to find the next prime number
     *
     * @param previous number preceding the next prime
     * @return the next located prime number
     */
    public static long findNextPrime(long previous)
    {
        if(previous < 2)
        {
            return 2;
        }
        else if(previous == 2)
        {
            return 3;
        }
        else
        {
            return nextPrime(previous);
        }
    }

    /**
     * Wrapper method that prepares to find the next prime number
     *
     * @param previous number preceding the next prime
     * @return the next located prime number
     */
    public static int findNextPrime(int previous)
    {
        if(previous < 2)
        {
            return 2;
        }
        else if(previous == 2)
        {
            return 3;
        }
        else
        {
            return nextPrime(previous);
        }
    }

    /**
     * Begins searching for the next prime number by starting
     * at the previous parameter
     *
     * @param previous number preceding the next prime
     * @return the next located prime number
     */
    private static long nextPrime(long previous)
    {
        long prime = previous;

        if(prime % 2 == 0)
        {
            prime++;
        }
        else
        {
            prime += 2;
        }

        while(!PrimeExaminer.isPrime(prime))
        {
            prime += 2;
        }

        return prime;
    }

    /**
     * Begins searching for the next prime number by starting
     * at the previous parameter
     *
     * @param previous number preceding the next prime
     * @return the next located prime number
     */
    private static int nextPrime(int previous)
    {
        int prime = previous;

        if(prime % 2 == 0)
        {
            prime++;
        }
        else
        {
            prime += 2;
        }

        while(!PrimeExaminer.isPrime(prime))
        {
            prime += 2;
        }

        return prime;
    }
}
