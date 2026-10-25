/* Contestant.java
 * Author: Name and Student ID
 * Date: October 25, 2026
 *
 * Represents one Contestant in the line-reordering simulation described in
 * Unit 1 Assignment 2. A Contestant IS-A Person, plus whatever data the
 * contest itself needs to track (added in later steps).
 */
import java.util.concurrent.ThreadLocalRandom;

public class Contestant extends Person {

	// Step 1: constructor so the driver can create Contestants and the
	// program will compile and run.
	public Contestant(String name, int age) {
		super(name, age);
	}

	/* Step 3: simulates flipping a coin to decide whether a Contestant
	 * answered their question correctly. Returns true ("correct") about
	 * half the time and false ("incorrect") about half the time.
	 */
	public static boolean flipCoin() {
		return ThreadLocalRandom.current().nextBoolean();
	}
}
