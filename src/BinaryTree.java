/**
 * Methods extracted from https://www.baeldung.com/java-binary-tree
 * */

/**
 * A single BinaryTree class with an association as value
 * @param <E> the Association-type object
 */
public class BinaryTree<E> {
    /**
     * The containing value of the BinaryTree
     */
    protected E value;
    protected BinaryTree<E> parent, left, right;

    public BinaryTree()
    {
        value = null;
        parent = left = right = null;
    }

    public BinaryTree(E value)
    {
        this.value = value;
        setLeft(new BinaryTree<>());
        setRight(new BinaryTree<>());
    }

    public BinaryTree(E value, BinaryTree<E> left, BinaryTree<E> right)
    {
        this(value);
        if(left != null) setLeft(left);
        if(right != null) setRight(right);
    }

    public BinaryTree<E> left() { return left; }
    public BinaryTree<E> right() { return right; }
    public BinaryTree<E> parent() { return parent; }

    public void setLeft(BinaryTree<E> newLeft)
    {
        left = newLeft;
        newLeft.setParent(this);
    }

    public void setRight(BinaryTree<E> newRight)
    {
        right = newRight;
        newRight.setParent(this);
    }

    public boolean isLeftChild()
    {
        if(parent != null)
        {
            BinaryTree left = parent.left();
            return this == left;
        }
        return false;
    }

    public boolean isRightChild()
    {
        if(parent != null)
        {
            BinaryTree right = parent.right();
            return this == right;
        }
        return false;
    }

    /**
     * Return true if this node has at least one child, false otherwise.
     */
    public boolean isInternal()
    {
        return ((left() != null) || (right != null));
    }

    public E value() { return value; }

    public void setValue(E newValue) { value = newValue; }

    protected void setParent(BinaryTree<E> newParent)
    {
        parent = newParent;
    }

    protected boolean isEmpty()
    {
        return value == null;
    }

    /**
     * Rotates right over this node.
     * pre: this node has a left child.
     * post: This node is one level lower. Left child is one level higher.
     * @return The new root.
     */
    protected BinaryTree<E> rotateRight()
    {
        BinaryTree<E> newRoot = left();
        if(newRoot == null) return this;
        BinaryTree<E> tempParent = this.parent();
        boolean isLeft = this.isLeftChild();
        setLeft(newRoot.right());
        newRoot.setRight(this);
        if(tempParent != null)
        {
            if(isLeft) tempParent.setLeft(newRoot);
            else tempParent.setRight(newRoot);
        }
        else newRoot.setParent(null);
        return newRoot;
    }
    /**
     * Rotates left over this node.
     * pre: this node has a right child.
     * post: This node is one level lower. Right child is one level higher.
     * @return The new root.
     */
    protected BinaryTree<E> rotateLeft()
    {
        BinaryTree<E> newRoot = this.right();
        if(newRoot == null) return this;
        BinaryTree<E> tempParent = this.parent();
        boolean isLeft = this.isLeftChild();
        setRight(newRoot.left());
        newRoot.setLeft(this);
        if(tempParent != null)
        {
            if(isLeft) tempParent.setLeft(newRoot);
            else tempParent.setRight(newRoot);
        }
        else newRoot.setParent(null);
        return newRoot;
    }

}
