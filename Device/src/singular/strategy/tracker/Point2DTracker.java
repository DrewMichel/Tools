package singular.strategy.tracker;

import java.awt.geom.Point2D;

/**
 * Created by Andrew Michel on 12/16/2017.
 */
public final class Point2DTracker
{
    private Point2DTracker(){}

    public static Point2D.Double track(double x1, double y1, double x2, double y2, double cap)
    {
        Point2D.Double position;

        double distance = Math.hypot(x1 - x2, y1 - y2);

        double theta = Math.atan2(y1 - y2, x1 - x2);

        if(distance > cap)
        {
            position = new Point2D.Double(Math.cos(theta) * cap, Math.sin(theta) * cap);
        }
        else
        {
            position = new Point2D.Double(Math.cos(theta) * distance, Math.sin(theta) * distance);
        }

        return position;
    }

    public static Point2D.Double track(Point2D p1, Point2D p2, double cap)
    {
        Point2D.Double position;

        double distance = p1.distance(p2);

        double theta = Math.atan2(p1.getY() - p2.getY(), p1.getX() - p2.getX());

        if(distance > cap)
        {
            position = new Point2D.Double(Math.cos(theta) * cap, Math.sin(theta) * cap);
        }
        else
        {
            position = new Point2D.Double(Math.cos(theta) * distance, Math.sin(theta) * distance);
        }

        return position;
    }
}
