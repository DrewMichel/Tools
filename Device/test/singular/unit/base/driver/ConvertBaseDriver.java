package singular.unit.base.driver;

/**
 * Created by Andrew Michel on 10/14/2017.
 *
 * This class exists to test base package classes
 */
import singular.unit.base.ConvertBase;

import java.util.Scanner;

public class ConvertBaseDriver
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        String number, converted;
        int startBase = 10, endBase;

        int value, lowIndex = Integer.MAX_VALUE;

        System.out.print("Enter number: ");
        number = keyboard.nextLine();

        //System.out.print("Enter number's base: ");
        //startBase = keyboard.nextInt();
        /*
        System.out.print("Enter desired base: ");
        endBase = keyboard.nextInt();

        converted = ConvertBase.convert(number, startBase, endBase);
        System.out.println("Converted number: " + converted);
        System.out.println("Integer toString: " + Integer.toString(Integer.parseInt(number), endBase));
        */

        /*
        System.out.println("TESTING MAGIC THREE");
        Integer base = findLowestBase(Integer.parseInt(number), 3);
        System.out.println(base);
        */

    }


    public static Integer findLowestBase(int value, int onesPlace)
    {
        String toString;
        String strBase = "" + onesPlace;

        if(value < onesPlace)
        {
            return null;
        }

        for(int i = 4; i <= 10; i++)
        {
            toString = Long.toString(value, i);

            if(toString.substring(toString.length() - 1, toString.length()).equals(strBase))
            {
                return i;
            }
        }


        return value - onesPlace;
    }

}
