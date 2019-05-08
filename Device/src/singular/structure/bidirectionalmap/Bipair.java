package singular.structure.bidirectionalmap;

/**
 * Created by Andrew Michel on 5/8/2019.
 */
public class Bipair<K, V>
{
    private K key;
    private V value;

    public Bipair(K keyIn, V valueIn)
    {
        set(keyIn, valueIn);
    }

    public K getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

    public void set(K keyIn, V valueIn)
    {
        key = keyIn;
        value = valueIn;
    }

    public void setKey(K keyIn)
    {
        key = keyIn;
    }

    public void setValue(V valueIn)
    {
        value = valueIn;
    }
}
