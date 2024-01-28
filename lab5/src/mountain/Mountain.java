package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {

    private Point a;
    private Point b;
    private Point c;
    private double devValue;
    private Map<Side, Point> midPointMap = new HashMap<>();

    public Mountain(Point a, Point b, Point c, double devValue) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        this.devValue = devValue;
    }

    @Override
    public String getTitle() {
        return "Mountain";
    }

    @Override
    public void draw(TurtleGraphics g) {
        fractalTriangle(g, order, a, b, c);
    }

    private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c) {
        if (order == 0) {
            turtle.moveTo(a.getX(), a.getY());
            turtle.penDown();
            turtle.forwardTo(b.getX(), b.getY());
            turtle.forwardTo(c.getX(), c.getY());
            turtle.forwardTo(a.getX(), a.getY());
        } else {

            // m points
            Point ab = getMidPoint(a, b);
            Point bc = getMidPoint(b, c);
            Point ca = getMidPoint(c, a);

            fractalTriangle(turtle, order - 1, ab, b, bc);
            fractalTriangle(turtle, order - 1, ab, bc, ca);
            fractalTriangle(turtle, order - 1, a, ab, ca);
            fractalTriangle(turtle, order - 1, ca, bc, c);

        }
    }

    private Point getMidPoint(Point a, Point b) {
        Side side = new Side(a, b);

        // The side already exists
        if (midPointMap.containsKey(side)) {
            return midPointMap.get(side);
        }

        // Calc the mid point x and y value
        int midX = (a.getX() + b.getX()) / 2;
        int midY = ((a.getY() + b.getY()) / 2) + (int) RandomUtilities.randFunc(devValue / 2);

        // Store these in a new Point
        Point midPoint = new Point(midX, midY);

        // Add these to the map with the side.
        midPointMap.put(side, midPoint);
        return midPoint;
    }

}
