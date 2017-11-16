package singular.sequence.prime;

import java.util.Iterator;

/**
 * Created by Andrew Michel on 11/16/2017.
 */
/*
public class EratoSieveList<E extends Number>
{
    private Node<E> head;
    private Node<E> tail;

    private long size;

    public EratoSieveList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public EratoSieveList(E data)
    {
        head = new Node<E>(data, head);
        tail = head;
        size = 0;
    }

    public boolean contains(Object o)
    {
        SieveIterator<E> sieveIterator = new SieveIterator<>();

        while(sieveIterator.hasNext())
        {
            if(sieveIterator.next().equals(o))
            {
                return true;
            }
        }

        return false;
    }

    public long size()
    {
        size = 0;

        Iterator<E> iterator = new SieveIterator<>();

        while(iterator.hasNext())
        {
            size++;
            iterator.next();
        }

        return size;
    }

    public void primePopulate(long top)
    {

    }


    //@Override
    //public void forEach(Consumer<? super E> action)
    //{
    //
    //}



    //@Override
    //public Spliterator<E> spliterator()
    //{
        //return null;
    //}


    // Inner class node start
    private static class Node<E>
    {
        private Node<E> link;
        private E data;

        public Node(E data, Node<E> link)
        {
            this.data = data;
            this.link = link;
        }

        @Override
        public String toString()
        {
            if(data == null)
            {
                return "null";
            }
            else
            {
                return data.toString();
            }
        }

        @Override
        public boolean equals(Object other)
        {
            if(other == null || other.getClass() != getClass())
            {
                return false;
            }
            else
            {
                Node<E> temp = (Node<E>) other;

                return data.equals(temp.data);
            }
        }
    } // Inner class node end

    // Inner class SieveIterator start
    private class SieveIterator<E> implements Iterator<E>
    {
        Node<E> lastItemReturned;
        Node<E> currentItem;

        public SieveIterator()
        {
            lastItemReturned = null;
            currentItem = (Node<E>) head;
        }

        @Override
        public boolean hasNext()
        {
            return currentItem != null;
        }

        @Override
        public E next()
        {
            E tempData = currentItem.data;

            lastItemReturned = currentItem;
            currentItem = currentItem.link;

            return tempData;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("remove");
        }
    } // Inner class SieveIterator end
}
*/