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

	// Step 5: running total of the positions this Contestant has occupied
	// at the end of each round, and how many rounds that total covers.
	// Storing a running sum/count instead of a full history lets the
	// average position be computed in O(1) no matter how many rounds are
	// played (important once rounds reach the thousands or tens of
	// thousands in Steps 6-7).
	private long positionSum;
	private int roundsPlayed;

	// Step 1: constructor so the driver can create Contestants and the
	// program will compile and run.
	public Contestant(String name, int age) {
		super(name, age);
		this.positionSum = 0;
		this.roundsPlayed = 0;
	}

	/* Step 3: simulates flipping a coin to decide whether a Contestant
	 * answered their question correctly. Returns true ("correct") about
	 * half the time and false ("incorrect") about half the time.
	 */
	public static boolean flipCoin() {
		return ThreadLocalRandom.current().nextBoolean();
	}

	/* Step 5: called once at the end of each round with this Contestant's
	 * current index in the line (0 = front), so getAveragePosition() can
	 * later report where this Contestant has tended to end up.
	 */
	public void recordPosition(int position) {
		positionSum += position;
		roundsPlayed++;
	}

	// Step 5: average position over every round recorded so far, or 0.0
	// if no rounds have been recorded yet.
	public double getAveragePosition() {
		if (roundsPlayed == 0) {
			return 0.0;
		}
		return (double) positionSum / roundsPlayed;
	}

	public int getRoundsPlayed() {
		return roundsPlayed;
	}

	@Override
	public String toString() {
		return "Contestant [name=" + getName() + ", age=" + getAge()
				+ ", avgPosition=" + String.format("%.2f", getAveragePosition())
				+ ", roundsPlayed=" + roundsPlayed + "]";
	}
}
