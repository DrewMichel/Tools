package singular.factor.lcm.driver;

import singular.factor.lcm.LeastCommonMultiple;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class exists to test lcm package classes
 */
public class LCMDriver
{
    public static void main(String[] args)
    {
        System.out.println("TESTING LEAST COMMON MULTIPLE'S FIND LCM");
        System.out.println(LeastCommonMultiple.findLCM(3, -5));

        System.out.println("TESTING LEAST COMMON MULTIPLE'S FIND LCM ARRAY");
        System.out.println(LeastCommonMultiple.findLCM(new int[]{5,3,5,9,7,10}));
    }
}
