package singular.sequence.factorial.driver;

import singular.sequence.factorial.FactorialIteration;
import singular.sequence.factorial.FactorialRecursion;
import singular.sequence.factorial.FactorialTracker;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class exists to test the factorial package classes
 */

public class FactorialDriver
{
    public static final int FACTORIAL_PLACE = 10;

    public static void main(String[] args)
    {

        FactorialTracker factorial = new FactorialTracker();

        System.out.println("\nTESTING FACTORIAL TRACKER");

        // offset by 1 because first factorial value is used as the starting point
        for(int i = 0; i < FACTORIAL_PLACE; i++)
        {
            System.out.println(factorial.nextFactorial());
        }

        System.out.println();
        System.out.println("FACTORIAL TRACKER PREVIOUS:   " + factorial.getPreviousFactorial());
        System.out.println("FACTORIAL TRACKER CURRENT:    " + factorial.getCurrentFactorial());

        System.out.println("\nTESTING FACTORIAL RECURSION");
        for(int i = 0; i < FACTORIAL_PLACE; i++)
        {
            System.out.println(FactorialRecursion.findFactorial(i));
        }

        System.out.println("\nTESTING FACTORIAL ITERATION");
        for(int i = 0; i < FACTORIAL_PLACE; i++)
        {
            System.out.println(FactorialIteration.findFactorial(i));
        }


    }
}
