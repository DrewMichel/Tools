package singular.structure.bidirectionalmap;

import singular.structure.singlylinkedlist.SinglyLinkedList;

import java.util.Iterator;

/**
 * Created by Andrew Michel on 5/8/2019.
 */
class HalfMap<K, V>
{
    // Constants
    public static final int DEFAULT_SIZE = 11;
    // Instance variables
    private SinglyLinkedList<Bipair<K, V>> list[];
    private int elements;

    // Constructors
    public HalfMap()
    {
        list = (SinglyLinkedList<Bipair<K, V>>[]) new SinglyLinkedList[DEFAULT_SIZE];
    }

    // Methods

    public int code(K indexer)
    {
        return indexer.hashCode() % list.length;
    }

    public boolean contains(Bipair<K, V> pair, int hash)
    {
        if(pair != null && list[hash] != null)
        {
            Iterator<Bipair<K, V>> iter = list[hash].iterator();

            while(iter.hasNext())
            {
                if(iter.next().getKey().equals(pair.getKey()))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean add(Bipair<K, V> pair, int hash)
    {


        return true;
    }
}
