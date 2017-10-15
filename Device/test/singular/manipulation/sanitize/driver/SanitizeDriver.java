package singular.manipulation.sanitize.driver;

import singular.manipulation.sanitize.Sanitize;

/**
 * Created by Andrew Michel on 10/14/2017.
 *
 * This class exists to test sanitize package classes
 */
public class SanitizeDriver
{
    public static void main(String[] args)
    {
        String tester = "abc def 123 &&,.sentence, word2 #test.!?";

        System.out.println("TESTING SANITIZE\n");
        System.out.println("ORIGINAL: " + tester);

        System.out.println("\nWITHOUT PUNCTUATION");
        System.out.println(Sanitize.sanitize(tester, true, false, false));

        System.out.println("\nWITHOUT NUMBERS");
        System.out.println(Sanitize.sanitize(tester, false, true, false));

        System.out.println("\nWITHOUT SPACES");
        System.out.println(Sanitize.sanitize(tester, false, false, true));

        System.out.println("\nWITHOUT PUNCTUATIONS AND NUMBERS");
        System.out.println(Sanitize.sanitize(tester, true, true, false));

        System.out.println("\nWITHOUT PUNCTUATIONS AND SPACES");
        System.out.println(Sanitize.sanitize(tester, true, false, true));

        System.out.println("\nWITHOUT NUMBERS AND SPACES");
        System.out.println(Sanitize.sanitize(tester, false, true, true));

        System.out.println("\nWITHOUT PUNCTUATIONS, NUMBERS, AND SPACES");
        System.out.println(Sanitize.sanitize(tester, true, true, true));
    }
}
