package singular.unit.base;

/**
 * Created by Andrew Michel on 10/14/2017.
 */
public final class ArithmeticBase
{
    private ArithmeticBase(){}

    public static String sub(String minuend, String subtrahend, int commonBase)
    {
        StringBuilder minuendBuilder = new StringBuilder(minuend);
        StringBuilder subtrahendBuilder = new StringBuilder(subtrahend);

        adjustLength(minuendBuilder, subtrahendBuilder);

        String sign = "";

        if(Integer.parseInt(subtrahend) > Integer.parseInt(minuend))
        {
            sign = "-";
            StringBuilder swap = minuendBuilder;
            minuendBuilder = subtrahendBuilder;
            subtrahendBuilder = swap;
        }

        return sign + performSubtraction(minuendBuilder, subtrahendBuilder, commonBase);
    }

    private static String performSubtraction(StringBuilder minuend, StringBuilder subtrahend, int commonBase)
    {
        StringBuilder out = new StringBuilder();

        int firstChar, secondChar, carry = 0, total = 0, offset = '0';

        int length = minuend.length() - 1;

        /*
        if(Integer.parseInt(subtrahend.toString()) > Integer.parseInt(minuend.toString()))
        {
            out.append("-");
        }
        */

        for(int i = length; i >= 0; i--)
        {
            firstChar = minuend.charAt(i) - offset;
            secondChar = subtrahend.charAt(i) - offset;

            if(secondChar + carry > firstChar)
            {
                total = commonBase + firstChar - secondChar - carry;
                carry = 1;
            }
            else
            {
                total = firstChar - secondChar - carry;
                carry = 0;
            }

            out.append(total); // % commonBase
        }

        while(out.length() > 0 && out.charAt(out.length() - 1) == '0')
        {
            out.setLength(out.length() - 1);
        }

        out.reverse();

        return out.toString();
    }

    public static String add(String first, String second, int commonBase)
    {
        StringBuilder firstBuilder = new StringBuilder(first);
        StringBuilder secondBuilder = new StringBuilder(second);

        adjustLength(firstBuilder, secondBuilder);

        return performAddition(firstBuilder, secondBuilder, commonBase);
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

    private static String performAddition(StringBuilder first, StringBuilder second, int commonBase)
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
