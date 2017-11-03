package singular.cluster.quicksort.driver;

import singular.cluster.quicksort.Quicksort;
import singular.colonize.populate.PopulateArray;
import singular.publish.broadcast.BroadcastArray;

/**
 * Created by Andrew Michel on 11/3/2017.
 *
 * This class exists to test quicksort package classes
 */
public class QuicksortDriver
{
    public static void main(String[] args)
    {
        double[] array = new double[100];

        PopulateArray.populate(array, 100);

        BroadcastArray.broadcast(array, 8, ",", 2);

        System.out.println();


        Quicksort.sort(array);

        BroadcastArray.broadcast(array, 8, ",", 2);

        System.out.println();
    }
}
