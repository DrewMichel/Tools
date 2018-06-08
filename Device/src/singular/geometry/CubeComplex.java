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

    // constructors
    public CubeComplex()
    {
        points = new ArrayList<>();
    }

    public CubeComplex(Vector3D origin, double width, double height, double length, boolean originCentered)
    {
        points = new ArrayList<>();

        this.centerOrigin = originCentered;
        this.origin = new Vector3D(origin);

        /*
        cube1.points.add(new Vector3D(100, 100, 100));
        cube1.points.add(new Vector3D(100, 200, 100));
        cube1.points.add(new Vector3D(200, 200, 100));
        cube1.points.add(new Vector3D(200, 100, 100));

        cube1.points.add(new Vector3D(100, 100, 200));
        cube1.points.add(new Vector3D(100, 200, 200));
        cube1.points.add(new Vector3D(200, 200, 200));
        cube1.points.add(new Vector3D(200, 100, 200));
         */

        if(originCentered == true)
        {
            points.add(new Vector3D(origin.x - width / 2, origin.y - height / 2, origin.z - length / 2));
            points.add(new Vector3D(origin.x - width / 2, origin.y + height / 2, origin.z - length / 2));
            points.add(new Vector3D(origin.x + width / 2, origin.y + height / 2, origin.z - length / 2));
            points.add(new Vector3D(origin.x + width / 2, origin.y - height / 2, origin.z - length / 2));

            points.add(new Vector3D(origin.x - width / 2, origin.y - height / 2, origin.z + length / 2));
            points.add(new Vector3D(origin.x - width / 2, origin.y + height / 2, origin.z + length / 2));
            points.add(new Vector3D(origin.x + width / 2, origin.y + height / 2, origin.z + length / 2));
            points.add(new Vector3D(origin.x + width / 2, origin.y - height / 2, origin.z + length / 2));
        }
        else
        {
            // implement later(?)
        }
    }


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
