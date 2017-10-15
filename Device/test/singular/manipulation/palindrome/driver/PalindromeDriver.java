package singular.manipulation.palindrome.driver;

import singular.manipulation.palindrome.PalindromeIteration;
import singular.manipulation.palindrome.PalindromeRecursion;

import java.util.Scanner;

/**
 * Created by Andrew Michel on 10/14/2017.
 *
 * This class exists to test palindrome package classes
 */
public class PalindromeDriver
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        String input;

        System.out.print("Enter sentence: ");

        input = keyboard.nextLine();

        System.out.println("TESTING PALINDROME RECURSION");
        System.out.println(input + " is a palindrome: " + PalindromeRecursion.isPalindrome(input));

        System.out.println("\nTESTING PALINDROME ITERATION");
        System.out.println(input + " is a palindrome: " + PalindromeIteration.isPalindrome(input));
    }
}
