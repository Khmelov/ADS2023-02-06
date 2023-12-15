package by.it.group251002.punko.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {

    private static final boolean RED = true;

    private static class Node {
        public int key;
        public String value;
        public Node left = null;
        public Node right = null;
        public boolean color = RED;
        public Node(int key, String data) {
            this.key = key;
            this.value = data;
        }

    }
    private Node root = null;
    private int size = 0;

    private Node search(int key) {
        Node node = root;
        while (node != null) {
            if (key < node.key)
                node = node.left;
            else if (key > node.key)
                node = node.right;
            else
                return node;
        }
        return null;
    }
    private Node rotateRight(Node node)
    {
        Node left = node.left;
        node.left = left.right;
        left.right = node;

        left.color = node.color;
        node.color = RED;
        return left;
    }
    private Node rotateLeft(Node node)
    {
        Node right = node.right;
        node.right = right.left;
        right.left = node;

        right.color = node.color;
        node.color = RED;
        return right;
    }
    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }
    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }
    private Node balance(Node node) {
        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }
    private Node getMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }
    private Node removeMin(Node node) {
        if (node.left == null)
            return node.right;

        if (!isRed(node.left) && !isRed(node.left.left))
            node = moveRedLeft(node);

        node.left = removeMin(node.left);
        return balance(node);
    }
    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }
    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    public void printTree(Node node, StringBuilder stringBuilder){
        if (node == null) return;
        printTree(node.left, stringBuilder);
        stringBuilder.append(node.key).append("=").append(node.value).append(", ");
        printTree(node.right, stringBuilder);
    }

    @Override
    public String toString(){

        if (size == 0) return "{}";

        var stringBuilder = new StringBuilder("{");

        printTree(root, stringBuilder);

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "");

        return stringBuilder.append("}").toString();
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
    public SortedMap<Integer, String> headMap(Integer toKey) {
        SortedMap<Integer, String> sortedMap = new by.it.group251003.Nasevich_Ilya.lesson12.MyRbMap();
        setToKey(root, toKey, sortedMap);
        return sortedMap;
    }
    private void setToKey(Node node, int toKey, SortedMap<Integer, String> sortedMap) {
        if (node == null)
            return;

        setToKey(node.left, toKey, sortedMap);

        if (node.key < toKey) {
            sortedMap.put(node.key, node.value);
            setToKey(node.right, toKey, sortedMap);
        }
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {

        SortedMap<Integer, String> sortedMap = new by.it.group251003.Nasevich_Ilya.lesson12.MyRbMap();
        setFromKey(root, fromKey, sortedMap);

        return sortedMap;
    }
    private void setFromKey(Node node, int fromKey, SortedMap<Integer, String> sortedMap) {
        if (node == null)
            return;

        setFromKey(node.right, fromKey, sortedMap);

        if (node.key >= fromKey) {
            sortedMap.put(node.key, node.value);
            setFromKey(node.left, fromKey, sortedMap);
        }
    }

    @Override
    public Integer firstKey() {
        if (root == null)
            return null;

        Node node = root;
        while (node.left != null)
            node = node.left;

        return node.key;
    }

    @Override
    public Integer lastKey() {
        if (root == null)
            return null;

        Node node = root;
        while (node.right != null)
            node = node.right;

        return node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return search((int)key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (!(value instanceof String)) return false;
        return containsValue(root, (String)value);
    }
    private boolean containsValue(Node node, String value) {
        if (node == null)
            return false;
        if (value.equals(node.value))
            return true;
        return containsValue(node.left, value) || containsValue(node.right, value);
    }

    @Override
    public String get(Object key) {
        Node result = search((int)key);
        return result == null ? null : result.value;
    }

    @Override
    public String put(Integer key, String value) {
        var node = search(key);
        if (node == null) {
            size++;
            root = put(root, key, value);
            return null;
        }
        else {
            var oldValue = node.value;
            node.value = value;
            return oldValue;
        }
    }
    private Node put(Node node, int key, String value) {
        if (node == null)
            return new Node(key, value);

        if (key < node.key)
            node.left = put(node.left, key, value);
        else if (key > node.key)
            node.right = put(node.right, key, value);
        else
            node.value = value;

        return balance(node);
    }

    @Override
    public String remove(Object key) {
        var oldValue = get(key);
        if (oldValue != null) {
            size--;
            root = remove(root, (int) key);
        }
        return oldValue;
    }
    private Node remove(Node node, int key) {
        if (key < node.key) {
            if (!isRed(node.left) && !isRed(node.left.left))
                node = moveRedLeft(node);
            node.left = remove(node.left, key);
        }
        else {
            if (isRed(node.left))
                node = rotateRight(node);
            if (key == node.key && node.right == null)
                return null;
            if (!isRed(node.right) && !isRed(node.right.left))
                node = moveRedRight(node);

            if (key == node.key) {
                Node min = getMin(node.right);
                node.key = min.key;
                node.value = min.value;
                node.right = removeMin(node.right);
            }
            else
                node.right = remove(node.right, key);
        }
        return balance(node);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        size = 0;
        root = null;
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
}