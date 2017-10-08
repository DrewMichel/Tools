package singular.manipulation.uniquesubstring.driver;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class exists to test uniquesubstring package classes
 */

import singular.manipulation.uniquesubstring.UniqueSubstring;

import java.util.Scanner;

public class UniqueSubstringDriver
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter word: ");

        String input = keyboard.nextLine();

        String output = UniqueSubstring.findLongestUniqueSubstring(input);

        System.out.println(output);
        System.out.println(output.length());
    }
}
