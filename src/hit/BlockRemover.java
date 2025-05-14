//YAEL DORON 213406259
package hit;


import collision.Ball;
import collision.Block;
import game.Game;

/**
 * A hit.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructs a hit.BlockRemover with the specified game and counter for remaining blocks.
     *
     * @param game            the game from which blocks will be removed
     * @param remainingBlocks the counter for the number of remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * Also removes this listener from the block that is being removed from the game.
     *
     * @param beingHit the block that is being hit and should be removed
     * @param hitter   the ball that is hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
