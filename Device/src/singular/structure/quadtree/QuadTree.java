package singular.structure.quadtree;

import singular.structure.stack.AdjustableStack;
import singular.structure.stack.StackTemplate;
import singular.structure.tree.TreeTemplate;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by Andrew Michel on 12/18/2017.
 */

// Add Serializable and Iterable<E>
public class QuadTree<E extends Comparable> implements TreeTemplate<E>, Iterable<E>
{
    public static final int DEPTH_SPACING_MULTIPLIER = 3;

    protected QuadNode<E> root;
    protected int size;

    protected boolean addSuccessful;
    protected E dataDeleted;

    public QuadTree()
    {
        root = null;

        size = 0;
        addSuccessful = false;
        dataDeleted = null;
    }

    protected QuadTree(QuadNode<E> localRoot)
    {
        root = localRoot;

        determineSize();

        addSuccessful = false;
        dataDeleted = null;
    }

    @Override
    public List<E> inorderTraversal()
    {
        ArrayList<E> list = new ArrayList<>();

        inorderTraverse(list, root);

        return list;
    }

    private void inorderTraverse(List<E> list, QuadNode<E> localRoot)
    {
        if(localRoot != null)
        {
            inorderTraverse(list, localRoot.children[QuadNode.OUTER_LEFT_CHILD]);

            inorderTraverse(list, localRoot.children[QuadNode.INNER_LEFT_CHILD]);
            list.add(localRoot.data);
            inorderTraverse(list, localRoot.children[QuadNode.INNER_RIGHT_CHILD]);

            inorderTraverse(list, localRoot.children[QuadNode.OUTER_RIGHT_CHILD]);
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(), spaces = new StringBuilder();

        preorderTraverse(sb, spaces, root, 0);

        return sb.toString();
    }

    private void preorderTraverse(StringBuilder sb, StringBuilder spaces, QuadNode<E> localRoot, int depth)
    {
        if(spaces.length() > depth * DEPTH_SPACING_MULTIPLIER)
        {
            spaces.setLength(depth * DEPTH_SPACING_MULTIPLIER);
        }
        else
        {
            while(depth * DEPTH_SPACING_MULTIPLIER > spaces.length())
            {
                spaces.append(" ");
            }
        }

        if(localRoot != null)
        {
            sb.append(String.format("%sD%d-%s%n", spaces.toString(), depth, localRoot.data));

            preorderTraverse(sb, spaces, localRoot.children[QuadNode.OUTER_LEFT_CHILD], depth + 1);

            preorderTraverse(sb, spaces, localRoot.children[QuadNode.INNER_LEFT_CHILD], depth + 1);

            preorderTraverse(sb, spaces, localRoot.children[QuadNode.INNER_RIGHT_CHILD], depth + 1);

            preorderTraverse(sb, spaces, localRoot.children[QuadNode.OUTER_RIGHT_CHILD], depth + 1);
        }
        else
        {
            sb.append(String.format("%sD%d-%s%n", spaces.toString(), depth, "null"));
        }
    }

    @Override
    public List<E> preorderTraversal()
    {
        ArrayList<E> list = new ArrayList<>();

        preorderTraverse(list, root);

        return list;
    }

    private void preorderTraverse(List<E> list, QuadNode<E> localRoot)
    {
        if(localRoot != null)
        {
            list.add(localRoot.data);

            preorderTraverse(list, localRoot.children[QuadNode.OUTER_LEFT_CHILD]);

            preorderTraverse(list, localRoot.children[QuadNode.INNER_LEFT_CHILD]);

            preorderTraverse(list, localRoot.children[QuadNode.INNER_RIGHT_CHILD]);

            preorderTraverse(list, localRoot.children[QuadNode.OUTER_RIGHT_CHILD]);
        }
    }

    @Override
    public List<E> postorderTraversal()
    {
        ArrayList<E> list = new ArrayList<>();

        postorderTraverse(list, root);

        return list;
    }

    private void postorderTraverse(List<E> list, QuadNode<E> localRoot)
    {
        if(localRoot != null)
        {
            postorderTraverse(list, localRoot.children[QuadNode.OUTER_LEFT_CHILD]);

            postorderTraverse(list, localRoot.children[QuadNode.INNER_LEFT_CHILD]);

            postorderTraverse(list, localRoot.children[QuadNode.INNER_RIGHT_CHILD]);

            postorderTraverse(list, localRoot.children[QuadNode.OUTER_RIGHT_CHILD]);

            list.add(localRoot.data);
        }
    }

    public boolean add(E data)
    {
        addSuccessful = false;

        root = add(root, data);

        return addSuccessful;
    }

    private QuadNode<E> add(QuadNode<E> localRoot, E data)
    {
        if(localRoot == null)
        {
            addSuccessful = true;
            size++;
            return new QuadNode<>(data);
        }

        int compareResult = localRoot.data.compareTo(data);

        // Q3 -1 Q4 -2
        // Q1  2 Q2  1

        if(compareResult > 1)
        {
            // Q1
            localRoot.children[QuadNode.OUTER_LEFT_CHILD] = add(localRoot.children[QuadNode.OUTER_LEFT_CHILD], data);
        }
        else if(compareResult > 0)
        {
            // Q2
            localRoot.children[QuadNode.INNER_LEFT_CHILD] = add(localRoot.children[QuadNode.INNER_LEFT_CHILD], data);
        }
        else if(compareResult < -1)
        {
            // Q4
            localRoot.children[QuadNode.OUTER_RIGHT_CHILD] = add(localRoot.children[QuadNode.OUTER_RIGHT_CHILD], data);
        }
        else if(compareResult < 0)
        {
            // Q3
            localRoot.children[QuadNode.INNER_RIGHT_CHILD] = add(localRoot.children[QuadNode.INNER_RIGHT_CHILD], data);
        }


        // Q1  2 Q2  1
        // Q3 -1 Q4 -2

//        if(localRoot.data.compareTo(data) < -1)
//        {
//            // Q4
//            localRoot.children[QuadNode.OUTER_RIGHT_CHILD] = add(localRoot.children[QuadNode.OUTER_RIGHT_CHILD], data);
//        }
//        else if(localRoot.data.compareTo(data) < 0)
//        {
//            // Q3
//            localRoot.children[QuadNode.INNER_RIGHT_CHILD] = add(localRoot.children[QuadNode.INNER_RIGHT_CHILD], data);
//        }
//        else if(localRoot.data.compareTo(data) > 1)
//        {
//            // Q2
//            localRoot.children[QuadNode.INNER_LEFT_CHILD] = add(localRoot.children[QuadNode.INNER_LEFT_CHILD], data);
//        }
//        else if(localRoot.data.compareTo(data) > 0)
//        {
//            // Q1
//            localRoot.children[QuadNode.OUTER_LEFT_CHILD] = add(localRoot.children[QuadNode.OUTER_LEFT_CHILD], data);
//        }
//        else
//        {
//            //System.err.println("DUPLICATE.  LOCAL ROOT DATA: " + localRoot.data + " DATA: " + data + " COMPARISON VALUE: " + localRoot.data.compareTo(data));
//        }

        return localRoot;
    }



    public E find(E data)
    {
        return find(root, data);
    }

    private E find(QuadNode<E> localRoot, E data)
    {
        if(localRoot == null)
        {
            return null;
        }

        int compareResult = localRoot.data.compareTo(data);

        if(compareResult > 1)
        {
            return find(localRoot.children[QuadNode.OUTER_LEFT_CHILD], data);
        }
        else if(compareResult > 0)
        {
            return find(localRoot.children[QuadNode.INNER_LEFT_CHILD], data);
        }
        else if(compareResult < -1)
        {
            return find(localRoot.children[QuadNode.OUTER_RIGHT_CHILD], data);
        }
        else if(compareResult < 0)
        {
            return find(localRoot.children[QuadNode.INNER_RIGHT_CHILD], data);
        }
        else //if(compareResult == 0)
        {
            return localRoot.data;
        }
    }

    public boolean contains(E data)
    {
        return find(data) != null;
    }

    public E remove(E data)
    {
        dataDeleted = null;

        root = remove(root, null, (byte) -1, data);

        return dataDeleted;
    }

    private QuadNode<E> remove(QuadNode<E> localRoot, QuadNode<E> parent, byte index, E data)
    {
        if(localRoot == null)
        {
            return localRoot;
        }

        int compareResult = localRoot.data.compareTo(data);

        if(compareResult > 1)
        {
            // Q1
            localRoot.children[QuadNode.OUTER_LEFT_CHILD] = remove(localRoot.children[QuadNode.OUTER_LEFT_CHILD], localRoot, QuadNode.OUTER_LEFT_CHILD, data);
        }
        else if(compareResult > 0)
        {
            // Q2
            localRoot.children[QuadNode.INNER_LEFT_CHILD]  = remove(localRoot.children[QuadNode.INNER_LEFT_CHILD], localRoot, QuadNode.INNER_LEFT_CHILD, data);;
        }
        else if(compareResult < -1)
        {
            // Q4
            localRoot.children[QuadNode.OUTER_RIGHT_CHILD]  = remove(localRoot.children[QuadNode.OUTER_RIGHT_CHILD], localRoot, QuadNode.OUTER_RIGHT_CHILD, data);;
        }
        else if(compareResult < 0)
        {
            // Q3
            localRoot.children[QuadNode.INNER_RIGHT_CHILD]  = remove(localRoot.children[QuadNode.INNER_RIGHT_CHILD], localRoot, QuadNode.INNER_RIGHT_CHILD, data);;
        }
        else //if(compareResult == 0)
        {
            // Found data

            dataDeleted = localRoot.data;
            size--;

            byte nullChildren = 0;
            byte childIndex = 0;

            for(byte i = 0; i < QuadNode.NUMBER_OF_CHILDREN; i++)
            {
                if(localRoot.children[i] == null)
                {
                    nullChildren++;
                }
                else
                {
                    childIndex = i;
                }
            }

            if(nullChildren >= QuadNode.NUMBER_OF_CHILDREN - 1)
            {
                // All null or one child
                return localRoot.children[childIndex]; // returns the single child or null
            }
//            else if(nullChildren == QuadNode.NUMBER_OF_CHILDREN)
//            {
//                // No Children
//                return null;
//            }
            else
            {
                // Multiple children

                // TODO: Needs revision

                QuadTree<E>[] distributions = new QuadTree[QuadNode.NUMBER_OF_CHILDREN];

                for(byte i = 0; i < distributions.length; i++)
                {
                    distributions[i] = new QuadTree<>(localRoot.children[i]);
                }

                if(parent != null)
                {
                    // TODO: Could pass parent node and index through to prevent retracing
                    //forceDelete(root, localRoot.data);
                    parent.children[index] = null;

                    // TODO: Iterate over distributions and add back into tree
                    for(byte i = 0; i < distributions.length; i++)
                    {
                        preorderAddTree(distributions[i]);
                    }

                    localRoot = parent.children[index];
                }
                else // if(parent == null) // parent is root
                {
                    root = null;

                    for(byte i = 0; i < distributions.length; i++)
                    {
                        preorderAddTree(distributions[i]);
                    }

                    localRoot = root;
                }
            }
        }

        return localRoot;
    }

    private boolean preorderAddTree(QuadTree<E> tree)
    {
        addSuccessful = false;

        preorderAdd(tree.root);

        return addSuccessful;
    }

    private void preorderAdd(QuadNode<E> localRoot)
    {
        if(localRoot != null)
        {
            add(localRoot.data);
            size--;
            preorderAdd(localRoot.children[QuadNode.OUTER_LEFT_CHILD]);
            preorderAdd(localRoot.children[QuadNode.INNER_LEFT_CHILD]);
            preorderAdd(localRoot.children[QuadNode.INNER_RIGHT_CHILD]);
            preorderAdd(localRoot.children[QuadNode.OUTER_RIGHT_CHILD]);
        }
    }

//    private boolean forceDelete(QuadNode<E> localRoot, E data)
//    {
//
//    }

//    private QuadNode<E> findLargestChild(QuadNode<E> localRoot)
//    {
//        // TODO: NEEDS IMPLEMENTATION
//        return null;
//    }

    public int determineSize()
    {
        size = 0;

        determine(root);

        return size;
    }

    private void determine(QuadNode<E> localRoot)
    {
        if(localRoot != null)
        {
            size++;
            determine(localRoot.children[QuadNode.OUTER_LEFT_CHILD]);
            determine(localRoot.children[QuadNode.INNER_LEFT_CHILD]);
            determine(localRoot.children[QuadNode.INNER_RIGHT_CHILD]);
            determine(localRoot.children[QuadNode.OUTER_RIGHT_CHILD]);
        }
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size < 1;
    }

    // TODO: Include additional iterators (classes?) for preorder and postorder?
    @Override
    public Iterator<E> iterator()
    {
        return inorderIterator();
        //return new InorderQuadIterator();
    }

    public InorderQuadIterator inorderIterator()
    {
        return new InorderQuadIterator();
    }

    public Iterator<E> preorderIterator()
    {
        return new PreorderQuadIterator();
    }

    public Iterator<E> postorderIterator()
    {
        return new PostorderQuadIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Spliterator<E> spliterator() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    // Nested class QuadNode begin
    protected static class QuadNode<T> // Add Serializable?
    {
        public static final byte NUMBER_OF_CHILDREN = 4;

        public static final byte OUTER_LEFT_CHILD = 0, INNER_LEFT_CHILD = 1, INNER_RIGHT_CHILD = 2, OUTER_RIGHT_CHILD = 3;

        protected QuadNode<T>[] children;

        protected T data;

        // Duplicates can be handled by counting the number of values at this node
        // (good for simple points)
        // Or by having a list of data
        // (good for unique or moving data where the position doesn't represent the entire object)

        public QuadNode(T data)
        {
            children = new QuadNode[NUMBER_OF_CHILDREN];

            this.data = data;
        }
    } // Nested class QuadNode end

    // Nested abstract class QuadIterator begin
    public abstract class QuadIterator implements Iterator<E>
    {
        protected StackTemplate<QuadNode<E>> nodeStack;

        // TODO: DETERMINE NECESSARY INSTANCE VARIABLES
        // protected QuadNode<E> parent;
        // protected QuadNode<E> next;
        // protected E lastItemReturned;

        // protected int expectedNodes;

        public QuadIterator()
        {
            nodeStack = new AdjustableStack<>();
        }

        protected void addAllLeftChildren(QuadNode<E> localRoot)
        {
            if(localRoot != null)
            {
                nodeStack.push(localRoot);
                addAllLeftChildren(localRoot.children[QuadNode.INNER_LEFT_CHILD]);
                addAllLeftChildren(localRoot.children[QuadNode.OUTER_LEFT_CHILD]);
            }
        }

        protected void addImmediateLeftChildren(QuadNode<E> localRoot)
        {
            if(localRoot.children[QuadNode.INNER_LEFT_CHILD] != null)
            {
                nodeStack.push(localRoot.children[QuadNode.INNER_LEFT_CHILD]);
            }

            if(localRoot.children[QuadNode.OUTER_LEFT_CHILD] != null)
            {
                nodeStack.push(localRoot.children[QuadNode.OUTER_LEFT_CHILD]);
            }
        }

        protected void addAllRightChildren(QuadNode<E> localRoot)
        {
            if(localRoot != null)
            {
                addAllRightChildren(localRoot.children[QuadNode.OUTER_RIGHT_CHILD]);
                addAllRightChildren(localRoot.children[QuadNode.INNER_RIGHT_CHILD]);

                nodeStack.push(localRoot);
            }
        }

        protected void addImmediateRightChildren(QuadNode<E> localRoot)
        {
            if(localRoot.children[QuadNode.OUTER_RIGHT_CHILD] != null)
            {
                nodeStack.push(localRoot.children[QuadNode.OUTER_RIGHT_CHILD]);
            }

            if(localRoot.children[QuadNode.INNER_RIGHT_CHILD] != null)
            {
                nodeStack.push(localRoot.children[QuadNode.INNER_RIGHT_CHILD]);
            }

        }

        @Override
        public boolean hasNext()
        {
            return !nodeStack.isEmpty();
        }

        public int size()
        {
            return nodeStack.size();
        }

        public int capacity()
        {
            return nodeStack.capacity();
        }

    } // Nested abstract class QuadIterator end

    // TODO: NEEDS IMPLEMENTATION
    // On creation populate stack by
    // Pushing current node
    // recursive call on inner left
    // recursive call on inner right

    // Nested class InorderQuadIterator begin
    public class InorderQuadIterator extends QuadIterator
    {
        public InorderQuadIterator()
        {
            super();

            initialPopulateNodeStack();
        }

        protected boolean initialPopulateNodeStack()
        {
            addAllLeftChildren(root);

            return true;
        }
        
        // Algorithm:
        // 1) node/lastItemReturned = stack.pop;
        // 2) call addAllLeftChildren on outer right child
        // 3) call addAllLeftChildren on inner right child
        // 4) return node/lastItemReturned;

        // Could use concurrent check
        @Override
        public E next() throws NoSuchElementException//, ConcurrentModificationException
        {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }

            QuadNode<E> top = nodeStack.pop();

            addAllLeftChildren(top.children[QuadNode.OUTER_RIGHT_CHILD]);

            addAllLeftChildren(top.children[QuadNode.INNER_RIGHT_CHILD]);

            return top.data;
        }

        @Override
        public void remove() throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }
    } // Nested class InorderQuadIterator end

    // Nested class PreorderQuadIterator begin
    public class PreorderQuadIterator extends QuadIterator
    {
        public PreorderQuadIterator()
        {
            super();

            initialPopulateNodeStack();
        }

        protected boolean initialPopulateNodeStack()
        {
            if(root != null)
            {
                nodeStack.push(root);
            }

            return true;
        }

        @Override
        public E next() throws NoSuchElementException//, ConcurrentModificationException
        {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }

            QuadNode<E> top = nodeStack.pop();

            // Move towards dynamic tree
            for(int i = QuadNode.NUMBER_OF_CHILDREN - 1; i >= 0; i--)
            {
                if(top.children[i] != null)
                {
                    nodeStack.push(top.children[i]);
                }
            }

            return top.data;
        }

        @Override
        public void remove() throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }
    } // Nested class PreorderQuadIterator end

    // Nested class PostorderQuadIterator begin
    public class PostorderQuadIterator extends QuadIterator
    {
        // TODO: NEEDS IMPLEMENTATION

        @Override
        public E next()
        {
            return null;
        }

        @Override
        public void remove() throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) throws UnsupportedOperationException
        {
            throw new UnsupportedOperationException();
        }
    } // Nested class PostorderQuadIterator end
}
