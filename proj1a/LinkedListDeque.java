/**
 * Deque implemented with double-linked-circular-list.
 * @author jkilopu
 */
public class LinkedListDeque<Any> {

    private int size;
    private AnyNode sentinel; // first == sentinel.next, last == sentinel.prev

    /**
     * Create an empty deque.
     */
    public LinkedListDeque() {
        sentinel = new AnyNode();
        size = 0;
    }
    /**
     * Create a copy of another deque.
     */
    public LinkedListDeque(LinkedListDeque other) {
        AnyNode p = other.sentinel.next;
        sentinel = new AnyNode();
        size = 0;
        while (p != other.sentinel) {
            addLast(p.item);
            p = p.next;
        }
    }

    /**
     * The node of Double-linked-list.
     */
    private class AnyNode {
        Any item;
        AnyNode prev;
        AnyNode next;

        /**
         * Create a node that is circular.
         */
        AnyNode() { // item is automatically assigned null when instantiate.
            prev = this;
            next = this;
        }

        /**
         * Return the item at the ith position in this.
         */
        private Any getRecursive(int index) {
            if (index == 0) {
                return this.item;
            }
            return this.next.getRecursive(index - 1);
        }
    }

    /**
     * Add a node at first.
     */
    public void addFirst(Any x) {
        AnyNode p = new AnyNode();
        p.item = x;
        p.prev = sentinel;
        p.next = sentinel.next;
        sentinel.next.prev = p;
        sentinel.next = p;
        size++;
    }

    /**
     * Add a node at last.
     */
    public void addLast(Any x) {
        AnyNode p = new AnyNode();
        p.item = x;
        p.next = sentinel;
        p.prev = sentinel.prev;
        sentinel.prev.next = p;
        sentinel.prev = p;
        size++;
    }

    /**
     * Print all the items in deque from first to last
     */
    public void printDeque() {
        AnyNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Remove the first node and return its value.
     */
    public Any removeFirst() {
        Any x = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (x != null) {  // avoid negative number
            size--;
        }
        return x;
    }

    /**
     * Remove the last node and return its value.
     */
    public Any removeLast() {
        Any x = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (x != null) {
            size--;
        }
        return x;
    }

    /**
     * Return the ith items
     */
    public Any get(int index) {
        AnyNode p = sentinel.next;
        for (int i = 0; i < index && p != sentinel; i++) {
            p = p.next;
        }
        return p.item;
    }

    /**
     * Return the ith items recursively.
     */
    public Any getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return this.sentinel.next.getRecursive(index);
    }

    /**
     * Return true if the deque is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the size of the deque.
     */
    public int size() {
        return size;
    }
}
