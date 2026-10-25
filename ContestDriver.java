import java.util.ArrayList;
import java.util.stream.Collectors;

public class ContestDriver {

	public static void main(String[] args) {
		System.out.println("Starting Simulation");

		// Step -1 Compile the program as is to be sure it runs

		// Step 0 - creates a single Contestant and prints its attributes,
		// just to show the Contestant class compiles and runs correctly
		Contestant ac = new Contestant("Billy", 22);
		System.out.println(ac);

		// Step 2 - build a line of Contestants with distinct names
		ArrayList<Contestant> line = makeContestants(10);
		System.out.println("Created " + line.size() + " Contestants:");
		for (Contestant c : line) {
			System.out.println(c);
		}

		// Steps 3 and 4 - play a round: for each Contestant, flip a coin,
		// then move the Contestant to the front (correct) or back
		// (incorrect) of the line.
		//
		// verifyAlgorithm() checks playRound()/applyAnswer() against the
		// fixed a-b-c-d-e example from the assignment handout (not random,
		// so it is repeatable), and oneRoundDemo() shows a real round using
		// actual coin flips.
		verifyAlgorithm();
		oneRoundDemo();

		// Step 5 - repeat Steps 3-4 for many rounds, recording each
		// Contestant's position at the end of every round, then check
		// that no Contestant is consistently stuck at one end of the line.
		fairnessCheck(8, 12);

		// Steps 6-7 added below (timing)
		System.out.println("Finished");
	}

	/* Builds a line of n Contestants, each with a distinct name
	 * ("Contestant1", "Contestant2", ...). n can range from 5 up to
	 * 1,000,000, so this only does O(n) work and does not rely on
	 * anything that would get slower as n grows (e.g. checking
	 * past names for duplicates).
	 */
	private static ArrayList<Contestant> makeContestants(int n) {
		ArrayList<Contestant> line = new ArrayList<>(n);
		for (int i = 1; i <= n; i++) {
			line.add(new Contestant("Contestant" + i, 18 + (i % 50)));
		}
		return line;
	}

	// Overload used by the demos below to build a line of Contestants
	// with specific, easy-to-read names (e.g. "a", "b", "c", ...).
	private static ArrayList<Contestant> makeContestants(String... names) {
		ArrayList<Contestant> line = new ArrayList<>();
		for (String name : names) {
			line.add(new Contestant(name, 20));
		}
		return line;
	}

	/* Moves a single Contestant to the front of the line if they answered
	 * correctly, or to the back if they answered incorrectly. c must
	 * already be present in line.
	 */
	private static void applyAnswer(ArrayList<Contestant> line, Contestant c, boolean correct) {
		line.remove(c);
		if (correct) {
			line.add(0, c);
		} else {
			line.add(c);
		}
	}

	/* Plays one round: every Contestant who was in the line when the round
	 * started answers a question, in the order they occupied the line at
	 * that moment. A correct answer (per Contestant.flipCoin()) sends them
	 * to the front of the line; an incorrect answer sends them to the
	 * back. Contestants are processed in their starting order even though
	 * the line itself is being rearranged underneath them as the round
	 * progresses.
	 */
	private static void playRound(ArrayList<Contestant> line) {
		ArrayList<Contestant> order = new ArrayList<>(line);
		for (Contestant c : order) {
			applyAnswer(line, c, Contestant.flipCoin());
		}
	}

	private static String namesOf(ArrayList<Contestant> line) {
		return line.stream().map(Contestant::getName).collect(Collectors.joining(" "));
	}

	/* Reproduces the worked example from the assignment handout using
	 * fixed (non-random) correct/incorrect answers, so the result is
	 * repeatable: a wrong, b right, c right, d wrong, e right should
	 * leave the line as "e c b a d".
	 */
	private static void verifyAlgorithm() {
		System.out.println("\n--- Verifying round algorithm against handout example ---");
		ArrayList<Contestant> line = makeContestants("a", "b", "c", "d", "e");
		boolean[] correctAnswers = { false, true, true, false, true };
		ArrayList<Contestant> order = new ArrayList<>(line);
		for (int i = 0; i < order.size(); i++) {
			applyAnswer(line, order.get(i), correctAnswers[i]);
		}
		String result = namesOf(line);
		System.out.println("Result: " + result);
		System.out.println(result.equals("e c b a d")
				? "PASS - matches handout example"
				: "FAIL - does not match handout example");
	}

	// Plays one real round (using actual coin flips) on a 5-Contestant
	// line and prints the line before and after.
	private static void oneRoundDemo() {
		System.out.println("\n--- One round demo (random coin flips) ---");
		ArrayList<Contestant> line = makeContestants("a", "b", "c", "d", "e");
		System.out.println("Before: " + namesOf(line));
		playRound(line);
		System.out.println("After:  " + namesOf(line));
	}

	// Step 5: records every Contestant's current index in the line.
	private static void recordPositions(ArrayList<Contestant> line) {
		for (int i = 0; i < line.size(); i++) {
			line.get(i).recordPosition(i);
		}
	}

	/* Step 5: plays the given number of rounds with n Contestants,
	 * recording each Contestant's position after every round, then prints
	 * each Contestant's average position. If the algorithm is fair, these
	 * averages should all land near the middle of the line (n - 1) / 2
	 * rather than clustering any one Contestant near the front or back.
	 */
	private static void fairnessCheck(int n, int rounds) {
		System.out.println("\n--- Fairness check: " + n + " Contestants, " + rounds + " rounds ---");
		ArrayList<Contestant> line = makeContestants(n);
		for (int r = 0; r < rounds; r++) {
			playRound(line);
			recordPositions(line);
		}
		for (Contestant c : line) {
			System.out.printf("%-14s avg position = %.2f (over %d rounds)%n",
					c.getName(), c.getAveragePosition(), c.getRoundsPlayed());
		}
	}

}
