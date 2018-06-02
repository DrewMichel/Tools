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

    public List<Vector3D> getAllPoints()
    {
        return points;
    }
}
