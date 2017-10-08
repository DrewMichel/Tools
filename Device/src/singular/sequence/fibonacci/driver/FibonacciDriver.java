package singular.sequence.fibonacci.driver;

import singular.sequence.fibonacci.FibonacciIteration;
import singular.sequence.fibonacci.FibonacciRecursion;

/**
 * Created by Andrew Michel on 10/8/2017.
 *
 * This class exists to test fibonacci package classes
 */
public class FibonacciDriver
{
    public static final int FIBONACCI_PLACE = 20;

    public static void main(String[] args)
    {
        System.out.println("TESTING POSITIVE FIBONACCI RECURSION");
        for(long i = 0; i < FIBONACCI_PLACE; i++)
        {
            System.out.printf("%5d, ", FibonacciRecursion.findFibonacci(i));
        }

        System.out.println("\n");

        System.out.println("TESTING NEGATIVE FIBONACCI RECURSION");
        for(long i = 0; i > -FIBONACCI_PLACE; i--)
        {
            System.out.printf("%5d, ", FibonacciRecursion.findFibonacci(i));
        }

        System.out.println("\n");

        System.out.println("TESTING POSITIVE FIBONACCI ITERATION");
        for(long i = 0; i < FIBONACCI_PLACE; i++)
        {
            System.out.printf("%5d, ", FibonacciIteration.findFibonacci(i));
        }

        System.out.println("\n");

        System.out.println("TESTING NEGATIVE FIBONACCI ITERATION");
        for(long i = 0; i > -FIBONACCI_PLACE; i--)
        {
            System.out.printf("%5d, ", FibonacciIteration.findFibonacci(i));
        }

        System.out.println();
    }
}
