package singular.unit.base;

/**
 * Created by Andrew Michel on 10/14/2017.
 */
public final class ConvertBase
{
    // Private constructor
    private ConvertBase(){}

    public static String convert(String number, int startBase, int endBase)
    {
        StringBuilder sb = new StringBuilder();

        long carry = 0, convert = Long.parseLong(number);

        do
        {
            carry = convert % endBase;
            convert = convert / endBase;

            sb.append(carry);

        }while(convert != 0);

        sb.reverse();

        return sb.toString();
    }

    public static String convert(String number, long startBase, long endBase)
    {
        StringBuilder sb = new StringBuilder();

        long carry = 0, convert = Long.parseLong(number);

        do
        {
            carry = convert % endBase;
            convert = convert / endBase;

            sb.append(carry);

        }while(convert != 0);

        sb.reverse();

        return sb.toString();
    }
}
