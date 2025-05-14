//YAEL DORON 213406259
package hit;

import collision.Ball;
import collision.Block;

/**
 * The hit.HitListener interface should be implemented by any class that wants to be notified of hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that is being hit
     * @param hitter the ball that is hitting the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}