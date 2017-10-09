package singular.unit.monetary.driver;

import singular.unit.monetary.ChangeCombination;

import java.util.ArrayList;

/**
 * Created by Andrew Michel on 10/8/2017.
 *
 * this class exists to test monetary package classes
 */
public class ChangeCombinationDriver
{
    public static void main(String[] args)
    {
        ArrayList<int[]> combinations = ChangeCombination.findCombinations(10);

        displayCombinations(combinations);
    }

    /**
     * Iterates through an ArrayList which should have been acquired
     * through the ChangeCombination class
     *
     * Prints out coin names with associated coin quantities and then
     * outputs the total number of combinations
     *
     * @param combinations ArrayList of type int[] that is used to display combinations
     */
    public static void displayCombinations(ArrayList<int[]> combinations)
    {
        int numberOfCombinations = 0;

        for(int i = 0; i < combinations.size(); i++)
        {
            for(int k = 0; k < combinations.get(i).length; k++)
            {
                System.out.print(ChangeCombination.getNameOfIndex(k) + ": " + combinations.get(i)[k] + " ");
            }
            numberOfCombinations++;
            System.out.println();
        }

        System.out.println("NUMBER OF COMBINATIONS: " + numberOfCombinations);
    }
}
