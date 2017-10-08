package singular.sequence.prime;

/**
 * Created by Andrew Michel on 10/7/2017.
 */
public class InvalidPrimeException extends IllegalArgumentException
{
    public InvalidPrimeException()
    {
        super("Invalid Prime Value");
    }

    public InvalidPrimeException(String message)
    {
        super(message);
    }
}
