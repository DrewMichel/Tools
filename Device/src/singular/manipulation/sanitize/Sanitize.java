package singular.manipulation.sanitize;

/**
 * Created by Andrew Michel on 10/14/2017.
 */
public final class Sanitize
{
    public static final boolean DEFAULT_REMOVE_PUNCTUATION = true, DEFAULT_REMOVE_NUMBERS = true,
                                DEFAULT_REMOVE_SPACES = false;

    public static final int SPACE_VALUE = ' ', MIN_UPPERCASE_LETTER_RANGE = 65,
                            MAX_UPPERCASE_LETTER_RANGE = 90, MIN_LOWERCASE_LETTER_RANGE = 97,
                            MAX_LOWERCASE_LETTER_RANGE = 122, MIN_NUMBER_RANGE = 48, MAX_NUMBER_RANGE = 57;

    private static final char[] PUNCTUATIONS = {'.', ',', '?', '!', '\'', '"', ':', ';', '-',
                                                '(', ')', '[', ']', '&', '@', '$', '%', '~', '#'};

    // Private constructor
    private Sanitize(){}

    public static String sanitize(String original)
    {
        return sanitize(new StringBuilder(original), DEFAULT_REMOVE_PUNCTUATION, DEFAULT_REMOVE_NUMBERS, DEFAULT_REMOVE_SPACES);
    }

    public static String sanitize(StringBuilder original)
    {
        return sanitize(original, DEFAULT_REMOVE_PUNCTUATION, DEFAULT_REMOVE_NUMBERS, DEFAULT_REMOVE_SPACES);
    }

    public static String sanitize(String original, boolean removePunctuation, boolean removeNumbers, boolean removeSpaces)
    {
        return sanitize(new StringBuilder(original), removePunctuation, removeNumbers, removeSpaces);
    }

    public static String sanitize(StringBuilder original, boolean removePunctuation, boolean removeNumbers, boolean removeSpaces)
    {
        StringBuilder out = new StringBuilder();

        int length = original.length();
        char currentChar;

        for(int i = 0; i < length; i++)
        {
            currentChar = original.charAt(i);

            if(isLetter(currentChar))
            {
                out.append(currentChar);
            }
            else if(removeNumbers == false && isNumber(currentChar))
            {
                out.append(currentChar);
            }
            else if(removeSpaces == false && currentChar == SPACE_VALUE)
            {
                out.append(currentChar);
            }
            else if(removePunctuation == false && isPunctuation(currentChar))
            {
                out.append(currentChar);
            }
        }

        return out.toString();
    }

    private static boolean isLetter(char character)
    {
        return ((character >= MIN_UPPERCASE_LETTER_RANGE && character <= MAX_UPPERCASE_LETTER_RANGE) || (character >= MIN_LOWERCASE_LETTER_RANGE && character <= MAX_LOWERCASE_LETTER_RANGE));
    }

    private static boolean isNumber(char character)
    {
        return (character >= MIN_NUMBER_RANGE && character <= MAX_NUMBER_RANGE);
    }

    private static boolean isPunctuation(char character)
    {
        for(int i = 0; i < PUNCTUATIONS.length; i++)
        {
            if(character == PUNCTUATIONS[i])
            {
                return true;
            }
        }

        return false;
    }

    public static int punctuationLength()
    {
        return PUNCTUATIONS.length;
    }

    public static char punctuationValue(int index)
    {
        return PUNCTUATIONS[index];
    }
}
