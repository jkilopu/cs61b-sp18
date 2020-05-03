package synthesizer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer <Integer>arb = new ArrayRingBuffer<>(4);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertFalse(arb.isFull());
        arb.enqueue(4);
        assertTrue(arb.isFull());
        assertEquals((Integer) 1, arb.dequeue());
        arb.enqueue(5);
        assertEquals((Integer) 2, arb.dequeue());
        assertEquals((Integer) 3, arb.dequeue());
        assertEquals((Integer) 4, arb.dequeue());
        assertEquals((Integer) 5, arb.dequeue());
        assertTrue(arb.isEmpty());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
