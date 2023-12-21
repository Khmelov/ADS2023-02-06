package by.it.group251004.ryabchikov.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {
    class Node {
        int key;
        String value;
        int height;
        Node left, right;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }
    Node root;

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public boolean containsKey(Object key) {
        return search((Integer) key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node result = search((Integer) key);
        return result == null ? null : result.value;
    }

    Node search(Integer key) {
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

    Node insert(Node node, int key, String value){

        if(node == null) return new Node(key, value);
        if(key < node.key)
            node.left = insert(node.left, key, value);
        else
            node.right = insert(node.right,key, value);
        return balance(node);

    }

    @Override
    public String put(Integer key, String value) {
        Node node = search(key);

        if (node == null){
            root = insert(root, key, value);
            return  null;
        }
        else{
            var res = node.value;
            node.value = value;
            return res;
        }
    }

    Node balance(Node node)
    {
        if (node == null)
            return node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balanceFactor = balanceFactor(node);
        if (balanceFactor > 1)
        {
            if (balanceFactor(node.left) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balanceFactor < -1)
        {
            if (balanceFactor(node.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    int height(Node node) {
        return node == null ? 0 : node.height;
    }

    int balanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }


    void updateHeight(Node node) {
        int leftChildHeight = height(node.left);
        int rightChildHeight = height(node.right);
        node.height = Math.max(leftChildHeight, rightChildHeight) + 1;
    }

    Node rotateRight(Node node)
    {
        Node leftChild = node.left;

        node.left = leftChild.right;
        leftChild.right = node;

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    Node rotateLeft(Node node)
    {
        Node rightChild = node.right;

        node.right = rightChild.left;
        rightChild.left = node;

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }

    @Override
    public String remove(Object key) {
        var res = new StringBuilder();
        root = remove(root, (Integer) key, res);
        return res.isEmpty() ? null : res.toString();
    }

    Node remove(Node node, Integer key, StringBuilder res)
    {
        if (node == null)
            return node;
        int comparison = key.compareTo(node.key);
        if (comparison < 0)
            node.left = remove(node.left, key, res);
        else if (comparison > 0)
            node.right = remove(node.right, key, res);
        else
        {
            res.append("generate" + key);
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            Node minNode = minValueNode(node.right);
            node.value = minNode.value;
            node.right = removeMinNode(node.right);
        }
        return balance(node);
    }


    Node removeMinNode(Node node)
    {
        if (node.left == null)
            return node.right;
        node.left = removeMinNode(node.left);
        return balance(node);
    }

    Node minValueNode(Node node) {
        return node.left == null ? node : minValueNode(node.left);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        root = clear(root);
    }

    Node clear(Node node) {
        if (node == null)
            return null;
        node.left = clear(node.left);
        node.right = clear(node.right);
        return null;
    }

    public String toString() {
        if (root == null) return "{}";
        StringBuilder sb = new StringBuilder().append('{');
        print(root, sb);
        sb.replace(sb.length() - 2, sb.length(), "}");
        return sb.toString();
    }

    private void print(Node node, StringBuilder sb) {
        if (node != null) {
            print(node.left, sb);
            sb.append(node.key + "=" + node.value + ", ");
            print(node.right, sb);
        }
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
