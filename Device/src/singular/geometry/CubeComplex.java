package singular.geometry;

/**
 * Created by Andrew Michel on 6/1/2018.
 */

import java.util.ArrayList;
import java.util.List;

public class CubeComplex extends Polygon
{
    // member variables
    public List<Vector3D> points;



    // constructor
    public CubeComplex()
    {
        points = new ArrayList<>();
    }

    /*
    public CubeComplex(Vector3D origin, double width, double height, double length, boolean originCenetered)
    {

    }
     */

    public List<Vector3D> getAllPoints()
    {
        return points;
    }

    public static Vector3D calculateOrigin(List<Vector3D> points)
    {
        Vector3D origin = null;
        Vector3D currentVector;
        int valids = 0;

        if(points != null)
        {
            double x = 0, y = 0, z = 0;

            for(int i = 0; i < points.size(); i++)
            {
                currentVector = points.get(i);

                if(currentVector != null)
                {
                    x += currentVector.x;
                    y += currentVector.y;
                    z += currentVector.z;
                    valids++;
                }
            }

            if(valids > 0)
            {
                origin = new Vector3D(x / valids, y / valids, z / valids);
            }
        }

        return origin;
    }
}
