//YAEL DORON 213406259
package hit;

/**
 * The hit.HitNotifier interface should be implemented by any class that can notify
 * registered listeners of hit events.
 */
public interface HitNotifier {
    /**
     * Adds a hit.HitListener to the list of listeners to hit events.
     *
     * @param hl the hit.HitListener to be added
     */
    void addHitListener(HitListener hl);
    /**
     * Removes a hit.HitListener from the list of listeners to hit events.
     *
     * @param hl the hit.HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}