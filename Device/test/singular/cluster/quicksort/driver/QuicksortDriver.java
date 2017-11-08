package singular.cluster.quicksort.driver;

import singular.cluster.quicksort.Quicksort;
import singular.colonize.populate.PopulateArray;
import singular.colonize.populate.PopulateList;
import singular.publish.broadcast.BroadcastArray;
import singular.publish.broadcast.BroadcastList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Michel on 11/3/2017.
 *
 * This class exists to test quicksort package classes
 */
public class QuicksortDriver
{
    public static void main(String[] args)
    {
        /*
        for(int i = 0; i < 10000; i++)
        {
            int[] array = new int[100];

            PopulateArray.populate(array, 100);

            Quicksort.sort(array);
        }
        */

        /*
        double[] array = new double[100];

        PopulateArray.populate(array, 100);

        BroadcastArray.broadcast(array, 8, ",", 2);

        System.out.println();

        Quicksort.sort(array);

        BroadcastArray.broadcast(array, 8, ",", 2);

        System.out.println();
        */

        /*
        for(int i = 0; i < 1000000; i++)
        {
            List<Integer> list = new ArrayList<>();

            PopulateList.populate(list, 20, 100);

            Quicksort.sort(list);
        }

        List<Integer> list = new ArrayList<>();

        PopulateList.populate(list, 20, 100);

        BroadcastList.broadcast(list);

        Quicksort.sort(list);

        System.out.println();
        BroadcastList.broadcast(list);
        */

    }
}
