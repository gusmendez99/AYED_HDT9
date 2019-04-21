import java.util.Map;

public class SplayEntry<K extends Comparable<K>,V> implements Map.Entry<K,V>, Comparable<SplayEntry<K, V>> {
    protected K key;
    protected V value;

    public SplayEntry(K k, V v)
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
    public int compareTo(SplayEntry<K, V> o)
    {
        return key.compareTo(o.getKey());
    }
}
