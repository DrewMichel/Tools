package singular.structure.binarytree;

import java.util.LinkedList;
import java.util.Queue;

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

    public E delete(E target)
    {
        root = delete(root, target);

        return deleteValue;
    }

    private Node<E> delete(Node<E> localRoot, E target)
    {
        // #1 Base case: target does not exist
        if(localRoot == null)
        {
            deleteValue = null;
            return null;
        }

        int compareResult = target.compareTo(localRoot.data);

        // #2 Recursive call: traverse down left
        // #3 Recursive call: traverse down right
        // #4 Base case: target located
        if(compareResult < 0)
        {
            localRoot.left = delete(localRoot.left, target);
            return localRoot;
        }
        else if(compareResult > 0)
        {
            localRoot.right = delete(localRoot.right, target);
            return localRoot;
        }
        else
        {
            deleteValue = localRoot.data;

            // #5 Base case: return right subtree
            // #6 Base case: return left subtree
            // #7 Base case: begin rotation
            if(localRoot.left == null)
            {
                return localRoot.right;
            }
            else if(localRoot.right == null)
            {
                return localRoot.left;
            }
            else
            {
                // #1 Base case: rotate left subtree into current position
                // #2 Base case: find greatest data under immediate
                //    left node's right side then rotate it into current position
                if(localRoot.left.right == null)
                {
                    localRoot.data = localRoot.left.data;

                    localRoot.left = localRoot.left.left;

                    return localRoot;
                }
                else
                {
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    private E findLargestChild(Node<E> parent)
    {
        // #1 Base case: moves current right node's left node to
        // current right node and returns the original right node's data
        // #2 Recursive call: traverse down right node
        if(parent.right.right == null)
        {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;

            return returnValue;
        }
        else
        {
            return findLargestChild(parent.right);
        }
    }

    public String breadthFirstTraversal()
    {
        Queue<Node<E>> travelQueue = new LinkedList<>();

        Node<E> currentNode = null;

        StringBuilder data = new StringBuilder();
        StringBuilder spaces = new StringBuilder("");

        int nodesCurrentHeight = 1;
        int nodesUpcomingHeight = 0;

        if(root != null)
        {
            // Adds root node to the queue
            travelQueue.add(root);

            // Loops until the queue is empty
            while(travelQueue.isEmpty() == false)
            {
                // Sets current node to the front of the queue
                currentNode = travelQueue.poll();

                // Decrements height level
                nodesCurrentHeight--;

                if(currentNode != null)
                {
                    // Adds current node's data to StringBuilder
                    data.append(spaces.toString() + currentNode.data + "\n");

                    // Adds current's left node to queue
                    if(currentNode.left != null)
                    {
                        travelQueue.add(currentNode.left);
                        nodesUpcomingHeight++;
                    }

                    // Adds current's right node to queue
                    if(currentNode.right != null)
                    {
                        travelQueue.add(currentNode.right);
                        nodesUpcomingHeight++;
                    }
                }

                if(nodesCurrentHeight == 0)
                {
                    spaces.append(" ");

                    nodesCurrentHeight = nodesUpcomingHeight;
                    nodesUpcomingHeight = 0;
                }
            }
        }

        return data.toString();
    }
}
