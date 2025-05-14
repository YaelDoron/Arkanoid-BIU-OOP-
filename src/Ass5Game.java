//YAEL DORON 213406259

import game.Game;

/**
 * The Ass3Game class contains the main method which serves as the entry point
 * for the game. It creates a game.Game object, initializes it, and then runs it.
 */
public class Ass5Game {

    /**
     * The main method which serves as the entry point for the game.
     * It creates a game.Game object, initializes it, and then runs it.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
