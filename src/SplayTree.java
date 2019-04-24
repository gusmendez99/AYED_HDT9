public class SplayTree<E extends Comparable<E>>
        extends BinarySearchTree<E>
{
    public E add(E value)
    {
        E old = super.add(value);
        splay(actionNode);
        return old;
    }

    public E remove(E element)
    {
        E old = super.remove(element);
        if(actionNode != null) splay(actionNode);
        return old;
    }

    public boolean contains(E element)
    {
        boolean b = super.contains(element);
        if(b) splay(actionNode);
        return b;
    }

    public E get(E element)
    {
        E e = super.get(element);
        if(e != null) splay(actionNode);
        return e;
    }

    /**
     * @param node Node to splay.
     * pre: node is not the root of the tree.
     * post: node is the root of the tree.
     */
    protected void splay(BinaryTree<E> node)
    {
        if(node.equals(root) || node == null) return;
        // get the parent and grandparent of this node
        BinaryTree<E> gp = null;
        BinaryTree<E> p = null;
        boolean hasGp = false;
        while(node.parent() != null)
        {
            p = node.parent();
            if(p != null) gp = p.parent();
            hasGp = gp != null;
            if (node.isLeftChild()) {
                if (hasGp) // node has a grandparent
                {
                    if (p.isLeftChild()) // left-left
                    {
                        gp.rotateRight();
                        p.rotateRight();
                    } else // left-right
                    {
                        p.rotateRight();
                        gp.rotateLeft();
                    }
                } else // left; node has only parent.
                {
                    p.rotateRight();
                }
            }
            else // node is right child
            {
                if (hasGp) // node has a grandparent
                {
                    if (p.isLeftChild()) // right-left
                    {
                        p.rotateLeft();
                        gp.rotateRight();
                    } else // right-right
                    {
                        gp.rotateLeft();
                        p.rotateLeft();
                    }
                } else // right; node has only parent.
                {
                    p.rotateLeft();
                }
            }

        }

        root = node;
    }
}
