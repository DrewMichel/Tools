package singular.factor.gcd.driver;

import singular.factor.gcd.GreatestCommonDivisor;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class exists to test gcd package classes
 */
public class GCDDriver
{
    public static void main(String[] args)
    {
        System.out.println("TESTING GREATEST COMMON DIVISOR's FIND GCD");
        System.out.println(GreatestCommonDivisor.findGCD(21,-7));
    }
}
