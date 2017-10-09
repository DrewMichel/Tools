package singular.cluster.comprised.driver;

import singular.cluster.comprised.Comprised;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrew Michel on 10/8/2017.
 *
 * This class exists to test comprised package classes
 */
public class ComprisedDriver
{
    public static void main(String[] args)
    {
        int total = 8;
        long sum = total;
        double added = sum;

        Long[] arr1 = {1L,2L,3L,9L};
        Long[] arr2 = {1L,2L,4L,4L};

        Integer[] arr3  = {1,2,3,9};
        Integer[] arr4  = {1,2,4,4};

        Double[] arr5 = {1D,2D,3D,9D};
        Double[] arr6 = {1D,2D,4D,4D};

        long[] array1 = {1,2,3,9};
        long[] array2 = {1,2,4,4};

        int[] array3  = {1,2,3,9};
        int[] array4  = {1,2,4,4};

        ArrayList<Long> aList1 = new ArrayList<>(Arrays.asList(arr1));
        ArrayList<Long> aList2 = new ArrayList<>(Arrays.asList(arr2));

        ArrayList<Integer> aList3 = new ArrayList<>(Arrays.asList(arr3));
        ArrayList<Integer> aList4 = new ArrayList<>(Arrays.asList(arr4));

        ArrayList<Double> aList5 = new ArrayList<>(Arrays.asList(arr5));
        ArrayList<Double> aList6 = new ArrayList<>(Arrays.asList(arr6));

        System.out.println("LONG ARRAY1 HAS TWO ELEMENTS WHICH SUM TO " + sum  + " : " + Comprised.comprises(array1, sum));
        System.out.println("LONG ARRAY2 HAS TWO ELEMENTS WHICH SUM TO " + sum  + " : " + Comprised.comprises(array2, sum));

        System.out.println("\n");

        System.out.println("INTEGER ARRAY3 HAS TWO ELEMENTS WHICH SUM TO " + total  + " : " + Comprised.comprises(array3, total));
        System.out.println("INTEGER ARRAY4 HAS TWO ELEMENTS WHICH SUM TO " + total  + " : " + Comprised.comprises(array4, total));

        System.out.println("\n");

        System.out.println("LONG ALIST1 HAS TWO ELEMENTS WHICH SUM TO " + sum  + " : " + Comprised.comprises(aList1, sum));
        System.out.println("LONG ALIST2 HAS TWO ELEMENTS WHICH SUM TO " + sum  + " : " + Comprised.comprises(aList2, sum));


        System.out.println("\n");

        System.out.println("INTEGER ALIST3 HAS TWO ELEMENTS WHICH SUM TO " + total  + " : " + Comprised.comprises(aList3, total));
        System.out.println("INTEGER ALIST4 HAS TWO ELEMENTS WHICH SUM TO " + total  + " : " + Comprised.comprises(aList4, total));


        System.out.println("\n");

        System.out.println("DOUBLE ALIST5 HAS TWO ELEMENTS WHICH SUM TO " + added  + " : " + Comprised.comprises(aList5, added));
        System.out.println("DOUBLE ALIST6 HAS TWO ELEMENTS WHICH SUM TO " + added  + " : " + Comprised.comprises(aList6, added));


    }
}
