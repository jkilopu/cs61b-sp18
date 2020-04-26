public interface Deque<T> {

    void addFirst(T item);
    void addLast(T item);
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
    boolean isEmpty();
    int size();
}
