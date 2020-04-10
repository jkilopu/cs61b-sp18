public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
    }

	/** Return the size of the list using... recursion! */
	public int size() {
        if(this.rest == null){
            return 1;
        }
		return this.rest.size() + 1;
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while(p != null){
            p = p.rest;
            totalSize++;
        }
		return totalSize;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
        IntList p = this;
        for(int j = 0; j < i; j++){
            p = p.rest;
        }
		return p.first;
	}

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

        System.out.println(L.iterativeSize());
        System.out.println(L.get(0));
	}
}