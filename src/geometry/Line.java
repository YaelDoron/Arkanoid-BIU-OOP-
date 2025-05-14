//YAEL DORON 213406259
package geometry;

import game.Treshold;
import game.Constants;
import java.util.List;

/**
 * The geometry.Line class represents a line segment in a 2D plane.
 */
public class Line {
    /**
     * The starting point of the line segment.
     */
    private final Point start;

    /**
     * The ending point of the line segment.
     */
    private final Point end;

    /**
     * Constructs a geometry.Line object with given start and end points.
     *
     * @param start The starting point of the line segment.
     * @param end   The ending point of the line segment.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a geometry.Line object with given coordinates of start and end points.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates the length of the line segment.
     *
     * @return The length of the line segment.
     */
    public double length() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), Constants.EXPONENT)
                + Math.pow(end.getY() - start.getY(), Constants.EXPONENT));
    }

    /**
     * Finds the midpoint of the line segment.
     *
     * @return The midpoint of the line segment.
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) / Constants.HALF;
        double middleY = (start.getY() + end.getY()) / Constants.HALF;
        return new Point(middleX, middleY);
    }

    /**
     * Retrieves the starting point of the line segment.
     *
     * @return The starting point of the line segment.
     */
    public Point start() {
        return start;
    }

    /**
     * Retrieves the ending point of the line segment.
     *
     * @return The ending point of the line segment.
     */
    public Point end() {
        return end;
    }

    /**
     * Checks the orientation of three points.
     *
     * @param p1 The first point.
     * @param q  The second point.
     * @param p3 The third point.
     * @return 0 if collinear, 1 if clockwise, 2 if counterclockwise.
     */
    private static int checkOrientation(Point p1, Point q, Point p3) {
        double val = (q.getY() - p1.getY()) * (p3.getX() - q.getX())
                - (q.getX() - p1.getX()) * (p3.getY() - q.getY());
        if (val == 0) {
            return 0;
        }
        if (val > 0) {
            return 1;
        }
        return 2;
    }

    /**
     * Checks if a point lies on a line segment.
     *
     * @param line The line segment.
     * @param p    The point to check.
     * @return True if the point lies on the line segment, otherwise false.
     */
    private boolean isPointOnLine(Line line, Point p) {
        return (Treshold.smallerOrEqual(p.getX(), Math.max(line.start.getX(), line.end.getX()))
                && Treshold.biggerOrEqual(p.getX(), Math.min(line.start.getX(), line.end.getX()))
                && Treshold.smallerOrEqual(p.getY(), Math.max(line.start.getY(), line.end.getY()))
                && Treshold.biggerOrEqual(p.getY(), Math.min(line.start.getY(), line.end.getY())));
    }

    /**
     * Checks if this line segment intersects with another line segment.
     *
     * @param other The other line segment.
     * @return True if the line segments intersect, otherwise false.
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        Point p1 = start;
        Point p2 = end;
        Point p3 = other.start;
        Point p4 = other.end;
        int o1 = checkOrientation(p1, p2, p3);
        int o2 = checkOrientation(p1, p2, p4);
        int o3 = checkOrientation(p3, p4, p1);
        int o4 = checkOrientation(p3, p4, p2);
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        if (o1 == 0 && isPointOnLine(this, p3)) {
            return true;
        }
        if (o2 == 0 && isPointOnLine(this, p4)) {
            return true;
        }
        if (o3 == 0 && isPointOnLine(other, p1)) {
            return true;
        }
        return o4 == 0 && isPointOnLine(other, p2);
    }

    /**
     * Checks if this line segment intersects with two other line segments.
     *
     * @param other1 The first other line segment.
     * @param other2 The second other line segment.
     * @return True if this line segment intersects with both other line segments, otherwise false.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (other1 == null || other2 == null) {
            return false;
        }
        return (this.isIntersecting(other1) && this.isIntersecting(other2));
    }

    /**
     * Calculates the slope of a line formed by two points.
     *
     * @param point1 The first point.
     * @param point2 The second point.
     * @return The slope of the line formed by the two points.
     */
    public static double calculateSlope(Point point1, Point point2) {
        // Calculate the difference in y and x coordinates
        double deltaY = point2.getY() - point1.getY();
        double deltaX = point2.getX() - point1.getX();

        // Avoiding division by zero
        if (deltaX == 0) {
            return Double.POSITIVE_INFINITY;
        } else {
            // Calculate and return the slope
            return deltaY / deltaX;
        }
    }

    /**
     * Calculates the intercept of a line with the y-axis.
     *
     * @param point The point through which the line passes.
     * @param m     The slope of the line.
     * @return The intercept of the line with the y-axis.
     */
    private double calculateB(Point point, double m) {
        return point.getY() - (m * point.getX());
    }

    /**
     * Checks if this line overlaps with another line.
     *
     * @param other The other line segment.
     * @return True if there is an overlap, otherwise false.
     */
    private boolean isOverlap(Line other) {
        if (Treshold.equal(this.start.getX(), this.end.getX())
                && Treshold.equal(other.start.getX(), other.end.getX())
                && Treshold.equal(this.start.getX(), other.end.getX())) {
            return (isPointOnLine(this, other.start));
        }
        if (Treshold.equal(calculateB(this.start, calculateSlope(this.start, this.end)),
                calculateB(other.start, calculateSlope(other.start, other.end)))) {
            return (isPointOnLine(this, other.start));
        }
        return false;
    }

    /**
     * Finds the intersection point between this line and another line.
     *
     * @param other The other line segment.
     * @return The intersection point if it exists, otherwise null.
     */
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }
        double x1 = this.start.getX(), y1 = this.start.getY();
        double x2 = this.end.getX(), y2 = this.end.getY();
        double x3 = other.start.getX(), y3 = other.start.getY();
        double x4 = other.end.getX(), y4 = other.end.getY();

        // If both of the lines are points, return the common point if exists
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            if (this.start.equals(other.start)) {
                return this.start;
            } else {
                return null;
            }
        }
        double determinanta = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (Treshold.equal(determinanta, 0)) {
            // Lines are parallel
            if (isOverlap(other)) {
                if (this.start.equals(other.end) && !isPointOnLine(new Line(other.start, other.end), this.end)) {
                    return new Point(this.start.getX(), this.start.getY());
                }
                if (this.end.equals(other.start) && !isPointOnLine(new Line(other.start, other.end), this.start)) {
                    return new Point(this.end.getX(), this.end.getY());
                }
                if (this.start.equals(other.start) && !isPointOnLine(new Line(other.start, other.end), this.end)) {
                    return new Point(this.start.getX(), this.start.getY());
                }
                if (this.end.equals(other.end) && !isPointOnLine(new Line(other.start, other.end), this.start)) {
                    return new Point(this.end.getX(), this.end.getY());
                }
            }
            return null;

        }


        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / determinanta;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / determinanta;

        if (Treshold.biggerOrEqual(ua, 0) && Treshold.smallerOrEqual(ua, 1)
                && Treshold.biggerOrEqual(ub, 0) && Treshold.smallerOrEqual(ub, 1)) {
            double intersectionX = x1 + ua * (x2 - x1);
            double intersectionY = y1 + ua * (y2 - y1);
            return new Point(intersectionX, intersectionY);
        }
        // Lines do not intersect
        return null;
    }

    /**
     * Checks if this line segment is equal to another line segment.
     *
     * @param other The other line segment.
     * @return True if the line segments are equal, otherwise false.
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * Finds the closest intersection point to the start of this line segment with a given rectangle.
     *
     * @param rect The rectangle to check for intersections.
     * @return The closest intersection point to the start of this line segment, or null if no intersection exists.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double minDistance = Double.MAX_VALUE;
        Point closestIntersectionToStartOfLine = null;
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints == null || intersectionPoints.isEmpty()) {
            return null;
        }
        for (Point point : intersectionPoints) {
            double distance = this.start.distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                closestIntersectionToStartOfLine = point;
            }
        }
        return closestIntersectionToStartOfLine;
    }

}
