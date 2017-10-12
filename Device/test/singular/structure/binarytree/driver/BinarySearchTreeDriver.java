package singular.structure.binarytree.driver;

import singular.structure.binarytree.BinarySearchTree;
import singular.structure.binarytree.BinaryTree;

/**
 * Created by Andrew Michel on 10/10/2017.
 *
 * This class exists to test binarytree package classes
 */
public class BinarySearchTreeDriver
{
    public static void main(String[] args)
    {
        BinarySearchTree<String> binaryTree1 = new BinarySearchTree<>();
        BinarySearchTree<Integer> binaryTree2 = new BinarySearchTree<>();

        int lastValue = 0;

        for(int i = 0; i < 20; i++)
        {
            lastValue = (int)((Math.random() * 100) + 1);
            binaryTree2.add(lastValue);
        }

        System.out.println(binaryTree2);

        /*
        System.out.println("DELETING: " + lastValue);
        System.out.println("RETURNED: " + binaryTree2.delete(lastValue));

        System.out.println(binaryTree2);
        */

        System.out.println(binaryTree2.breadthFirstTraversal());
    }
}
