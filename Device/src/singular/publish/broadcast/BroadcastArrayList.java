package singular.publish.broadcast;

import java.util.ArrayList;

public final class BroadcastArrayList
{
    public static final int DEFAULT_SPACING = 1;
    public static final String DEFAULT_SEPARATOR = ", ";
    public static final boolean DEFAULT_NEWLINE = true;

    // Private constructor
    private BroadcastArrayList(){}

    public static<E extends Number> void broadcast(ArrayList<E> array, int spacing, String separator)
    {
        System.out.printf("%" + spacing + ".2f", array.get(0).doubleValue());

        for(int i = 1; i < array.size(); i++)
        {
            System.out.printf("%s%" + spacing + ".2f", separator, array.get(i).doubleValue());
        }
    }

    public static<E extends Number> void broadcast(ArrayList<E> array)
    {
        broadcast(array, DEFAULT_SPACING, DEFAULT_SEPARATOR);
    }

    public static<E extends Number> void broadcast(ArrayList<ArrayList<E>> array, int spacing, String separator, boolean newLine)
    {
        for(int i = 0; i < array.size(); i++)
        {
            System.out.printf("%" + spacing + ".2f", array.get(i).get(0).doubleValue());

            for(int k = 1; k < array.get(i).size(); k++)
            {
                System.out.printf("%s%" + spacing + ".2f", separator, array.get(i).get(k).doubleValue());
            }

            if(newLine)
            {
                System.out.println();
            }
        }
    }

    public static<E extends Number> void broadcast(ArrayList<ArrayList<E>> array, boolean newLine)
    {
        broadcast(array, DEFAULT_SPACING, DEFAULT_SEPARATOR, newLine);
    }
}
