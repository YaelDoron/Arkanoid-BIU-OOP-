//YAEL DORON 213406259
package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The geometry.Rectangle class represents a rectangle defined by its upper-left corner, width, and height.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Creates a new rectangle with the specified location and dimensions.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line the line to check for intersections with the rectangle
     * @return a list of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        // The corners of the rectangle
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);

        // Define the edges of the rectangle
        Line top = new Line(upperLeft, upperRight);
        Line bottom = new Line(lowerLeft, lowerRight);
        Line left = new Line(upperLeft, lowerLeft);
        Line right = new Line(upperRight, lowerRight);

        // Check intersections with each edge and add to the list if they exist
        Point intersection = line.intersectionWith(top);
        if (intersection != null) {
            intersectionPoints.add(intersection);
        }
        intersection = line.intersectionWith(bottom);
        if (intersection != null) {
            intersectionPoints.add(intersection);
        }
        intersection = line.intersectionWith(left);
        if (intersection != null) {
            intersectionPoints.add(intersection);
        }
        intersection = line.intersectionWith(right);
        if (intersection != null) {
            intersectionPoints.add(intersection);
        }
        return intersectionPoints;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
}
