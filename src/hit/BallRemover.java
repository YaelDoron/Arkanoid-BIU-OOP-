//YAEL DORON 213406259
package hit;

import collision.Ball;
import collision.Block;
import game.Game;

/**
 * The hit.BallRemover class is a hit.HitListener that removes balls from the game and
 * decreases the count of remaining balls when they hit a block.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructs a hit.BallRemover.
     *
     * @param game the game from which balls will be removed
     * @param remainingBalls a counter for the remaining balls in the game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * It removes the ball from the game and decreases the remaining balls count.
     *
     * @param beingHit the block that is being hit
     * @param hitter the ball that is hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
