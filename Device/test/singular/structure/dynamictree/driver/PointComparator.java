package singular.structure.dynamictree.driver;

import singular.structure.quadtree.driver.ComparablePoint;

import java.util.Comparator;

/**
 * Created by Andrew Michel on 1/4/2018.
 */
public class PointComparator implements Comparator<ComparablePoint>
{
    @Override
    public int compare(ComparablePoint o1, ComparablePoint o2)
    {
        // Q3 1 Q4 0
        // Q1 3 Q2 2

        if(o1.getX() == o2.getX() && o1.getY() == o2.getY())
        {
            return -1;
        }
        else if(o1.getX() >= o2.getX() && o1.getY() >= o2.getY())
        {
            return 3;
        }
        else if(o1.getX() <= o2.getX() && o1.getY() >= o2.getY())
        {
            return 2;
        }
        else if(o1.getX() >= o2.getX() && o1.getY() <= o2.getY())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
