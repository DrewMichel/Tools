package singular.geometry;

/**
 * Created by Andrew Michel on 6/1/2018.
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class Polygon
{
    // member variables
    public Vector3D rotation;
    public Vector3D scale;
    public Vector3D origin;
    public boolean centerOrigin;
    public boolean sphere;
    public Color color;

    // constructor

    public Polygon()
    {
        rotation = new Vector3D();
        scale = new Vector3D();
        origin = new Vector3D();
        centerOrigin = false;
        sphere = false;
        color = Color.PINK;
    }

    // member functions

    // CubeComplex can return points directly since it stores them
    // CubeSimple could calculate its points based on width, height, length, then return them
    public abstract List<Vector3D> getAllPoints();

    // include rotation, ect..?
}
