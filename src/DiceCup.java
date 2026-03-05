/**
 * Name: George Khachaturov
 * CSC 202
 * Project - DiceCup class
 * 
 * Description: A class to model a cup of dice for any game rolling multiple standard 6-sided dice
 *
 */
public class DiceCup {
	/**
	 * the number of dice in the cup in no other number is specified
	 */
	public static final int DEFAULT_NUM_DICE = 3;

	private Die[] cupOfDice;   // the "cup" for the dice that will be rolled
	
	/**
	 * Creates a dice cup with the default number of dice
	 */
	public DiceCup() {
		this(DEFAULT_NUM_DICE);
	}
	
	/**
	 * Creates a dice cup with the specified number of dice
	 * @param numDice - the number of dice in the cup
	 */
	public DiceCup(int numDice) {
		this.cupOfDice = new Die[numDice];

		for (int i = 0; i < cupOfDice.length; i++) {
			cupOfDice[i] = new Die();
		}
	}

	/**
	 * Simulates rolling this dice cup
	 */
	public void roll() {
		for (int i = 0; i < cupOfDice.length; i++) {
			cupOfDice[i].roll();
		}

	}

	/**
	 * Provides access to the value of the 1st, 2nd, etc. dice depending
	 * on the value of dieNum
	 * @param dieNum -- the number of the dice whose value is desired
	 * @return the value of the desired dice
	 */
	public int getDieValue(int dieNum) {
		return cupOfDice[dieNum - 1].getValue();
	}

	/**
	 * Calculates and returns the sum of the values of the dice in this dice cup
	 * @return the sum of the values of the dice in this dice cup
	 */
	public int getDiceSum() {
		int diceSum = 0;
		for (int i = 0; i < cupOfDice.length; i++) {
			diceSum += cupOfDice[i].getValue();
		}

		return diceSum;
	}
	
	/**
	 * Provides access to the number of dice in this dice cup
	 * @return the number of dice in this dice cup
	 */
	public int getNumDice() {
		return cupOfDice.length;
	}

	/**
	 * Provides a string representation of this dice cup
	 * of the form 5, 3, 4
	 * @return a string representation of this dice cup
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < cupOfDice.length; i++) {
			if (i > 0) {
				result += ", ";
			}
			result += cupOfDice[i].getValue();
		}

		return result;

	}

}
