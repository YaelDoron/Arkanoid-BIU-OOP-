//YAEL DORON 213406259
package collision;

import biuoop.DrawSurface;
import game.Game;
import game.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import game.Constants;
import game.GameEnvironment;
import java.awt.Color;


/**
 * The collision.Ball class represents a ball with a specific size, color, position, and velocity.
 */
public class Ball implements Sprite {
    private Point center;
    private final int size;
    private Color color;
    private Velocity velocity = new Velocity(Constants.INTELIZEDVALUE, Constants.INTELIZEDVALUE);
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a collision.Ball with the specified center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
    }

    /**
     * Constructs a collision.Ball with the specified center point, radius, color, and game environment.
     *
     * @param center          the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the game environment containing collidable objects
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Returns the x-coordinate of the center of the ball.
     *
     * @return the x-coordinate of the center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the center of the ball.
     *
     * @return the y-coordinate of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the size (radius) of the ball.
     *
     * @return the size (radius) of the ball
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the change in x-coordinate per unit time
     * @param dy the change in y-coordinate per unit time
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param velocity the velocity to be set
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * Sets the center point of the ball.
     *
     * @param center the new center point of the ball
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Moves the ball one step, handling collisions with objects in the game environment.
     */
    public void moveOneStep() {
        // I Compute the ball trajectory
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        //I Check (using the game environment) if moving on this trajectory will hit anything.
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        //If not, I move the ball to the end of the trajectory
        if (collisionInfo == null) {
            this.center = trajectory.end();
        } else {
            //Otherwise (there is a hit)
            //I move the ball to "almost" the hit point, but just slightly before it
            double distanceToCollision = this.center.distance(collisionInfo.collisionPoint());
            double speed = Math.sqrt(Math.pow(this.velocity.getDx(), Constants.EXPONENT)
                    + Math.pow(this.velocity.getDy(), Constants.EXPONENT));

            double deltaX = this.velocity.getDx() * (distanceToCollision / speed) * Constants.FACTOR;
            double deltaY = this.velocity.getDy() * (distanceToCollision / speed) * Constants.FACTOR;

            double moveToX = this.center.getX() + deltaX;
            double moveToY = this.center.getY() + deltaY;
            this.center = new Point(moveToX, moveToY);
//I update the velocity to the new velocity returned by the hit() method and notify the hit object
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity));

            // Check if the ball is stuck inside the paddle
            if (collisionInfo.collisionObject().getCollisionRectangle().getUpperLeft().getY()
                    == Constants.SCREENHEIGHT - Constants.GREYRECTSHORTEDGE - Constants.PADDLEHEIGHT
                    && this.isInside(collisionInfo.collisionObject().getCollisionRectangle())) {
                this.setCenter(new Point(this.center.getX(), this.center.getY() - this.size - 1));
            }

        }
    }

    /**
     * Draws the ball on the given surface.
     *
     * @param surface the surface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.size);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds this object to the specified game as a sprite.
     *
     * @param g the game to which this sprite should be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Sets the game environment for this object.
     *
     * @param gameEnvironment the game environment to set
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Checks if the ball's center is inside the given rectangle.
     *
     * @param rectangle the rectangle to check against
     * @return true if the ball's center is inside the rectangle, false otherwise
     */
    public boolean isInside(Rectangle rectangle) {
        return (this.center.getX() >= rectangle.getUpperLeft().getX()
                && this.center.getX() <= rectangle.getUpperLeft().getX() + rectangle.getWidth()
                && this.center.getY() >= rectangle.getUpperLeft().getY()
                && this.center.getY() <= rectangle.getUpperLeft().getY() + rectangle.getHeight());
    }

    /**
     * Removes this ball from the game.
     *
     * @param g the game from which this ball should be removed
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
    /**
     * Sets the color of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }
}