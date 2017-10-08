package singular.manipulation.uniquesubstring;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class contains a static method to locate and return the
 * longest substring without repeating characters
 */
public final class UniqueSubstring
{
    // Private constructor
    private UniqueSubstring() {}

    /**
     * Finds the longest substring without repeating characters
     * within the original parameter String and returns it
     *
     * @param original String
     * @return the longest substring without repeating characters
     */
    public static String findLongestUniqueSubstring(String original)
    {
        String current = "", out = "";

        char currentChar;
        int length = original.length();

        for(int i = 0; i < length; i++)
        {
            currentChar = original.charAt(i);

            if((current.indexOf(currentChar) < 0))
            {
                current = current + currentChar;
            }
            else
            {
                current = "" + currentChar;
            }

            if(current.length() > out.length())
            {
                out = current;
            }
        }

        return out;
    }
}
