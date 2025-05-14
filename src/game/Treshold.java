//YAEL DORON 213406259
package game;

/**
 * The {@code game.Treshold} class provides methods to compare two double values
 * It includes methods to check if one value is smaller than, equal to, or greater than another value
 * within the specified epsilon range.
 * The epsilon value is set to 1e-8.
 */
public class Treshold {

    /**
     * Checks if {@code x} is smaller than or equal to {@code y} within the epsilon range.
     *
     * @param num1 the first value to compare
     * @param num2 the second value to compare
     * @return {@code true} if {@code x} is smaller than or equal to {@code y} within the epsilon range,
     * {@code false} otherwise
     */
    public static boolean smallerOrEqual(double num1, double num2) {
        return (num1 < num2 || equal(num1, num2));
    }

    /**
     * Checks if {@code x} is equal to {@code y} within the epsilon range.
     *
     * @param num1 the first value to compare
     * @param num2 the second value to compare
     * @return {@code true} if {@code x} is equal to {@code y} within the epsilon range, {@code false} otherwise
     */
    public static boolean equal(double num1, double num2) {
        double epsilon = 0.00000001;
        return Math.abs(num1 - num2) <= epsilon;
    }

    /**
     * Checks if {@code x} is greater than or equal to {@code y} within the epsilon range.
     *
     * @param x the first value to compare
     * @param y the second value to compare
     * @return {@code true} if {@code x} is greater than or equal to {@code y} within the epsilon range,
     * {@code false} otherwise
     */
    public static boolean biggerOrEqual(double x, double y) {
        return (x > y || equal(x, y));
    }
}
