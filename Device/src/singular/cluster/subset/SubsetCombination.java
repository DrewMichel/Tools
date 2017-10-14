package singular.cluster.subset;

import java.util.ArrayList;

public final class SubsetCombination
{
    public static final int DEFAULT_TARGET = 5;

    // Private constructor
    private SubsetCombination(){}

    public static ArrayList<ArrayList<Integer>> findSubsetCombinations(ArrayList<Integer> original, Integer targetValue)
    {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();

        ArrayList<Integer> copy = new ArrayList<>(original);

        findCombinations(copy, targetValue, combinations,0);

        return combinations;
    }

    private static void findCombinations(ArrayList<Integer> original, Integer targetValue, ArrayList<ArrayList<Integer>> combinations, int index)
    {
        // #1 Base case: prevents recursive calls via else-if statements
        // #2 Recursive call && base case: prevents recursive call if reached end of list
        if(targetValue.equals(sumList(original)))
        {
            if(!combinations.contains(original))
            {
                combinations.add(original);
            }
        }
        else if(!original.isEmpty() && index < original.size())
        {
            ArrayList<Integer> newList = new ArrayList<>(original);
            newList.remove(index);

            // Recursive calls
            findCombinations(original, targetValue, combinations, index + 1);
            findCombinations(newList, targetValue, combinations, index);
        }
    }

    private static Integer sumList(ArrayList<Integer> currentList)
    {
        Integer sum = 0;

        for(Integer current : currentList)
        {
            sum += current;
        }

        return sum;
    }
}
