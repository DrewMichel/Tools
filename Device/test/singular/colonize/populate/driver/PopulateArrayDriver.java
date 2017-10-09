package singular.colonize.populate.driver;

import singular.colonize.populate.PopulateArray;
import singular.publish.broadcast.BroadcastArray;

/**
 * Created by Andrew Michel on 10/9/2017.
 */
public class PopulateArrayDriver
{
    public static void main(String[] args)
    {
        int[] array = new int[100];

        PopulateArray.populate(array, 10, 0);

        BroadcastArray.broadcast(array);

        System.out.println();
    }
}
