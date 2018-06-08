package singular.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Michel on 6/7/2018.
 */
public class Vector2D
{
    public double x, y;

    public Vector2D()
    {
        x = 0;
        y = 0;
    }

    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D other)
    {
        this.x = other.x;
        this.y = other.y;
    }

    @Override
    public String toString()
    {
        return x + ", " + y;
    }

    public Vector2D normal()
    {
        return new Vector2D(y, -x);
    }

    public double magnitude()
    {
        return ((x * x) + (y * y));
    }

    public Vector2D unit()
    {
        double magnitude = magnitude();

        return new Vector2D(x / magnitude, y / magnitude);
    }

    public static Vector2D multiply(Vector2D vector, double scalar)
    {
        return new Vector2D(vector.x * scalar, vector.y * scalar);
    }

    public static Vector2D subtract(Vector2D one, Vector2D two)
    {
        return new Vector2D(one.x - two.x, one.y - two.y);
    }

    public static Vector2D addition(Vector2D one, Vector2D two)
    {
        return new Vector2D(one.x + two.x, one.y + two.y);
    }

    public static double scalarProduct(Vector2D one, Vector2D two)
    {
        return dotProduct(one, two);
    }

    public static double dotProduct(Vector2D one, Vector2D two)
    {
        return ((one.x * two.x) + (one.y * two.y));
    }

    public static Vector2D vectorProduct(Vector2D one, Vector2D two)
    {
        return crossProduct(one, two);
    }

    public static Vector2D crossProduct(Vector2D one, Vector2D two)
    {
        return null;
    }

    public static List<Vector2D> offset(List<Vector2D> points, Vector2D origin)
    {
        List<Vector2D> list = new ArrayList<Vector2D>();

        for(int i = 0; i < points.size(); i++)
        {
            list.add(new Vector2D(points.get(i).x - origin.x, points.get(i).y - origin.y));
        }

        return list;
    }

    public static List<Vector2D> translate(List<Vector2D> points, Vector2D origin)
    {
        List<Vector2D> list = new ArrayList<Vector2D>();

        for(int i = 0; i < points.size(); i++)
        {
            list.add(new Vector2D(points.get(i).x + origin.x, points.get(i).y + origin.y));
        }

        return list;
    }
}
