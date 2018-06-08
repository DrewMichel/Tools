package singular.geometry;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Michel on 6/7/2018.
 */
public class Square2D extends Rectangle
{
    public double degrees;
    public Color color;

    public Square2D()
    {
        super();
        degrees = 0;
        color = Color.WHITE;
    }

    public Square2D(int x, int y, int width, int height, double degrees, Color color)
    {
        super(x, y, width, height);
        this.degrees = degrees;
        this.color = color;
    }

    public double getDegrees()
    {
        return degrees;
    }

    public double getRadians()
    {
        return Math.toRadians(degrees);
    }

    public Vector2D getOrigin()
    {
        return new Vector2D(x + width/2, y + height/2);
    }

    public void setDegrees(double degrees)
    {
        this.degrees = degrees;
    }

    public List<Vector2D> getRotatedPoints()
    {
        List<Vector2D> list = new ArrayList<>();

        Vector2D origin = getOrigin();

        List<Vector2D> points = Vector2D.offset(getBasePoints(), origin);

        double distance = 0;
        double radian = 0;

        // needs check
        for(int i = 0; i < points.size(); i++)
        {
            distance = Math.sqrt(points.get(i).x * points.get(i).x + points.get(i).y * points.get(i).y);
            radian = Math.atan2(points.get(i).y, points.get(i).x) + getRadians();

            list.add(new Vector2D( distance * Math.cos(radian), distance * Math.sin(radian)));
        }

        //System.exit(1);

        list = Vector2D.translate(list, origin);

        return list;
    }

    public List<Vector2D> getBasePoints()
    {
        List<Vector2D> list = new ArrayList<>();

        list.add(new Vector2D(x, y));
        list.add(new Vector2D(x, y + height));
        list.add(new Vector2D(x + width, y + height));
        list.add(new Vector2D(x + width, y));

        return list;
    }
}
