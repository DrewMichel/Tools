package singular.cluster.bubblesort.driver;

import singular.cluster.bubblesort.BubbleSort;
import singular.colonize.populate.PopulateArray;
import singular.publish.broadcast.BroadcastArray;

/**
 * Created by Andrew Michel on 10/9/2017.
 *
 * This class exits to test bubblesort package classes
 */
public class BubbleSortDriver
{
    public static void main(String[] args)
    {
        int[] array = new int[20];

        PopulateArray.populate(array, 10, 1);

        System.out.println("OUTPUTTING ORIGINAL ARRAY");
        BroadcastArray.broadcast(array);

        System.out.println("\n");

        BubbleSort.sort(array);

        System.out.println("OUTPUTTING SORTED ARRAY");
        BroadcastArray.broadcast(array);

        System.out.println();
    }
}
