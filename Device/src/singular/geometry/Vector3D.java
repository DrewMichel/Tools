package singular.geometry;

/**
 * Created by Andrew Michel on 6/1/2018.
 */
public class Vector3D
{
    // member variables
    public double x, y, z;

    // constructors
    public Vector3D()
    {
        x = 1;
        y = 1;
        z = 1;
    }

    public Vector3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // member functions
    @Override
    public String toString()
    {
        return x + ", " + y + ", " + z;
    }

    // static functions

    // synonym for dot product
    public static Double scalarProduct(Vector3D one, Vector3D two)
    {
        return dotProduct(one, two);
    }

    // synonym for scalar product
    public static Double dotProduct(Vector3D one, Vector3D two)
    {
        Double product = null;

        if(one != null && two != null)
        {
            product = (one.x * two.x) + (one.y * two.y) + (one.z * two.z);
        }

        return product;
    }

    // synonym for cross product
    public static Vector3D vectorProduct(Vector3D one, Vector3D two)
    {
        return crossProduct(one, two);
    }

    // synonym for vector product
    public static Vector3D crossProduct(Vector3D one, Vector3D two)
    {
        Vector3D product = null;

        if(one != null && two != null)
        {
            product = new Vector3D((one.y * two.z) - (one.z * two.y), (one.x * two.z) - (one.z * two.x), (one.x * two.y) - (one.y * two.x));
        }

        return product;
    }

}
