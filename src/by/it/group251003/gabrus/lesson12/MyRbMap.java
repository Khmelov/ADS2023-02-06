package by.it.group251003.gabrus.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {
    public MyRbMap() {
        head = null;
    }
    private MyRbMap(Node p) {
        head = p;
    }
    private Node head;
    private final static boolean RED = true;
    private final static boolean BLACK = false;
    private static class Node {
        int key;
        String value;
        Node left;
        Node right;
        int size = 1;
        boolean color = RED;
        Node (int key, String value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
    private void flipColors(Node p) {
        p.left.color = BLACK;
        p.right.color = BLACK;
        p.color = RED;
    }
    private void fixSize(Node p) {
        if (p.left == null && p.right == null) {
            p.size = 1;
        } else {
            int lSize = p.left != null ? p.left.size : 0;
            int rSize = p.right != null ? p.right.size : 0;
            p.size = lSize + rSize + 1;
        }
    }
    private Node rotateLeft(Node q) // левый поворот вокруг q
    {
        Node p = q.right;
        q.right = p.left;
        p.left = q;

        p.color = q.color;
        q.color = RED;

        fixSize(q);
        fixSize(p);

        return p;
    }
    private Node rotateRight(Node p) // правый поворот вокруг p
    {
        Node q = p.left;
        p.left = q.right;
        q.right = p;

        q.color = p.color;
        p.color = RED;

        fixSize(p);
        fixSize(q);

        return q;
    }
    private boolean isRed(Node p) {
        if (p == null) return false;
        return p.color == RED;
    }
    private Node balance(Node p) {
        fixSize(p);
        if (isRed(p.right) && !isRed(p.left)) { p = rotateLeft(p); }
        if (isRed(p.left) && isRed(p.left.left)) { p = rotateRight(p); }
        if (isRed(p.left) && isRed(p.right)) { flipColors(p); }

        return p;
    }

    private StringBuilder toStringValues(Node p, StringBuilder str) {
        if (p == null) {
            return str;
        } else {
            str = toStringValues(p.left, str);
            str.append(p.key).append("=").append(p.value).append(", ");
            str = toStringValues(p.right, str);
        }

        return str;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");

        str = toStringValues(head, str);
        if (str.charAt(str.length() - 1) == ' ')
            str.replace(str.length() - 2, str.length(), "");

        str.append("}");

        return str.toString();
    }

    private String returnResult;
    private Node insert(Node p, Integer key, String value) {
        if (p == null) {
            returnResult = null;
            return new Node(key, value);
        } else if (key == p.key) {
            returnResult = p.value;
            p.value = value;
            return p;
        }

        if (key < p.key) {
            p.left = insert(p.left, key, value);
        } else {
            p.right = insert(p.right, key, value);
        }

        if (returnResult == null)
            return balance(p);
        else
            return p;
    }

    @Override
    public String put(Integer key, String value) {
        if (key == null || value == null)
            throw new NullPointerException();

        if (head == null) {
            head = new Node(key, value);
        } else {
            head = insert(head, key, value);
        }

        return returnResult;
    }

    private Node findMin(Node p)
    {
        return p.left != null ? findMin(p.left) : p;
    }

    private Node removeMin(Node p) // удаление узла с минимальным ключом из дерева p
    {
        if (p.left == null)
            return p.right;
        p.left = removeMin(p.left);
        return balance(p);
    }

    private Node removeWithMin(Node p, int key) // удаление ключа k из дерева p
    {
        if (p == null) {
            returnResult = null;
            return null;
        }

        if (key < p.key) {
            p.left = removeWithMin(p.left, key);
        } else if( key > p.key ) {
            p.right = removeWithMin(p.right, key);
        } else {
            returnResult = p.value;
            Node q = p.left;
            Node r = p.right;

            if (r == null) return q;

            Node min = findMin(r);
            min.right = removeMin(r);
            min.left = q;
            return balance(min);
        }

        return balance(p);
    }

    @Override
    public String remove(Object key) {
        head = removeWithMin(head, (Integer)key);

        return returnResult;
    }

    private Node findNode(Integer key) {
        Node p = head;

        while (p != null) {
            if ((Integer)key < p.key) {
                p = p.left;
            } else if ((Integer)key > p.key) {
                p = p.right;
            } else {
                return p;
            }
        }

        return null;
    }
    @Override
    public String get(Object k) {
        Node p = findNode((Integer)k);

        return p == null ? null : p.value;
    }

    @Override
    public boolean containsKey(Object key) {
        Node p = findNode((Integer) key);

        return p != null;
    }

    @Override
    public int size() { return head != null ? head.size : 0; }

    @Override
    public void clear() { head = null; }

    @Override
    public boolean isEmpty() { return head == null; }

    private void findToKeys(Node p, Integer key, MyRbMap newRbMap) {
        if (p != null) {
            findToKeys(p.left, key, newRbMap);
            if (p.key < key) { newRbMap.put(p.key, p.value); }
            findToKeys(p.right, key, newRbMap);
        }
    }
    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        if (toKey == null) {
            throw new NullPointerException();
        }

        MyRbMap newRbMap = new MyRbMap();
        findToKeys(head, toKey, newRbMap);

        return newRbMap;
    }

    private void findFromKeys(Node p, Integer key, MyRbMap newRbMap) {
        if (p != null) {
            findFromKeys(p.left, key, newRbMap);
            if (p.key >= key) { newRbMap.put(p.key, p.value); }
            findFromKeys(p.right, key, newRbMap);
        }
    }
    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        if (fromKey == null) {
            throw new NullPointerException();
        }

        MyRbMap newRbMap = new MyRbMap();
        findFromKeys(head, fromKey, newRbMap);

        return newRbMap;
    }

    @Override
    public Integer firstKey() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        Node p = head;

        while (p.left != null) {
            p = p.left;
        }

        return p.key;
    }

    @Override
    public Integer lastKey() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        Node p = head;

        while (p.right != null) {
            p = p.right;
        }

        return p.key;
    }

    //----------------------------------------------------------------------------//

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
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
    public Set<Map.Entry<Integer, String>> entrySet() {
        return null;
    }
}
