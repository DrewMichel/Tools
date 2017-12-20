package singular.structure.quadtree.driver;

import singular.structure.quadtree.QuadTree;

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

        Random generator = new Random();

        int count = 10000000;

        ComparablePoint current = null;

        System.out.println("CREATING TREE");
        for(int i = 0; i < count; i++)
        {
            current = new ComparablePoint(generator.nextInt(Integer.MAX_VALUE), generator.nextInt(Integer.MAX_VALUE));
            //System.out.print(current + " ");
            tree.add(current);
        }

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

        System.out.println("\n\nDISPLAYING INORDER ITERATOR ELEMENTS");
        //Iterator<ComparablePoint> inorderIterator = tree.iterator();
        QuadTree.QuadIterator inorderIterator = tree.inorderIterator();

        int iterations = 0;

        while(inorderIterator.hasNext())
        {
            //System.out.print(inorderIterator.next() + " ");
            inorderIterator.next();
            iterations++;
        }

        System.out.println("\n\nINORDER ITERATOR CAPACITY: " + inorderIterator.capacity());
        System.out.println("NUMBER OF ITERATIONS: " + iterations);

//        inorderList = tree.inorderTraversal();
//
//        System.out.println("\n\nDISPLAYING INORDER LIST");
//        for(ComparablePoint cp : inorderList)
//        {
//            System.out.print(cp + " " + tree.contains(cp) + " ");
//        }
//        System.out.println("\n" + new ComparablePoint(-1,-1) + " " + tree.contains(new ComparablePoint(-1,-1)));

        System.out.println("\n\nCOUNT NUMBER: " + count);
        //System.out.println("LIST SIZE: " + inorderList.size());
        System.out.println("TREE SIZE: " + tree.size());
    }
}
