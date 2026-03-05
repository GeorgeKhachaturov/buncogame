/**
 * Name: George Khachaturov
 * CSC 202
 * Project - Player class
 *
 * Description: A class to model a player in our simplified Bunco game.
 *
*/


public class BuncoPlayer {
    /**
     * instance variables for player's name, current round score, current game score and rounds won in total.
     */
    private final String name;
    private int currentRoundScore = 0;
    private int currentGameScore = 0;
    private int roundsWon = 0;
    /**
     * The name used for the human player's opponent
     */
    public static final String DEFAULT_NAME = "Computer";

    /**
     * Creates a player with the default name
     */
    public BuncoPlayer() {
        this.name = DEFAULT_NAME;
    }

    /**
     * Creates a player with the name provided
     *
     * @param name - the name of this player
     */
    public BuncoPlayer(String name) {
        this.name = name;
    }


    /**
     * Provides access to this player's name
     *
     * @return this player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Provides access to this player's score for the current round
     *
     * @return this player's score for the current round
     */
    public int getRoundScore() {
        return this.currentRoundScore;
    }

    /**
     * Provides access to this player's current game score
     *
     * @return this player's current game score
     */
    public int getGameScore() {
        return this.currentGameScore;
    }

    /**
     * Provides access to this player's current number of rounds won
     *
     * @return the number of rounds won by this player, at present
     */
    public int getNumRoundsWon() {
        return this.roundsWon;
    }

    /**
     * Adds the points provided to this player's score for the current round
     *
     * @param points - the points to be added to this player's score for the current round
     */
    public void addPointsToRoundScore(int points) {
        this.currentRoundScore += points;
    }

    /**
     * Increases the number of rounds this player won by 1
     */
    public void increaseNumRoundsWon() {
        this.roundsWon++;
    }

    /**
     * Adjusts this player's current round score and game score at the end of a round
     */
    public void endRound() {
        this.currentGameScore += getRoundScore();
        this.currentRoundScore = 0;
    }

    /**
     * Provides a string representation of this player
     *
     * @return a string representation of this player
     */
    public String toString() {
        return "BuncoPlayer " + "[name = " + getName() + ", round score = " + getRoundScore() +
                ", rounds won = " + getNumRoundsWon() + ", game score = " + getGameScore() + "]";
    }

}
