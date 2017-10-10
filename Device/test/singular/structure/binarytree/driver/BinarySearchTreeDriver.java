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

        for(int i = 0; i < 20; i++)
        {
            binaryTree2.add((int)(Math.random() * 100) + 1);
        }

        System.out.println(binaryTree2);
    }
}
