public class OffByOne implements CharacterComparator {
    /**
     * Return true if x differs y by 1
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
