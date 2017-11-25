package singular.structure.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * Created by Andrew Michel on 11/25/2017.
 */
public class AdjustableQueue<E> implements Queue<E>
{
    // Declaring constants
    public static final int DEFAULT_CAPACITY = 10, DEFAULT_FRONT = 0, DEFAULT_END = 0;

    // Declaring instance variables
    E[] queue;
    int front, end;

    /**
     * Default constructor
     */
    public AdjustableQueue()
    {
        queue = (E[]) new Object[DEFAULT_CAPACITY];
        front = DEFAULT_FRONT;
        end = DEFAULT_END;
    }

    /**
     * Parameterized constructor
     * @param capacity used to initialize initial queue capacity
     */
    public AdjustableQueue(int capacity)
    {
        queue = (E[]) new Object[capacity];
        front = DEFAULT_FRONT;
        end = DEFAULT_END;
    }

    /**
     * Copy constructor
     * @param copy AdjustableQueue that has its elements copied into the queue
     */
    public AdjustableQueue(AdjustableQueue copy)
    {
        queue = (E[]) new Object[copy.queue.length];
        System.arraycopy(copy.queue, 0, queue, 0, copy.queue.length);
        front = copy.front;
        end = copy.end;
    }

    /**
     * Determines the number of elements within the queue
     * @return the number of elements within the queue
     */
    @Override
    public int size()
    {
        return end - front;
    }

    /**
     * Determines if the queue does not contain any elements
     * @return true if the queue has no elements, else false
     */
    @Override
    public boolean isEmpty()
    {
        return 1 > size();
    }

    /**
     * Determines if the queue contains the parameter object
     * @param o object that is searched for within the queue
     * @return true if the queue contains the parameter object, else false
     */
    @Override
    public boolean contains(Object o)
    {
        for(int i = front; i < end; i++)
        {
            if(o.equals(queue[i]))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Copies the elements within the queue into an array
     * @return an Object array that contains the elements within the queue
     */
    @Override
    public Object[] toArray()
    {
        Object[] array = new Object[size()];

        System.arraycopy(queue, front, array, 0, size());

        return array;
    }

    /**
     * Copies the elements within the queue into an array
     * @param a array parameter that is used to determine the generic type of T
     *          If the array is large enough to store the elements within the queue,
     *          the elements will be placed into the array.  Otherwise, a new array
     *          will be created and returned
     * @param <T> generic type
     * @return an array of type T with the elements from the queue
     */
    @Override
    public <T> T[] toArray(T[] a)
    {
        T[] array;

        if(a.length >= size())
        {
            array = a;
        }
        else
        {
            array = (T[]) new Object[size()];
        }

        System.arraycopy(queue, front, array, 0, size());

        return array;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * Clears all of the elements within the queue
     */
    @Override
    public void clear()
    {
        for(E ea : queue)
        {
            ea = null;
        }

        front = 0;
        end = 0;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    public void increaseCapacity()
    {
        E[] updated = (E[]) new Object[queue.length * 2 + 1];

        System.arraycopy(queue, front, updated, 0, size());

        end = size();
        front = 0;

        queue = updated;
    }

    public void trimCapacity()
    {
        E[] updated = (E[]) new Object[size()];

        System.arraycopy(queue, front, updated, 0, size());

        end = size();
        front = 0;

        queue = updated;
    }
}
