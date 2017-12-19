package singular.structure.quadtree.driver;

import java.awt.*;

/**
 * Created by Andrew Michel on 12/18/2017.
 */
public class ComparablePoint extends Point implements Comparable
{
    public ComparablePoint()
    {
        super();
    }

    public ComparablePoint(int x, int y)
    {
        super(x, y);
    }

    // Could change to return index position in array that data should be added into
    // With -1 as equals?
    // Would a custom comparison be made instead?
    // Could return a byte as well
    @Override
    public int compareTo(Object o)
    {
        if(o == null || o.getClass() != getClass())
        {
            throw new IllegalArgumentException("" + o);
        }

        ComparablePoint temp = (ComparablePoint)o;


        // Q3 -1 Q4 -2
        // Q1  2 Q2  1

        if(getX() == temp.getX() && getY() == temp.getY())
        {
            //System.err.println("COMPARABLE POINT RETURNING 0 WITH THIS " + toString() + " against " + temp);
            return 0;
        }
        else if(getX() >= temp.getX() && getY() >= temp.getY())
        {
            return 2;
        }
        else if(getX() <= temp.getX() && getY() >= temp.getY())
        {
            return 1;
        }
        else if(getX() >= temp.getX() && getY() <= temp.getY())
        {
            return -1;
        }
        else
        {
            return -2;
        }


        // Q1  2 Q2  1
        // Q3 -1 Q4 -2

//        if(getX() == temp.getX() && getY() == temp.getY())
//        {
//            //System.err.println("COMPARABLE POINT RETURNING 0 WITH THIS " + toString() + " against " + temp);
//            return 0;
//        }
//        else if(getX() >= temp.getX() && getY() >= temp.getY())
//        {
//            // Q1
//            return 2;
//        }
//        else if(getX() <= temp.getX() && getY() >= temp.getY())
//        {
//            // Q2
//            return 1;
//        }
//        else if(getX() >= temp.getX() && getY() <= temp.getY())
//        {
//            // Q3
//            return -1;
//        }
//        else //if(getX() < temp.getX() && getY() < temp.getY())
//        {
//            // Q4
//            return -2;
//        }

    }

    @Override
    public String toString()
    {
        return String.format("(%.0f, %.0f)", getX(), getY());
        //return String.format("Comparable Point: (%.0f, %.0f)", getX(), getY());
    }
}
