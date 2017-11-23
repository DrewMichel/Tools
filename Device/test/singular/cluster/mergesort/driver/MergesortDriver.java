package singular.cluster.mergesort.driver;

import singular.cluster.mergesort.Mergesort;

/**
 * Created by Andrew Michel on 11/22/2017.
 */
public class MergesortDriver
{
    public static final long NANOSECONDS_IN_SECOND = 1000000000L;

    public static void main(String[] args)
    {
        Integer[] array = new Integer[10000000];
        double startTime = 0, finishTime = 0;

        for(int i = 0; i < array.length; i++)
        {
            array[i] = (int) (Math.random() * 1000 + 1);
        }

        System.out.println("PRE SORT");
        /*
        for(int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }*/

        System.out.println("\nPOST SORT");

        startTime = System.nanoTime();
        Mergesort.sort(array);
        finishTime = (System.nanoTime() - startTime) / NANOSECONDS_IN_SECOND;

        /*
        for(int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        */

        System.out.println();
        System.out.printf("SORT SECONDS: %.10f", finishTime);

        System.out.println("\nEXIT");
    }
}
