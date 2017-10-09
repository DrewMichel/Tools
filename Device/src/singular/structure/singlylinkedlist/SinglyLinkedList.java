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

        head = new Node<E>(null, data);
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
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public E[] toArray()
    {
        Object array = new Object[1];
        return (E[]) array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e)
    {
        if(size == 0)
        {
            head = new Node<E>(null, e);
            tail = head;
        }
        else if(size == 1)
        {
            tail = new Node<E>(null, e);
            head.link = tail;
        }
        else
        {
            tail.link = new Node<E>(null, e);
            tail = tail.link;
        }

        size++;

        return true;
    }

    @Override
    public boolean remove(Object o)
    {
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
    public void clear() {

    }

    @Override
    public E get(int index)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
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
            throw new IndexOutOfBoundsException();
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

            current.data = element;
            return current.data;
        }
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
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
}
