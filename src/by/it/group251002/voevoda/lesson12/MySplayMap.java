package by.it.group251002.voevoda.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {

    private static class Node {
        public Integer key;
        public String value;
        public Node p, left, right;

        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            p = left = right = null;
        }
    }

    public MySplayMap() {
        root = null;
        size = 0;
    }

    Node root;
    int size;

    private enum Situation {
        Splayed,
        Zig,
        ZigZig,
        ZigZag,
    }

    private Situation defineSituation(Node x) {
        if (x == null || x.p == null) {
            return Situation.Splayed;
        }

        if (x.p.p == null) {
            return Situation.Zig;
        }

        if (x == x.p.left && x.p == x.p.p.left || x == x.p.right && x.p == x.p.p.right) {
            return Situation.ZigZig;
        }

        return Situation.ZigZag;
    }

    @Override
    public String toString() {

        if (isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder("{");
        toStringUtil(sb, root);
        return sb.delete(sb.length() - 2, sb.length()).append("}").toString();
    }

    private void toStringUtil(StringBuilder sb, Node node) {
        if (node != null) {
            toStringUtil(sb, node.left);
            sb.append(String.format("%s=%s, ", node.key, node.value));
            toStringUtil(sb, node.right);
        }
    }

    @Override
    public String put(Integer key, String value) {
        Node closestNode = findNode(root, key);

        if (closestNode != null && key.equals(closestNode.key)) {
            String result = closestNode.value;
            closestNode.value = value;
            splay(closestNode);
            return result;
        }

        Node x = bstInsert(closestNode, key, value);
        splay(x);
        ++size;
        return null;
    }

    @Override
    public String remove(Object key) {
        Node z = findNode(root, (Integer) key);
        if (z == null || !z.key.equals(key)) {
            return null;
        }

        String result = z.value;
        splay(z);
        bstDelete(z);
        --size;
        return result;
    }

    @Override
    public String get(Object key) {
        Node x = findNode(root, (Integer) key);
        splay(x);
        return x != null && x.key.equals(key) ? x.value : null;
    }

    @Override
    public boolean containsKey(Object key) {
        Node x = findNode(root, (Integer) key);
        splay(x);
        return x != null && x.key.equals(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        SortedMap<Integer, String> hm = new MySplayMap();
        constructHeadMap(hm, toKey, root);
        return hm;
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        SortedMap<Integer, String> tm = new MySplayMap();
        constructTailMap(tm, fromKey, root);
        return tm;
    }

    @Override
    public Integer firstKey() {
        if (isEmpty()) {
            return null;
        }

        Node ptr = root;
        while (ptr.left != null) {
            ptr = ptr.left;
        }
        return ptr.key;
    }

    @Override
    public Integer lastKey() {
        if (isEmpty()) {
            return null;
        }

        Node ptr = root;
        while (ptr.right != null) {
            ptr = ptr.right;
        }
        return ptr.key;
    }

    @Override
    public Integer lowerKey(Integer key) {
        SortedMap<Integer, String> hm = headMap(key);
        return hm.lastKey();
    }

    @Override
    public Integer floorKey(Integer key) {
        Node x = findNode(root, key);
        if (x != null && x.key.equals(key)) {
            return x.key;
        }
        return lowerKey(key);
    }

    @Override
    public Integer ceilingKey(Integer key) {
        SortedMap<Integer, String> tm = tailMap(key);
        return tm.firstKey();
    }

    @Override
    public Integer higherKey(Integer key) {
        Node ptr = root;
        Node higher = null;
        while (ptr != null) {
            if (key < ptr.key) {
                higher = ptr;
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }
        if (higher == null) {
            return null;
        }
        splay(higher);

        return higher.key;
    }

    //////////////////////////////////////////////
    //////////////////////////////////////////////
    ////////                             /////////
    ////////            UNUSED           /////////
    ////////                             /////////
    //////////////////////////////////////////////
    //////////////////////////////////////////////

    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> firstEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> lastEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> pollFirstEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> descendingMap() {
        return null;
    }

    @Override
    public NavigableSet<Integer> navigableKeySet() {
        return null;
    }

    @Override
    public NavigableSet<Integer> descendingKeySet() {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> subMap(Integer fromKey, boolean fromInclusive, Integer toKey, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> headMap(Integer toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey, boolean inclusive) {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public Set<Integer> keySet() {
        return null;
    }

    @Override
    public Collection<String> values() {
        return null;
    }

    @Override
    public Set<Entry<Integer, String>> entrySet() {
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    //////////////////////////////////////////////
    //////////////////////////////////////////////
    ////////                             /////////
    ////////       Support methods       /////////
    ////////                             /////////
    //////////////////////////////////////////////
    //////////////////////////////////////////////

    Node findNode(Node ptr, Integer key) {
        if (ptr == null) {
            return null;
        }

        if (key == ptr.key) {
            return ptr;
        } else if (key < ptr.key && ptr.left != null) {
            return findNode(ptr.left, key);
        } else if (key > ptr.key && ptr.right != null) {
            return findNode(ptr.right, key);
        }

        return ptr;
    }

    private Node findMinNode(Node ptr) {
        if (ptr == null) {
            return null;
        }
        while (ptr.left != null) {
            ptr = ptr.left;
        }
        return ptr;
    }

    private Node bstInsert(Integer key, String value) {
        Node closestNode = findNode(root, key);
        return bstInsert(closestNode, key, value);
    }

    private Node bstInsert(Node closestNode, Integer key, String value) {
        Node insertedNode;

        if (closestNode == null) {
            insertedNode = new Node(key, value);
            root = insertedNode;
        } else if (key == closestNode.key) {
            closestNode.value = value;
            insertedNode = closestNode;
            return insertedNode;
        } else if (key < closestNode.key) {
            insertedNode = new Node(key, value);
            closestNode.left = insertedNode;
            insertedNode.p = closestNode;
        } else {
            insertedNode = new Node(key, value);
            closestNode.right = insertedNode;
            insertedNode.p = closestNode;
        }

        return insertedNode;
    }

    private void bstDelete(Node z) {
        if (z.left == null) {
            transplant(z, z.right);
        } else if (z.right == null) {
            transplant(z, z.left);
        } else {
            Node y = findMinNode(z.right);
            if (y.p != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.p = y;
        }
    }

    private void transplant(Node u, Node v) {
        if (u.p == null) {
            root = v;
        } else if (u.p.left == u) {
            u.p.left = v;
        } else {
            u.p.right = v;
        }
        if (v != null) {
            v.p = u.p;
        }
    }

    private void leftRotate(Node x) {
        if (x == null || x.right == null) {
            return;
        }

        Node y = x.right;
        Node beta = y.left;

        x.right = beta;
        if (beta != null) {
            beta.p = x;
        }

        y.left = x;

        Node z = x.p;
        if (z == null) {
            root = y;
        } else if (z.left == x) {
            z.left = y;
        } else {
            z.right = y;
        }
        y.p = z;
        x.p = y;
    }

    private void rightRotate(Node y) {
        if (y == null || y.left == null) {
            return;
        }

        Node x = y.left;
        Node beta = x.right;

        y.left = beta;
        if (beta != null) {
            beta.p = y;
        }

        x.right = y;

        Node z = y.p;
        if (z == null) {
            root = x;
        } else if (z.left == y) {
            z.left = x;
        } else {
            z.right = x;
        }
        x.p = z;
        y.p = x;
    }

    private void zig(Node x) {
        if (x == null || x.p == null || x.p.p != null) {
            return;
        }

        if (x == x.p.left) {
            rightRotate(x.p);
        } else {
            leftRotate(x.p);
        }
    }

    private void zigZig(Node x) {
        if (x == null || x.p == null || x.p.p == null) {
            return;
        }

        if (x == x.p.left && x.p == x.p.p.left) {
            rightRotate(x.p.p);
            rightRotate(x.p);
        } else if (x == x.p.right && x.p == x.p.p.right) {
            leftRotate(x.p.p);
            leftRotate(x.p);
        }
    }

    private void zigZag(Node x) {
        if (x == null || x.p == null || x.p.p == null) {
            return;
        }

        if (x == x.p.left && x.p == x.p.p.right) {
            rightRotate(x.p);
            leftRotate(x.p);
        } else if (x == x.p.right && x.p == x.p.p.left) {
            leftRotate(x.p);
            rightRotate(x.p);
        }
    }

    private void splay(Node x) {
        for (Situation s = defineSituation(x); s != Situation.Splayed; s = defineSituation(x)) {
            switch (s) {
                case Zig -> zig(x);
                case ZigZig -> zigZig(x);
                case ZigZag -> zigZag(x);
            }
        }
    }

    private void constructHeadMap(SortedMap<Integer, String> hm, Integer toKey, Node ptr) {
        if (ptr == null) {
            return;
        }
        constructHeadMap(hm, toKey, ptr.left);
        if (ptr.key < toKey) {
            hm.put(ptr.key, ptr.value);
            constructHeadMap(hm, toKey, ptr.right);
        }
    }

    private void constructTailMap(SortedMap<Integer, String> tm, Integer fromKey, Node ptr) {
        if (ptr == null) {
            return;
        }
        constructTailMap(tm, fromKey, ptr.right);
        if (ptr.key >= fromKey) {
            tm.put(ptr.key, ptr.value);
            constructTailMap(tm, fromKey, ptr.left);
        }
    }

}
