package singular.sequence.factorial;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class can be used to track current and previous factorial
 * values along with their respective indices
 */
public class FactorialTracker
{
    // Instance variables
    private long currentFactorial, previousFactorial, currentIndex, previousIndex;

    // Default constructor
    public FactorialTracker()
    {
        currentFactorial = 1;
        previousFactorial = -1;

        currentIndex = 0;
        previousIndex = -1;
    }

    /**
     * Calculates the next factorial, assigns currentFactorial to it,
     * sets previousFactorial to currentFactorial's recent value
     * and increments both indices
     * @return currentFactorial instance variable
     */
    public long nextFactorial()
    {
        currentIndex++;
        previousIndex++;
        long swap = currentFactorial;
        currentFactorial = (currentFactorial * currentIndex);
        previousFactorial = swap;

        return currentFactorial;
    }

    /**
     * Returns the previously found factorial value
     * Should only be called after calling nextFactorial method at least once
     * @return previousFactorial instance variable
     */
    public long getPreviousFactorial()
    {
        return previousFactorial;
    }

    /**
     * Returns the current factorial value
     * @return currentFactorial instance variable
     */
    public long getCurrentFactorial()
    {
        return currentFactorial;
    }

    /**
     * Returns the current factorial's index
     * @return currentIndex instance variable
     */
    public long getCurrentIndex()
    {
        return currentIndex;
    }

    /**
     * Returns the previously found factorial value's index
     * Should only be called after calling nextFactorial method at least once
     * @return previousIndex instance variable
     */
    public long getPreviousIndex()
    {
        return previousIndex;
    }
}
