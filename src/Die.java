/**
 * Name: George Khachaturov
 * CSC 202
 * Project - DiceCup class
 * Die class represents one standard 6 sides die which can be rolled
 * to generate a random number 1-6 inclusive.
 */
import java.util.Random;

class Die {
	private static Random randGen = new Random(); // used to generate random numbers
	private static final int DEFAULT_NUM_SIDES = 6; // the number of sides for the die

	private int value; // the current value of the die

	/**
	 * Sets the seed for the random number generator which makes the sequence of number predictable
	 * @param seed - the number that will create a predictable sequence
	 */
	public static void setSeed(long seed) {
		randGen = new Random(seed);
	}
	
	/**
	 * Creates a standard 6-sided die
	 */
	public Die() {
		roll();
	}
	
	/**
	 * Rolls the die
	 */
	public void roll() {
		value = randGen.nextInt(1, DEFAULT_NUM_SIDES + 1);
	}
	
	/**
	 * Accesses the current value of the die
	 * @return the current value of the die
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Provides a String representation of the die
	 * @return a String representation of the die
	 */
	public String toString() {
		return "Die [value = " + value + "]";
	}
}
