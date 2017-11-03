package singular.colonize.populate;

/**
 * Created by Andrew Michel on 10/9/2017.
 */
public final class PopulateArray
{
    // Private constructor
    private PopulateArray(){}

    public static void populate(double[] array, double max, double min)
    {
        for(int i = 0; i < array.length; i++)
        {
            array[i] = (((Math.random() * (max - min)) + min));
        }
    }

    public static void populate(double[] array, double max)
    {
        populate(array, max, 0);
    }

    public static void populate(int[] array, int max, int min)
    {
        for(int i = 0; i < array.length; i++)
        {
            array[i] = (((int) ((Math.random() * (max - min)) + min)));
        }
    }

    public static void populate(int[] array, int max)
    {
        populate(array, max,0);
    }
}
