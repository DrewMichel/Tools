package singular.unit.base.driver;

import singular.unit.base.ArithmeticBase;

import java.util.Scanner;

/**
 * Created by Andrew Michel on 10/14/2017.
 *
 * This class exists to test base package classes
 */
public class ArithmeticBaseDriver
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        String first, second, added, reduced;
        int base;

        System.out.print("Enter first number:  ");
        first = keyboard.nextLine();

        System.out.print("Enter second number: ");
        second = keyboard.nextLine();

        System.out.print("Enter shared base:   ");
        base = keyboard.nextInt();

        added = ArithmeticBase.add(first, second, base);
        reduced = ArithmeticBase.sub(first, second, base);

        System.out.println("Numbers added:       " + String.format("%" + (added.length() + 1) + "s", added));
        System.out.println("Numbers reduced:     " + String.format("%" + (added.length() + 1) + "s", reduced));
    }
}
