package singular.strategy.hanoi.driver;

import singular.strategy.hanoi.TowersOfHanoi;

import java.util.Scanner;

/**
 * Created by Andrew Michel on 10/15/2017.
 *
 * This class exists to test hanoi package classes.
 */
public class TowersOfHanoiDriver
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        TowersOfHanoi hanoi;

        int disks;

        System.out.print("Enter number of disks: " );

        disks = keyboard.nextInt();

        hanoi = new TowersOfHanoi(disks);

        System.out.println("DISPLAYING ORIGINAL TOWER");
        hanoi.displayTowers();

        System.out.println("\n\n");

        hanoi.solve();

        System.out.println("DISPLAYING SOLVED TOWER");
        hanoi.displayTowers();
        System.out.println("NUMBER OF MOVES: " + hanoi.getNumberOfMoves());
        /*
        System.out.println("\n\n");

        hanoi.reset();

        hanoi.displayTowers();
        */

    }
}