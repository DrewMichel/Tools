package singular.structure.queue.driver;

import singular.structure.queue.AdjustableQueue;

import java.util.Iterator;

/**
 * Created by Andrew Michel on 11/25/2017.
 */
public class QueueDriver
{
    public static void main(String[] args)
    {
        int size = 50, current = 0;

        AdjustableQueue<Integer> queue = new AdjustableQueue<>();

        int[] array = new int[size];

        for(int i = 0; i < size; i++)
        {
            current = (int) (Math.random() * 100 + 1);
            array[i] = current;
            queue.add(current);
        }

        System.out.println("ARRAY");
        for(int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }

        Integer[] arr = new Integer[size];

        queue.toArray(arr);

        System.out.println("\nTO ARRAY");
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }

        Iterator<Integer> iterator = queue.iterator();

        System.out.println("\nQUEUE ITERATOR");
        while(iterator.hasNext())
        {
            System.out.print(iterator.next() + " ");
        }

        System.out.println("\nQUEUE");
        while(!queue.isEmpty())
        {
            System.out.print(queue.poll() + " ");
        }

        System.out.println();
    }
}
