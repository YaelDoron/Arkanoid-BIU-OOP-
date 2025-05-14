//YAEL DORON 213406259
package collision;

import biuoop.DrawSurface;

/**
 * The collision.Sprite interface represents a game object that can be drawn on the screen and updates its
 * state over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the specified DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed, allowing it to update its state.
     */
    void timePassed();
}
