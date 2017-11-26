package singular.structure.singlylinkedlist.driver;

import singular.structure.singlylinkedlist.SinglyLinkedList;

/**
 * Created by Andrew Michel on 10/9/2017.
 *
 * This class exists to test singlylinkedlist package classes
 */
public class SinglyLinkedListDriver
{
    public static final int TEST_VALUE = 20;


    public static void main(String[] args)
    {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

        System.out.println("TESTING ADD METHOD");
        for(int i = 0; i < TEST_VALUE; i++)
        {
            singlyLinkedList.add(i);
        }

        for(int i = 0; i < singlyLinkedList.size(); i++)
        {
            System.out.printf("%4s -> ", singlyLinkedList.get(i).toString());
        }

        System.out.println("\n");

        System.out.println("TESTING SET INDEX METHOD");
        for(int i = 0; i < singlyLinkedList.size(); i++)
        {
            System.out.print(singlyLinkedList.set(i, -i) + " ");
        }

        System.out.println("\nDELETING");
        while(singlyLinkedList.size() > 0)
        {
            System.out.print(singlyLinkedList.remove(0) + " ");
        }

        /*
        System.out.println("\nDELETING");
        for(int i = singlyLinkedList.size() - 1; i>= 0; i--)
        {
             System.out.print(singlyLinkedList.remove(i) + " ");
        }
        */

        for(int i = 0; i < singlyLinkedList.size(); i++)
        {
            System.out.printf("%4s -> ", singlyLinkedList.get(i).toString());
        }

        System.out.println("\n");
    }
}
