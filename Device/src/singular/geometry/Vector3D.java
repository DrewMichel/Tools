package singular.geometry;

/**
 * Created by Andrew Michel on 6/1/2018.
 */
public class Vector3D
{
    // constants
    static final Vector3D X_IDENTITY_VECTOR = new Vector3D(1, 0, 0), Y_IDENTITY_VECTOR = new Vector3D(0, 1, 0), Z_IDENTITY_VECTOR = new Vector3D(0, 0, 1);
    static final Vector3D ZERO_VECTOR = new Vector3D(0, 0, 0);

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

    @Override
    public boolean equals(Object other)
    {
        if(other == null || other.getClass() != this.getClass())
        {
            return false;
        }

        Vector3D temp = (Vector3D) other;

        return (x == temp.x && y == temp.y && z == temp.z);
    }

    /*
    @Override
    public int hashcode()
    {

    }
     */

    // static functions

    public static Vector3D subtraction(Vector3D one, Vector3D two)
    {
        return new Vector3D(one.x - two.x, one.y - two.y, one.z - two.z);
    }

    public static Vector3D addition(Vector3D one, Vector3D two)
    {
        return new Vector3D(one.x + two.x, one.y + two.y, one.z + two.z);
    }

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
