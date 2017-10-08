package singular.cluster.comprised;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Andrew Michel on 10/8/2017.
 *
 * This class accepts an array or list of numbers and determines
 * if any two numbers within the array or list sum into a desired number
 *
 * Comprising is intended to be performed with whole numbers.
 * Caution should be taken when passing a list of floats as the
 * associated method compares generics as longs
 *
 * Based on google interview mock question
 * How to: Work at Google — Example Coding/Engineering Interview
 * https://www.youtube.com/watch?v=XKu_SEDAykw
 */
public final class Comprised
{
    // Private constructor
    private Comprised(){}

    /**
     * Determines if any two values in the array sum into the desired number
     *
     * @param array storing numbers
     * @param sum desired number
     * @return true if two values sum into the desired amount, else false
     */
    public static boolean comprises(long[] array, long sum)
    {
        HashSet<Long> set = new HashSet<>();

        for(int i = 0; i < array.length; i++)
        {
            if(set.contains(array[i]))
            {
                return true;
            }

            set.add(sum - array[i]);
        }

        return false;
    }

    /**
     * Determines if any two values in the array sum into the desired number
     *
     * @param array storing numbers
     * @param sum desired number
     * @return true if two values sum into the desired amount, else false
     */
    public static boolean comprises(int[] array, int sum)
    {
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i < array.length; i++)
        {
            if(set.contains(array[i]))
            {
                return true;
            }

            set.add(sum - array[i]);
        }

        return false;
    }

    /**
     * Determines if any two values in the list sum into the desired number
     * Converts into longs so accuracy regarding floats may return an incorrect boolean
     *
     * @param list storing numbers
     * @param sum desired number
     * @param <E> extends the Number class
     * @return true if two values sum into the desired amount, else false
     */
    public static<E extends Number> boolean comprises(List<E> list, E sum)
    {
        HashSet<E> set = new HashSet<E>();

        for(int i = 0; i < list.size(); i++)
        {
            if(set.contains(list.get(i).longValue()))
            {
                return true;
            }

            set.add((E)((Long)(sum.longValue() - list.get(i).longValue())));
        }

        return false;
    }
}
