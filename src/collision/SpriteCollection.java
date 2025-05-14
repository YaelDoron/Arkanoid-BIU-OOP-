//YAEL DORON 213406259
package collision;


import java.util.List;
import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * The collision.SpriteCollection class represents a collection of sprites in a game.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Constructs a new collision.SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : new ArrayList<>(sprites)) {
            sprite.timePassed();
        }
    }

    /**
     * Calls the drawOn(d) method on all sprites in the collection.
     *
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
