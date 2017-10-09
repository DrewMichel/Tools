package singular.unit.monetary;

import java.util.ArrayList;

/**
 * Created by Andrew Michel on 10/8/2017.
 *
 * This class contains static methods to find the possible
 * combinations of change given a value in change and return them
 * as an ArrayList of type int[]
 *
 * Yields at minimum 1 combination even with zero or negative change
 * Theoretically given zero change, the one matching combination would be zero of every coin
 *
 * Example: 1 dollar = 100, 42 cents = 42
 */
public final class ChangeCombination
{
    // Declaring constants
    public static final int QUARTER_VALUE = 25;
    public static final int DIME_VALUE = 10;
    public static final int NICKEL_VALUE = 5;
    public static final int PENNY_VALUE = 1;

    // Creating coin value and name parallel arrays
    private static final int[] COIN_VALUES = {QUARTER_VALUE, DIME_VALUE, NICKEL_VALUE, PENNY_VALUE};
    private static final String[] COIN_NAMES = {"QUARTERS", "DIMES", "NICKELS", "PENNIES"};

    private ChangeCombination(){}

    /**
     * Wrapper method that returns an ArrayList of type int[] containing all the possible change combinations
     * for the original parameter
     *
     * @param original the amount of money in change
     * @return ArrayList of type int[] containing the combinations
     */
    public static ArrayList<int[]> findCombinations(int original)
    {
        ArrayList<int[]> combinations = new ArrayList<>();

        int[] quantity = new int[COIN_VALUES.length];

        combinations(combinations, original, 0, quantity);

        return combinations;
    }

    /**
     * Recursion method that adds combinations into an ArrayList of type int[]
     *
     * @param list ArrayList of type int[] that receives the combinations
     * @param amount the remaining change
     * @param index position used to modify or access array values appropriately
     * @param quantity int[] storing current combination values.  Added to list after exhausting amount.
     */
    private static void combinations(ArrayList<int[]> list, int amount, int index, int[] quantity)
    {
        if(amount > 0)
        {
            int[] newQuantity = copyArray(quantity);

            if(amount / COIN_VALUES[index] > 0)
            {
                quantity[index]++;
                combinations(list, amount - COIN_VALUES[index], index, quantity);
            }

            if(COIN_VALUES.length > index + 1)
            {
                combinations(list, amount, index + 1, newQuantity);
            }
        }
        else
        {
            list.add(quantity);
        }
    }

    /**
     * Can be used to determine the number of coins that this class supports
     *
     * @return the number of supported coin types
     */
    public static int getNumberOfTypes()
    {
        return COIN_VALUES.length;
    }

    /**
     * Returns the String name associated with the index
     *
     * @param index position that the coin name resides
     * @return a coin's name
     */
    public static String getNameOfIndex(int index)
    {
        return COIN_NAMES[index];
    }

    /**
     * Copies the elements and size of the original parameter into a new int array
     * and returns the copy
     *
     * @param original int array that has its size and elements copied into a new int array
     * @return a new int array with the size and elements from the original
     */
    private static int[] copyArray(int[] original)
    {
        int[] copy = new int[original.length];

        for(int i = 0; i < copy.length; i++)
        {
            copy[i] = original[i];
        }

        return copy;
    }
}
