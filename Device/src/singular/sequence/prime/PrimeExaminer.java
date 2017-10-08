package singular.sequence.prime;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class contains static methods to verify
 * if a whole number is prime
 */
public final class PrimeExaminer
{
    // Private constructor
    private PrimeExaminer(){}

    /**
     * Wrapper method that determines if the parameter is a prime number
     *
     * @param value whole number that is checked for prime status
     * @return true if parameter is prime, else false
     */
    public static boolean isPrime(int value)
    {
        if(2 > value)
        {
            return false;
        }
        else if(value == 2)
        {
            return true;
        }
        else if(value % 2 == 0)
        {
            return false;
        }
        else
        {
            return verifyPrime(value);
        }
    }

    /**
     * Wrapper method that determines if the parameter is a prime number
     *
     * @param value whole number that is checked for prime status
     * @return true if parameter is prime, else false
     */
    public static boolean isPrime(long value)
    {
        if(2 > value)
        {
            return false;
        }
        else if(value == 2)
        {
            return true;
        }
        else if(value % 2 == 0)
        {
            return false;
        }
        else
        {
            return verifyPrime(value);
        }
    }

    /**
     * Determines if the parameter value is a prime number
     *
     * @param value whole number that is checked for prime status
     * @return true if parameter is prime, else false
     */
    private static boolean verifyPrime(int value)
    {
        int size = value / 2;

        for(int i = 3; i < size; i += 2)
        {
            if(value % i == 0)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Determines if the parameter value is a prime number
     *
     * @param value whole number that is checked for prime status
     * @return true if parameter is prime, else false
     */
    private static boolean verifyPrime(long value)
    {
        long size = value / 2;

        for(long i = 3; i < size; i += 2)
        {
            if(value % i == 0)
            {
                return false;
            }
        }

        return true;
    }
}
