package singular.strategy.SAT;

import singular.geometry.Polygon;
import singular.geometry.Square2D;
import singular.geometry.Vector2D;
import singular.geometry.Vector3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Michel on 6/1/2018.
 */

// add main, jframe to test
// create geometry package and add cubes
public final class SeparatingAxisTheorem
{
    // private constructor
    private SeparatingAxisTheorem()
    {
    }

    // static functions

    /*
    Here's a complete list of axes to test, given two OBBs,
    A and B, where x, y and z refer to the basis vectors / three unique normals.
    0 = x axis, 1 = y axis, 2 = z axis

    a0
    a1
    a2
    b0
    b1
    b2
    cross( a0, b0 )
    cross( a0, b1 )
    cross( a0, b2 )
    cross( a1, b0 )
    cross( a1, b1 )
    cross( a1, b2 )
    cross( a2, b0 )
    cross( a2, b1 )
    cross( a2, b2 )
    */
    public static boolean process(Polygon one, Polygon two)
    {
        List<Vector3D> onePoints = one.getAllPoints();
        List<Vector3D> twoPoints = two.getAllPoints();

        double oneRotationX = 1, oneRotationY = 1, oneRotationZ = 1, twoRotationX = 1, twoRotationY = 1, twoRotationZ = 1;

        if(one.rotation != null)
        {
            oneRotationX = one.rotation.x;
            oneRotationY = one.rotation.y;
            oneRotationZ = one.rotation.z;
        }

        if(two.rotation != null)
        {
            twoRotationX = two.rotation.x;
            twoRotationY = two.rotation.y;
            twoRotationZ = two.rotation.z;
        }

        return true;
    }

    public static Vector2D process(Square2D one, Square2D two)
    {
        if(one == null || two == null)
        {
            return null;
        }

        double oneRotation = one.getDegrees();
        double twoRotation = two.getDegrees();
        double currentOverlap = 0;
        double minOverlap = Double.MAX_VALUE;
        Vector2D minPenetrationAxis = null;

        Vector2D current = null;
        Vector2D oneMinMax = null;
        Vector2D twoMinMax = null;

        List<Vector2D> normals = getEdgeNormals(one.getRotatedPoints());

        // double check?
        normals.addAll(getEdgeNormals(two.getRotatedPoints()));

        List<Vector2D> oneRotatedPoints = one.getRotatedPoints();
        List<Vector2D> twoRotatedPoints = two.getRotatedPoints();

        if(normals.isEmpty() || oneRotatedPoints.isEmpty() || twoRotatedPoints.isEmpty())
        {
            return null;
        }

        // loop over all the possible separating axes and if any do not overlap
        // return false

        for(int i = 0; i < normals.size(); i++)
        {
            current = normals.get(i);

            // project (dot) both shapes onto the current axis
            oneMinMax = project(oneRotatedPoints, current); // Vector2D representing min, max as x, y. very ghetto
            twoMinMax = project(twoRotatedPoints, current);

            // check for no overlap. If there is no overlap, return false
            if(oneMinMax.x > twoMinMax.y || twoMinMax.x > oneMinMax.y)
            {
                return null;
            }

            currentOverlap = Math.min(oneMinMax.y, twoMinMax.y) - Math.max(oneMinMax.x, twoMinMax.x);

            if(currentOverlap < minOverlap)
            {
                minOverlap = currentOverlap;
                minPenetrationAxis = new Vector2D(current);
            }
        }

        // Otherwise all axes overlapped and collision is true
        return Vector2D.multiply(minPenetrationAxis, minOverlap); // can return both values to change based on mass, etc... save for c++
    }

    public static List<Vector2D> getEdgeNormals(List<Vector2D> vertices)
    {
        int size = vertices.size();
        List<Vector2D> normals = new ArrayList<>();

        Vector2D start = null, end = null;

        Vector2D edge = null;

        for(int i = 0; i < size; i++)
        {
            start = vertices.get(i);
            end = vertices.get((i + 1) % size);

            // subtract the 2 vectors to get the edge vector
            edge = Vector2D.subtract(start, end);

            // get the edge normal
            edge = edge.normal();

            // converts the edge normal to value ranges
            edge = edge.unit();

            normals.add(edge);
        }

        return normals;
    }

    // Returns a Vector2D with the min value as x and the min value as y
    public static Vector2D project(List<Vector2D> vertices, Vector2D axis)
    {
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE, projection = 0;

        for(int i = 0; i < vertices.size(); i++)
        {
            projection = Vector2D.dotProduct(axis, vertices.get(i));

            if(projection < min)
            {
                min = projection;
            }
            if(projection > max)
            {
                max = projection;
            }
        }

        return new Vector2D(min, max); // ghetto
    }
}
