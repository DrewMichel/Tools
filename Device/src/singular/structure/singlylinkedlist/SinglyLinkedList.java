package singular.structure.singlylinkedlist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Andrew Michel on 10/9/2017.
 */
public class SinglyLinkedList<E> implements List<E>
{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList()
    {
        size = 0;
        head = null;
        tail = null;
    }

    public SinglyLinkedList(E data)
    {
        size = 1;

        head = new Node<>(null, data);
        tail = head;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean contains(Object o)
    {
        Node<E> current = head;

        while(current != null)
        {
            if(o.equals(current.data))
            {
                return true;
            }

            current = current.link;
        }

        return false;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new InnerListIterator<>();
    }

    @Override
    public E[] toArray()
    {
        E[] array = (E[]) new Object[size];

        Node<E> current = head;

        int index = 0;

        while(current != null)
        {
            array[index] = current.data;

            index++;
            current = current.link;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        T[] array = (T[]) new Object[size];

        Node<T> current = (Node<T>) head;

        int index = 0;

        while(current != null)
        {
            array[index] = current.data;

            index++;
            current = current.link;
        }

        return array;
    }

    @Override
    public boolean add(E e)
    {
        if(size == 0)
        {
            head = new Node<>(null, e);
            tail = null;
        }
        else if(size == 1)
        {
            tail = new Node<>(null, e);
            head.link = tail;
        }
        else
        {
            tail.link = new Node<>(null, e);
            tail = tail.link;
        }

        size++;

        return true;
    }

    @Override
    public boolean remove(Object o)
    {
        if(head != null)
        {
            if(o.equals(head.data))
            {
                head = head.link;
                size--;
                return true;
            }
            else
            {
                Node<E> current = head;

                while(current.link != null)
                {
                    if(o.equals(current.link.data))
                    {
                        if(current.link == tail)
                        {
                            tail = current;
                        }

                        current.link = current.link.link;
                        size--;
                        return true;
                    }

                    current = current.link;
                }
            }
        }

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
    public boolean addAll(int index, Collection<? extends E> c) {
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

    @Override
    public void clear()
    {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("" + index);
        }
        else
        {
            int i = 0;

            Node<E> current = head;

            while(i < index)
            {
                i++;
                current = current.link;
            }

            return current.data;
        }
    }

    @Override
    public E set(int index, E element)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("" + index);
        }
        else
        {
            int i = 0;
            E previousData = null;
            Node<E> current = head;

            while(i < index)
            {
                i++;
                current = current.link;
            }

            previousData = current.data;
            current.data = element;
            return previousData;
        }
    }

    @Override
    public void add(int index, E element)
    {

    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("" + index);
        }

        E item = null;

        if(index == 0)
        {
            item = head.data;
            head = head.link;
        }
        else
        {
            Node<E> current = head;
            int position = 1;

            while(position < index)
            {
                current = current.link;
                position++;
            }

            item = current.link.data;

            if(current.link == tail)
            {
                tail = current;
            }

            current.link = current.link.link;

        }
        size--;

        return item;
    }

    @Override
    public int indexOf(Object o)
    {
        int index = 0;

        Node<E> current = head;

        while(current != null)
        {
            if(o.equals(current.data))
            {
                return index;
            }

            current = current.link;
            index++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {
        int lastPosition = -1, index = 0;

        Node<E> current = head;

        while(current != null)
        {
            if(o.equals(current.data))
            {
                lastPosition = index;
            }

            current = current.link;
            index++;
        }

        return lastPosition;
    }

    @Override
    public ListIterator<E> listIterator()
    {
        return new InnerListIterator<>();
    }

    @Override
    public ListIterator<E> listIterator(int index)
    {
        return new InnerListIterator<>(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private static class Node<E>
    {
        private Node<E> link;
        private E data;

        public Node(Node<E> link, E data)
        {
            this.link = link;
            this.data = data;
        }
    }

    private class InnerListIterator<T> implements ListIterator<T>
    {


        public InnerListIterator()
        {

        }

        public InnerListIterator(int index)
        {

        }

        @Override
        public boolean hasNext()
        {
            return false;
        }

        @Override
        public T next()
        {
            return null;
        }

        @Override
        public boolean hasPrevious()
        {
            return false;
        }

        @Override
        public T previous()
        {
            return null;
        }

        @Override
        public int nextIndex()
        {
            return 0;
        }

        @Override
        public int previousIndex()
        {
            return 0;
        }

        @Override
        public void remove()
        {

        }

        @Override
        public void set(T t)
        {

        }

        @Override
        public void add(T t)
        {

        }
    }
}
