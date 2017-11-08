package singular.cluster.quicksort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew Michel on 11/3/2017.
 */
public final class Quicksort
{
    public static void sort(double[] array)
    {
        sort(array, 0, array.length - 1);
    }

    public static void sort(double[] array, int startIndex, int endIndex)
    {
        if(endIndex - startIndex == 1 && array[startIndex] > array[endIndex])
        {
            double swap = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = swap;
        }
        else if(endIndex - startIndex > 1)
        {
            int splitPoint = split(array, startIndex, endIndex);

            sort(array, startIndex, splitPoint);
            sort(array, splitPoint + 1, endIndex);
        }
    }

    private static int split(double[] array, int start, int end)
    {
        int size = end - start + 1;
        double[] tempArray = new double[size];

        double splitValue = array[start]; // original
        //double splitValue = (array[start] + array[end] + array[size / 2]) / 3; // handout that doesn't work
        //double splitValue = array[start + ((end - start) / 2)];
        //double splitValue = array[(start + end) / 2];

        int up = 0, down = size - 1;

        for(int i = start + 1; i <= end; i++)
        {
            if(array[i] <= splitValue)
            {
                tempArray[up] = array[i];
                up++;
            }
            else
            {
                tempArray[down] = array[i];
                down--;
            }
        }

        tempArray[up] = array[start];

        for(int i = 0; i < size; i++)
        {
            array[start + i] = tempArray[i];
        }

        return (start + up);
    }

    // Needs testings
    public static<E extends Comparable<E>> void sort(List<E> list)
    {
        sort(list, 0, list.size() - 1);
    }

    private static<E extends Comparable<E>> void sort(List<E> list, int start, int end)
    {
        if(end - start == 1 && (list.get(start)).compareTo(list.get(end)) > 0)
        {
            E temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
        }
        else if(end - start > 1)
        {
            int splitPoint = split(list, start, end);
            sort(list, start, splitPoint);
            sort(list, splitPoint + 1, end);
        }
    }


    private static <E extends Comparable<E>> int split(List<E> list, int start, int end)
    {
        int size = end - start + 1;

        E[] tempArray = (E[])(new Comparable[size]);

        //E splitValue = list.get((start + end) / 2);
        E splitValue = list.get(start);

        int up = 0, down = size - 1;

        for(int i = start + 1; i <= end; i++)
        {
            if(list.get(i).compareTo(splitValue) <= 0)
            {
                tempArray[up] = list.get(i);
                up++;
            }
            else
            {
                tempArray[down] = list.get(i);
                down--;
            }
        }

        tempArray[up] = list.get(start);

        for(int i = 0; i < size; i++)
        {
            list.set(start + i, tempArray[i]);
        }

        return (start + up);
    }

}
