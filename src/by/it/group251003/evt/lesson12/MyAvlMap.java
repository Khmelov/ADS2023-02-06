package by.it.group251003.evt.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public class MyAvlMap implements Map<Integer, String> {

    private Node root;
    private int size;
    @Override
    public String toString() {

        if (isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder("{");
        inorderTraversal(sb, root);
        return sb.delete(sb.length() - 2, sb.length()).append("}").toString();
    }

    private void inorderTraversal(StringBuilder sb, Node node) {
        if (node != null) {
            inorderTraversal(sb, node.left);
            sb.append(String.format("%s=%s, ", node.key, node.value));
            inorderTraversal(sb, node.right);
        }
    }

    @Override
    public String put(Integer key, String value) {

        Node tryNode = findByKey(root, key);
        if (tryNode != null) {
            String prevVal = tryNode.value;
            tryNode.value = value;
            return prevVal;
        }

        size++;
        root = insert(root, key, value);
        return null;
    }

    @Override
    public String remove(Object key) {

        Node tryNode = findByKey(root, key);
        if (tryNode == null) {
            return null;
        }

        String val = tryNode.value;
        root = remove(root, (Integer) key);
        size--;
        return val;
    }

    @Override
    public String get(Object key) {
        Node target = findByKey(root, key);
        return (target == null) ? null : target.value;
    }

    @Override
    public boolean containsKey(Object key) {
        return findByKey(root, key) != null;
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
        return (size == 0);
    }

    private byte height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int calcBalanceFactor(Node node) {
        return height(node.right) - height(node.left);
    }

    private void fixHeight(Node node) {
        byte lHeight = height(node.left);
        byte rHeight = height(node.right);
        node.height = (byte) (Math.max(lHeight, rHeight) + 1);
    }

    private Node rotateRight(Node node) {
        Node q = node.left;
        node.left = q.right;
        q.right = node;
        fixHeight(node);
        fixHeight(q);
        return q;
    }

    private Node rotateLeft(Node node) {
        Node q = node.right;
        node.right = q.left;
        q.left = node;
        fixHeight(q);
        fixHeight(node);
        return q;
    }

    private Node findByKey(Node node, Object key) {

        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        }

        Comparable<? super Integer> comparable = (Comparable<? super Integer>) key;
        if (comparable.compareTo(node.key) < 0) {
            return findByKey(node.left, key);
        }

        return findByKey(node.right, key);
    }

    private Node findMin(Node node) {
        return node.left == null ? node : findMin(node.left);
    }

    private Node removeMin(Node node) {

        if (node.left == null) {
            return node.right;
        }

        node.left = removeMin(node.left);
        return balance(node);
    }

    private Node remove(Node node, Integer key) {

        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = remove(node.left, key);
        }
        else if (key > node.key) {
            node.right = remove(node.right, key);
        }
        else {
            Node q = node.left;
            Node r = node.right;
            if (r == null) {
                return q;
            }
            Node min = findMin(r);
            min.right = removeMin(r);
            min.left = q;
            return balance(min);
        }

        return balance(node);
    }

    private Node balance(Node node) {

        fixHeight(node);
        if (calcBalanceFactor(node) == 2) {

            if (calcBalanceFactor(node.right) < 0) {
                node.right = rotateRight(node.right);
            }

            return rotateLeft(node);
        }

        if (calcBalanceFactor(node) == -2) {

            if (calcBalanceFactor(node.left) > 0) {
                node.left = rotateLeft(node.left);
            }

            return rotateRight(node);
        }

        return node;
    }

    private Node insert(Node node, Integer key, String value) {

        if (node == null) {
            return new Node(key ,value);
        }

        if (key > node.key) {
            node.right = insert(node.right, key ,value);
        }
        else {
            node.left = insert(node.left, key, value);
        }

        return balance(node);
    }

    //–––––––

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

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

    private static class Node {

        Integer key;
        String value;
        byte height;
        Node left, right;

        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            height = 1;
        }

        @Override
        public String toString() {
            return String.format("{%s=%s}", key, value);
        }
    }
}