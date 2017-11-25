package singular.cluster.mergesort;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Andrew Michel on 11/22/2017.
 */
public final class Mergesort
{
    private Mergesort(){}

    public static <T extends Comparable<T>> void sort(List<T> list)
    {
        int size = list.size();

        if(size > 1)
        {
            int halfSize = size / 2, iteratorIndex = 0;

            List<T> leftList = new ArrayList<>(halfSize);
            List<T> rightList = new ArrayList<>(size - halfSize);

            ListIterator<T> leftIterator = leftList.listIterator();
            ListIterator<T> rightIterator = rightList.listIterator();

            for(T tee : list)
            {
                if(tee != null)
                {
                    if(iteratorIndex < halfSize)
                    {
                        leftIterator.add(tee);
                        iteratorIndex++;
                    }
                    else
                    {
                        rightIterator.add(tee);
                    }
                }
            }

            sort(leftList);
            sort(rightList);

            merge(list, leftList, rightList);
        }
    }

    public static <T extends Comparable<T>> void sort(T[] table)
    {
        if(table.length > 1)
        {
            int halfSize = table.length / 2;

            T[] leftTable = (T[]) new Comparable[halfSize];
            T[] rightTable = (T[]) new Comparable[table.length - halfSize];

            System.arraycopy(table, 0, leftTable, 0, halfSize);
            System.arraycopy(table, halfSize, rightTable, 0, table.length - halfSize);

            sort(leftTable);
            sort(rightTable);

            merge(table, leftTable, rightTable);
        }
    }

    private static <T extends Comparable<T>> void merge(List<T> outputSequence,
                                                       List<T> leftSequence,
                                                       List<T> rightSequence)
    {
        int i = 0, j = 0, k = 0, leftSize = leftSequence.size(), rightSize = rightSequence.size();

        while(i < leftSize && j < rightSize)
        {
            if(leftSequence.get(i).compareTo(rightSequence.get(j)) < 0)
            {
                outputSequence.set(k++, leftSequence.get(i++));
            }
            else
            {
                outputSequence.set(k++, rightSequence.get(j++));
            }
        }

        while(i < leftSize)
        {
            outputSequence.set(k++, leftSequence.get(i++));
        }

        while(j < rightSize)
        {
            outputSequence.set(k++, rightSequence.get(j++));
        }
    }

    private static <T extends Comparable<T>> void merge(T[] outputSequence,
                                                        T[] leftSequence,
                                                        T[] rightSequence)
    {
        int i = 0, j = 0, k = 0;

        while(i < leftSequence.length && j < rightSequence.length)
        {
            if(leftSequence[i].compareTo(rightSequence[j]) < 0)
            {
                outputSequence[k++] = leftSequence[i++];
            }
            else
            {
                outputSequence[k++] = rightSequence[j++];
            }
        }

        while(i < leftSequence.length)
        {
            outputSequence[k++] = leftSequence[i++];
        }

        while(j < rightSequence.length)
        {
            outputSequence[k++] = rightSequence[j++];
        }
    }

    /*
    public static <T extends Comparable<T>> void filesort(File path)
    {
        T[] arrayOne = null;
        T[] arrayTwo = null;
        T[] combined = null;

        ObjectInputStream originalInput = null;
        ObjectInputStream tempInputOne = null;
        ObjectInputStream tempInputTwo = null;

        ObjectOutputStream originalOutput = null;
        ObjectOutputStream tempOutputOne = null;
        ObjectOutputStream tempOutputTwo = null;

        int firstIterator = 0, secondIterator = 0, combinedIterator = 0, runSize = 10, fileSize = 0;
        boolean driving = true, reading = true, firstDistribution = true, rereading = true;

        do
        {
            try
            {
                originalInput = new ObjectInputStream(new FileInputStream(path));

                tempOutputOne = new ObjectOutputStream(new FileOutputStream(path.getName() + "1"));
                tempOutputTwo = new ObjectOutputStream(new FileOutputStream(path.getName() + "2"));

                while(reading)
                {
                    if(firstDistribution)
                    {
                        arrayOne = (T[]) new Comparable[runSize];
                        arrayTwo = (T[]) new Comparable[runSize];

                        firstIterator = 0;
                        secondIterator = 0;

                        while(firstIterator < runSize)
                        {
                            arrayOne[firstIterator] = (T) originalInput.readObject();
                            firstIterator++;
                            fileSize++;
                        }

                        while(secondIterator < runSize)
                        {
                            arrayTwo[secondIterator] = (T) originalInput.readObject();
                            secondIterator++;
                            fileSize++;
                        }

                        sort(arrayOne);
                        sort(arrayTwo);

                        for(int i = 0; i < arrayTwo.length; i++)
                        {
                            tempOutputOne.writeObject(arrayOne[i]);
                            tempOutputTwo.writeObject(arrayTwo[i]);
                        }

                        //offset1 += runSize;
                        //offset2 += runSize;
                    }
                    else
                    {
                        firstIterator = 0;
                        secondIterator = 0;

                        while(firstIterator < runSize)
                        {
                            tempOutputOne.writeObject(originalInput.readObject());
                            firstIterator++;
                        }

                        while(secondIterator < runSize)
                        {
                            tempOutputTwo.writeObject(originalInput.readObject());
                            secondIterator++;
                        }
                    }
                }
            }
            catch(EOFException e)
            {
                if(firstDistribution)
                {
                    firstDistribution = false;

                    // empty arrays into temp files
                    T[] tempOne = (T[]) new Comparable[firstIterator];
                    T[] tempTwo = (T[]) new Comparable[secondIterator];

                    for(int i = 0; i < tempOne.length; i++)
                    {
                        tempOne[i] = arrayOne[i];
                    }

                    for(int i = 0; i < tempTwo.length; i++)
                    {
                        tempTwo[i] = arrayTwo[i];
                    }

                    sort(tempOne);
                    sort(tempTwo);

                    try
                    {
                        for(int i = 0; i < tempOne.length; i++)
                        {
                            tempOutputOne.writeObject(tempOne[i]);
                        }

                        for(int i = 0; i < tempTwo.length; i++)
                        {
                            tempOutputTwo.writeObject(tempTwo[i]);
                        }
                    }
                    catch(IOException ex)
                    {
                        e.printStackTrace();
                        ex.printStackTrace();
                    }
                }

                try
                {
                    if(originalInput != null)
                    {
                        originalInput.close();
                    }

                    if(tempOutputOne != null)
                    {
                        tempOutputOne.close();
                    }

                    if(tempOutputTwo != null)
                    {
                        tempOutputTwo.close();
                    }
                }
                catch(IOException ex)
                {
                    e.printStackTrace();
                    ex.printStackTrace();
                }
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
                driving = false;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                driving = false;
            }
            catch(ClassCastException e)
            {
                e.printStackTrace();
                driving = false;
            }
            catch(ClassNotFoundException e)
            {
                e.printStackTrace();
                driving = false;
            }

            if(driving)
            {
                try
                {
                    originalOutput = new ObjectOutputStream(new FileOutputStream(path));
                    tempInputOne = new ObjectInputStream(new FileInputStream(path.getName() + "1"));
                    tempInputTwo = new ObjectInputStream(new FileInputStream(path.getName() + "2"));

                    // interweave runs or chunk them?

                    while(rereading)
                    {
                        combined = (T[]) new Comparable[runSize * 2];

                        firstIterator = 0;
                        secondIterator = 0;
                        combinedIterator = 0;

                        while(firstIterator < runSize)
                        {
                            combined[firstIterator] = (T) tempInputOne.readObject();
                            firstIterator++;
                            combinedIterator++;
                        }

                        while(secondIterator < runSize)
                        {
                            combined[firstIterator + secondIterator] = (T) tempInputOne.readObject();
                            secondIterator++;
                            combinedIterator++;
                        }

                        sort(combined);

                        for(int i = 0; i < combined.length; i++)
                        {
                            originalOutput.writeObject(combined[i]);
                        }
                    }
                }
                catch(EOFException e)
                {
                    T[] temp = (T[]) new Comparable[combinedIterator];

                    for(int i = 0; i < temp.length; i++)
                    {
                        temp[i] = combined[i];
                    }

                    sort(temp);

                    try
                    {
                        for(int i = 0; i < temp.length; i++)
                        {
                            originalOutput.writeObject(temp[i]);
                        }

                    }
                    catch(IOException ex)
                    {
                        e.printStackTrace();
                        ex.printStackTrace();
                    }
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                    driving = false;
                }
                catch(ClassCastException e)
                {
                    e.printStackTrace();
                    driving = false;
                }
                catch(ClassNotFoundException e)
                {
                    e.printStackTrace();
                    driving = false;
                }
            }

            // set to 5 before and move to the top of the loop?
            runSize *= 2;

        }while(driving && runSize < fileSize);
    }
    */

    public static <T extends Comparable<T>> void filesort(File path)
    {
        // Declarations
        ObjectInputStream originalInput = null;
        ObjectInputStream tempInputOne = null;
        ObjectInputStream tempInputTwo = null;

        ObjectOutputStream originalOutput = null;
        ObjectOutputStream tempOutputOne = null;
        ObjectOutputStream tempOutputTwo = null;

        int firstIterator = 0, secondIterator = 0, combinedIterator = 0,
                runSize = 10, fileSize = 0, doubledRunSize = 0;
        boolean driving = true, reading = true, firstDistribution = true,
                writing = true,
                firstOngoing = true, secondOngoing = true, innerWriting = true;

        ArrayList<T> arrayOne = new ArrayList<>(runSize);
        ArrayList<T> arrayTwo = new ArrayList<>(runSize);

        T currentOne = null, currentTwo = null;

        boolean testing = false;

        // implementation

        // runSize = 5;

        do
        {
            // runSize *= 2;

            try
            {
                originalInput = new ObjectInputStream(new FileInputStream(path));

                tempOutputOne = new ObjectOutputStream(new FileOutputStream(path.getName() + "1"));
                tempOutputTwo = new ObjectOutputStream(new FileOutputStream(path.getName() + "2"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.exit(0);
            }

            reading = true;

            while(reading)
            {
                firstIterator = 0;
                secondIterator = 0;

                try
                {
                    if(firstDistribution)
                    {
                        arrayOne.clear();

                        try
                        {
                            while(firstIterator < runSize)
                            {
                                arrayOne.add((T)originalInput.readObject());
                                firstIterator++;
                                fileSize++;
                            }
                        }
                        catch(EOFException ex)
                        {
                            reading = false;
                        }

                        sort(arrayOne);

                        for(T tee : arrayOne)
                        {
                            if(tee != null)
                            {
                                tempOutputOne.writeObject(tee);
                            }
                        }
                    }
                    else
                    {
                        // write into temp one
                        try
                        {
                            while(firstIterator < runSize)
                            {
                                tempOutputOne.writeObject(originalInput.readObject());
                                firstIterator++;
                            }
                        }
                        catch(EOFException e)
                        {
                            reading = false;
                        }
                    }

                }
                catch (EOFException e)
                {
                    reading = false;
                }
                catch(ClassNotFoundException e)
                {
                    e.printStackTrace();
                    System.exit(0);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                    System.exit(0);
                }

                try
                {
                    if(firstDistribution)
                    {
                        arrayTwo.clear();

                        try
                        {
                            while(secondIterator < runSize)
                            {
                                arrayTwo.add((T)originalInput.readObject());
                                secondIterator++;
                                fileSize++;
                            }
                        }
                        catch(EOFException ex)
                        {
                            reading = false;
                        }

                        sort(arrayTwo);

                        for(T tee : arrayTwo)
                        {
                            if(tee != null)
                            {
                                tempOutputTwo.writeObject(tee);
                            }
                        }
                    }
                    else
                    {
                        try
                        {
                            while(secondIterator < runSize)
                            {
                                tempOutputTwo.writeObject(originalInput.readObject());
                                secondIterator++;
                            }
                        }
                        catch(EOFException e)
                        {
                            reading = false;
                        }
                    }
                }
                catch (EOFException e)
                {
                    reading = false;
                }
                catch(ClassNotFoundException e)
                {
                    e.printStackTrace();
                    System.exit(0);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                    System.exit(0);
                }

                if(testing)
                {
                    System.out.println("END OF READING");
                }
            } // end of reading

            firstDistribution = false;
            closeStream(originalInput);
            closeStream(tempOutputOne);
            closeStream(tempOutputTwo);

            // writing to original

            // open streams
            try
            {
                originalOutput = new ObjectOutputStream(new FileOutputStream(path));

                tempInputOne = new ObjectInputStream(new FileInputStream(path.getName() + "1"));
                tempInputTwo = new ObjectInputStream(new FileInputStream(path.getName() + "2"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.exit(0);
            }

            writing = true;

            doubledRunSize = runSize * 2;
            firstOngoing = true;
            secondOngoing = true;

            while(writing)
            {
                currentOne = null;
                currentTwo = null;
                innerWriting = true;
                combinedIterator = 0;
                firstIterator = 0;
                secondIterator = 0;

                while(innerWriting)
                {
                    if(currentOne == null && firstIterator < runSize)
                    {
                        try
                        {
                            currentOne = (T) tempInputOne.readObject();
                            firstIterator++;
                        }
                        catch(EOFException e)
                        {
                            currentOne = null;
                            firstOngoing = false;
                        }
                        catch(ClassNotFoundException e)
                        {
                            e.printStackTrace();
                            System.exit(0);
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                            System.exit(0);
                        }
                    }

                    if(currentTwo == null && secondIterator < runSize)
                    {
                        try
                        {
                            currentTwo = (T) tempInputTwo.readObject();
                            secondIterator++;
                        }
                        catch(EOFException e)
                        {
                            currentTwo = null;
                            secondOngoing = false;
                        }
                        catch(ClassNotFoundException e)
                        {
                            e.printStackTrace();
                            System.exit(0);
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                            System.exit(0);
                        }
                    }

                    try
                    {
                        if(currentOne != null && currentTwo != null)
                        {
                            if(currentOne.compareTo(currentTwo) < 0)
                            {
                                originalOutput.writeObject(currentOne);
                                currentOne = null;
                                combinedIterator++;
                            }
                            else
                            {
                                originalOutput.writeObject(currentTwo);
                                currentTwo = null;
                                combinedIterator++;
                            }
                        }
                        else if(currentOne != null)
                        {
                            originalOutput.writeObject(currentOne);
                            currentOne = null;
                            combinedIterator++;
                        }
                        else if(currentTwo != null)
                        {
                            originalOutput.writeObject(currentTwo);
                            currentTwo = null;
                            combinedIterator++;
                        }
                        else
                        {
                            firstOngoing = false;
                            secondOngoing = false;
                        }
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                        System.exit(0);
                    }

                    innerWriting = (firstOngoing || secondOngoing) && combinedIterator < doubledRunSize;

                    if(testing)
                    {
                        System.out.println("END OF INNER WRITING");
                    }
                }

                writing = firstOngoing || secondOngoing;

                if(testing)
                {
                    System.out.println("END OF WRITING");
                }
            }

            closeStream(originalOutput);
            closeStream(tempInputOne);
            closeStream(tempInputTwo);

            runSize *= 2;

            if(testing)
            {
                System.out.println("END OF DRIVING");
                System.out.println("FILE SIZE: " + fileSize);
                System.out.println("RUN SIZE : " + runSize);
            }


        } while(driving && runSize <= fileSize);// end of driving
        // while(driving && runSize < fileSize);
    }



    public static <T extends Comparable<T>> void filesort(String path)
    {
        filesort(new File(path));
    }

    public static boolean closeStream(Closeable stream)
    {
        try
        {
            if(stream != null)
            {
                stream.close();
            }

            return true;
        }
        catch(IOException e)
        {
            return false;
        }
    }
}
