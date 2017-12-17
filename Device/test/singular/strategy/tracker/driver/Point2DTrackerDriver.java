package singular.strategy.tracker.driver;

import singular.strategy.tracker.Point2DTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Created by Andrew Michel on 12/16/2017.
 */
public class Point2DTrackerDriver extends JFrame
{
    public static final double LEADER_MAX_SPEED = .4, FOLLOWER_MAX_SPEED = .25;


    private JPanel panel;
    private MouseEvent mousePoint;

    private Ellipse2D.Double leader, follower, orbit;
    double orbitAngle;

    public static void main(String[] args)
    {
        Point2DTrackerDriver example = new Point2DTrackerDriver();
        example.setVisible(true);
    }

    public Point2DTrackerDriver()
    {
        super("Tracker Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1080,720);

        //getContentPane().setBackground(Color.BLACK);
        panel = new CustomJPanel();
        panel.setBackground(Color.BLACK);

        add(panel);
        panel.setVisible(true);

        mousePoint = new MouseEvent(this, 0,0,0,0,0,0,false,0);//new Point2D.Double(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());

        leader = new Ellipse2D.Double(0,0,50,50);
        follower = new Ellipse2D.Double(0,0, 35, 35);
        orbit = new Ellipse2D.Double(0,0,20,20);
        orbitAngle = 0;

        setLocationRelativeTo(null);
        panel.addMouseMotionListener(new MouseDetecter());
        //setVisible(true);
    }

    private class MouseDetecter implements MouseMotionListener
    {
        @Override
        public void mouseDragged(MouseEvent e)
        {

        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            mousePoint = e;
        }
    }

    private class CustomJPanel extends JPanel
    {
        public CustomJPanel()
        {
            super();
        }

        @Override
        public void paintComponent(Graphics g)
        {
            //System.err.println(mousePoint);

            g.fillRect(0,0, getWidth(), getHeight());

            Point2D.Double point = Point2DTracker.track(mousePoint.getX(), mousePoint.getY(), leader.getCenterX(), leader.getCenterY(), LEADER_MAX_SPEED);
            leader.setFrame(point.getX() + leader.getX(), point.getY() + leader.getY(), leader.getWidth(), leader.getHeight());


            g.setColor(Color.RED);
            ((Graphics2D)g).fill(leader);

            point = Point2DTracker.track(leader.getCenterX(), leader.getCenterY(), follower.getCenterX(), follower.getCenterY(), FOLLOWER_MAX_SPEED);

            if(Point2D.Double.distance(leader.getCenterX(), leader.getCenterY(), follower.getCenterX(), follower.getCenterY()) > leader.getWidth() / 2 + follower.getWidth() / 2)
            {
                follower.setFrame(point.getX() + follower.getX(), point.getY() + follower.getY(),follower.getWidth(), follower.getHeight());
            }

            g.setColor(Color.GREEN);
            ((Graphics2D)g).fill(follower);

            orbit.setFrame(Math.cos(orbitAngle) * leader.getWidth() * 2 + leader.getCenterX() - orbit.getWidth() / 2, Math.sin(orbitAngle) * leader.getHeight() * 2 + leader.getCenterY()  - orbit.getHeight() / 2, orbit.getWidth(), orbit.getHeight());

            g.setColor(Color.BLUE);
            ((Graphics2D)g).fill(orbit);
            orbitAngle += 0.001;

            //revalidate();
            repaint();
        }
    }
}
