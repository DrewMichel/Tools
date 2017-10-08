package singular.manipulation.reversedigit;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class contains static methods to reverse the digits
 * within a whole number while retaining the sign
 *
 * Example: -5679 returns -9765
 *           3742 returns  2473
 */
public final class ReverseDigit
{
    // Private constructor
    private ReverseDigit(){}

    /**
     * Returns a whole number's reversed digit while
     * maintaining the original sign
     *
     * @param digit original value
     * @return reversed digit
     */
    public static final int reverseDigit(int digit)
    {
        StringBuilder temp = new StringBuilder();
        StringBuilder out = new StringBuilder();

        Integer value = digit;

        String sign = "";
        int index = 0;

        if(0 > value)
        {
            sign = "-";
            index = 1;
        }

        temp.append(value.toString().substring(index));
        temp = temp.reverse();

        out.append(sign + temp);

        return Integer.parseInt(out.toString());
    }

    /**
     * Returns a whole number's reversed digit while
     * maintaining the original sign
     *
     * @param digit original value
     * @return reversed digit
     */
    public static final long reverseDigit(long digit)
    {
        StringBuilder temp = new StringBuilder();
        StringBuilder out = new StringBuilder();

        Long value = digit;

        String sign = "";
        int index = 0;

        if(0 > value)
        {
            sign = "-";
            index = 1;
        }

        temp.append(value.toString().substring(index));
        temp = temp.reverse();

        out.append(sign + temp);

        return Long.parseLong(out.toString());
    }
}
