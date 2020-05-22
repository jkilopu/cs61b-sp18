package hw3.hash;

import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* Add index and num to map */
        HashMap<Integer, Integer> hm = new HashMap<>();
        Integer N = oomages.size();
        for (Oomage o : oomages) {
            Integer bucketIndex = (o.hashCode() & 0x7FFFFFFF) % M;
            if (!hm.containsKey(bucketIndex)) {
                hm.put(bucketIndex, 0);
            }
            else {
                hm.put(bucketIndex, hm.get(bucketIndex) + 1);
            }
        }
        /* Examine */
        for (Integer bucketIndex : hm.keySet()) {
            if (hm.get(bucketIndex) > N / 2.5 || hm.get(bucketIndex) < N / 50.0) {
                return false;
            }
        }
        return true;
    }
}
