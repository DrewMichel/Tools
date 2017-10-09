package singular.manipulation.reversedigit.driver;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class exists to test reversedigit package classes
 */

import singular.manipulation.reversedigit.ReverseDigit;

public class ReverseDigitDriver
{
    public static final int TEST_INT = 57329;
    public static final long TEST_LONG = 7435762347834L;

    public static void main(String[] args)
    {
        System.out.println("TESTING POSITIVE INTEGER REVERSE");
        System.out.println(ReverseDigit.reverseDigit(TEST_INT));

        System.out.println("\nTESTING NEGATIVE INTEGER REVERSE");
        System.out.println(ReverseDigit.reverseDigit(-TEST_INT));

        System.out.println("\n" + TEST_INT);

        System.out.println("\nTESTING POSITIVE LONG REVERSE");
        System.out.println(ReverseDigit.reverseDigit(TEST_LONG));

        System.out.println("\nTESTING NEGATIVE LONG REVERSE");
        System.out.println(ReverseDigit.reverseDigit(-TEST_LONG));

        System.out.println("\n" + TEST_LONG);
    }
}
