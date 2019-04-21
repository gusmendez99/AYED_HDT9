public class SplayTreeMap<K extends Comparable<K>,V>
{
    protected SplayTree<SplayEntry<K,V>> tree;

    public SplayTreeMap()
    {
        tree = new SplayTree<>();
    }

    public V put(K key, V value)
    {
        SplayEntry<K,V> old = tree.add(new SplayEntry<>(key, value));
        if(old == null) return null;
        return old.getValue();
    }

    public V remove(K key)
    {
        SplayEntry<K,V> temp = tree.remove(new SplayEntry<>(key, null));
        if(temp == null) return null;
        return temp.getValue();
    }

    public boolean contains(K key)
    {
        SplayEntry<K,V> temp = tree.get(new SplayEntry<>(key, null));
        if(temp == null) return false;
        return temp.getValue() != null;
    }

    public V get(K key)
    {
        SplayEntry<K,V> temp = tree.get(new SplayEntry<>(key, null));
        if(temp == null) return null;
        return temp.getValue();
    }

    
}
