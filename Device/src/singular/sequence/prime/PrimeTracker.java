package singular.sequence.prime;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This class can be used to track current and previous prime
 * numbers along with their respective indices
 *
 * It is recommended that mutator methods are used with care to avoid
 * non-prime numbers
 */
public class PrimeTracker
{
    // Instance variables
    private long currentPrime, previousPrime, currentIndex, previousIndex;

    // Default constructor
    public PrimeTracker()
    {
        currentPrime = -1;
        previousPrime = -1;

        currentIndex = -1;
        previousIndex = -2;
    }

    /**
     * Sets the previous prime number to the current one
     * Sets the current prime number to the next one
     * Increments the indices of both prime numbers
     *
     * @return the next prime number stored as the current one
     */
    public long nextPrime()
    {
        previousPrime = currentPrime;
        currentPrime = PrimeFinder.findNextPrime(currentPrime);


        currentIndex++;
        previousIndex++;

        return currentPrime;
    }

    // Accessor and mutator methods
    public long getCurrentPrime()
    {
        return currentPrime;
    }

    public void setCurrentPrime(long currentPrime)
    {
        this.currentPrime = currentPrime;
    }

    public long getPreviousPrime()
    {
        return previousPrime;
    }

    public void setPreviousPrime(long previousPrime)
    {
        this.previousPrime = previousPrime;
    }

    public long getCurrentIndex()
    {
        return currentIndex;
    }

    public void setCurrentIndex(long currentIndex)
    {
        this.currentIndex = currentIndex;
    }

    public long getPreviousIndex()
    {
        return previousIndex;
    }

    public void setPreviousIndex(long previousIndex)
    {
        this.previousIndex = previousIndex;
    }

    // Object overrides
    @Override
    public String toString()
    {
        return ("#" + previousIndex + " " + previousPrime + " -> #" + currentIndex + " " + currentPrime);
    }

    @Override
    public boolean equals(Object other)
    {
        if(other == null)
        {
            return false;
        }

        if(other.getClass() != this.getClass())
        {
            return false;
        }

        PrimeTracker temp = (PrimeTracker) other;

        return (this.currentPrime == temp.currentPrime && this.currentIndex == temp.currentIndex && this.previousPrime == temp.previousPrime && this.previousIndex == temp.previousIndex);
    }

    @Override
    public int hashCode()
    {
        return ((int)(currentPrime * currentIndex * previousPrime * previousIndex));
    }
}
