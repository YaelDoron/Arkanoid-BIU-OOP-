//YAEL DORON 213406259
package collision;

import game.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * The collision.Collidable interface represents objects that can be collided with in the game.
 * Implementing classes will define the shape of the object and the response to collisions.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return A {@link Rectangle} representing the shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified point with the given velocity.
     * Returns the new velocity expected after the hit, based on the force the object inflicted.
     *
     * @param hitter          The ball that hit the block.
     * @param collisionPoint  The {@link Point} where the collision occurred.
     * @param currentVelocity The current {@link Velocity} of the object that collided.
     * @return The new {@link Velocity} after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
