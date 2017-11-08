package singular.unit.round.driver;

import singular.unit.round.Round;

/**
 * Created by Andrew Michel on 11/8/2017.
 *
 * This class exists to test round package classes
 */
public class RoundDriver
{
    public static void main(String[] args)
    {
        double g = 54578.343456465656;

        int b = 10;

        System.out.printf("%." + b + "f%n", Round.round(g, b));
    }
}
