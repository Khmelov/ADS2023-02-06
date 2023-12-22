package by.it.group251003.kukhotskovolets.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Comparator;

public class MyRbMap implements SortedMap<Integer, String> {
    public boolean isBalanced() {
        int black = 0;
        Node node = root;
        while (node != null) {
            if (!isRed(node))
                black++;
            node = node.leftNode;
        }
        return isBalanced(root, black);
    }
    private boolean isBalanced(Node node, int black) {
        if (node == null)
            return black == 0;
        if (!isRed(node))
            black--;
        return isBalanced(node.leftNode, black) && isBalanced(node.rightNode, black);
    }
    private void checkBalance() {
        if (!isBalanced())
            throw new IllegalStateException("The tree is unbalanced");
    }
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private static class Node {
        public int key;
        public String data;
        public Node leftNode = null;
        public Node rightNode = null;
        public boolean nodeColor = RED;
        public Node(int key, String data) {
            this.key = key;
            this.data = data;
        }
    }
    private Node root = null;
    private int size = 0;
    private boolean isInvalidKeyType(Object o) {
        return !(o instanceof Integer);
    }
    private boolean isInvalidValueType(Object o) {
        return !(o instanceof String);
    }
    private Node search(int key) {
        Node node = root;
        while (node != null) {
            if (key < node.key)
                node = node.leftNode;
            else if (key > node.key)
                node = node.rightNode;
            else
                return node;
        }
        return null;
    }
    private Node rightRotate(Node node)
    {
        Node left = node.leftNode;
        node.leftNode = left.rightNode;
        left.rightNode = node;

        left.nodeColor = node.nodeColor;
        node.nodeColor = RED;
        return left;
    }
    private Node leftRotate(Node node)
    {
        Node right = node.rightNode;
        node.rightNode = right.leftNode;
        right.leftNode = node;

        right.nodeColor = node.nodeColor;
        node.nodeColor = RED;
        return right;
    }
    private boolean isRed(Node node) {
        return node != null && node.nodeColor == RED;
    }
    private void flipColors(Node node) {
        node.nodeColor = !node.nodeColor;
        node.leftNode.nodeColor = !node.leftNode.nodeColor;
        node.rightNode.nodeColor = !node.rightNode.nodeColor;
    }
    private Node balance(Node node) {
        if (isRed(node.rightNode) && !isRed(node.leftNode))
            node = leftRotate(node);
        if (isRed(node.leftNode) && isRed(node.leftNode.leftNode))
            node = rightRotate(node);
        if (isRed(node.leftNode) && isRed(node.rightNode))
            flipColors(node);

        return node;
    }
    private Node getMin(Node node) {
        while (node.leftNode != null)
            node = node.leftNode;
        return node;
    }
    private Node removeMin(Node node) {
        if (node.leftNode == null)
            return node.rightNode;

        if (!isRed(node.leftNode) && !isRed(node.leftNode.leftNode))
            node = moveRedLeft(node);

        node.leftNode = removeMin(node.leftNode);
        return balance(node);
    }
    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.rightNode.leftNode)) {
            node.rightNode = rightRotate(node.rightNode);
            node = leftRotate(node);
            flipColors(node);
        }
        return node;
    }
    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.leftNode.leftNode)) {
            node = rightRotate(node);
            flipColors(node);
        }
        return node;
    }

    @Override
    public String toString() {
        checkBalance();
        if (root == null)
            return "{}";

        StringBuilder sb = new StringBuilder("{");
        toString(root, sb);
        sb.replace(sb.length() - 2, sb.length(), "}");
        return sb.toString();
    }
    private void toString(Node node, StringBuilder sb) {
        if (node != null) {
            toString(node.leftNode, sb);
            sb.append(node.key).append("=").append(node.data).append(", ");
            toString(node.rightNode, sb);
        }
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
        if (toKey == null)
            throw new NullPointerException("The key cannot be null");

        SortedMap<Integer, String> sortedMap = new MyRbMap();
        setToKey(root, toKey, sortedMap);

        return sortedMap;
    }
    private void setToKey(Node node, int toKey, SortedMap<Integer, String> sortedMap) {
        if (node == null)
            return;

        setToKey(node.leftNode, toKey, sortedMap);

        if (node.key < toKey) {
            sortedMap.put(node.key, node.data);
            setToKey(node.rightNode, toKey, sortedMap);
        }
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        if (fromKey == null)
            throw new NullPointerException("The key cannot be null");

        SortedMap<Integer, String> sortedMap = new MyRbMap();
        setFromKey(root, fromKey, sortedMap);

        return sortedMap;
    }
    private void setFromKey(Node node, int fromKey, SortedMap<Integer, String> sortedMap) {
        if (node == null)
            return;

        setFromKey(node.rightNode, fromKey, sortedMap);

        if (node.key >= fromKey) {
            sortedMap.put(node.key, node.data);
            setFromKey(node.leftNode, fromKey, sortedMap);
        }
    }

    @Override
    public Integer firstKey() {
        if (root == null)
            return null;

        Node node = root;
        while (node.leftNode != null)
            node = node.leftNode;

        return node.key;
    }

    @Override
    public Integer lastKey() {
        if (root == null)
            return null;

        Node node = root;
        while (node.rightNode != null)
            node = node.rightNode;

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
        if (isInvalidKeyType(key)) {
            return false;
        }
        return search((int)key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (isInvalidValueType(value)) {
            //throw new ClassCastException("The value is not of type String");
            return false;
        }
        return containsValue(root, (String)value);
    }
    private boolean containsValue(Node node, String value) {
        if (node == null)
            return false;
        if (value.equals(node.data))
            return true;
        return containsValue(node.leftNode, value) || containsValue(node.rightNode, value);
    }

    @Override
    public String get(Object key) {
        if (isInvalidKeyType(key))
            throw new ClassCastException("The key is not of type Integer");
        Node result = search((int)key);
        return result == null ? null : result.data;
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
            var oldValue = node.data;
            node.data = value;
            return oldValue;
        }
    }
    private Node put(Node node, int key, String value) {
        if (node == null)
            return new Node(key, value);

        if (key < node.key)
            node.leftNode = put(node.leftNode, key, value);
        else if (key > node.key)
            node.rightNode = put(node.rightNode, key, value);
        else
            node.data = value;

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
            if (!isRed(node.leftNode) && !isRed(node.leftNode.leftNode))
                node = moveRedLeft(node);
            node.leftNode = remove(node.leftNode, key);
        }
        else {
            if (isRed(node.leftNode))
                node = rightRotate(node);
            if (key == node.key && node.rightNode == null)
                return null;
            if (!isRed(node.rightNode) && !isRed(node.rightNode.leftNode))
                node = moveRedRight(node);

            if (key == node.key) {
                Node min = getMin(node.rightNode);
                node.key = min.key;
                node.data = min.data;
                node.rightNode = removeMin(node.rightNode);
            }
            else
                node.rightNode = remove(node.rightNode, key);
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
