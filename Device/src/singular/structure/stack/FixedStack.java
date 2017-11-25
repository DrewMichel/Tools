package singular.structure.stack;

import java.util.EmptyStackException;

/**
 * Created by Andrew Michel on 11/25/2017.
 *
 * This class wraps an internal array that is treated as a fixed-size stack
 */
public class FixedStack<E> implements StackTemplate<E>
{
    // Declaring instance variables
    E[] stack;
    int top;

    /**
     * Default constructor
     */
    public FixedStack()
    {
        stack = (E[]) new Object[StackTemplate.DEFAULT_CAPACITY];
        top = StackTemplate.DEFAULT_TOP;
    }

    /**
     * Parameterized constructor
     * @param capacity used to initialize initial stack capacity
     */
    public FixedStack(int capacity)
    {
        stack = (E[]) new Object[capacity];
        top = StackTemplate.DEFAULT_TOP;
    }

    /**
     * Copy constructor
     * @param copy AdjustableStack object that has its elements copied onto this stack
     */
    public FixedStack(FixedStack copy)
    {
        stack = (E[]) new Object[copy.top];
        System.arraycopy(copy.stack, 0, stack, 0, copy.top);
        top = copy.top;
    }

    /**
     * Determines the number of elements within the stack
     * @return the number of elements inserted into the stack
     */
    @Override
    public int size()
    {
        return top;
    }

    /**
     * Determines if this stack contains any elements
     * @return true if the stack is empty, else false
     */
    @Override
    public boolean isEmpty()
    {
        return 1 > top;
    }

    /**
     * Removes and returns the element that is on top of the stack
     * @return the element that is on top of the stack
     * @throws EmptyStackException
     */
    @Override
    public E pop() throws EmptyStackException
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }

        return stack[--top];
    }

    /**
     * Retrieves but does not remove the element that is on top of the stack
     * @return the element that is on top of the stack
     * @throws EmptyStackException
     */
    @Override
    public E peek() throws EmptyStackException
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }

        return stack[top - 1];
    }

    /**
     * Pushes the parameter item onto the top of the stack
     * @param item that is pushed onto the stack
     * @return the item that is pushed onto the stack
     */
    @Override
    public E push(E item)
    {
        return stack[top++] = item;
    }

    /**
     * Copies the elements within this stack into an array with a
     * length equal to the number of elements inserted into the stack
     * @return an array that contains the elements within this array
     */
    @Override
    public E[] toArray()
    {
        E[] array = (E[]) new Object[top];

        System.arraycopy(stack, 0, array, 0, top);

        return array;
    }

    /**
     * Search method that determines the distance from the top
     * of the stack that the parameter object is from the top of the stack
     * @param o object that is searched for within the stack
     * @return the 1-based distance from the top of the stack if the object is located, else returns -1
     */
    @Override
    public int search(Object o)
    {
        int distance = 1;

        for(int i = top; i >= 0; i--)
        {
            if(o.equals(stack[i]))
            {
                return distance;
            }

            distance++;
        }

        return -1;
    }

    /**
     * Removes all contents contained within the stack
     */
    @Override
    public void clear()
    {
        for(int i = top - 1; i >= 0; i--)
        {
            stack[i] = null;
        }

        top = 0;
    }

    /**
     * Returns the capacity of the array used as the stack
     * Since the capacity of the stack can increase as elements are inserted,
     * it is not recommended to use this method as a conditional while inserting
     * @return the underlying stack's length
     */
    @Override
    public int capacity()
    {
        return stack.length;
    }

    /**
     * Contains method that determines if the parameter object exists within the stack
     * @param o object that is searched for within the stack
     * @return true if the stack contains the parameter object, else false
     */
    @Override
    public boolean contains(Object o)
    {
        return search(o) != -1;
    }
}
