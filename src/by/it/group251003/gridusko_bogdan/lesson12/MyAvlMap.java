package by.it.group251003.gridusko_bogdan.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {
    static class Node<Integer, String> {
        public Integer key;
        public String value;

        public int height = 0;

        Node<Integer, String> left, right;

        public Node(Integer key, String val) {
            this.key = key;
            this.value = val;
            this.height = 1;
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "{}";
        }

        StringBuilder res = new StringBuilder().append("{");
        inorder(root, res);
        return res.replace(res.length()-2, res.length(), "}").toString();
    }

    private void inorder(Node<Integer, String> node, StringBuilder res) {
        if (node == null)
            return;

        inorder(node.left, res);
        res.append(node.key).append("=").append(node.value).append(", ");
        inorder(node.right, res);
    }

    private Node<Integer, String> root = null;

    private int Height(Node node) {
        return node == null ? 0 : node.height;
    }


    private Node<Integer, String> smallRotateLeft(Node<Integer, String> node) {
        Node<Integer, String> newNode = node.right;

        node.right = newNode.left;
        newNode.left = node;
        node.height = 1 + Math.max(Height(node.left), Height(node.right));
        newNode.height = 1 + Math.max(Height(newNode.left), Height(newNode.right));
        return newNode;
    }

    private Node<Integer, String> smallRotateRight(Node<Integer, String> node) {
        Node<Integer, String> newNode = node.left;

        node.left = newNode.right;
        newNode.right = node;
        node.height = 1 + Math.max(Height(node.left), Height(node.right));
        newNode.height = 1 + Math.max(Height(newNode.left), Height(newNode.right));
        return newNode;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node start) {
        if (start == null) {
            return 0;
        }

        return 1 + size(start.left) + size(start.right);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return containsKey((Integer) key, root) != null;
    }

    private Node<Integer, String> containsKey(Integer key, Node<Integer, String> node) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;

        if (cmp < 0)
            return containsKey(key, node.left);

        return containsKey(key, node.right);
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node<Integer, String> node = containsKey((Integer) key, root);
        return node == null ? null : node.value;
    }

    StringBuilder result = new StringBuilder();
    @Override
    public String put(Integer key, String value) {
        result = new StringBuilder();
        root = put(root, key, value);
        return result.isEmpty() ? null : result.toString();
    }

    Node<Integer, String> put(Node<Integer, String> node, Integer key, String value) {
        if (node == null)
            return new Node<Integer, String>(key, value);

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, value);
        else if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            if (!node.value.equals(value)) {
                node.value = value;
                result.append("generate").append(key);
            }

        return balance(node);
    }

    private int balanceFactor(Node<Integer, String> node) {
        if (node == null)
            return 0;

        return Height(node.left) - Height(node.right);
    }

    Node<Integer, String> balance(Node<Integer, String> node)
    {
        if (node == null)
            return node;

        node.height = 1 + Math.max(Height(node.left), Height(node.right));
        int balanceFactor = balanceFactor(node);

        if (balanceFactor > 1)
        {
            if (balanceFactor(node.left) < 0)
                node.left = smallRotateLeft(node.left);
            return smallRotateRight(node);
        }

        if (balanceFactor < -1)
        {
            if (balanceFactor(node.right) > 0)
                node.right = smallRotateRight(node.right);
            return smallRotateLeft(node);
        }

        return node;
    }

    @Override
    public String remove(Object key) {
        result = new StringBuilder();
        root = remove(root, (Integer) key);
        return result.isEmpty() ? null : result.toString();
    }

    Node<Integer, String> remove(Node<Integer, String> node, Integer key) {
        if (node == null)
            return node;

        int comparison = key.compareTo(node.key);
        if (comparison < 0)
            node.left = remove(node.left, key);
        else if (comparison > 0)
            node.right = remove(node.right, key);
        else {
            result.append("generate").append(key);
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            Node<Integer, String> minNode = minValueNode(node.right);
            node.value = minNode.value;
            node.right = removeMinNode(node.right);
        }

        return balance(node);
    }

    Node<Integer, String> removeMinNode(Node<Integer, String> node)
    {
        if (node.left == null)
            return node.right;

        node.left = removeMinNode(node.left);
        return balance(node);
    }

    Node<Integer, String> minValueNode(Node<Integer, String> node) {
        return node.left == null ? node : minValueNode(node.left);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        root = clear(root);
    }

    Node<Integer, String> clear(Node<Integer, String> node) {
        if (node == null)
            return null;

        node.left = clear(node.left);
        node.right = clear(node.right);

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
}
