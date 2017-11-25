package singular.structure.stack.driver;

import singular.structure.stack.AdjustableStack;
import singular.structure.stack.FixedStack;

/**
 * Created by Andrew Michel on 11/25/2017.
 *
 * This class exists to test stack package classes
 */
public class StackDriver
{
    public static void main(String[] args)
    {
        int size = 15;

        FixedStack<Integer> fixedStack = new FixedStack<>(size);
        int[] array = new int[size];

        int current = 0;

        for(int i = 0; i < size; i++)
        {
            current = (int)(Math.random() * 100 + 1);
            fixedStack.push(current);
            array[i] = current;
        }
        System.out.println("STACK");
        while(!fixedStack.isEmpty())
        {
            System.out.print(fixedStack.pop() + " ");
        }

        System.out.println("\nARRAY");
        for(int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        /*
        AdjustableStack<Integer> adjustableStack = new AdjustableStack<>();

        int[] array = new int[size];

        int current = 0;

        for(int i = 0; i < size; i++)
        {
            current = (int)(Math.random() * 100 + 1);
            adjustableStack.push(current);
            array[i] = current;
        }

        System.out.println("CAPACITY: " + adjustableStack.capacity());

        System.out.println(current + " HAS A DISTANCE OF " + adjustableStack.search(current) + " FROM THE TOP OF THE STACK");

        System.out.println("STACK ELEMENTS AND SIZE: " + adjustableStack.size());
        while(!adjustableStack.isEmpty())
        {
            System.out.print(adjustableStack.pop() + " ");
        }

        System.out.println("\nARRAY");
        for(int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        */
    }
}
