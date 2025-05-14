//YAEL DORON 213406259
package collision;

import biuoop.DrawSurface;
import game.Game;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import hit.HitListener;
import hit.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The collision.Block class represents a rectangular block that can be collided with.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private final Color color;
    private final List<HitListener> hitListeners = new ArrayList<>();


    /**
     * Constructs a collision.Block with the specified upper left point, width, height, and color.
     *
     * @param upperLeft the upper left point of the block
     * @param width     the width of the block
     * @param height    the height of the block
     * @param color     the color of the block
     */
    public Block(Point upperLeft, int width, int height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * Returns the "collision shape" of the object.
     *
     * @return A {@link Rectangle} representing the shape of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notifies the object that a collision occurred at the specified point with the given velocity.
     * Returns the new velocity expected after the hit, based on the force the object inflicted.
     *
     * @param collisionPoint  The {@link Point} where the collision occurred.
     * @param currentVelocity The current {@link Velocity} of the object that collided.
     * @return The new {@link Velocity} after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Check collision with the left or right sides of the rectangle
        if (collisionPoint.getX() == rectangle.getUpperLeft().getX()
                || collisionPoint.getX() == rectangle.getUpperLeft().getX() + rectangle.getWidth()) {
            dx = -dx;
        }

        // Check collision with the top or bottom sides of the rectangle
        if (collisionPoint.getY() == rectangle.getUpperLeft().getY()
                || collisionPoint.getY() == rectangle.getUpperLeft().getY() + rectangle.getHeight()) {
            dy = -dy;
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * Adds this block to the game as both a collision.Sprite and a collidable.
     *
     * @param g the game to which this block should be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Checks if the ball's color matches the block's color.
     *
     * @param ball the ball to check against
     * @return true if the ball's color matches the block's color, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor() == this.color;
    }
    /**
     * Removes this block from the game.
     *
     * @param game the game from which to remove this block
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
    /**
     * Adds a hit.HitListener to this block.
     *
     * @param hl the hit.HitListener to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * Removes a hit.HitListener from this block.
     *
     * @param hl the hit.HitListener to remove
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
// Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
// Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Returns the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return color;
    }
}