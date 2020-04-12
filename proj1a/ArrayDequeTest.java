public class ArrayDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkValue(int expected, int actual) {
        if (expected != actual) {
            System.out.println("function returned " + actual + ", but expected: " + expected);
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

    public static void addGetTest() {
        System.out.println("Running get test.");

        ArrayDeque<Integer> a1 = new<Integer> ArrayDeque();
        a1.addFirst(1);
        a1.addLast(2);
        a1.addLast(3);
        boolean passed  = checkValue(2, a1.get(1));
        a1.removeFirst();
        passed  = checkValue(3, a1.get(1)) && passed;
        printTestStatus(passed);
    }

    public static void addRemoveTest() {
        System.out.println("Running remove test.");

        ArrayDeque<Integer> a1 = new<Integer> ArrayDeque();
        a1.addFirst(5);
        a1.printDeque();
        a1.addFirst(10);
        a1.printDeque();
        a1.addLast(20);
        a1.printDeque();

        a1.removeLast();
        a1.printDeque();
        a1.removeFirst();
        a1.printDeque();
        a1.removeLast();
        a1.printDeque();

        boolean passed = checkEmpty(true, a1.isEmpty());
        printTestStatus(passed);
    }

    public static void addResizeTest() {
        System.out.println("Running resize test.");

        ArrayDeque<Integer> a1 = new<Integer> ArrayDeque();
        boolean passed = false;
        for (int i = 0; i < 20; i++) {
            a1.addLast(i);
            a1.printDeque();
            passed = checkValue(0, a1.get(0));
        }

        for (int i = 0; i < 19; i++) {
            a1.removeFirst();
            a1.printDeque();
            passed = checkValue(i + 1, a1.get(0)) && passed;
        }

        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");

//        addRemoveTest();
//        addGetTest();
        addResizeTest();
    }
}
