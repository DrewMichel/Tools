package singular.cluster.subset.driver;

import singular.cluster.subset.SubsetCombination;
import singular.colonize.populate.PopulateList;
import singular.publish.broadcast.BroadcastList;

import java.util.ArrayList;

public class SubsetCombinationDriver
{
    public static final int DEFAULT_TARGET = 10;

    public static void main(String[] args)
    {
        ArrayList<Integer> list = new ArrayList<>();

        PopulateList.populate(list, 20, 20, 1);

        System.out.println("TESTING BROADCAST ARRAYLIST 1D");
        BroadcastList.broadcast(list);

        System.out.println();

        System.out.println("TESTING FIND COMBINATIONS");
        ArrayList<ArrayList<Integer>> combinations = SubsetCombination.findSubsetCombinations(list, DEFAULT_TARGET);

        System.out.println("TESTING BROADCAST ARRAYLIST 2D");
        System.out.println("TESTING SUBSET COMBINATIONS.  SETS SHOULD EQUAL: " + DEFAULT_TARGET);
        System.out.println("NUMBER OF COMBINATIONS: " + combinations.size());
        BroadcastList.broadcast(combinations, true);
    }
}
