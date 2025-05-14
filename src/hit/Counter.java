//YAEL DORON 213406259
package hit;

/**
 * The hit.Counter class is used for counting things.
 * It provides methods to increase, decrease, and get the current count.
 */
public class Counter {
    private int counter = 0;

    /**
     * Adds the given number to the current count.
     *
     * @param number the number to add to the current count
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * Subtracts the given number from the current count.
     *
     * @param number the number to subtract from the current count
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return counter;
    }
}