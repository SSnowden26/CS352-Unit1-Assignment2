import java.util.ArrayList;

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

		// Steps 3 and 4
		// Play a round
		// For each Contestant, flip a coin, then move the Contestant to
		// one end of the ArrayList or the other.


		// Steps 5 - 7
		// Repeat Steps 3 and 4 many times to gather data.
		// You will need to modify the Contestant class so each Contestant
		// will keep track of its individual statistics. Be smart about how you record its
		// position at the end of each round.
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

}
