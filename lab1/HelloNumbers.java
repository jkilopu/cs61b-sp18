public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0, s = 0;
        while (x < 10) {
            System.out.print(s + " ");
            x = x + 1;
            s += x;
        }
        System.out.println();
    }
}