package singular.structure.dynamictree;

import singular.structure.stack.AdjustableStack;
import singular.structure.stack.StackTemplate;
import singular.structure.tree.TreeTemplate;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by Andrew Michel on 1/1/2018.
 *
 * Recommended that the number of children node remain an even number
 *
 * The comparator should return the index position that the data will be inserted into with relation to a parent node
 * The comparator's compare method first argument will be the parent node's data with the second argument the data to be inserted
 *
 * If the comparator is null, the objects will be cast to Comparable with lesser objects traversing down
 * the left most index and greater objects traversing down the right most index
 */
public class DynamicTree<E> implements TreeTemplate<E>, Iterable<E>
{
    // Defaults to a binary tree
    public static final int DEFAULT_NUMBER_OF_CHILDREN = 2;

    protected final int NUMBER_OF_CHILDREN;

    // null comparator should behave like a binary tree
    // nodes will have to be type-casted to Comparable if the comparator is null
    protected Comparator<E> comparator;

    protected int size;

    protected boolean addSuccessful;
    protected E dataDeleted;

    protected DynamicNode<E> root;

    public DynamicTree()
    {
        NUMBER_OF_CHILDREN = DEFAULT_NUMBER_OF_CHILDREN;

        basicInitialization();
    }

    public DynamicTree(int numberOfChildren, Comparator<E> comparator)
    {
        NUMBER_OF_CHILDREN = numberOfChildren;

        basicInitialization();

        this.comparator = comparator;
    }

    private void basicInitialization()
    {
        comparator = null;
        root = null;
        size = 0;
        dataDeleted = null;
        addSuccessful = false;
    }

    public int size()
    {
        return size;
    }

    public boolean add(E data)
    {
        addSuccessful = false;

        // Recursive method call
        root = add(root, data);

        return addSuccessful;
    }

    // TODO: NEEDS TESTING
    protected DynamicNode<E> add(DynamicNode<E> localRoot, E data)
    {
        // Base case and insertion
        if(localRoot == null)
        {
            addSuccessful = true;
            size++;

            return new DynamicNode<>(NUMBER_OF_CHILDREN, data);
        }
        else
        {
            // Compare data against localRoot.data and make recursive call

            int compareResult;

            if(comparator == null)
            {
                // cast to Comparable or Comparable<E>?
                compareResult = ((Comparable)(localRoot.data)).compareTo(data);

                if(compareResult > 0)
                {
                    localRoot.children[localRoot.leftMostIndex()] = add(localRoot.children[localRoot.leftMostIndex()], data);
                }
                else if(compareResult < 0)
                {
                    localRoot.children[localRoot.rightMostIndex()] = add(localRoot.children[localRoot.rightMostIndex()], data);
                }
            }
            else
            {
                // use comparator
                compareResult = comparator.compare(localRoot.data, data);

                // TODO: NEEDS TESTING

                if(compareResult >= 0)
                {
                    localRoot.children[compareResult] = add(localRoot.children[compareResult], data);
                }
            }

            return localRoot;
        }


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

    public Iterator<E> postorderIterator() { return new DynamicPostorderIterator(); }

    @Override
    public void forEach(Consumer<? super E> action)
    {

    }

    @Override
    public Spliterator<E> spliterator()
    {
        return null;
    }

    protected abstract class DynamicIterator implements Iterator<E>
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
        public E next() throws NoSuchElementException
        {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }

            DynamicNode<E> node = nodeStack.pop();

            pushDescendingChildren(node);

            return node.data;
        }
    }

    protected static class DynamicContainer<T>
    {
        protected DynamicNode<T> node;

        protected int index;

        public DynamicContainer(DynamicNode node, int index)
        {
            this.node = node;
            this.index = index;
        }
    }

    // TODO: NEEDS TESTING
    // Nested class begin
    protected class DynamicPostorderIterator implements Iterator<E>
    {
        public static final int DEFAULT_INDEX = 0;

        protected StackTemplate<DynamicContainer<E>> containerStack;

        public DynamicPostorderIterator()
        {
            super();

            setup();
        }

        private void setup()
        {
            containerStack = new AdjustableStack<>();

            if(root != null)
            {
                containerStack.push(new DynamicContainer<E>(root, DEFAULT_INDEX));
            }
        }

        protected void pushNextChild(DynamicContainer<E> container)
        {
            boolean pushed = false;

            for(int i = container.index; i < NUMBER_OF_CHILDREN && !pushed; i++)
            {
                container.index++;

                if(container.node.children[i] != null)
                {
                    pushed = true;

                    containerStack.push(new DynamicContainer<E>(container.node.children[i], DEFAULT_INDEX));

                    pushNextChild(containerStack.peek());
                }
            }
        }

        public int capacity()
        {
            return containerStack.capacity();
        }

        @Override
        public boolean hasNext()
        {
            return !containerStack.isEmpty();
        }

        @Override
        public E next() throws NoSuchElementException
        {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }

            pushNextChild(containerStack.peek());

            return containerStack.pop().node.data;
        }

        @Override
        public void remove() throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }
    } // Nested class end

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


        protected int leftMostIndex()
        {
            return 0;
        }

        protected int rightMostIndex()
        {
            return children.length - 1;
        }

    } // Nested static class end
}
