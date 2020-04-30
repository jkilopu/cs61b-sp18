import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    /**
     * Test for methods in StudentArrayDeque.
     */
    public void testStudentArrayDeque() { // 第一个问题:泛型究竟要怎么写?
        while(true) {
            StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
            ArrayDequeSolution rad = new ArrayDequeSolution<>();
            int times = StdRandom.uniform(10);
            String message = randomAdd(sad, rad, times);
            assertRandomRemove(sad, rad, times, message);
        }
    }

    /**
     * Randomly add random values to both deque.
     * Return a sequence of method in String.
     */
    public static String randomAdd (StudentArrayDeque sad, ArrayDequeSolution rad, int times) {
        double numberBetweenZeroAndOne;
        int value;
        String message = "";

        for (int i = 0; i < times; i++) {
            numberBetweenZeroAndOne = StdRandom.uniform();
            value = StdRandom.uniform(10);
            if (numberBetweenZeroAndOne < 0.5) {
                sad.addFirst(value);
                rad.addFirst(value);
                message += "addFirst(" + value + ")\n";
            }
            else {
                sad.addLast(value);
                rad.addLast(value);
                message += "addLast(" + value + ")\n";
            }
        }

        return message;
    }

    /**
     * Randomly remove and test if the remove values equal.
     */
    public static void assertRandomRemove(StudentArrayDeque sad, ArrayDequeSolution rad, int times, String message) {
        Integer X, exp;
        double numberBetweenZeroAndOne;

        for (int i = 0; i < times; i++) {
            numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                X = (Integer) sad.removeFirst();
                exp = (Integer) rad.removeFirst();
                message += "removeFirst(): " + X + "\n";
            }
            else {
                X = (Integer) sad.removeLast();
                exp = (Integer) rad.removeLast();
                message += "removeLast(): " + X + "\n";
            }
            assertEquals(message, exp ,X);
        }
    }
}
