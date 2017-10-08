package singular.sequence.fibonacci;

/**
 * Created by Andrew Michel on 10/8/2017.
 */
public class InvalidFibonacciException extends IllegalArgumentException
{
    public InvalidFibonacciException()
    {
        super("Invalid Fibonacci Value");
    }

    public InvalidFibonacciException(String message)
    {
        super(message);
    }
}
