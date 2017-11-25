package singular.structure.stack;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Andrew Michel on 11/25/2017.
 *
 * This interface contains abstract methods that enable a class to function as a stack
 */
public interface StackTemplate<E>
{
    // Declaring static constants
    public static final int DEFAULT_CAPACITY = 10, DEFAULT_TOP = 0;

    // Abstract method headers
    public int size();

    public boolean isEmpty();

    public E pop();

    public E peek();

    public E push(E item);

    public E[] toArray();

    public int search(Object o);

    public void clear();

    public int capacity();

    public boolean contains(Object o);
}
