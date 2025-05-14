//YAEL DORON 213406259
package collision;
import geometry.Point;


/**
 * Represents information about a collision, including the point of collision
 * and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a collision.CollisionInfo object with the given collision point and collidable.
     *
     * @param collisionPoint The point where the collision occurs.
     * @param collidable     The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collidable;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }


    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}
