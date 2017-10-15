package singular.unit.base;

/**
 * Created by Andrew Michel on 10/14/2017.
 */
public final class ArithmeticBase
{
    private ArithmeticBase(){}

    public static String add(String first, String second, int commonBase)
    {
        StringBuilder firstBuilder = new StringBuilder(first);
        StringBuilder secondBuilder = new StringBuilder(second);

        adjustLength(firstBuilder, secondBuilder);

        return addTogether(firstBuilder, secondBuilder, commonBase);
    }

    private static void adjustLength(StringBuilder first, StringBuilder second)
    {
        int firstLength = first.length();
        int secondLength = second.length();

        first.reverse();
        second.reverse();

        while(firstLength < secondLength)
        {
            firstLength++;
            first.append("0");
        }

        while(secondLength < firstLength)
        {
            secondLength++;
            second.append("0");
        }

        first.reverse();
        second.reverse();
    }

    private static String addTogether(StringBuilder first, StringBuilder second, int commonBase)
    {
        StringBuilder out = new StringBuilder();

        int firstChar, secondChar, carry = 0, total, offset = '0';

        int length = first.length() - 1;

        for(int i = length; i >= 0; i--)
        {
            firstChar = first.charAt(i) - offset;
            secondChar = second.charAt(i) - offset;

            total = firstChar + secondChar + carry;
            carry = total / commonBase;

            out.append(total % commonBase);
        }

        if(carry > 0)
        {
            out.append(carry);
        }

        out.reverse();

        return out.toString();
    }
}
