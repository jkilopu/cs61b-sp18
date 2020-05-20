package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) > 0) {
            return getHelper(key, p.right);
        }
        if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        }
        return p.value;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
        }
        else if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        }
        else {
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /**
     * Return a Set view of keys(added orderly) in tree p.
     */
    private void keySetHelper(Set<K> set, Node p) {
        if (p == null) {
            return;
        }
        keySetHelper(set, p.left);
        set.add(p.key);
        keySetHelper(set, p.right);
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set set = new TreeSet();
        keySetHelper(set, root);
        return set;
    }

    /**
     * Find and return an array of node that contains node to delete and pre node.
     * @param value If not null, must be compared.
     * @param p The root of tree to be searched.
     * @return An array of node that contains deleted node and pre node, null on failed find.
     * None recursively.
     */
    private List find(K key, V value, Node p) {
        if (key == null) {
            return null;
        }
        Node prev = null;
        while (p != null && (key.compareTo(p.key) != 0 || (value != null && !value.equals(p.value)))) {
            prev = p;
            if (key.compareTo(p.key) > 0) {
                p = p.right;
            }
            else {
                p = p.left;
            }
        }
        List<Node> list = new ArrayList(2);
        list.add(0 ,prev);
        list.add(1, p);
        return list;
    }

    /**
     * Return the node which have the smallest key on tree p.
     */
    private Node min(Node p) {
        if (p == null) {
            return null;
        }
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    /**
     * Return true if p have two kids.
     */
    private boolean hasTwoKids(Node p) {
        return (p != null && p.left != null && p.right != null);
    }

    /**
     * @param p Removed node.
     * @param pNew New node which parent is prev.
     * @param prev Parent of pNew.
     */
    private void reconnect(Node p, Node pNew, Node prev) {
        if (prev == null) {
            root = pNew;
        }
        else {
            if (prev.left == p) {
                prev.left = pNew;
            }
            else {
                prev.right = pNew;
            }
        }
    }

    private void removeLessThanOneKidNode(Node p, Node prev) {
        if (p == null) {
            return;
        }
        Node pNew;
        if (p.left != null) {
            pNew = p.left;
        }
        else {
            pNew = p.right;
        }
        reconnect(p, pNew, prev);
        size--;
    }

    /**
     * @source Algorithms 4th
     * @return The node to be reconnect.
     */
    private Node deleteMin(Node p) {
        if (p.left == null) {
            return p.right;
        }
        p.left = deleteMin(p.left);
        return p;
    }

    private void removeTwoKidsNode(Node p, Node prev) {
        if (p == null) {
            return;
        }
        Node succ = min(p.right);
        succ.right = deleteMin(p.right);
        succ.left = p.left;
        reconnect(p, succ, prev);
        size--;
    }

    /**
     * Remove node p and the tree won't be destroyed.
     * @param p If p is null, we do noting.
     */
    private void removeNode(Node p, Node prev) {
        if (hasTwoKids(p)) {
            removeTwoKidsNode(p, prev);
        }
        else {
            removeLessThanOneKidNode(p, prev);
        }
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        List list = find(key, null, root);
        Node p = (Node) list.get(1), prev = (Node) list.get(0);
        V removeValue;
        if (p == null) {
            return null;
        }
        removeValue = p.value;
        removeNode(p, prev);
        return removeValue;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key,  V value) {
        List list = find(key, value, root);
        Node p = (Node) list.get(1), prev = (Node) list.get(0);
        V removeValue;
        if (p == null) {
            return null;
        }
        removeValue = p.value;
        removeNode(p, prev);
        return removeValue;
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> set = keySet();
        return set.iterator();
    }
}
