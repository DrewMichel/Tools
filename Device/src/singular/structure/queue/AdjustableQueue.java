package singular.structure.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Created by Andrew Michel on 11/25/2017.
 *
 * This class wraps an array to implement it as a queue
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
        System.arraycopy(copy.queue, copy.front, queue, 0, copy.queue.length);

        end = copy.end - copy.front;
        front = 0;
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

    /**
     * Creates and returns a new Iterator that can be used to
     * iterate over the elements within the queue
     * @return an Iterator
     */
    @Override
    public Iterator<E> iterator()
    {
        return new AdjustableIterator<>();
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

    /**
     * Adds the parameter into the queue
     * @param e item that is added into the queue
     * @return true if the parameter was added to the queue
     */
    @Override
    public boolean add(E e)
    {
        if(end >= queue.length)
        {
            increaseCapacity();
        }

        queue[end++] = e;

        return true;
    }

    /**
     * Removes the parameter Object from the queue
     * Currently unsupported
     * @param o Object that is removed from the queue
     * @return true if the parameter Object was removed, else false
     * @throws UnsupportedOperationException when called
     */
    @Override
    public boolean remove(Object o) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Determines if the queue contains all of the elements contained within the parameter Collection
     * @param c Collection compared against the elements within the queue
     * @return true if the queue contains all elements within the parameter Collection, else false
     */
    @Override
    public boolean containsAll(Collection<?> c)
    {
        Iterator<?> collectionIterator = c.iterator();

        while(collectionIterator.hasNext())
        {
            if(contains(collectionIterator.next()) == false)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds all of the elements within the parameter Collection into the queue
     * Currently unsupported
     * @param c Collection that has its elements added into the queue
     * @return true if the elements were added into the queue, else false
     * @throws UnsupportedOperationException when called
     */
    @Override
    public boolean addAll(Collection<? extends E> c) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all of the elements within the parameter Collection from the queue
     * Currently unsupported
     * @param c Collection that has its elements removed from the queue
     * @return true if the elements were removed from the queue, else false
     * @throws UnsupportedOperationException when called
     */
    @Override
    public boolean removeAll(Collection<?> c) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Retains all of the elements within the parameter Collection in the queue
     * Currently unsupported
     * @param c Collection that has its elements retained in the queue
     * @return true if the elements were retained in the queue, else false
     * @throws UnsupportedOperationException when called
     */
    @Override
    public boolean retainAll(Collection<?> c) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }
    /**
     * Clears all of the elements within the queue and repositions
     * the front and end of the queue
     */
    @Override
    public void clear()
    {
        for(int i = front; front < end; i++)
        {
            queue[i] = null;
        }

        front = 0;
        end = 0;
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean offer(E e)
    {
        if(end >= queue.length)
        {
            increaseCapacity();
        }

        queue[end++] = e;

        return true;
    }

    /**
     * Retrieves the element at the front of the queue and removes it
     * @return the element at the front of the queue, or throws NoSuchElementException if the queue is empty
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E remove() throws NoSuchElementException
    {
        if(front >= end)
        {
            throw new NoSuchElementException();
        }

        E item = queue[front];
        queue[front] = null;
        front++;

        return item;
    }

    /**
     * Retrieves the element at the front of the queue and removes it
     * @return the element at the front of the queue, or null if the queue is empty
     */
    @Override
    public E poll()
    {
        if(front >= end)
        {
            return null;
        }

        E item = queue[front];
        queue[front] = null;
        front++;

        return item;
    }

    /**
     * Retrieves the element at the front of the queue without removing it
     * @return the element at the front of the queue, or throws NoSuchElementException if the queue is empty
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E element() throws NoSuchElementException
    {
        if(front >= end)
        {
            throw new NoSuchElementException();
        }

        return queue[front];
    }

    /**
     * Retrieves the element at the front of the queue without removing it
     * @return the element at the front of the queue, or null if the queue is empty
     */
    @Override
    public E peek()
    {
        if(front >= end)
        {
            return null;
        }

        return queue[front];
    }

    /**
     * Increases the capacity of the queue and repositions
     * the front and ending of the queue
     */
    public void increaseCapacity()
    {
        E[] updated = (E[]) new Object[queue.length * 2 + 1];

        System.arraycopy(queue, front, updated, 0, size());

        end = size();
        front = 0;

        queue = updated;
    }

    /**
     * Trims the capacity of the queue to the number of elements
     * within the queue and repositions the front and ending of the queue
     */
    public void trimCapacity()
    {
        E[] updated = (E[]) new Object[size()];

        System.arraycopy(queue, front, updated, 0, size());

        end = size();
        front = 0;

        queue = updated;
    }

    private class AdjustableIterator<T> implements Iterator<T>
    {
        // Declaring instance variables
        int currentIndex;

        /**
         * Default constructor
         */
        public AdjustableIterator()
        {
            currentIndex = front;
        }

        /**
         * Determines if the iterator hasn't reached the end of the queue yet
         * @return true if the queue contains additional elements, else false
         */
        @Override
        public boolean hasNext()
        {
            return currentIndex < end;
        }

        /**
         * Returns the next element within the queue and positions the
         * iterator to be prepared for another next call
         * @return the next element within the queue
         * @throws NoSuchElementException if there a no remaining elements within the queue
         */
        @Override
        public T next() throws NoSuchElementException
        {
            if(hasNext() == false)
            {
                throw new NoSuchElementException();
            }

            return (T) queue[currentIndex++];
        }

        /**
         * Removes the element most recently returned by the next method
         * Currently unsupported
         * @throws UnsupportedOperationException when called
         */
        @Override
        public void remove() throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }
    }
}
