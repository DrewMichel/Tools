package singular.manipulation.palindrome;

import singular.manipulation.sanitize.Sanitize;

/**
 * Created by Andrew Michel on 10/14/2017.
 */
public final class PalindromeIteration
{
    // Private constructor
    private PalindromeIteration(){}

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

        return palindrome(sanitized);
    }

    private static boolean palindrome(String sentence)
    {
        int front = 0, back = sentence.length() - 1, halfLength = sentence.length() / 2;

        for(int i = 0; i < halfLength; i++)
        {
            if(sentence.charAt(front) != sentence.charAt(back))
            {
                return false;
            }
            else
            {
                front++;
                back--;
            }
        }

        return true;
    }
}
