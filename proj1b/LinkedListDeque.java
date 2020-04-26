public class LinkedListDeque<T> implements Deque<T> {

    private int size;
    private TNode sentinel; // first == sentinel.next, last == sentinel.prev

    /**
     * Create an empty deque.
     */
    public LinkedListDeque() {
        sentinel = new TNode();
        size = 0;
    }

    /**
     * The node of Double-linked-list.
     */
    private class TNode {
        T item;
        TNode prev;
        TNode next;

        /**
         * Create a node that is circular.
         */
        TNode() { // item is automatically assigned null when instantiate.
            prev = this;
            next = this;
        }

        /**
         * Return the item at the ith position in this.
         */
        private T getRecursive(int index) {
            if (index == 0) {
                return this.item;
            }
            return this.next.getRecursive(index - 1);
        }
    }

    /**
     * Add a node at first.
     */
    @Override
    public void addFirst(T x) {
        TNode p = new TNode();
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
    @Override
    public void addLast(T x) {
        TNode p = new TNode();
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
    @Override
    public void printDeque() {
        TNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Remove the first node and return its value.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {    // avoid negative number
            return null;
        }
        T x = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return x;
    }

    /**
     * Remove the last node and return its value.
     */
    @Override
    public T removeLast() {
        if (size == 0) {    // avoid negative number
            return null;
        }
        T x = sentinel.prev.item;
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
    @Override
    public T get(int index) {
        TNode p = sentinel.next;
        for (int i = 0; i < index && p != sentinel; i++) {
            p = p.next;
        }
        return p.item;
    }

    /**
     * Return the ith items recursively.
     */
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return this.sentinel.next.getRecursive(index);
    }

    /**
     * Return true if the deque is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the size of the deque.
     */
    @Override
    public int size() {
        return size;
    }
}
