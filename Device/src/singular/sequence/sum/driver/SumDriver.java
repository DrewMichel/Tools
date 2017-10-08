package singular.sequence.sum.driver;

import singular.sequence.sum.SumCalculation;
import singular.sequence.sum.SumIteration;
import singular.sequence.sum.SumRecursion;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class exists to test the sum package classes
 */
public class SumDriver
{
    public static final int SUM_PLACE = 10;

    public static void main(String[] args)
    {
        System.out.println("TESTING POSITIVE SUM ITERATION");
        for(int i = 0; i <= SUM_PLACE; i++)
        {
            System.out.println(SumIteration.findSum(i));
        }

        System.out.println("TESTING NEGATIVE SUM ITERATION");
        for(int i = 0; i >= -SUM_PLACE; i--)
        {
            System.out.println(SumIteration.findSum((i)));
        }

        System.out.println("\nTESTING POSITIVE SUM RECURSION");
        for(int i = 0; i <= SUM_PLACE; i++)
        {
            System.out.println(SumRecursion.findSum(i));
        }

        System.out.println("\nTESTING NEGATIVE SUM RECURSION");
        for(int i = 0; i >=  -SUM_PLACE; i--)
        {
            System.out.println(SumRecursion.findSum(i));
        }

        System.out.println("\nTESTING POSITIVE SUM CALCULATION");
        for(int i = 0; i <= SUM_PLACE; i++)
        {
            System.out.println(SumCalculation.findSum(i));
        }

        System.out.println("\nTESTING NEGATIVE SUM CALCULATION");
        for(int i = 0; i >= -SUM_PLACE; i--)
        {
            System.out.println(SumCalculation.findSum(i));
        }
    }
}
