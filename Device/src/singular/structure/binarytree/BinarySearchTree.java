package singular.structure.binarytree;

/**
 * Created by Andrew Michel on 10/10/2017.
 *
 * Based on Koffman and Wolfgang
 * Data Structures: Abstraction and Design using Java
 * Second edition
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E>
{
    protected boolean addedValue;
    protected E deleteValue;

    public BinarySearchTree()
    {

    }

    public BinarySearchTree(E data)
    {
        add(root, data);
    }

    public boolean add(E data)
    {
        root = add(root, data);
        return addedValue;
    }

    private Node<E> add(Node<E> localRoot, E data)
    {
        // #1 Base case: insert successful
        // #2 Base case: insert failed
        // #3 Recursive call: traverse down left
        // #4 Recursive call: traverse down right
        if(localRoot == null)
        {
            addedValue = true;
            return new Node<E>(data);
        }
        else if(data.compareTo(localRoot.data) == 0)
        {
            addedValue = false;
            return localRoot;
        }
        else if(data.compareTo(localRoot.data) < 0)
        {
            localRoot.left = add(localRoot.left, data);
            return localRoot;
        }
        else
        {
            localRoot.right = add(localRoot.right, data);
            return localRoot;
        }
    }
    public E find(E target)
    {
        return find(root, target);
    }

    private E find(Node<E> localRoot, E target)
    {
        // #1 Base case: target does not exist
        if(localRoot == null)
        {
            return null;
        }

        // Compares the target data against the current node's data
        int compareResult = target.compareTo(localRoot.data);

        // #2 Base case: target found
        // #3 Recursive call: traverse down left
        // #4 Recursive call: traverse down right
        if(compareResult == 0)
        {
            return localRoot.data;
        }
        else if(compareResult < 0)
        {
            return find(localRoot.left, target);
        }
        else
        {
            return find(localRoot.right, target);
        }
    }
}
