//YAEL DORON 213406259
package game;

import geometry.Point;

/**
 * game.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor to initialize the velocity with specified changes in x and y axes.
     *
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a velocity from an angle and speed.
     *
     * @param angle the angle of the velocity vector in degrees
     * @param speed the speed of the velocity
     * @return a new game.Velocity object with calculated dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = (speed * Math.sin(Math.toRadians(angle)));
        double dy = (-speed * Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * Gets the change in position on the x-axis.
     *
     * @return the change in position on the x-axis
     */
    public double getDx() {
        return dx;
    }

    /**
     * Sets the change in position on the x-axis.
     *
     * @param dx the change in position on the x-axis
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Gets the change in position on the y-axis.
     *
     * @return the change in position on the y-axis
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets the change in position on the y-axis.
     *
     * @param dy the change in position on the y-axis
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Takes a point with position (x, y) and returns a new point
     * with position (x + dx, y + dy).
     *
     * @param p the original point
     * @return a new point with updated position
     */
    public Point applyToPoint(Point p) {
        p = new Point(p.getX() + dx, p.getY() + dy);
        return p;
    }
}
