/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds a few items, then removes all of item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		lld1.addFirst(11);
		lld1.addFirst(13);
		lld1.addFirst(15);
		lld1.printDeque();
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		lld1.printDeque();
		lld1.removeFirst();
		lld1.printDeque();
		lld1.removeFirst();
		lld1.printDeque();
		lld1.removeFirst();
		lld1.printDeque();
		lld1.removeFirst();
		lld1.printDeque();
		lld1.removeFirst();
		lld1.printDeque();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);

	}

	public static void addConstrictor2Test(){
		System.out.println("Running the second constructor test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		lld1.addFirst(1);
		lld1.addFirst(2);
		lld1.addFirst(3);
		lld1.addFirst(4);

		LinkedListDeque<Integer> lld2 = new LinkedListDeque<Integer>(lld1);

		boolean passed = checkSize(4, lld2.size());
		lld2.printDeque();
		printTestStatus(passed);
	}

	public static void addGetRecursiveAndGetTest(){
		System.out.println("Running the getRecursive and get test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		lld1.addLast(432);
		lld1.addLast(123);
		lld1.addLast(334);
		lld1.addLast(981);

		boolean passed = checkSize(334, lld1.getRecursive(2)); // Not check"size" hh
		passed = checkSize(981, lld1.getRecursive(3)) && passed;
		printTestStatus(passed);
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addConstrictor2Test();
		addGetRecursiveAndGetTest();
		addIsEmptySizeTest();
		addRemoveTest();
	}
} 