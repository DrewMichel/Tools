package singular.structure.dynamictree;

import singular.structure.tree.TreeTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by Andrew Michel on 1/1/2018.
 *
 * Recommended that the number of children node remain an even number
 */
public class DynamicTree <E extends Comparable> implements TreeTemplate<E>, Iterable<E>
{
    // Defaults to a binary tree
    public static final int DEFAULT_NUMBER_OF_CHILDREN = 2;

    private final int NUMBER_OF_CHILDREN;

    protected int size;

    protected boolean addSuccessful;
    protected E dataDeleted;

    protected DynamicNode<E> root;

    public DynamicTree()
    {
        NUMBER_OF_CHILDREN = DEFAULT_NUMBER_OF_CHILDREN;

        basicInitialization();
    }

    public DynamicTree(int numberOfChildren)
    {
        NUMBER_OF_CHILDREN = numberOfChildren;

        basicInitialization();
    }

    public DynamicTree(int numberOfChildren, E data)
    {
        NUMBER_OF_CHILDREN = numberOfChildren;

        basicInitialization();

        root = new DynamicNode<>(NUMBER_OF_CHILDREN, data);

        size = 1;
    }

    private void basicInitialization()
    {
        root = null;
        size = 0;
        dataDeleted = null;
        addSuccessful = false;
    }

    // TODO: javadocs
    // inorder traversal wrapper method
    @Override
    public List<E> inorderTraversal()
    {
        // TODO: NEEDS TESTING
        List<E> list = new ArrayList<>();

        // Calls inorder recursive method
        inorderTraverse(list, root);

        return list;
    }

    // Should only require repositioning the list add method for preorder and postorder traverses
    private void inorderTraverse(List<E> list, DynamicNode<E> localRoot)
    {
        // Base case
        if(localRoot != null)
        {
            // Recursively traverses to the left
            for(int i = 0; i < localRoot.children.length / 2; i++)
            {
                inorderTraverse(list, localRoot.children[i]);
            }

            // Adds the current node's data to the list
            list.add(localRoot.data);

            // Recursively traverses to the right
            for(int i = localRoot.children.length / 2; i < localRoot.children.length; i++)
            {
                inorderTraverse(list, localRoot.children[i]);
            }
        }
    }

    @Override
    public List<E> preorderTraversal()
    {
        return null;
    }

    @Override
    public List<E> postorderTraversal()
    {
        return null;
    }


    @Override
    public Iterator<E> iterator()
    {
        return null;
    }

    @Override
    public void forEach(Consumer<? super E> action)
    {

    }

    @Override
    public Spliterator<E> spliterator()
    {
        return null;
    }

    // Nested static class begin
    protected static class DynamicNode<T>
    {
        protected DynamicNode<T>[] children;

        protected T data;

        public DynamicNode(int numberOfChildren, T data)
        {
            children = new DynamicNode[numberOfChildren];

            this.data = data;
        }

    } // Nested static class end
}
