import java.util.ArrayList;
import java.util.List;
//Code extracted from https://www.geeksforgeeks.org/binary-search-tree-data-structure/

/**
 * Class that represents the complete tree of all the possible BinaryTrees, it has operations to alter data of
 * the BinaryTrees and change the order or add a new BinaryTree
 *
 * @param <E> the value of the key of a BinaryTree
 * @author Luis Urbina
 * @version 1.0
 * @since 22/03/2019
 */
public class BinarySearchTree<E extends Comparable<E>> {
    protected BinaryTree<E> root;
    protected BinaryTree<E> actionNode;
    protected int size;

    public BinarySearchTree() {
        root = new BinaryTree<>();
        size = 0;
    }

    public boolean isEmpty() {
        return root.isEmpty();
    }

    public void clear() {
        root = new BinaryTree<>();
        size = 0;
    }

    public int size() {
        return size;
    }

    public E add(E value) {
        // create new entry to be inserted
        //Entry<K,V> e = new SEntry(key, value);
        E old = null;
        if (root.isEmpty()) {
            root = new BinaryTree<>(value);
            actionNode = root;
            size++;
        } else {
            // find node where provided key should go
            BinaryTree<E> foundNode = findNode(value, root);
            // save found node as action-node for use in other algorithms
            actionNode = foundNode;
            // key already in tree, update value
            if (!foundNode.isEmpty()) {
                //old = foundNode.value().getValue();
                //foundNode.setValue(e);
                old = foundNode.value();
                foundNode.setValue(value);
            } else {
//              foundNode.setValue(e);
                foundNode.setValue(value);
                // add empty children nodes if needed
                if (foundNode.left() == null)
                    foundNode.setLeft(new BinaryTree<>());
                if (foundNode.right() == null)
                    foundNode.setRight(new BinaryTree<>());
                size++;
            }
        }
        return old;
    }

    public boolean contains(E value) {
        return get(value) == null;
    }

    public E get(E element) {
        if (element == null || root.isEmpty()) return null;
        BinaryTree<E> node = findNode(element, root);
        if (node.isEmpty()) return null;
        actionNode = node;
        return node.value();
    }

    public E remove(E value) {
        if (value == null) return null;
        BinaryTree<E> foundNode = findNode(value, root);
        // key is not in tree
        if (foundNode.isEmpty()) return null;
        E temp = foundNode.value();
        // handle removing the last node
        if (size == 1) {
            clear();
            return temp;
        } else {
            if (foundNode.left().isEmpty())
                removeExternal(foundNode.left());
            else if (foundNode.right().isEmpty())
                removeExternal(foundNode.right());
            else {
                // find replacement node
                BinaryTree<E> rep = foundNode.right();
                while (rep.left().isInternal()) rep = rep.left();
                // move rep value to foundNode
                foundNode.setValue(rep.value());
                // remove external node that is left child of replacement node
                removeExternal(rep.left());
                // update action-node to point to foundNode parent
                actionNode = foundNode.parent();
            }
        }
        size--;
        return temp;
    }

    public List<E> valuesInOrder() {
        List<E> list = new ArrayList<>();
        if (isEmpty()) return list;
        inOrderRecursive(root, list);
        return list;
    }

    /**
     * Helper method used by add, get and remove methods.
     *
     * @param node
     * @param value
     * @return
     */
    protected BinaryTree<E> findNode(E value, BinaryTree<E> node) {
        if (node.isEmpty()) return node;
        else {
            // compare current node key with provided key
            int c = node.value().compareTo(value);
            if (c == 0) return node; // we found the node
            else if (c > 0) // node key > key
            {
                // search left
                return findNode(value, node.left());
            } else {
                // search right
                return findNode(value, node.right());
            }
        }
    }

    /**
     * Remove external node v and its parent p, and replace p with v's sibling.
     *
     * @param v
     */
    protected void removeExternal(BinaryTree<E> v) {
        // node is not external
        if (!v.isEmpty()) return;
        // grab v's sibling
        BinaryTree<E> s = null;
        // grab v's parent
        BinaryTree<E> p = v.parent();
        if (v.isLeftChild()) s = p.right();
        else s = p.left();
        if (p.parent() != null) {
            if (p.isLeftChild()) p.parent().setLeft(s);
            else p.parent().setRight(s);
        } else {
            root = s;
            root.setParent(null);
        }
    }

    protected void inOrderRecursive(BinaryTree<E> node, List<E> list) {
        if (node.left() != null && !node.left().isEmpty())
            inOrderRecursive(node.left(), list);
        list.add(node.value());
        if (node.right() != null && !node.right().isEmpty())
            inOrderRecursive(node.right(), list);
    }

}