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

    /**
     * Mergesort method that accepts a List of a type that implements Comparable
     * @param list to be sorted
     * @param <T> generic that bounds to Comparable
     */
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

    /**
     * Mergesort method that accepts an array of a type that implements Comparable
     * @param table to be sorted
     * @param <T> generic that bounds to Comparable
     */
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

    /**
     * Merge method that merges the left and right sequences into the output sequence
     * @param outputSequence list that receives the left and right sequence elements
     * @param leftSequence list that is merged into the output sequence
     * @param rightSequence list that is merged into the output sequence
     * @param <T> generic that bounds to Comparable
     */
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

    /**
     * Merge method that merges the left and right sequences into the output sequence
     * @param outputSequence array that receives the left and right sequence elements
     * @param leftSequence array that is merged into the output sequence
     * @param rightSequence array that is merged into the output sequence
     * @param <T> generic that bounds to Comparable
     */
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

    /**
     * Mergesort method that operates on a file filled with a type of object that implements Comparable
     * @param path file that contains the original data and is overwritten with the sorted data
     * @param <T> generic that bounds to Comparable
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
                writing = true, firstOngoing = true, secondOngoing = true,
                innerWriting = true;

        ArrayList<T> arrayOne = new ArrayList<>(runSize);
        ArrayList<T> arrayTwo = new ArrayList<>(runSize);

        T currentOne = null, currentTwo = null;

        // implementation

        do
        {
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
                }

                writing = firstOngoing || secondOngoing;
            }

            closeStream(originalOutput);
            closeStream(tempInputOne);
            closeStream(tempInputTwo);

            runSize *= 2;
        } while(driving && runSize <= fileSize);// end of driving
    }

    /**
     * Mergesort method that operates on a file filled with a type of object that implements Comparable
     * @param path file that contains the original data and is overwritten with the sorted data
     * @param <T> generic that bounds to Comparable
     */
    public static <T extends Comparable<T>> void filesort(String path)
    {
        filesort(new File(path));
    }

    /**
     * Calls the stream's close method.
     * Could be moved to another package at a later date.
     * @param stream to be closed
     * @return true if the stream is closed or false if the stream could not be closed
     */
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
