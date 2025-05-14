//YAEL DORON 213406259
package geometry;

import game.Constants;
import game.Treshold;

/**
 * The {@code geometry.Point} class represents a point in a 2D Cartesian coordinate system.
 * It contains methods for calculating the distance to another point and checking
 * equality between two points.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a new {@code geometry.Point} with the specified coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance from this point to another point.
     *
     * @param other The other point to which the distance is calculated.
     *              If {@code other} is {@code null}, the method returns
     *              a failure code defined in {@code game.Constants.FAILCODE}.
     * @return The Euclidean distance between this point and the {@code other} point.
     * If {@code other} is {@code null}, returns {@code game.Constants.FAILCODE}.
     */
    public double distance(Point other) {
        if (other == null) {
            return Constants.FAILCODE;
        }
        return Math.sqrt((other.x - this.x) * (other.x - this.x)
                + (other.y - this.y) * (other.y - this.y));
    }

    /**
     * Compares this point to the specified point. The result is {@code true} if and
     * only if the argument is not {@code null} and is a {@code geometry.Point} object that
     * has the same coordinates as this point.
     *
     * @param other the point to compare with this point
     * @return {@code true} if the points are equal, {@code false} otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (Treshold.equal(this.x, other.x) && Treshold.equal(other.y, this.y));
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate of this point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate of this point
     */
    public void setY(double y) {
        this.y = y;
    }

}
