package singular.publish.broadcast;

/**
 * Created by Andrew Michel on 10/9/2017.
 */
public final class BroadcastArray
{
    public static final int DEFAULT_SPACING = 1;
    public static final String DEFAULT_SEPARATOR = ", ";
    public static final boolean DEFAULT_NEWLINE = true;

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

    public static void broadcast(int[][] array, int spacing, String separator, boolean newLine)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(array[i].length > 0)
            {
                System.out.printf("%" + spacing + "d", array[i][0]);
            }

            for(int k = 1; k < array[i].length; k++)
            {
                System.out.printf("%s%" + spacing + "d", separator, array[i][k]);
            }

            if(newLine)
            {
                System.out.println();
            }
        }
    }

    public static void broadcast(int[][] array)
    {
        broadcast(array, DEFAULT_SPACING, DEFAULT_SEPARATOR, DEFAULT_NEWLINE);
    }
}
