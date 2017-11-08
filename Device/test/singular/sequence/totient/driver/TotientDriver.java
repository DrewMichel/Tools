package singular.sequence.totient.driver;

import singular.sequence.totient.Totient;

/**
 * Created by Andrew Michel on 11/8/2017.
 */
public class TotientDriver
{
    public static void main(String[] args)
    {
        for(int i = 0; i <= 100; i++)
        {
            System.out.print(i + ": " + Totient.totient(i) + " " + Totient.totient((long)i) + "\n");
        }
    }
}
