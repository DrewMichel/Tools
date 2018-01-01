package singular.structure.quadtree.driver;

import singular.structure.quadtree.QuadTree;
import singular.unit.time.Chrono;
import singular.unit.time.Chronometer;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Andrew Michel on 12/18/2017.
 */
public class QuadTreeTraversalDriver
{
    public static void main(String[] args)
    {
        QuadTree<ComparablePoint> tree = new QuadTree<>();

        List<ComparablePoint> inorderList = null, preorderList = null;

        Chrono addTimer = null, iterateTimer = null;

        Random generator = new Random();

        int count = 10000000;
        int range = Integer.MAX_VALUE;

        ComparablePoint current = null;

        System.out.println("CREATING TREE");

        addTimer = new Chronometer();
        for(int i = 0; i < count; i++)
        {
            current = new ComparablePoint(generator.nextInt(range), generator.nextInt(range));
            //System.out.print(current + " ");
            tree.add(current);
        }
        addTimer.updateEndTime();

        System.out.println(addTimer);

        preorderList = tree.preorderTraversal();

        Iterator<ComparablePoint> preorderIterator = tree.preorderIterator();

//        System.out.println("\n\nDISPLAYING PREORDER LIST");
//        for(ComparablePoint cp : preorderList)
//        {
//            System.out.print(cp + " ");
//        }
//
//        System.out.println("\n\nDISPLAYING PREORDER ITERATOR");
//        while(preorderIterator.hasNext())
//        {
//            System.out.print(preorderIterator.next() + " ");
//        }

        //preorderIterator.next();
        for(ComparablePoint cp : preorderList)
        {
            //assert cp.compareTo(preorderIterator.next()) == 0; // assert has to be enabled
            ComparablePoint next = preorderIterator.next();

            if(cp.compareTo(next) != 0)
            {
                throw new AssertionError("" + cp.compareTo(next));
            }
        }
        System.out.println("ASSERT CLEARED ALL ELEMENTS EQUAL");


//        inorderList = tree.inorderTraversal();
//        preorderList = tree.preorderTraversal();
//
//        System.out.println("\n\n DISPLAYING PREORDER LIST");
//        System.out.println(tree);
//        for(ComparablePoint cp : preorderList)
//        {
//            System.out.print(cp + " ");
//        }
//
//        System.out.println("\n\nDISPLAYING INORDER LIST");
//        for(ComparablePoint cp : inorderList)
//        {
//            System.out.print(cp + " ");
//        }
//
//        System.out.println("\n\nCOUNT NUMBER: " + count);
//        System.out.println("LIST SIZE: " + inorderList.size());
//
//        System.out.println("\n\nTREE SIZE: " + tree.size());
//        System.out.println("\nREMOVING FROM TREE");
//        for(int i = 0; i < preorderList.size() / 2; i++)
//        {
//            //System.out.print(preorderList.get(i) + " ");
//            System.out.print(tree.remove(preorderList.get(i)) + " ");
//        }
//
//        System.out.println("\n\nDISPLAYING BEFORE REMOVAL");
//        for(ComparablePoint cp : inorderList)
//        {
//            System.out.print(cp + " ");
//        }
//
//        inorderList = tree.inorderTraversal();
//        System.out.println("\n\nDISPLAYING AFTER REMOVAL");
//        for(ComparablePoint cp : inorderList)
//        {
//            System.out.print(cp + " ");
//        }

//        inorderList = tree.inorderTraversal();
//        System.out.println("\n\nDISPLAYING INORDER LIST");
//        for(ComparablePoint cp : inorderList)
//        {
//            System.out.print(cp + " ");
//        }

//        System.out.println("\n\nDISPLAYING INORDER ITERATOR ELEMENTS");
//        //Iterator<ComparablePoint> inorderIterator = tree.iterator();
//        int iterations = 0;
//
//        iterateTimer = new Chronometer();
//        QuadTree.QuadIterator inorderIterator = tree.inorderIterator();
//
//        while(inorderIterator.hasNext())
//        {
//            //System.out.print(inorderIterator.next() + " ");
//            inorderIterator.next();
//            iterations++;
//        }
//        iterateTimer.updateEndTime();
//
//        System.out.println(iterateTimer);
//
//        System.out.println("\n\nINORDER ITERATOR CAPACITY: " + inorderIterator.capacity());
//        System.out.println("NUMBER OF ITERATIONS: " + iterations);

//        inorderList = tree.inorderTraversal();
//
//        System.out.println("\n\nDISPLAYING INORDER LIST");
//        for(ComparablePoint cp : inorderList)
//        {
//            System.out.print(cp + " " + tree.contains(cp) + " ");
//        }
//        System.out.println("\n" + new ComparablePoint(-1,-1) + " " + tree.contains(new ComparablePoint(-1,-1)));

        System.out.println("\n\nCOUNT NUMBER: " + count);
        System.out.println("RANGE NUMBER: " + range);
//        //System.out.println("LIST SIZE: " + inorderList.size());
        System.out.println("PREORDER LIST SIZE: " + preorderList.size());
        System.out.println("TREE SIZE: " + tree.size());
        System.out.println("PREORDER ITERATOR CAPACITY: " + ((QuadTree.PreorderQuadIterator)preorderIterator).capacity());
//
//        System.out.println("TOTAL " + new Chronometer(addTimer.getStartTime(), iterateTimer.getEndTime() - (iterateTimer.getStartTime() - addTimer.getEndTime())));
    }
}
