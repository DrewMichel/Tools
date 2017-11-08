package singular.sequence.totient;

/**
 * Created by Andrew Michel on 11/8/2017.
 */
public final class Totient
{
    private Totient(){}

    public static int totient(int number)
    {
        int output = number;

        for(long prime = 2; prime * prime <= number; prime++)
        {
            if(number % prime == 0)
            {
                while(number % prime == 0)
                {
                    number /= prime;
                }

                output -= output / prime;
            }
        }

        if(number > 1)
        {
            output -= output / number;
        }

        return output;
    }

    public static long totient(long number)
    {
        long output = number;

        for(long prime = 2; prime * prime <= number; prime++)
        {
            if(number % prime == 0)
            {
                while(number % prime == 0)
                {
                    number /= prime;
                }

                output -= output / prime;
            }
        }

        if(number > 1)
        {
            output -= output / number;
        }

        return output;
    }
}
