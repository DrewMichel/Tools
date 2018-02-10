package singular.strategy.tracker;

import java.awt.geom.Point2D;

/**
 * Created by Andrew Michel on 12/16/2017.
 *
 * This class contains static methods to receive two 2D points as doubles
 * or as Point2D objects along with the speed cap of the first parameter and returns
 * a 2D point that represents the position that the first object can move to
 */
public final class Point2DTracker
{
    // Private constructor prevents an object of this class from being created
    private Point2DTracker(){}

    /**
     *
     * @param x1 the x position of the first object
     * @param y1 the y position of the first object
     * @param x2 the x position of the second object
     * @param y2 the y position of the second object
     * @param cap the distance cap on the first object
     * @return position a Point2D.Double object that represents the position that the
     *          first object can move to without exceeding the cap or passing the
     *          second object
     */
    public static Point2D.Double track(double x1, double y1, double x2, double y2, double cap)
    {
        Point2D.Double position;

        // Calculates the distance between the first and second objects
        double distance = Math.hypot(x1 - x2, y1 - y2);

        // Calculates the theta (angle) that the first object would move in to reach the second object
        double theta = Math.atan2(y1 - y2, x1 - x2);

        // If the distance to the object is greater than the allowed movement cap,
        // Calculate and set the return position to the maximum allowed cap based on angle
        // Else, calculate and set the return position to the distance based on angle
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

    /**
     *
     * @param p1 the first 2D object
     * @param p2 the second 2D object
     * @param cap the distance cap on the first object
     * @return position a Point2D.Double object that represents the position that the
     *          first object can move to without exceeding the cap or passing the
     *          second object
     */
    public static Point2D.Double track(Point2D p1, Point2D p2, double cap)
    {
        Point2D.Double position;

        // Calculates the distance between the first and second objects
        double distance = p1.distance(p2);

        // Calculates the theta (angle) that the first object would move in to reach the second object
        double theta = Math.atan2(p1.getY() - p2.getY(), p1.getX() - p2.getX());

        // If the distance to the object is greater than the allowed movement cap,
        // Calculate and set the return position to the maximum allowed cap based on angle
        // Else, calculate and set the return position to the distance based on angle
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
