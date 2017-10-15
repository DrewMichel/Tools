package singular.manipulation.palindrome;

import singular.manipulation.sanitize.Sanitize;

/**
 * Created by Andrew Michel on 10/14/2017.
 */
public final class PalindromeRecursion
{
    // Private constructor
    private PalindromeRecursion(){}

    public static boolean isPalindrome(String sentence)
    {
        if(sentence == null)
        {
            return false;
        }

        String sanitized = Sanitize.sanitize(sentence, true,true,true).toLowerCase();

        if(sanitized.length() == 0)
        {
            return false;
        }

        return palindrome(sanitized, 0, sanitized.length() - 1);
    }

    private static boolean palindrome(String sentence, int front, int back)
    {
       if(front >= back)
       {
           return true;
       }
       else if(sentence.charAt(front) != sentence.charAt(back))
       {
           return false;
       }
       else
       {
           return palindrome(sentence, front + 1, back - 1);
       }
    }
}
