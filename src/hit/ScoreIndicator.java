//YAEL DORON 213406259
package hit;

import biuoop.DrawSurface;
import collision.Sprite;
import game.Game;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The hit.ScoreIndicator class represents a sprite that displays the current score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    private final Rectangle rectangle;
    private final Color color;

    /**
     * Constructs a hit.ScoreIndicator with the specified score counter, rectangle, and color.
     *
     * @param score     the counter holding the score to display
     * @param rectangle the rectangle defining the position and size of the score indicator
     * @param color     the color of the score indicator
     */
    public ScoreIndicator(Counter score, Rectangle rectangle, Color color) {
        this.score = score;
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);

        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawText((int) (rectangle.getUpperLeft().getX() + rectangle.getWidth()) / 2,
                (int) ((rectangle.getUpperLeft().getY() + rectangle.getHeight()) / 1.5),
                "Score: " + score.getValue(), 10);
    }

    @Override
    public void timePassed() {
    }
    /**
     * Adds this score indicator to the specified game as a sprite.
     *
     * @param g the game to which this score indicator should be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
