package singular.cluster.mergesort.driver;

import singular.cluster.mergesort.Mergesort;
import singular.publish.broadcast.BroadcastList;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Andrew Michel on 11/22/2017.
 */
public class MergesortDriver
{
    public static final long NANOSECONDS_IN_SECOND = 1000000000L;

    public static void main(String[] args)
    {
        ArrayList<Integer> list = new ArrayList<>();
        double startTime = 0, finishTime = 0;

        for(int i = 0; i < 100000; i++)
        {
            list.add((int) (Math.random() * 1000 + 1));
        }

        System.out.println("PRE SORT LIST");
        //BroadcastList.broadcast(list);
        System.out.println();

        File path = new File("data.txt");

        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        try
        {
            outputStream = new ObjectOutputStream(new FileOutputStream(path));

            for(Integer i : list)
            {
                outputStream.writeObject(i);
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        Mergesort.closeStream(outputStream);
        startTime = System.nanoTime();
        Mergesort.filesort(path);
        finishTime = (System.nanoTime() - startTime) / NANOSECONDS_IN_SECOND;

        System.out.printf("%.0f HOURS %.10f SECONDS%n", finishTime / 60, finishTime % 60);

        boolean reading = true;

        System.out.println("POST SORT FILE");

        ArrayList<Integer> out = new ArrayList<>();

        try
        {
            inputStream = new ObjectInputStream(new FileInputStream(path));

            while(reading)
            {
                out.add((Integer)inputStream.readObject());
            }
        }
        catch(EOFException e)
        {
            System.out.println("\nREACHED END OF FILE");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        Mergesort.closeStream(inputStream);

        Integer previous = out.get(0);

        for(int i = 1; i < out.size(); i++)
        {
            assert out.get(i) >= previous;
            System.out.print(out.get(i) + " ");
        }
        System.out.println("\nORIGINAL SIZE: " + list.size());
        System.out.println("OUTPUT SIZE  : " + out.size());

        /*
        Integer[] array = new Integer[30];
        double startTime = 0, finishTime = 0;

        for(int i = 0; i < array.length; i++)
        {
            array[i] = (int) (Math.random() * 1000 + 1);
        }

        System.out.println("PRE SORT");

        for(int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }

        System.out.println("\nPOST SORT");

        startTime = System.nanoTime();
        Mergesort.sort(array);
        finishTime = (System.nanoTime() - startTime) / NANOSECONDS_IN_SECOND;


        for(int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }


        System.out.println();
        System.out.printf("SORT SECONDS: %.10f", finishTime);

        System.out.println("\nEXIT");
        */

        /*
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < 30; i++)
        {
            list.add((int) (Math.random() * 1000 + 1));
        }

        BroadcastList.broadcast(list);

        System.out.println();
        Mergesort.sort(list);

        BroadcastList.broadcast(list);
        System.out.println();
        */
    }
}
