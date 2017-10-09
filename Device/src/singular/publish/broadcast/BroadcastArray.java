package singular.publish.broadcast;

/**
 * Created by Andrew Michel on 10/9/2017.
 */
public final class BroadcastArray
{
    public static final int DEFAULT_SPACING = 1;
    public static final String DEFAULT_SEPARATOR = ", ";

    private BroadcastArray(){}

    public static void broadcast(int[] array, int spacing, String separator)
    {
        System.out.printf("%" + spacing + "d", array[0]);

        for(int i = 1; i < array.length; i++)
        {
            System.out.printf("%s%" + spacing + "d", separator, array[i]);
        }
    }

    public static void broadcast(int[] array)
    {
        broadcast(array, DEFAULT_SPACING, DEFAULT_SEPARATOR);
    }
}
