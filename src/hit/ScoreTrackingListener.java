//YAEL DORON 213406259
package hit;

import collision.Ball;
import collision.Block;

/**
 * The hit.ScoreTrackingListener class is responsible for tracking scores when a block is hit.
 * It increases the score by a fixed amount when a block is hit by a ball.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    /**
     * Constructs a hit.ScoreTrackingListener with the specified score counter.
     *
     * @param scoreCounter the counter to track the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Updates the score when a block is hit by increasing it and removes itself from the block's hit listeners.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}