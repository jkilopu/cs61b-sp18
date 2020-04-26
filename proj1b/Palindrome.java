public class Palindrome {

    /**
     * Convert string to deque, the first Character is at first of deque.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new ArrayDeque();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /**
     * Return true if the word is palindrome.
     */
    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        Character ch1, ch2;
        do {
            ch1 = d.removeFirst();
            ch2 = d.removeLast();
            if (ch1 != ch2 && ch2 != null) { // Avoid one character status.
                return false;
            }
        } while (ch1 != null && ch2 != null);
        return true;
    }

    /**
     * Return true if the word is a palindrome according to the character comparison test.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        Character ch1, ch2; // Character and char can revert automatically
        do {
            ch1 = d.removeFirst();
            ch2 = d.removeLast();
            if (ch2 != null && !cc.equalChars(ch1, ch2)) {
                // "ch2 != null" is in the front so as to avoid null convert to char.
                return false;
            }
        } while (ch1 != null && ch2 != null);
        return true;
    }
}
