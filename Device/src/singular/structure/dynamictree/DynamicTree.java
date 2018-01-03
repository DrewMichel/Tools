package singular.structure.dynamictree;

import singular.structure.stack.AdjustableStack;
import singular.structure.stack.StackTemplate;
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
        List<E> list = new ArrayList<>();

        // Calls preorder recursive method
        preorderTraverse(list, root);

        return list;
    }

    private void preorderTraverse(List<E> list, DynamicNode<E> localRoot)
    {
        if(localRoot != null)
        {
            list.add(localRoot.data);

            for(DynamicNode<E> node : localRoot.children)
            {
                preorderTraverse(list, node);
            }
        }
    }

    @Override
    public List<E> postorderTraversal()
    {
        List<E> list = new ArrayList<>();

        // Calls postorder recursive method
        postorderTraverse(list, root);

        return list;
    }

    private void postorderTraverse(List<E> list, DynamicNode<E> localRoot)
    {
        if(localRoot != null)
        {
            for(DynamicNode<E> node : localRoot.children)
            {
                postorderTraverse(list, node);
            }

            list.add(localRoot.data);
        }
    }

    @Override
    public Iterator<E> iterator()
    {
        return new DynamicInorderIterator();
    }

    public Iterator<E> inorderIterator()
    {
        return new DynamicInorderIterator();
    }

    public Iterator<E> preorderIterator()
    {
        return new DynamicPreorderIterator();
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

    public abstract class DynamicIterator implements Iterator<E>
    {
        protected StackTemplate<DynamicNode<E>> nodeStack;

        public DynamicIterator()
        {
            nodeStack = new AdjustableStack<>();
        }

        protected void pushAscendingChildren(DynamicNode<E> localRoot)
        {
            for(int i = 0; i < NUMBER_OF_CHILDREN; i++)
            {
                if(localRoot.children[i] != null)
                {
                    nodeStack.push(localRoot.children[i]);
                }
            }
        }

        protected void pushDescendingChildren(DynamicNode<E> localRoot)
        {
            for(int i = NUMBER_OF_CHILDREN - 1; i >= 0; i--)
            {
                if(localRoot.children[i] != null)
                {
                    nodeStack.push(localRoot.children[i]);
                }
            }
        }

        @Override
        public boolean hasNext()
        {
            return !nodeStack.isEmpty();
        }

        @Override
        public void remove() throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }
    }

    // TODO: NEEDS IMPLEMENTATION
    protected class DynamicInorderIterator extends DynamicIterator
    {
        public DynamicInorderIterator()
        {
            super();

            setup();
        }

        private void setup()
        {

        }

        @Override
        public E next()
        {
            return null;
        }
    }

    // TODO: NEEDS TESTING
    protected class DynamicPreorderIterator extends DynamicIterator
    {
        public DynamicPreorderIterator()
        {
            super();

            setup();
        }

        private void setup()
        {
            if(root != null)
            {
                nodeStack.push(root);
            }
        }

        @Override
        public E next()
        {
            DynamicNode<E> node = nodeStack.pop();

            pushDescendingChildren(node);

            return node.data;
        }
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

        // is not a parent.  has no children
        protected boolean isLeaf()
        {
            for(DynamicNode<T> node : children)
            {
                if(node != null)
                {
                    return false;
                }
            }

            return true;
        }

        // is a parent.  has children
        protected boolean isBranch()
        {
            return !isLeaf();
        }

        protected int directChildren()
        {
            int nodes = 0;

            for(DynamicNode<T> node : children)
            {
                if(node != null)
                {
                    nodes++;
                }
            }

            return nodes;
        }

        // TODO: NEEDS TESTING
        // TODO: MOVE TO DYNAMIC TREE INSTEAD OF NODE? allChildren(DynamicNode<E> localRoot).  Mostly just recursiveChildren?
        protected int allChildren()
        {
            int descendants = recursiveChildren(this);

            return descendants;
        }

        protected int recursiveChildren(DynamicNode<T> localRoot)
        {
            if(localRoot == null)
            {
                return 0;
            }
            else
            {
                int descendants = directChildren();

                for(DynamicNode<T> node : children)
                {
                    if(node != null)
                    {
                        descendants += recursiveChildren(node);
                    }
                }

                return descendants;
            }
        }

    } // Nested static class end
}
