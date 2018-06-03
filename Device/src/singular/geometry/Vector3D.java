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

    @Override
    public int hashCode()
    {
        return (int) ((x + 104059) * (y + 99469) * (z + 87679)); // prime numbers
    }

    // static functions

    // Am I incorrect in assuming that a mathematical vector which represents a magnitude and direction
    // is the same as a programmatic vector which represents an x, y, z position?

    // Should I include rotation, distance from origin to edge, or some addition information?

    // proj u
    //     v
    public static Vector3D project(Vector3D u, Vector3D v)
    {
        //                   dot     magnitude   multiply by scalar
        // proj u onto v = ((u * v) / (|| v ||^2)) * v

        double dotResult = dotProduct(u, v);

        double magnitude = (v.x * v.x + v.y * v.y + v.z * v.z);
        double magnitudeSquared = magnitude * magnitude;

        double scalar = dotResult / magnitudeSquared;

        Vector3D vectorResult = multiply(v, scalar);

        return vectorResult;
    }

    public static Vector3D subtraction(Vector3D one, Vector3D two)
    {
        return new Vector3D(one.x - two.x, one.y - two.y, one.z - two.z);
    }

    public static Vector3D addition(Vector3D one, Vector3D two)
    {
        return new Vector3D(one.x + two.x, one.y + two.y, one.z + two.z);
    }

    public static Vector3D multiply(Vector3D vector, double scalar)
    {
        return new Vector3D(vector.x * scalar, vector.y * scalar, vector.z * scalar);
    }

    public static Vector3D divide(Vector3D vector, double scalar)
    {
        return new Vector3D(vector.x / scalar, vector.y / scalar, vector.z / scalar);
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
