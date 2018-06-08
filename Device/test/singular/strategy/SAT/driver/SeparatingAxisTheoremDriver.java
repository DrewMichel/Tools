package singular.strategy.SAT.driver;

import singular.geometry.Polygon;
import singular.geometry.Square2D;
import singular.geometry.Vector2D;
import singular.strategy.SAT.SeparatingAxisTheorem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Andrew Michel on 6/1/2018.
 */
public class SeparatingAxisTheoremDriver extends JFrame
{
    public List<Polygon> polygons;
    private double rotation;
    private CustomPanel primaryPanel;

    private Square2D stationary;
    private Square2D mouseSquare;

    private static Point mousePosition;

    public SeparatingAxisTheoremDriver()
    {
        super("SAT Driver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rotation = 0;
        stationary = new Square2D(200, 200, 100, 100, rotation, Color.WHITE);
        mouseSquare = new Square2D(0,0,2,2,0, Color.CYAN);
        mousePosition = new Point(0, 0);

        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");

        // Set the blank cursor to the JFrame
        getContentPane().setCursor(blankCursor);
        /*
        rotation = 0;

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

        CubeComplex cube5 = new CubeComplex(new Vector3D(200, 200, 200), 300, 300, 300, true);
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
        cube5.color = Color.CYAN;

        polygons = new ArrayList<>();
        polygons.add(cube1);
        polygons.add(cube2);
        polygons.add(cube3);
        polygons.add(cube4);

        System.out.println("DOT: " + dot);
        System.out.println("CROSS: " + cross);
        */

        primaryPanel = new CustomPanel();
        primaryPanel.setBackground(Color.BLACK);

        add(primaryPanel);

        setUndecorated(true);
        getContentPane().setBackground(Color.black);
        setSize(600, 400);
        setLocationRelativeTo(null);

        addKeyListener(new CustomKeyListener());
        addMouseMotionListener(new CustomMouseListener());

        setVisible(true);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(16);
                stationary.degrees += 0.5;

                Vector2D result = SeparatingAxisTheorem.process(stationary, mouseSquare);

                if(result != null)
                {
                    stationary.color = Color.RED;
                    mouseSquare.color = Color.ORANGE;
                }
                else
                {
                    stationary.color = Color.WHITE;
                    mouseSquare.color = Color.CYAN;
                }

                revalidate();
                repaint();

            }
            catch(InterruptedException e)
            {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    /*
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        Rectangle rect1 = new Rectangle(200, 200, 100, 100);
        g2d.rotate(Math.toRadians(rotation), rect1.x + rect1.width/2, rect1.y + rect1.height / 2);
        //g2d.translate(100, 100);
        g2d.draw(rect1);
        g2d.fill(rect1);
        */
        /*
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

            // attempt to draw line

            if(polygons.size() > 1)
            {
                Vector3D a0 = null;
                Vector3D target1 = new Vector3D(0, 0, -2);
                Vector3D target2 = Vector3D.subtraction(polygons.get(0).origin, polygons.get(0).getAllPoints().get(0));
                Vector3D projection1 = Vector3D.project(target1, target2);
                Vector3D projection2 = Vector3D.project(target2, target1);


                a0 = Vector3D.crossProduct(target1, target2);

                g.setColor(Color.WHITE);

                g.drawLine((int) a0.x, (int) a0.y, (int) a0.x + 100, (int) a0.y + 100);

                System.out.println("A0: " + a0);
                System.out.println("TARGET 1: " + target1);
                System.out.println("TARGET 2: " + target2);
                System.out.println("DOT: " + Vector3D.dotProduct(target1, target2));
                System.out.println("Proj 1: " + projection1);
                System.out.println("Proj 2: " + projection2);
            }
        }
        */
    //}

    public static void main(String args[])
    {
        SeparatingAxisTheoremDriver driver = new SeparatingAxisTheoremDriver();

        driver.run();
    }

    private class CustomMouseListener implements MouseMotionListener
    {
        @Override
        public void mouseDragged(MouseEvent e)
        {
            mousePosition = e.getPoint();

            mouseSquare.x = e.getX();
            mouseSquare.y = e.getY();
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            mousePosition = e.getPoint();

            mouseSquare.x = e.getX();
            mouseSquare.y = e.getY();
        }
    }

    private class CustomKeyListener implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                System.exit(0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public class CustomPanel extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(stationary.color);

            //Rectangle rect1 = new Rectangle(200, 200, 100, 100);
            //g2d.rotate(Math.toRadians(rotation), rect1.x + rect1.width/2, rect1.y + rect1.height / 2);
            //g2d.translate(100, 100);

            List<Vector2D> basePoints = stationary.getBasePoints();
            List<Vector2D> rotatedPoints = stationary.getRotatedPoints();

            g2d.setColor(Color.GREEN);
            for(int i = 0; i < basePoints.size(); i++)
            {
                g2d.draw(new Rectangle((int)basePoints.get(i).x, (int)basePoints.get(i).y, 2, 2));
            }

            g2d.setColor(stationary.color);
            for(int i = 0; i < rotatedPoints.size(); i++)
            {
                g2d.draw(new Rectangle((int)rotatedPoints.get(i).x, (int)rotatedPoints.get(i).y, 2, 2));
            }

            //g2d.rotate(stationary.getRadians(), stationary.getCenterX(), stationary.getCenterY());
            //g2d.draw(stationary);
           // g2d.fill(stationary);

            List<Vector2D> mousePoints = mouseSquare.getRotatedPoints();

            g2d.setColor(mouseSquare.color);

            g2d.draw(mouseSquare);



            //g2d.rotate(stationary.getRadians(), stationary.getCenterX(), stationary.getCenterX());
            //g2d.rotate(1, 1, 1);
            //g2d.draw(mouseSquare);
            //g2d.fill(mouseSquare);
        }
    }
}
