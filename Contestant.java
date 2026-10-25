/* Contestant.java
 * Author: Name and Student ID
 * Date: October 25, 2026
 *
 * Represents one Contestant in the line-reordering simulation described in
 * Unit 1 Assignment 2. A Contestant IS-A Person, plus whatever data the
 * contest itself needs to track (added in later steps).
 */
public class Contestant extends Person {

	// Step 1: constructor so the driver can create Contestants and the
	// program will compile and run.
	public Contestant(String name, int age) {
		super(name, age);
	}
}
