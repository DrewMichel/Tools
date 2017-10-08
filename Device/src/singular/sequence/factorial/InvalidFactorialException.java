package singular.sequence.factorial;

/**
 * Created by Andrew Michel on 10/7/2017.
 *
 * This exception class is intended to be thrown when a factorial
 * method is attempting to find a negative n factorial value
 */
public class InvalidFactorialException extends IllegalArgumentException
{
    // Default constructor
    public InvalidFactorialException()
    {
        super("Invalid Factorial Value");
    }

    // Constructor that accepts a String message and passes it to super
    public InvalidFactorialException(String message)
    {
        super(message);
    }
}
