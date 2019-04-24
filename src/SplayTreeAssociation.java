import java.util.Map.Entry;

public class SplayTreeAssociation<K extends Comparable<K>,V> implements Entry<K,V>, Comparable<SplayTreeAssociation<K,V>>
{
    protected K key;
    protected V value;

    public SplayTreeAssociation(K k, V v)
    {
        key = k;
        value = v;
    }

    @Override
    public K getKey()
    {
        return key;
    }

    @Override
    public V getValue()
    {
        return value;
    }

    @Override
    public V setValue(V value)
    {
        V temp = value;
        this.value = value;
        return temp;
    }

    @Override
    public int compareTo(SplayTreeAssociation<K, V> o)
    {
        return key.compareTo(o.getKey());
    }
}