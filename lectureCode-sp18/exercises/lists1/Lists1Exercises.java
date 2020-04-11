public class Lists1Exercises {
    /** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) {
        IntList newL = new IntList(L.first + x, null);
        IntList p = newL;
        while(L.rest != null){
            p.rest = new IntList(L.rest.first + x, null);
            p = p.rest;
            L = L.rest;
        }
        return newL;        
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        IntList p = L;
        while(p != null){
            p.first += x;
            p = p.rest;
        }
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        System.out.println(L.size());
        System.out.println(L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
        System.out.println(L.get(2));
        System.out.println(incrList(L, 3).get(2));
        System.out.println(dincrList(L, 3).get(2));        
    }
}