package singular.sequence.prime.driver;

import singular.sequence.prime.PrimeExaminer;
import singular.sequence.prime.PrimeTracker;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class exists to test prime package classes
 */
public class PrimeDriver
{
    public static final long PRIME_PLACE = 100;

    public static void main(String[] args)
    {
        PrimeTracker primeTracker = new PrimeTracker();

        System.out.println("TESTING PRIME INTEGER EXAMINER");
        for(int i = 0; i < PRIME_PLACE; i++)
        {
            if(PrimeExaminer.isPrime(i))
            {
                System.out.print(i + ", ");
            }
        }

        System.out.println("\n\nTESTING PRIME LONG EXAMINER");
        for(long i = 0; i < PRIME_PLACE; i++)
        {
            if(PrimeExaminer.isPrime(i))
            {
                System.out.print(i + ", ");
            }
        }

        System.out.println("\n\nTESTING PRIME TRACKER");
        for(int i = 0; i < PRIME_PLACE; i++)
        {
            System.out.print(primeTracker.nextPrime() + ", ");
        }

        System.out.println();
    }
}
