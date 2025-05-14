//YAEL DORON 213406259
package game;

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The game.GameEnvironment class represents the game environment,
 * managing the collection of collidable objects and providing collision detection.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructs a game.GameEnvironment with a list of collidables.
     *
     * @param collidables a list of collidable objects
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = new ArrayList<>(collidables);
    }


    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Removes the given collidable from the environment.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * Determines the closest collision that will occur given a trajectory.
     * If no collision will occur, returns null.
     *
     * @param trajectory the trajectory of the moving object
     * @return the information about the closest collision, or null if no collision will occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollision = null;
        double minDistance = Double.MAX_VALUE;

        for (Collidable collidable : collidables) {
            Rectangle rectangle = collidable.getCollisionRectangle();
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(rectangle);

            if (collisionPoint != null) {
                double distance = trajectory.start().distance(collisionPoint);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCollision = new CollisionInfo(collisionPoint, collidable);
                }
            }
        }
        return closestCollision;
    }
}