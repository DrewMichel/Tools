package singular.structure.binarytree;

import singular.structure.tree.TreeTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Andrew Michel on 10/10/2017.
 *
 * Based on Koffman and Wolfgang
 * Data Structures: Abstraction and Design using Java
 * Second edition
 */
public class BinaryTree<E> implements TreeTemplate<E>, Serializable, Iterable<E>
{
    protected Stack<Node<E>> stack = new Stack<>();

    protected Node<E> root;

    public BinaryTree()
    {
        root = null;
    }

    protected BinaryTree(Node<E> root)
    {
        this.root = root;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)
    {
        root = new Node<E>(data);

        if(leftTree != null)
        {
            root.left = leftTree.root;
        }
//        else
//        {
//            root.left = null;
//        }

        if(rightTree != null)
        {
            root.right = rightTree.root;
        }
//        else
//        {
//            root.right = null;
//        }
    }

    public BinaryTree<E> getRightSubTree()
    {
        if(root != null && root.right != null)
        {
            return new BinaryTree<E>(root.right);
        }
        else
        {
            return null;
        }
    }

    public BinaryTree<E> getLeftSubTree()
    {
        if(root != null && root.left != null)
        {
            return new BinaryTree<E>(root.left);
        }
        else
        {
            return null;
        }
    }

    public E getData()
    {
        if(root != null)
        {
            return root.data;
        }
        else
        {
            return null;
        }
    }

    public boolean isLeaf()
    {
        return (root == null || root.left == null || root.right == null);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        preOrderTraverse(root, 1, sb);

        return sb.toString();
    }

    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
    {
        for(int i = 1; i < depth; i++)
        {
            sb.append(" ");
        }

        if(node == null)
        {
            sb.append("null\n");
        }
        else
        {
            sb.append(node.toString());
            sb.append("\n");

            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    public static BinaryTree<String> readBinaryTree(BufferedReader bufferedReader) throws IOException
    {
        String content = bufferedReader.readLine().trim();

        if(content.equals("null"))
        {
            return null;
        }
        else
        {
            BinaryTree<String> leftTree = readBinaryTree(bufferedReader);
            BinaryTree<String> rightTree = readBinaryTree(bufferedReader);

            return new BinaryTree<String>(content, leftTree, rightTree);
        }
    }

    @Override
    public Iterator iterator()
    {
        return new BinaryTreeIterator();
    }

    @Override
    public List<E> inorderTraversal()
    {
        ArrayList<E> list = new ArrayList<>();

        inorderTraverse(list, root);

        return list;
    }

    private void inorderTraverse(List<E> list, Node<E> localRoot)
    {
        if(localRoot != null)
        {
            inorderTraverse(list, localRoot.left);
            list.add(localRoot.data);
            inorderTraverse(list, localRoot.right);
        }
    }

    @Override
    public List<E> preorderTraversal()
    {
        ArrayList<E> list = new ArrayList<>();

        preorderTraverse(list, root);

        return list;
    }

    private void preorderTraverse(List<E> list, Node<E> localRoot)
    {
        if(localRoot != null)
        {
            list.add(localRoot.data);
            preorderTraverse(list, localRoot.left);
            preorderTraverse(list, localRoot.right);
        }
    }

    @Override
    public List<E> postorderTraversal()
    {
        ArrayList<E> list = new ArrayList<>();

        postorderTraverse(list, root);

        return list;
    }

    private void postorderTraverse(List<E> list, Node<E> localRoot)
    {
        if(localRoot != null)
        {
            postorderTraverse(list, localRoot.left);
            postorderTraverse(list, localRoot.right);
            list.add(localRoot.data);
        }
    }

    private class BinaryTreeIterator implements Iterator<Node<E>>
    {
        private Node<E> currentNode;
        private Node<E> lastItemReturned;

        public BinaryTreeIterator()
        {
            currentNode = root;

            if(currentNode != null)
            {
                while(currentNode.left != null)
                {
                    stack.push(currentNode);

                    currentNode = currentNode.left;
                }
            }
        }

        @Override
        public boolean hasNext()
        {
            return currentNode != null;
        }

        @Override
        public Node<E> next()
        {
            if(hasNext() == false)
            {
                throw new NoSuchElementException("Iterator has surpassed endpoint");
            }

            lastItemReturned = currentNode;

            if(currentNode.right != null)
            {
                stack.push(currentNode);

                currentNode = currentNode.right;

                while(currentNode.left != null)
                {
                    stack.push(currentNode);

                    currentNode = currentNode.left;
                }
            }
            else
            {
                while(stack.empty() == false && currentNode.equals(stack.peek().right))
                {
                    currentNode = stack.pop();
                }

                if(stack.empty() == true)
                {
                    currentNode = null;
                }
                else
                {
                    currentNode = stack.pop();
                }
            }

            return lastItemReturned;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }


    /**
     * Protected nested static Node class stores type E data, and two Nodes of type E
     *
     * @param <E> generic type received through outer class
     */
    protected static class Node<E> implements Serializable
    {
        public Node<E> left, right;
        public E data;

        public Node(E data)
        {
            left = null;
            right = null;
            this.data = data;
        }

        @Override
        public String toString()
        {
            return data.toString();
        }
    }
    // Protected nested static node class end
}
