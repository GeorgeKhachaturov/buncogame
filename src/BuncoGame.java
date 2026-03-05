/**
 * Name: George Khachaturov
 * CSC 202
 * Project BuncoGame class
 *
 * Description: This program plays a simplified Bunco game between
 *     a human player and the computer. Bunco is played with three
 *     standard 6-sided dice. In each round, the players alternate
 *     turns rolling the dice trying to roll the round number. If
 *     the player rolls one or two of the round number, the player earns
 *     one or two points respectively. If the player rolls three of
 *     the round number, then the player earns 21 points (a bunco).
 *     If the player rolls three of any other number, then the player
 *     earns 5 points (a mini-bunco). If the player does not roll
 *     any of the round number, the player's turn end and the opponent
 *     begins to roll. When a player's score in a round is 21 or greater,
 *     the round ends. After six rounds, the winner is the player who
 *     won the most rounds ties broken by the total number of points.
 *
*/

import java.util.*;

public class BuncoGame {

    /**
     * rounds are numbered 1 to a maximum of MAX_ROUND
     */
    public static final int MAX_ROUND = 6;

    /**
     * a player scoring this score or greater ends the round
     */
    public static final int ROUND_GOAL = 21;

    /**
     * the number of points earned by the player rolling a bunco
     */
    public static final int BUNCO_SCORE = 21;

    /**
     *  the number of points earned by a player rolling a mini-bunco
     */
    public static final int MINI_BUNCO_SCORE = 5;

    /**
     * Determines who will start the game by rolling dice, the higher amount wins.
     *
     * @param scanner - Scanner object
     * @param diceCup - diceCup object
     * @param player - player
     * @param computer - computer
     * @return true if player starts and return false if computer starts the round 1.
     */
    public static boolean determineWhoStarts(Scanner scanner, DiceCup diceCup, BuncoPlayer player, BuncoPlayer computer) {
        System.out.println(player.getName() + " will challenge Computer.");
        System.out.println("Highest roll total will start Round 1.");

        int playerScore = 0;
        int computerScore = 0;
        String rollInput = "";
        do {
            System.out.println();
            System.out.print("Roll? ");
            rollInput = scanner.nextLine();

            diceCup.roll();
            playerScore += diceCup.getDiceSum();
            System.out.println(player.getName() + " rolls " + diceCup.toString() + " = " + diceCup.getDiceSum());

            diceCup.roll();
            computerScore += diceCup.getDiceSum();
            System.out.println(computer.getName() + " rolls " + diceCup.toString() + " = " + diceCup.getDiceSum());

        } while(rollInput.isEmpty() && playerScore == computerScore);

        if (playerScore > computerScore) {
            System.out.println(player.getName() + " will roll first in Round 1.");
            return true;
        } else {
            System.out.println(computer.getName() + " will roll first in Round 1.");
            return false;
        }

    }

    /**
     * Runs program for one particular round
     *
     * @param scanner - Scanner
     * @param diceCup - diceCup
     * @param player - player
     * @param computer - computer
     * @param round - current round number
     * @param startingPlayer - if true player starts, if false computer starts.
     */
    public static void playRound(Scanner scanner, DiceCup diceCup, BuncoPlayer player, BuncoPlayer computer, int round, boolean startingPlayer) {
        System.out.println("\n\nROUND " + round);

        boolean playerTurn = startingPlayer;

        while(player.getRoundScore() < ROUND_GOAL && computer.getRoundScore() < ROUND_GOAL) {
            if(playerTurn) {
                playersTurn(scanner, diceCup, player, round);
                System.out.println();
            } else {
                computersTurn(diceCup, computer, round);
                System.out.println();
            }
            playerTurn = !playerTurn;
        }

        if(player.getRoundScore() > computer.getRoundScore()) {
            player.increaseNumRoundsWon();
        } else if (computer.getRoundScore() > player.getRoundScore()) {
            computer.increaseNumRoundsWon();
        }

        player.endRound();
        computer.endRound();

        System.out.println("\nEND ROUND " + round);
        System.out.println(player.getName() + "--" + " rounds won: " + player.getNumRoundsWon()
                + "," + " game score: " + player.getGameScore());
        System.out.println(computer.getName() + "--" + " rounds won: " + computer.getNumRoundsWon()
                + "," + " game score: " + computer.getGameScore());
    }

    /**
     * Executes the human player's turn, rolling and scoring repeatedly.
     *
     * @param scanner - Scanner
     * @param diceCup - diceCup
     * @param player - player
     * @param round - current round number
     * @return true when the player's turn ends
     */
    public static boolean playersTurn(Scanner scanner, DiceCup diceCup, BuncoPlayer player, int round) {
        boolean stop = false;

        System.out.print("Roll?");
        String playerInput = scanner.nextLine();

        while (playerInput.isEmpty() && !stop) {
            diceCup.roll();
            System.out.println(player.getName() + " rolls " + diceCup.toString());
            int rollScore = calculateRollScore(diceCup, round);
            player.addPointsToRoundScore(rollScore);
            System.out.println("Roll score: " + rollScore + ", Round " + round + " score: " + player.getRoundScore());

            if (player.getRoundScore() >= ROUND_GOAL) {
                stop = true;
            } else if (rollScore == 0) {
                stop = true;
            } else {
                System.out.print("Roll?");
                playerInput = scanner.nextLine();
            }
        }

        return stop;
    }

    /**
     * Executes computer's turn, rolling and scoring repeatedly.
     *
     * @param diceCup - diceCup
     * @param computer - computer
     * @param round - current round number
     * @return true when the computer's turn ends.
     */
    public static boolean computersTurn(DiceCup diceCup, BuncoPlayer computer, int round) {
        boolean stop = false;

        while(!stop) {
            diceCup.roll();
            int rollScore = calculateRollScore(diceCup, round);
            computer.addPointsToRoundScore(rollScore);
            System.out.println(computer.getName() + " rolls " + diceCup.toString());
            System.out.println("Roll score: " + rollScore + ", Round " + round + " score: " + computer.getRoundScore());

            if (computer.getRoundScore() >= ROUND_GOAL) {
                stop = true;
            } else if (rollScore == 0) {
                stop = true;
            }
        }

        return stop;
    }

    /**
     * Returns true if all three dice match the current round number.
     *
     * @param diceCup - diceCup
     * @param round - current round number
     * @return - true if it's a bunco, false otherwise.
     */
    public static boolean isABunco(DiceCup diceCup, int round) {
        int valueOne = diceCup.getDieValue(1);
        int valueTwo = diceCup.getDieValue(2);
        int valueThree = diceCup.getDieValue(3);
        return valueOne == valueTwo && valueOne == valueThree && valueThree == round;
    }

    /**
     * Returns true if all three dice match, but they don't match with the current round.
     *
     * @param diceCup - diceCup
     * @param round - current round number
     * @return true if it is a mini-bunco, false otherwise.
     */
    public static boolean isAMiniBunco(DiceCup diceCup, int round) {
        int valueOne = diceCup.getDieValue(1);
        int valueTwo = diceCup.getDieValue(2);
        int valueThree = diceCup.getDieValue(3);
        return valueOne == valueTwo && valueOne == valueThree && valueThree != round;
    }

    /**
     * Calculates the roll score
     *
     * @param diceCup - diceCup
     * @param round - current round number
     * @return the score earned for the roll.
     */
    public static int calculateRollScore(DiceCup diceCup, int round) {
        int rollScore = 0;

        int valueOne = diceCup.getDieValue(1);
        int valueTwo = diceCup.getDieValue(2);
        int valueThree = diceCup.getDieValue(3);

        if (isABunco(diceCup, round)) {
            rollScore += BUNCO_SCORE;
        } else if (isAMiniBunco(diceCup, round)) {
            rollScore += MINI_BUNCO_SCORE;
        } else {
            if (valueOne == round) rollScore++;
            if (valueTwo == round) rollScore++;
            if (valueThree == round) rollScore++;
        }

        return rollScore;
    }

    /**
     * Compares final scores and prints winner
     *
     * @param player - player
     * @param computer - object
     */
    public static void printWinner(BuncoPlayer player, BuncoPlayer computer) {
        System.out.println("\n\nGAME RESULTS");
        if (player.getNumRoundsWon() > computer.getNumRoundsWon()) {
            System.out.println(player.getName() + " won the game!");
        } else if (computer.getNumRoundsWon() > player.getNumRoundsWon()) {
            System.out.println(computer.getName() + " won the game!");
        } else if (player.getGameScore() > computer.getGameScore()) {
            System.out.println(player.getName() + " won the game!");
        } else if (computer.getGameScore() > player.getGameScore()) {
            System.out.println(computer.getName() + " won the game!");
        } else {
            System.out.println("Tie game!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DiceCup diceCup = new DiceCup();

        System.out.println("Welcome to Bunco!");
        System.out.println();

        System.out.print("Enter name of player: ");
        String playerName = scanner.nextLine();

        BuncoPlayer player = new BuncoPlayer(playerName);
        BuncoPlayer computer = new BuncoPlayer();

        // Game loop will continue for 6 rounds and finally print the winner.
        boolean startingPlayer = determineWhoStarts(scanner, diceCup, player, computer);
        for (int roundCount = 1; roundCount <= MAX_ROUND ; roundCount++) {
            playRound(scanner, diceCup, player, computer, roundCount, startingPlayer);
            startingPlayer = !startingPlayer;
        }
        printWinner(player, computer);
    }

}
