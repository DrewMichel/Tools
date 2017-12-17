package singular.strategy.tracker.driver;

import singular.strategy.tracker.Point2DTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andrew Michel on 12/16/2017.
 */
public class Point2DTrackerDriver extends JFrame
{
    public static final double LEADER_MAX_SPEED = .4, FOLLOWER_MAX_SPEED = .25;


    private JPanel panel;
    private MouseEvent mousePoint;

    //private Ellipse2D.Double leader, follower, orbit;
    //double orbitAngle;

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

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //getContentPane().setBackground(Color.BLACK);
        panel = new PlanetPanel(50,100,20, 4,0.35,0.15,500, 40);
        panel.setBackground(Color.BLACK);

        // hides the cursor
        panel.setCursor( panel.getToolkit().createCustomCursor(
                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                new Point(),
                null ) );

        add(panel);



        mousePoint = new MouseEvent(this, 0,0,0,getWidth() / 2,getHeight() / 2,0,false,0);//new Point2D.Double(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());

        //leader = new Ellipse2D.Double(0,0,50,50);
        //follower = new Ellipse2D.Double(0,0, 35, 35);
        //orbit = new Ellipse2D.Double(0,0,20,20);
        //orbitAngle = 0;

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

    private static class Planet
    {
        public Point2D.Double point;
        public double diameter, orbit, distance, rate;
        public Color color;

        public Planet(){}

        public void drawPlanet(Graphics g)
        {
            g.fillArc((int)point.getX(), (int)point.getY(),(int) diameter, (int)diameter, 0, 360);
        }

        public double getXCenter()
        {
            return point.getX() + diameter / 2;
        }

        public double getYCenter()
        {
            return point.getY() + diameter / 2;
        }
    }

    private class PlanetPanel extends JPanel
    {
        private final Random generator = new Random();

        private ArrayList<Planet> followers;
        private ArrayList<Planet> orbitals;
        private Planet leader;

        private int orbitalCount, followerCount;

        private double diameterLimit, diameterMin, speedCap, distanceMax, distanceMin, speedMin;

        private final int MIN_RED = 40, MIN_GREEN = 40, MIN_BLUE = 40, RED_RANGE = 216, BLUE_RANGE = 216, GREEN_RANGE = 216;

        public PlanetPanel(int followerCount, int orbitalCount, double diameterLimit, double diameterMin, double speedCap, double speedMin, double distanceMax, double distanceMin)
        {
            super();

            this.followerCount = followerCount;
            this.orbitalCount = orbitalCount;

            this.diameterLimit = diameterLimit;
            this.speedCap = speedCap;
            this.distanceMax = distanceMax;
            this.speedMin = speedMin;
            this.diameterMin = diameterMin;
            this.distanceMin = distanceMin;

            followers = new ArrayList<>(followerCount);
            orbitals = new ArrayList<>(orbitalCount);

            populatePlanets(followers, followerCount, false);
            populatePlanets(orbitals, orbitalCount, true);

            populateLeader();
        }

        public void populatePlanets(ArrayList<Planet> list, int count, boolean canReverse)
        {
            list.clear();

            Planet current;

            for(int i = 0; i < count; i++)
            {

                current = new Planet();

                current.point = new Point2D.Double();
                current.color = new Color(generator.nextInt(RED_RANGE) + MIN_RED, generator.nextInt(GREEN_RANGE) + MIN_GREEN, generator.nextInt(BLUE_RANGE) + MIN_BLUE);

                current.diameter = (generator.nextDouble() * (diameterLimit - diameterMin)) + diameterMin;
                current.orbit = generator.nextDouble() * generator.nextInt();

                current.distance = generator.nextDouble() * (distanceMax - distanceMin) + distanceMin;
                current.rate = generator.nextDouble() * (speedCap - speedMin) + speedMin;

                if(canReverse && generator.nextInt(2) == 0)
                {
                    current.rate *= -1;
                }

                list.add(current);
            }
        }

        public void populateLeader()
        {
            leader = new Planet();

            leader.diameter = 30;
            leader.color = Color.RED;
            leader.rate = LEADER_MAX_SPEED;
            leader.orbit = 0;
            leader.distance = 0;
            leader.point = new Point2D.Double(540, 360);
        }

        public void updateLeader()
        {
            Point2D.Double p = Point2DTracker.track(mousePoint.getX(), mousePoint.getY(), leader.point.getX() + leader.diameter / 2, leader.point.getY() + leader.diameter / 2,leader.rate);
            leader.point.setLocation(p.getX() + leader.point.getX(), p.getY() + leader.point.getY());
            //System.err.println(leader.point);
        }

        public void updateFollowers()
        {
            Planet previous = leader;
            Point2D.Double p = null;

            for(Planet planet : followers)
            {
                /*
                point = Point2DTracker.track(Point2DTrackerDriver.this.leader.getCenterX(), Point2DTrackerDriver.this.leader.getCenterY(), follower.getCenterX(), follower.getCenterY(), FOLLOWER_MAX_SPEED);

                if(Point2D.Double.distance(Point2DTrackerDriver.this.leader.getCenterX(), Point2DTrackerDriver.this.leader.getCenterY(), follower.getCenterX(), follower.getCenterY()) > Point2DTrackerDriver.this.leader.getWidth() / 2 + follower.getWidth() / 2)
                {
                    follower.setFrame(point.getX() + follower.getX(), point.getY() + follower.getY(),follower.getWidth(), follower.getHeight());
                }
                */

                p = Point2DTracker.track(previous.getXCenter(), previous.getYCenter(), planet.getXCenter(), planet.getYCenter(), planet.rate);

                if(Point2D.Double.distance(previous.getXCenter(), previous.getYCenter(), planet.getXCenter(), planet.getYCenter()) > previous.diameter / 2 + planet.diameter / 2)
                {
                    planet.point.setLocation(planet.point.getX() + p.getX(), planet.point.getY() + p.getY());
                }

                previous = planet;
            }

        }

        public void updateOrbitals()
        {
            for(Planet planet: orbitals)
            {
                planet.point.setLocation(Math.cos(planet.orbit) * planet.distance + leader.getXCenter() - planet.diameter, Math.sin(planet.orbit) * planet.distance + leader.getYCenter() - planet.diameter);

                planet.orbit += planet.rate / 250;
            }
        }

        public void updatePlanet()
        {
            updateLeader();

            updateOrbitals();

            updateFollowers();
        }

        public void updateVisual(Graphics g)
        {
            //System.err.println(mousePoint);

            g.setColor(leader.color);
            leader.drawPlanet(g);


            for(Planet planet : followers)
            {
                g.setColor(planet.color);
                planet.drawPlanet(g);
            }

            for(Planet planet : orbitals)
            {
                g.setColor(planet.color);
                planet.drawPlanet(g);
            }


            repaint();
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            updatePlanet();

            updateVisual(g);

            /*
            //System.err.println(mousePoint);

            g.fillRect(0,0, getWidth(), getHeight());

            Point2D.Double point = Point2DTracker.track(mousePoint.getX(), mousePoint.getY(), Point2DTrackerDriver.this.leader.getCenterX(), Point2DTrackerDriver.this.leader.getCenterY(), LEADER_MAX_SPEED);
            Point2DTrackerDriver.this.leader.setFrame(point.getX() + Point2DTrackerDriver.this.leader.getX(), point.getY() + Point2DTrackerDriver.this.leader.getY(), Point2DTrackerDriver.this.leader.getWidth(), Point2DTrackerDriver.this.leader.getHeight());


            g.setColor(Color.RED);
            ((Graphics2D)g).fill(Point2DTrackerDriver.this.leader);

            point = Point2DTracker.track(Point2DTrackerDriver.this.leader.getCenterX(), Point2DTrackerDriver.this.leader.getCenterY(), follower.getCenterX(), follower.getCenterY(), FOLLOWER_MAX_SPEED);

            if(Point2D.Double.distance(Point2DTrackerDriver.this.leader.getCenterX(), Point2DTrackerDriver.this.leader.getCenterY(), follower.getCenterX(), follower.getCenterY()) > Point2DTrackerDriver.this.leader.getWidth() / 2 + follower.getWidth() / 2)
            {
                follower.setFrame(point.getX() + follower.getX(), point.getY() + follower.getY(),follower.getWidth(), follower.getHeight());
            }

            g.setColor(Color.GREEN);
            ((Graphics2D)g).fill(follower);

            orbit.setFrame(Math.cos(orbitAngle) * Point2DTrackerDriver.this.leader.getWidth() * 2 + Point2DTrackerDriver.this.leader.getCenterX() - orbit.getWidth() / 2, Math.sin(orbitAngle) * Point2DTrackerDriver.this.leader.getHeight() * 2 + Point2DTrackerDriver.this.leader.getCenterY()  - orbit.getHeight() / 2, orbit.getWidth(), orbit.getHeight());

            g.setColor(Color.BLUE);
            ((Graphics2D)g).fill(orbit);
            orbitAngle += 0.001;

            //revalidate();
            repaint();
            */
        }
    }
}
