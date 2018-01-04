package singular.structure.dynamictree.driver;

import singular.structure.dynamictree.DynamicTree;
import singular.structure.quadtree.driver.ComparablePoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Andrew Michel on 1/4/2018.
 */
public class DynamicTreePostorderDriver
{
    public static void main(String[] args)
    {
        DynamicTree<ComparablePoint> tree = new DynamicTree<>(4, new PointComparator());

        List<ComparablePoint> postorderList = null;

        Random generator = new Random();

        int bounds = Integer.MAX_VALUE, nodes = 10000000;

        for(int i = 0; i < nodes; i++)
        {
            ComparablePoint cp = new ComparablePoint(generator.nextInt(bounds), generator.nextInt(bounds));

            tree.add(cp);
        }

        postorderList = tree.postorderTraversal();
        Iterator<ComparablePoint> postorderIterator = tree.postorderIterator();

        //System.out.println("DISPLAYING POST ORDER LIST");

        //for(ComparablePoint cp : postorderList)
        //{
            //System.out.print(cp + " ");
        //}

        //System.out.println("\n\nDISPLAYING POST ORDER ITERATOR");

        while(postorderIterator.hasNext())
        {
            //System.out.print(postorderIterator.next() + " ");
            postorderIterator.next();
        }

        System.out.println("\n\nNUMBER OF NODES:   " + nodes);
        System.out.println("TREE SIZE:         " + tree.size());
        System.out.println("LIST SIZE:         " + postorderList.size());
        System.out.println("BOUNDS:            " + bounds);

        // Requires nested class to be public
        //System.out.println("ITERATOR CAPACITY: " + ((DynamicTree.DynamicPostorderIterator)postorderIterator).capacity());
    }
}
