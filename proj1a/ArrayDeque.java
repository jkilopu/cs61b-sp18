public class ArrayDeque<T> {
    private static final int FACTOR = 2;
    private static final int PROPORTION = 4;
    private int capacity;
    private int first;
    private int last;
    private int size;
    T[] items;    // (first, last)

    /**
     * Create an empty deque.
     */
    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        first = 0;
        last = 1;
        size = 0;
    }

    /**
     * Adjust the position of first or last.
     */
    private int succ(int pos) {
        if (pos > capacity - 1) {
            return 0;
        }
        if (pos < 0) {
            return capacity - 1;
        }
        return pos;
    }

    /**
     * Resize the deque to cap size.
     */
    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        for (int i = succ(first + 1), j = 1; i != last || (capacity == size && j == 1);
             i = succ(++i), j++) {
            a[j] = items[i];
        }
        first = 0;
        last = size + 1;
        items = a;
        capacity = cap;
    }

    /**
     * Add a node at first.
     */
    public void addFirst(T item) {
        items[first] = item;
        first = succ(--first);
        size++;
        if (size == capacity) {
            resize(capacity * FACTOR);
        }
    }

    /**
     * Add a node at last.
     */
    public void addLast(T item) {
        items[last] = item;
        last = succ(++last);
        size++;
        if (size == capacity) {
            resize(capacity * FACTOR);
        }
    }

    /**
     * Print all the items in deque (first, last).
     */
    public void printDeque() {
        for (int i = succ(first + 1); i != last; i = succ(++i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /**
     * Remove the first node and return its value.
     */
    public T removeFirst() {
        if (size == 0)
            return null;
        first = succ(++first);
        T ret = items[first];
        items[first] = null;
        size--;
        if (capacity > 16 && (double) capacity / size > PROPORTION) {
            resize(capacity / (PROPORTION / 2));
        }
        return ret;
    }

    /**
     * Remove the last node and return its value.
     */
    public T removeLast() {
        if (size == 0)
            return null;
        last = succ(--last);
        T ret = items[last];
        items[last] = null;
        size--;
        if (capacity > 16 && (double) capacity / size > PROPORTION) {
            resize(capacity / (PROPORTION / 2));
        }
        return ret;
    }

    /**
     * Return the ith items
     */
    public T get(int index) {
        int j = succ(first + 1);
        for (int i = 0; i < index; i++) {
            if (i > size) {
                return null;
            }
            j = succ(++j);
        }
        return items[j];
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
