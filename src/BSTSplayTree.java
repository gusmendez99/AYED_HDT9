public class BSTSplayTree<K extends Comparable<K>,V>
{
    protected SplayTree<SplayTreeAssociation<K,V>> tree;

    public BSTSplayTree()
    {
        tree = new SplayTree<>();
    }

    public V put(K key, V value)
    {
        SplayTreeAssociation<K,V> old = tree.add(new SplayTreeAssociation<>(key, value));
        if(old == null) return null;
        return old.getValue();
    }

    public V remove(K key)
    {
        SplayTreeAssociation<K,V> temp = tree.remove(new SplayTreeAssociation<>(key, null));
        if(temp == null) return null;
        return temp.getValue();
    }

    public boolean contains(K key)
    {
        SplayTreeAssociation<K,V> temp = tree.get(new SplayTreeAssociation<>(key, null));
        if(temp == null) return false;
        return temp.getValue() != null;
    }

    public V get(K key)
    {
        SplayTreeAssociation<K,V> temp = tree.get(new SplayTreeAssociation<>(key, null));
        if(temp == null) return null;
        return temp.getValue();
    }

    
}