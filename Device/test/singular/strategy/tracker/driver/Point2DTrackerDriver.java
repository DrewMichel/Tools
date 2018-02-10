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
 *
 * This class exists to test the tracking performance of the class Point2DTracker.
 *
 * This class contains numerous nested classes because reasons.
 *
 * This class creates a JFrame window as well as two types of "Planet" object ArrayLists
 * and a unique "Planet."
 * The unique "Planet" is known as the leader and follows the user's mouse
 * The first ArrayList consists of "followers" that each follow the preceding element.
 * The first "follower" will follow the leader.
 * The second ArrayList consists of "orbitals" that each rotate around the leader.
 */
public class Point2DTrackerDriver extends JFrame
{
    // Pixel based constant that is used to limit the maximum speed of the "Planets"
    // Can be increased or decreased to influence the rate of movement of the "Planets"
    public static final double LEADER_MAX_SPEED = .4, FOLLOWER_MAX_SPEED = .25;
    public static final double ORBITAL_RATE_DIVISOR = 250;

    // Declaring instance variables
    private JPanel panel;
    private MouseEvent mousePoint;

    //private Ellipse2D.Double leader, follower, orbit;
    //double orbitAngle;

    // Main method
    public static void main(String[] args)
    {
        Point2DTrackerDriver example = new Point2DTrackerDriver();
        example.setVisible(true);
    }

    // Default constructor
    public Point2DTrackerDriver()
    {
        // Initial setup
        super("Tracker Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1080,720);

        // Sets fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //getContentPane().setBackground(Color.BLACK);

        // Creates the primary PlanetPanel which contains the leader, followers, and orbitals
        panel = new PlanetPanel(50,100,20, 4,0.35,0.15,500, 40);

        // Sets the background color of the PlanetPanel to black, cause space.
        panel.setBackground(Color.BLACK);

        // Hides the cursor
        panel.setCursor( panel.getToolkit().createCustomCursor(
                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                new Point(),
                null ) );

        // Adds the PlanetPanel to the JFrame
        add(panel);

        // Creates the mouse event that is used to listen for the mouse's position
        mousePoint = new MouseEvent(this, 0,0,0,getWidth() / 2,getHeight() / 2,0,false,0);//new Point2D.Double(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());

        //leader = new Ellipse2D.Double(0,0,50,50);
        //follower = new Ellipse2D.Double(0,0, 35, 35);
        //orbit = new Ellipse2D.Double(0,0,20,20);
        //orbitAngle = 0;

        // Moves the JFrame to the center of the screen
        setLocationRelativeTo(null);

        // Adds the mouse event to the Planet Panel
        panel.addMouseMotionListener(new MouseDetecter());
        //setVisible(true);
    }

    // Nested class that updates the instance variable mousePoint whenever the mouse moves.
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

    // Nested class that presents a "Planet"
    private static class Planet
    {
        // Declares instance variables
        public Point2D.Double point;
        public double diameter, orbit, distance, rate;
        public Color color;

        // Default constructor
        public Planet(){}

        // Method that can be called to drawn this object using its Point2D and diameter
        public void drawPlanet(Graphics g)
        {
            g.fillArc((int)point.getX(), (int)point.getY(),(int) diameter, (int)diameter, 0, 360);
        }

        // Method that returns the x center of the Planet by offseting from the origin
        public double getXCenter()
        {
            return point.getX() + diameter / 2;
        }

        // Method that returns the y center of the Planet by offseting from the origin
        public double getYCenter()
        {
            return point.getY() + diameter / 2;
        }
    }

    // Nested class that extends JPanel and contains several instances of Planets
    // to display them
    private class PlanetPanel extends JPanel
    {
        // Declares instance variables
        private final Random generator = new Random();

        private ArrayList<Planet> followers;
        private ArrayList<Planet> orbitals;
        private Planet leader;

        private int orbitalCount, followerCount;

        private double diameterLimit, diameterMin, speedCap, distanceMax, distanceMin, speedMin;

        // RGB color based constants that determine the min color as well as the random color range allowed.
        // Note that the random generator accepts 216 as 0-215, giving 216 possible combinations.
        // Therefore, the min + range cannot exceed 225.
        // These values can be altered to affect the color distribution of the planets
        private final int MIN_RED = 40, MIN_GREEN = 40, MIN_BLUE = 40, RED_RANGE = 216, BLUE_RANGE = 216, GREEN_RANGE = 216;

        private final int DEFAULT_LEADER_DIAMETER = 30;
        private final Color DEFAULT_LEADER_COLOR = Color.RED;

        /**
         * Parameter constructor that receives the number of followers, the number of orbitals,
         * the maximum and minimum allowed diameter sizes, the maximum and minimum speed caps,
         * and the maximum and minimum allowed orbital distances
         *
         * @param followerCount number of followers
         * @param orbitalCount number of orbitals
         * @param diameterLimit maximum allowed planet diameter
         * @param diameterMin minimum allowed planet diameter
         * @param speedCap maximum allowed speed cap
         * @param speedMin minimum allowed speed cap
         * @param distanceMax maximum allowed orbital distance
         * @param distanceMin minimum allowed orbital distance
         */
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

        /**
         * Populates an ArrayList of Planets by using constants, instance variables, and parameters
         *
         * @param list ArrayList containing the planets
         * @param count the number of planets to be inserted into the list
         * @param canReverse boolean used for orbital population.  If true, allows the planets to rotate in both directions
         */
        public void populatePlanets(ArrayList<Planet> list, int count, boolean canReverse)
        {
            list.clear();

            Planet current;

            // loop start
            for(int i = 0; i < count; i++)
            {
                // Creates a new planet
                current = new Planet();

                current.point = new Point2D.Double();

                // Generates the planet's colors using constants
                current.color = new Color(generator.nextInt(RED_RANGE) + MIN_RED, generator.nextInt(GREEN_RANGE) + MIN_GREEN, generator.nextInt(BLUE_RANGE) + MIN_BLUE);

                // Generates the planet's diameter using instance variables
                current.diameter = (generator.nextDouble() * (diameterLimit - diameterMin)) + diameterMin;

                // Generates the planet's orbit
                current.orbit = generator.nextDouble() * generator.nextInt();

                // Generates the planet's distance (from the leader) using instance variables
                current.distance = generator.nextDouble() * (distanceMax - distanceMin) + distanceMin;

                // Generates the planet's rate using instance variables
                current.rate = generator.nextDouble() * (speedCap - speedMin) + speedMin;

                // If the object can reverse, generates a number to determine if it should reverse its movement rate
                if(canReverse && generator.nextInt(2) == 0)
                {
                    current.rate *= -1;
                }

                // Adds the planet to the list
                list.add(current);
            } // loop end
        }

        /**
         * Creates the planet leader using constants and lazy hard values
         */
        public void populateLeader()
        {
            leader = new Planet();

            leader.diameter = DEFAULT_LEADER_DIAMETER;
            leader.color = DEFAULT_LEADER_COLOR;
            leader.rate = LEADER_MAX_SPEED;
            leader.orbit = 0;
            leader.distance = 0;
            leader.point = new Point2D.Double(540, 360);
        }

        /**
         * Updates the leader's position
         */
        public void updateLeader()
        {
            // Creates a new Point2D.Double that represents the distance that the leader can move
            // by tracking the mouse's position against the leader's center
            Point2D.Double p = Point2DTracker.track(mousePoint.getX(), mousePoint.getY(), leader.point.getX() + leader.diameter / 2, leader.point.getY() + leader.diameter / 2, leader.rate);

            // Updates the leader's position by the calculated point's x and y values so that the leader will be drawn at the position
            leader.point.setLocation(p.getX() + leader.point.getX(), p.getY() + leader.point.getY());
            //System.err.println(leader.point);
        }

        /**
         * Updates the "follower" planets
         */
        public void updateFollowers()
        {
            // Sets previous planet to leader
            Planet previous = leader;
            Point2D.Double p = null;

            // loop starts
            for(Planet planet : followers)
            {
                /*
                point = Point2DTracker.track(Point2DTrackerDriver.this.leader.getCenterX(), Point2DTrackerDriver.this.leader.getCenterY(), follower.getCenterX(), follower.getCenterY(), FOLLOWER_MAX_SPEED);

                if(Point2D.Double.distance(Point2DTrackerDriver.this.leader.getCenterX(), Point2DTrackerDriver.this.leader.getCenterY(), follower.getCenterX(), follower.getCenterY()) > Point2DTrackerDriver.this.leader.getWidth() / 2 + follower.getWidth() / 2)
                {
                    follower.setFrame(point.getX() + follower.getX(), point.getY() + follower.getY(),follower.getWidth(), follower.getHeight());
                }
                */

                // Creates a new Point2D.Double that represents the distance that the current follower can move
                // by tracking against the previous planet's center
                p = Point2DTracker.track(previous.getXCenter(), previous.getYCenter(), planet.getXCenter(), planet.getYCenter(), planet.rate);

                // If the updated position will ccause the planet's diameters to overlap, the planet has its position set so the diameters are touching instead
                if(Point2D.Double.distance(previous.getXCenter(), previous.getYCenter(), planet.getXCenter(), planet.getYCenter()) > previous.diameter / 2 + planet.diameter / 2)
                {
                    planet.point.setLocation(planet.point.getX() + p.getX(), planet.point.getY() + p.getY());
                }

                // Sets previous planet to current
                previous = planet;
            } // loop ends

        }

        /**
         * Updates the "orbital" planets
         */
        public void updateOrbitals()
        {
            // start loop
            for(Planet planet: orbitals)
            {
                // Sets the planet's position after calculating the next location it should move to
                planet.point.setLocation(Math.cos(planet.orbit) * planet.distance + leader.getXCenter() - planet.diameter, Math.sin(planet.orbit) * planet.distance + leader.getYCenter() - planet.diameter);

                // Increments the planet's orbit by its rate divided by a constant
                planet.orbit += planet.rate / ORBITAL_RATE_DIVISOR;
            } // loop ends
        }

        /**
         * Calls all relevant planet update methods
         */
        public void updatePlanet()
        {
            updateLeader();

            updateOrbitals();

            updateFollowers();
        }

        /**
         * Calls each planet's drawPlanet method.
         *
         * @param g Graphics object that can be passed from a canvas.
         */
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

        /**
         * Overrides the default paintComponent. This allows access to drawing the planets each update.
         *
         * @param g Graphics object that can be passed from a canvas.
         */
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            // Update's each planet's position
            updatePlanet();

            // Update's each planet's display
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
