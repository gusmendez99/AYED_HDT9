public class BSTRedBlackTree<K extends Comparable<K>,V>  {
    protected BinarySearchTree<RedBlackTreeAssociation<K,V>> tree;

    public BSTRedBlackTree()
    {
        tree = new BinarySearchTree<>();
    }

    public V put(K key, V value)
    {
        RedBlackTreeAssociation<K,V> prev = tree.add(new RedBlackTreeAssociation<>(key, value, true));
        // restructure tree
        insertRestructure(tree.actionNode);
        if(prev == null) return null;
        return prev.getValue();
    }

    public V remove(K key)
    {
        RedBlackTreeAssociation<K,V> prev = tree.remove(new RedBlackTreeAssociation<>(key, null, false));
        if(prev == null) return null;
        return prev.getValue();
    }

    public V get(K key)
    {
        RedBlackTreeAssociation<K,V> found = tree.get(new RedBlackTreeAssociation<>(key, null, false));
        if(found == null) return null;
        return found.getValue();
    }

    public boolean contains(K key)
    {
        return get(key) != null;
    }

    protected void insertRestructure(BinaryTree<RedBlackTreeAssociation<K,V>> node)
    {
        // case 1 - color the root black
        if(node.parent() == null)
        {
            node.value().isRed = false;
            return;
        }
        // case 2 - no action taken
        if(!node.parent().value().isRed) return;
        // look for conditions
        BinaryTree<RedBlackTreeAssociation<K,V>> gp = node.parent().parent();
        BinaryTree<RedBlackTreeAssociation<K,V>> un = null;
        boolean isParentRed = node.parent().value().isRed();
        boolean isUncleRed = false;
        if(gp != null) // node has a grandparent, grab uncle
        {
            if(node.parent().isLeftChild()) un = gp.right();
            else un = gp.left();
        }
        if(un == null || un.value() == null) isUncleRed = false;
        else isUncleRed = un.value().isRed;
        // case 3 - push darkness from gp
        if(isParentRed)
        {
            // case 3 push darkness from gp
            if(isUncleRed)
            {
                gp.value().setRed();
                node.parent().value().setBlack();
                if(un != null) un.value().setBlack();
                // fix gp
                insertRestructure(gp);
                return;
            }
            else
            {
                boolean isLeft = node.isLeftChild();
                boolean isPLeft = node.parent().isLeftChild();
                // case 4
                if(isLeft && !isPLeft)
                {
                    node.parent().rotateRight();
                    node = node.right();
                }
                else if(!isLeft && isPLeft)
                {
                    node.parent().rotateLeft();
                    node = node.left();
                }
                node.parent().parent().value().setRed();
                node.parent().value().setBlack();
                if(node.isLeftChild() && node.isLeftChild()) gp.rotateRight();
                else gp.rotateLeft();
                if(node.parent().parent() == null)
                {
                    tree.root = node.parent();
                    tree.root.value().setBlack();
                }
            }
        }
    }
}
