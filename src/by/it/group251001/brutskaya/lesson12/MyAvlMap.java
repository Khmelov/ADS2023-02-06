package by.it.group251001.brutskaya.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MyAvlMap implements Map<Integer, String> {
    class Node {
        int key;
        String value;
        int height;
        Node left;
        Node right;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    int size;
    Node root;

    int max(int a, int b) {
        return Math.max(a, b);
    }

    int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right) + 1);
        x.height = max(height(x.left), height(x.right) + 1);

        return x;
    }

    Node leftRotation(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        y.height = max(height(y.left), height(y.right) + 1);
        x.height = max(height(x.left), height(x.right) + 1);
        return y;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "{}";
        StringBuilder sb = new StringBuilder();
        String delimiter = "";
        sb.append("{");
        Stack<Node> nodestack = new Stack<>();
        Node current = root;
        while (!nodestack.isEmpty() || current != null) {
            if (current != null) {
                nodestack.push(current);
                current = current.left;
            } else {
                current = nodestack.pop();
                sb.append(delimiter).append(current.key).append("=").append(current.value);
                delimiter = ", ";
                current = current.right;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String get(Object key) {
        if (key == null || !(key instanceof Integer))
            return null;
        Integer searchKey = (Integer) key;
        Node result = search(root, searchKey);
        return (result != null) ? result.value : null;
    }

    public Node search(Node node, int key) {
        if (node == null || key == node.key) {
            return node;
        }
        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    @Override
    public String put(Integer key, String value) {
        if (key == null) {
            throw new NullPointerException();
        }
        String oldValue = get(key);
        root = put(root, key, value);
        return oldValue;
    }

    public Node put(Node node, Integer key, String value) {
        if (node==null){
            size++;
            return new Node(key, value);
        }
        if (key< node.key){
            node.left = put(node.left, key, value);
        } else if (key > node.key) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }
        updateHeight(node);
        return balance(node);
    }

    public void updateHeight(Node node){
        if (node!=null){
            node.height = Math.max(height(node.left), height(node.right));
        }
    }

    public Node balance(Node node){
        int balance = getBalance(node);
        if (balance > 1 && node.key<node.left.key)
            return rightRotate(node);
        if (balance <-1 && node.key > node.right.key)
            return  leftRotation(node);

        if (balance > 1 && node.key> node.left.key){
            node.left=leftRotation(node.left);
            return rightRotate(node);
        }
        if (balance< -1 && node.key< node.right.key){
            node.right = rightRotate(node.right);
            return leftRotation(node);
        }
        return node;
    }

    @Override
    public String remove(Object key) {
        if (! (key instanceof Integer))
            return null;
        int removeKey = (int) key;
        String removedValue = get(removeKey);
        root = remove(root,removeKey);
        if (removedValue!=null){
            size--;
        }
        return removedValue;
    }

    public Node remove(Node node, int key){
        if (node==null){
            return null;
        }
        if (key<node.key){
            node.left = remove(node.left, key);
        } else if (key>node.key) {
            node.right=remove(node.right, key);
        } else if (node.left==null || node.right==null){
            node = (node.left!=null)? node.left : node.right;
        } else {
            Node successor = findMin(node.right);
            node.key = successor.key;
            node.value=successor.value;
            node.right = remove(node.right, successor.key);
        }
        if (node!=null){
            updateHeight(node);
            return balance(node);
        }
        return null;
    }

    public Node findMin(Node node){
        while (node.left!=null){
            node=node.left;
        }
        return node;
    }
    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof Integer)) {
            return false;
        }
        Integer searchKey = (Integer) key;
        return containsKey(root, searchKey);
    }

    public boolean containsKey(Node node, int key) {
        if (node == null)
            return false;
        if (key < node.key) {
            return containsKey(node.left, key);
        } else if (key > node.key) {
            return containsKey(node.right, key);
        } else return true;
    }

    /////////////////////////
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
}
