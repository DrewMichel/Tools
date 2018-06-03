package singular.strategy.SAT;

import singular.geometry.Polygon;
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



        return false;
    }
}
