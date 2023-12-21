package by.it.group251002.urbanovich.lesson12;

import java.util.*;

public class MyAvlMap implements Map<Integer, String> {
    private class Node {
        Integer key;
        int height;
        String value;
        Node left, right;

        Node(Integer _key) {
            key = _key;
            left = null;
            right = null;
            height = 1;
            value = null;
        }
    }


    private Node root = null;
    private int size = 0;

    private void inOrder(Node node, StringBuilder s) {
        if (node == null) return;
        inOrder(node.left, s);
        s.append(node.key).append("=").append(node.value).append(", ");
        inOrder(node.right, s);
    }

    private Node findNode(Object key) {
        Node currNode = root;
        while (currNode != null && !currNode.key.equals(key)) {
            if ((Integer) key < currNode.key)
                currNode = currNode.left;
            else
                currNode = currNode.right;
        }
        return currNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        String res = "{";
        inOrder(root, sb);
        if (sb.length() > 2)
            res = sb.toString().substring(0, sb.length() - 2);
        return res + "}";
    }

    @Override
    public boolean containsKey(Object key) {
        Node currNode = root;
        while (currNode != null && !currNode.key.equals(key)) {
            if ((Integer) key < currNode.key)
                currNode = currNode.left;
            else
                currNode = currNode.right;
        }
        return currNode != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node currNode = root;
        while (currNode != null && !currNode.key.equals(key)) {
            if ((Integer) key < currNode.key)
                currNode = currNode.left;
            else
                currNode = currNode.right;
        }
        return currNode != null ? currNode.value : null;
    }

    @Override
    public String put(Integer key, String value) {
        boolean exists = containsKey(key);
        String prev = exists ? get(key) : null;
        if (exists)
            findNode(key).value = value;
        else {
            root = insert(root, key);
            findNode(key).value = value;
            size++;
        }
        return prev;
    }

    @Override
    public String remove(Object key) {
        boolean exists = containsKey(key);
        String res = exists ? findNode(key).value : null;
        if (exists) {
            root = removeNode(root, (Integer) key);
            size--;
        }

        return res;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        while (!isEmpty())
            remove(root.key);
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

    private int balanceFactor(Node node) {
        return node == null ? 0 : height(node.right) - height(node.left);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        int heightLeft = height(node.left);
        int heightRight = height(node.right);
        node.height = (heightLeft > heightRight ? heightLeft : heightRight) + 1;
    }

    private Node rotateRight(Node node) {
        Node lNode = node.left;
        node.left = lNode.right;
        lNode.right = node;
        fixHeight(node);
        fixHeight(lNode);
        return lNode;
    }

    private Node rotateLeft(Node node) {
        Node rNode = node.right;
        node.right = rNode.left;
        rNode.left = node;
        fixHeight(node);
        fixHeight(rNode);
        return rNode;
    }

    private Node findMin(Node node) {
        return node.left != null ? findMin(node.left) : node;
    }

    private Node removeMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = removeMin(node.left);
        return balance(node);
    }

    private Node removeNode(Node node, int key) {
        if (node == null) return null;
        if (key < node.key)
            node.left = removeNode(node.left, key);
        else if (key > node.key)
            node.right = removeNode(node.right, key);
        else {
            Node left = node.left;
            Node right = node.right;
            node = null;
            if (right == null) return left;
            Node min = findMin(right);
            min.right = removeMin(right);
            min.left = left;
            return balance(min);
        }
        return balance(node);
    }

    private Node balance(Node node) {
        fixHeight(node);
        if (balanceFactor(node) == 2) {
            if (balanceFactor(node.right) < 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        if (balanceFactor(node) == -2) {
            if (balanceFactor(node.left) > 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        return node;
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key)
            node.left = insert(node.left, key);
        else
            node.right = insert(node.right, key);
        return balance(node);
    }

}
