package singular.strategy.SAT.driver;

import singular.geometry.*;
import singular.geometry.Polygon;
import singular.strategy.SAT.SeparatingAxisTheorem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Michel on 6/1/2018.
 */
public class SeparatingAxisTheoremDriver extends JFrame
{
    public List<Polygon> polygons;

    public SeparatingAxisTheoremDriver()
    {
        super("SAT Driver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Vector3D one = new Vector3D(3, -5, 4);
        Vector3D two = new Vector3D(2, 6, 5);

        double dot = Vector3D.dotProduct(one, two);
        Vector3D cross = Vector3D.crossProduct(one, two);

        CubeComplex cube1 = new CubeComplex();
        cube1.points.add(new Vector3D(100, 100, 100));
        cube1.points.add(new Vector3D(100, 200, 100));
        cube1.points.add(new Vector3D(200, 200, 100));
        cube1.points.add(new Vector3D(200, 100, 100));

        cube1.points.add(new Vector3D(100, 100, 200));
        cube1.points.add(new Vector3D(100, 200, 200));
        cube1.points.add(new Vector3D(200, 200, 200));
        cube1.points.add(new Vector3D(200, 100, 200));

        CubeComplex cube2 = new CubeComplex();
        cube2.points.add(new Vector3D(170, 170, 100));
        cube2.points.add(new Vector3D(170, 270, 100));
        cube2.points.add(new Vector3D(270, 270, 100));
        cube2.points.add(new Vector3D(270, 170, 100));

        cube2.points.add(new Vector3D(170, 170, 200));
        cube2.points.add(new Vector3D(170, 270, 200));
        cube2.points.add(new Vector3D(270, 270, 200));
        cube2.points.add(new Vector3D(270, 170, 200));

        CubeComplex cube3 = new CubeComplex();
        cube3.points.add(new Vector3D(15, 15, 100));
        cube3.points.add(new Vector3D(15, 99, 100));
        cube3.points.add(new Vector3D(99, 99, 100));
        cube3.points.add(new Vector3D(99, 15, 100));

        cube3.points.add(new Vector3D(15, 15, 200));
        cube3.points.add(new Vector3D(15, 99, 200));
        cube3.points.add(new Vector3D(99, 99, 200));
        cube3.points.add(new Vector3D(99, 15, 200));

        CubeComplex cube4 = new CubeComplex();
        cube4.points.add(new Vector3D(220, 50, 300));
        cube4.points.add(new Vector3D(220, 120, 300));
        cube4.points.add(new Vector3D(170, 120, 300));
        cube4.points.add(new Vector3D(170, 50, 300));

        cube4.points.add(new Vector3D(220, 50, 400));
        cube4.points.add(new Vector3D(220, 120, 400));
        cube4.points.add(new Vector3D(170, 120, 400));
        cube4.points.add(new Vector3D(170, 50, 400));

        // cube5 = new CubeComplex(origin, width, height, length, originCentered)
        // generate points based on arguments
        // if not originCentered, create at corner. otherwise create around origin

        //System.out.println(CubeComplex.calculateOrigin(cube4.points));
        cube1.origin = CubeComplex.calculateOrigin(cube1.points);
        cube2.origin = CubeComplex.calculateOrigin(cube2.points);
        cube3.origin = CubeComplex.calculateOrigin(cube3.points);
        cube4.origin = CubeComplex.calculateOrigin(cube4.points);

        cube1.color = Color.RED;
        cube2.color = Color.GREEN;
        cube3.color = Color.BLUE;
        cube4.color = Color.YELLOW;

        polygons = new ArrayList<>();
        polygons.add(cube1);
        polygons.add(cube2);
        polygons.add(cube3);
        polygons.add(cube4);

        System.out.println("DOT: " + dot);
        System.out.println("CROSS: " + cross);

        getContentPane().setBackground(Color.black);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        Polygon currentPolygon = null;
        Vector3D currentVector = null;
        Vector3D previousVector = null;

        if(polygons != null)
        {
            for(int i = 0; i < polygons.size(); i++)
            {
                currentPolygon = polygons.get(i);

                if(currentPolygon != null && currentPolygon.getAllPoints() != null)
                {
                    if(currentPolygon.color != null)
                    {
                        g.setColor(currentPolygon.color);
                    }
                    else
                    {
                        g.setColor(Color.WHITE);
                    }

                    if(currentPolygon.getAllPoints().size() == 1)
                    {
                        currentVector = currentPolygon.getAllPoints().get(i);

                        if(currentVector != null)
                        {
                            g.drawLine((int) currentVector.x, (int) currentVector.y, (int) currentVector.x, (int) currentVector.y);
                        }
                    }
                    else
                    {
                        previousVector = currentPolygon.getAllPoints().get(currentPolygon.getAllPoints().size() - 1);

                        for(int k = 0; k < currentPolygon.getAllPoints().size(); k++)
                        {
                            currentVector = currentPolygon.getAllPoints().get(k);


                            if(currentVector != null && previousVector != null)
                            {
                                g.drawLine((int) currentVector.x, (int) currentVector.y, (int) previousVector.x, (int) previousVector.y);
                            }

                            previousVector = currentVector;
                        }
                    }

                    if(currentPolygon.origin != null)
                    {
                        g.drawLine((int) currentPolygon.origin.x, (int) currentPolygon.origin.y, (int) currentPolygon.origin.x, (int) currentPolygon.origin.y);
                    }
                }
            }
        }
    }

    public static void main(String args[])
    {
        SeparatingAxisTheoremDriver driver = new SeparatingAxisTheoremDriver();
    }
}
