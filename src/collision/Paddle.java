//YAEL DORON 213406259
package collision;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Constants;
import game.Game;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The collision.Paddle class represents the paddle in the game, which is controlled by the player.
 * The paddle can move left and right and can collide with the ball, changing its trajectory.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final int velocity;

    /**
     * Constructs a collision.Paddle object with the given keyboard sensor.
     *
     * @param keyboard the keyboard sensor used to control the paddle
     */
    public Paddle(biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(new Point(
                (double) (Constants.SCREENWIDTH - 2 * Constants.GREYRECTSHORTEDGE
                        - Constants.PADDLEWIDTH) / Constants.HALF,
                Constants.SCREENHEIGHT - Constants.GREYRECTSHORTEDGE - Constants.PADDLEHEIGHT),
                Constants.PADDLEWIDTH, Constants.PADDLEHEIGHT);
        this.velocity = Constants.BALLSPEED;
    }

    /**
     * Moves the paddle to the left by its velocity.
     * If the paddle reaches the left boundary, it wraps around to the right side.
     */
    public void moveLeft() {
        double newX = this.rectangle.getUpperLeft().getX() - velocity;
        if (newX < Constants.GREYRECTSHORTEDGE) {
            newX = Constants.SCREENWIDTH - Constants.GREYRECTSHORTEDGE - Constants.PADDLEWIDTH;
        }
        rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(),
                this.rectangle.getHeight());
    }

    /**
     * Moves the paddle to the right by its velocity.
     * If the paddle reaches the right boundary, it wraps around to the left side.
     */
    public void moveRight() {
        double newX = this.rectangle.getUpperLeft().getX() + velocity;
        if (newX > Constants.SCREENWIDTH - Constants.GREYRECTSHORTEDGE - this.rectangle.getWidth()) {
            newX = Constants.GREYRECTSHORTEDGE;
        }
        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(),
                this.rectangle.getHeight());
    }


    /**
     * Notifies the paddle that time has passed, and it should check for keyboard input to move.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the paddle
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle of the paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Handles the collision of the paddle with the ball.
     * The paddle is divided into 5 regions, each causing the ball to bounce at a different angle.
     *
     * @param hitter          the ball object that collided with this block
     * @param collisionPoint  the point at which the collision occurs
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int widthOfSection = (int) this.rectangle.getWidth() / Constants.REGIONSAMOUNT;
        double collisionX = collisionPoint.getX();
        double topLeftX = this.rectangle.getUpperLeft().getX();
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), Constants.EXPONENT)
                + Math.pow(currentVelocity.getDy(), Constants.EXPONENT));

        //hit in region one
        if (collisionX >= topLeftX && collisionX < topLeftX + widthOfSection) {
            return Velocity.fromAngleAndSpeed(Constants.FIRSTREGIONANGLE, speed);
        } else if (collisionX >= topLeftX + widthOfSection
                && collisionX < topLeftX + Constants.SECONDREGION * widthOfSection) {
            //hit in region two
            return Velocity.fromAngleAndSpeed(Constants.SECONDREGIONANGLE, speed);
        } else if (collisionX >= topLeftX + Constants.SECONDREGION * widthOfSection
                && collisionX < topLeftX + Constants.THIRDREGION * widthOfSection) {
            //hit in region three
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (collisionX >= topLeftX + Constants.THIRDREGION * widthOfSection
                && collisionX < topLeftX + Constants.FOURTHREGION * widthOfSection) {
            //hit in region four
            return Velocity.fromAngleAndSpeed(Constants.FOURTHREGIONANGLE, speed);
        } else {
            //hit in region five
            return Velocity.fromAngleAndSpeed(Constants.FIFTHREGIONANGLE, speed);
        }
    }

    /**
     * Adds this paddle to the given game.
     *
     * @param g the game to add the paddle to
     */
    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}