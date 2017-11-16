package singular.sequence.prime;

/**
 * Created by Andrew Michel on 11/15/2017.
 *
 * Sieve of Eratosthenes
 */
public final class EratoSieve
{
    public static final boolean DEFAULT_VALUE = true;
    public static final int FIRST_PRIME = 2;
    public static final int DEFAULT_MODIFIER = 2;

    private EratoSieve(){}

    public static boolean[] generateArray(int top)
    {
        return generateArray(top, DEFAULT_VALUE);
    }

    public static boolean[] generateArray(int top, boolean defaultValue)
    {
        boolean[] primes = new boolean[top + 1];
        int length = primes.length, modifier = DEFAULT_MODIFIER;

        if(defaultValue == true)
        {
            for(int i = 2; i < primes.length; i++)
            {
                primes[i] = defaultValue;
            }
        }

        primes[0] = !defaultValue;
        primes[1] = !defaultValue;

        for(long i = FIRST_PRIME; i*i < length; i++)
        {
            modifier = DEFAULT_MODIFIER;

            for(long k = i * modifier; k < length; k = i * ++modifier)
            {
                primes[(int)k] = !defaultValue;
            }
        }

        return primes;
    }


}
