package singular.colonize.populate;

import java.util.ArrayList;

public final class PopulateArrayList
{
    // Private constructor
    private PopulateArrayList(){}

    public static<E extends Number> void populate(ArrayList<E> list, int size,  int max, int min)
    {
        Integer randomValue = 0;

        for(int i = 0; i < size; i++)
        {
            randomValue = ((int)((Math.random() * (max - min)) + min));

            list.add((E) randomValue);
        }
    }

    public static<E extends Number> void populate(ArrayList<E> list, int size, int max)
    {
        populate(list, size, max, 0);
    }
}
