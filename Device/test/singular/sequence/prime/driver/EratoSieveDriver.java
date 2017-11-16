package singular.sequence.prime.driver;

import singular.sequence.prime.EratoSieve;

/**
 * Created by Andrew Michel on 11/15/2017.
 */
public class EratoSieveDriver
{
    public static final double NANOSECONDS_IN_SECOND = 1000000000;

    public static void main(String[] args)
    {/*
        int top = 10000000;
        int amount = 0;
        double start = 0, finish = 0;

        start = System.nanoTime();
        boolean[] primes = EratoSieve.generateArray(top);
        finish = (System.nanoTime() - start) / NANOSECONDS_IN_SECOND;

        for(int i = 0; i < primes.length; i++)
        {
            if(primes[i])
            {
                amount++;
            }
        }

        System.out.println("SIEVE OF ERATOSTHENES");
        System.out.println("TOP: " + top);
        System.out.println("AMOUNT: " + amount);
        System.out.printf("SECONDS: %.10f%n", finish);
        */

        int top = Integer.MAX_VALUE / 2;
        int amount = 0;
        double start = 0, finish = 0;

        System.out.println("SIEVE OF ERATOSTHENES");
        System.out.println("TOP: " + top);

        start = System.nanoTime();
        boolean[] primes = EratoSieve.generateArray(top, false);
        finish = (System.nanoTime() - start) / NANOSECONDS_IN_SECOND;

        for(int i = 0; i < primes.length; i++)
        {
            if(!primes[i])
            {
                amount++;
            }
        }

        System.out.println("AMOUNT: " + amount);
        System.out.printf("SECONDS: %.10f%n", finish);
    }
}
