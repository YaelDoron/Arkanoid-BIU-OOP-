//YAEL DORON 213406259
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collision.Ball;
import collision.Block;
import collision.Collidable;
import collision.Paddle;
import collision.Sprite;
import collision.SpriteCollection;
import geometry.Point;
import geometry.Rectangle;
import hit.BallRemover;
import hit.BlockRemover;
import hit.Counter;
import hit.ScoreTrackingListener;
import hit.ScoreIndicator;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

/**
 * The game.Game class represents the game logic, including the initialization of game elements,
 * the game environment, and the game loop.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final Counter remainingBlocks = new Counter();
    private final Counter availabeBalls = new Counter();
    private final Counter score = new Counter();

    /**
     * Constructs a game.Game object, initializing the sprite collection, game environment, and GUI.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(new ArrayList<>());
        this.gui = new GUI("Araknoid", Constants.SCREENWIDTH, Constants.SCREENHEIGHT);
    }

    /**
     * Returns the game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Removes the specified collidable object from the game environment.
     *
     * @param c the collidable object to be removed
     */

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes the specified sprite from the game.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes a new game by creating the blocks, balls, paddle, and adding them to the game.
     */
    public void initialize() {
        Random rnd = new Random();
        BallRemover ballRemover = new BallRemover(this, availabeBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score,
                new Rectangle(new Point(Constants.INTELIZEDVALUE, Constants.INTELIZEDVALUE),
                        Constants.SCREENWIDTH, 15), Color.WHITE);
        scoreIndicator.addToGame(this);

        //creating the grey rectangles in the edges of the screen
        Block greyFirst = new Block(new Point(Constants.INTELIZEDVALUE, 15),
                Constants.SCREENWIDTH, Constants.GREYRECTSHORTEDGE, Color.GRAY);
        greyFirst.addToGame(this);


        Block greySecond = new Block(new Point(Constants.GREYRECTSHORTEDGE, Constants.SCREENHEIGHT
                - 1), Constants.SCREENWIDTH - 2 * Constants.GREYRECTSHORTEDGE,
                Constants.GREYRECTSHORTEDGE, Color.GRAY);
        greySecond.addToGame(this);
        //register the hit.BallRemover class as a listener of the death-region.
        greySecond.addHitListener(ballRemover);

        Block greyThird = new Block(new Point(Constants.INTELIZEDVALUE, Constants.GREYRECTSHORTEDGE + 15),
                Constants.GREYRECTSHORTEDGE, Constants.SCREENHEIGHT
                - Constants.GREYRECTSHORTEDGE, Color.GRAY);
        greyThird.addToGame(this);

        Block greyFourth = new Block(new Point(Constants.SCREENWIDTH - Constants.GREYRECTSHORTEDGE,
                Constants.GREYRECTSHORTEDGE + 15), Constants.GREYRECTSHORTEDGE, Constants.
                SCREENWIDTH
                - Constants.GREYRECTSHORTEDGE, Color.GRAY);
        greyFourth.addToGame(this);
        //adding the screen
        Block screen = new Block(new Point(Constants.GREYRECTSHORTEDGE, Constants.GREYRECTSHORTEDGE + 15),
                Constants.SCREENWIDTH - 2 * Constants.GREYRECTSHORTEDGE,
                Constants.SCREENHEIGHT - 2 * Constants.GREYRECTSHORTEDGE - 15 + Constants.GREYRECTSHORTEDGE,
                Color.GREEN);
        screen.addToGame(this);

        //adding the balls to the screen
        Ball ball = new Ball(new Point(Constants.FIRSTBALLSTARTX, Constants.FIRSTBALLSTARTY),
                Constants.BALLSRADIUS, Color.white);
        ball.setGameEnvironment(this.environment);
        int angle1 = rnd.nextInt(Constants.MAXANGLE);
        ball.setVelocity(Velocity.fromAngleAndSpeed(angle1, Constants.BALLSPEED));
        ball.addToGame(this);

        Ball secondBall = new Ball(new Point(Constants.SECONDBALLSTARTX, Constants.SECONDBALLSTARTY),
                Constants.BALLSRADIUS, Color.white);
        secondBall.setGameEnvironment(this.environment);
        int angle2 = rnd.nextInt(Constants.MAXANGLE);
        secondBall.setVelocity(Velocity.fromAngleAndSpeed(angle2, Constants.BALLSPEED));
        secondBall.addToGame(this);

        Ball thirdBall = new Ball((new Point(Constants.THIRDBALLSTARTX, Constants.THIRDBALLSTARTY)),
                Constants.BALLSRADIUS, Color.WHITE);
        thirdBall.setGameEnvironment(this.environment);
        int angle3 = rnd.nextInt(Constants.MAXANGLE);
        thirdBall.setVelocity(Velocity.fromAngleAndSpeed(angle3, Constants.BALLSPEED));
        thirdBall.addToGame(this);

        availabeBalls.increase(3);


        //adding the paddle to the screen
        Paddle paddle = new Paddle(this.gui.getKeyboardSensor());
        paddle.addToGame(this);
        Color[] colors = {Color.CYAN, Color.RED, Color.YELLOW, Color.WHITE, Color.PINK, Color.BLUE};

        for (int i = Constants.INTELIZEDVALUE; i < Constants.LINESOFBLOCKS; i++) {
            for (int j = Constants.INTELIZEDVALUE; j < Constants.MAXBLOCKSINLINE - i; j++) {
                //adding the blocks to the screen
                Block block = new Block(new Point(Constants.RIGHTBLOCKUPPERLEFTX - j * Constants.BLOCKWIDTH,
                        Constants.FIRSTROWY + i * Constants.BLOCKHEIGHT), Constants.BLOCKWIDTH,
                        Constants.BLOCKHEIGHT, colors[i]);
                remainingBlocks.increase(1);

                //create a hit.BlockRemover object that holds a reference to the counter
                BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
                //register the block remover object as a listener to all the blocks
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                block.addToGame(this);
            }
        }

        this.run();
    }

    /**
     * Runs the game, starting the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        sleeper.sleepFor(Constants.SLEEPER);
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.remainingBlocks.getValue() > 0 & this.availabeBalls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.fillRectangle(Constants.INTELIZEDVALUE, Constants.INTELIZEDVALUE, Constants.SCREENWIDTH,
                    Constants.SCREENHEIGHT);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > Constants.INTELIZEDVALUE) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

        }
        if (availabeBalls.getValue() == 0) {
            gui.close();
            return;
        }

        //if no more blocks end game
        if (remainingBlocks.getValue() == 0) {
            score.increase(100);
            gui.close();
        }
    }

}
