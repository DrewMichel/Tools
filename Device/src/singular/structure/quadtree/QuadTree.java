package singular.structure.quadtree;

import singular.structure.tree.TreeTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Michel on 12/18/2017.
 */

// Add Serializable and Iterable<E>
// Make abstract?
public class QuadTree<E extends Comparable> implements TreeTemplate<E>
{
    protected QuadNode<E> root;
    protected long size;

    protected boolean addSuccessful;
    protected E dataDeleted;

    public QuadTree()
    {
        root = null;

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
        if(spaces.length() > depth * 3)
        {
            spaces.setLength(depth * 3);
        }
        else
        {
            while(depth * 3 > spaces.length())
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
            return new QuadNode<>(data);
        }

        // Q3 -1 Q4 -2
        // Q1  2 Q2  1

        if(localRoot.data.compareTo(data) > 1)
        {
            // Q1
            localRoot.children[QuadNode.OUTER_LEFT_CHILD] = add(localRoot.children[QuadNode.OUTER_LEFT_CHILD], data);
        }
        else if(localRoot.data.compareTo(data) > 0)
        {
            // Q2
            localRoot.children[QuadNode.INNER_LEFT_CHILD] = add(localRoot.children[QuadNode.INNER_LEFT_CHILD], data);
        }
        else if(localRoot.data.compareTo(data) < -1)
        {
            // Q4
            localRoot.children[QuadNode.OUTER_RIGHT_CHILD] = add(localRoot.children[QuadNode.OUTER_RIGHT_CHILD], data);
        }
        else if(localRoot.data.compareTo(data) < 0)
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

    // Add Serializable and make E extend Comparable?
    protected static class QuadNode<E>
    {
        public static final int NUMBER_OF_CHILDREN = 4;

        public static final int OUTER_LEFT_CHILD = 0, INNER_LEFT_CHILD = 1, INNER_RIGHT_CHILD = 2, OUTER_RIGHT_CHILD = 3;

        protected QuadNode<E>[] children;

        protected E data;

        // Duplicates can be handled by counting the number of values at this node
        // (good for simple points)
        // Or by having a list of data
        // (good for unique or moving data where the position doesn't represent the entire object)

        public QuadNode(E data)
        {
            children = new QuadNode[NUMBER_OF_CHILDREN];

            this.data = data;
        }
    }
}
