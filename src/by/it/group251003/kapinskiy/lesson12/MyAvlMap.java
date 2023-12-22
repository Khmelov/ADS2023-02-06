package by.it.group251003.kapinskiy.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {

    static class Node<Integer, String> {
        Integer key;
        String value;
        int height = 0;
        Node<Integer, String> left, right;

        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root = null;
    private int size = 0;


    private int getHeight(Node node){
        return (node == null)? -1:node.height;
    }

    void updateHeight(Node node){
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    int getBalance(Node node){
        return (node == null)? 0: getHeight(node.right) - getHeight(node.left);
    }

    void swap (Node<Integer, String> a, Node<Integer, String> b){
        int saveKey = a.key;
        String saveVal = a.value;
        a.key = b.key;
        a.value = b.value;
        b.key = saveKey;
        b.value = saveVal;
    }

    void rightRotate(Node node){
        swap(node, node.left);
        Node buffer = node.right;
        node.right = node.left;
        node.left = node.right.left;
        node.right.left = node.right.right;
        node.right.right = buffer;
        updateHeight(node.right);
        updateHeight(node);
    }

    void leftRotate(Node node){
        swap(node, node.right);
        Node buffer = node.left;
        node.left = node.right;
        node.right = node.left.right;
        node.left.right = node.left.left;
        node.left.left = buffer;
        updateHeight(node.left);
        updateHeight(node);
    }

    void balance(Node node){
        int balance = getBalance(node);
        if (balance == -2){
            if (getBalance(node.left) == 1) leftRotate(node.left);
            rightRotate(node);
        }
        else if (balance == 2){
            if (getBalance(node.right) == -1)
                rightRotate(node.right);
            leftRotate(node);
        }
    }

    String insert(Node<Integer, String> node, Integer key, String value){
        String save = null;
        if (root == null){
            root = new Node(key, value);
            node = root;
        } else if (key.compareTo(node.key) == 0){
            save = node.value;
            node.value = value;
        } else if (key.compareTo(node.key) < 0){
            if (node.left == null) node.left = new Node(key, value);
            else save = insert(node.left, key, value);
        } else if (key.compareTo(node.key) > 0){
            if (node.right == null) node.right = new Node(key, value);
            else save = insert(node.right, key, value);
        }
        updateHeight(node);
        balance(node);
        return save;
    }

    Node findMax(Node node){
        if (node == null) return null;
        if (node.right == null) return node;
        return findMax(node.right);
    }

    Node delete(Node<Integer, String> node, Integer key){
        if (node == null) return null;
        else if (key.compareTo(node.key) < 0) node.left = delete(node.left, key);
        else if (key.compareTo(node.key) > 0) node.right = delete(node.right, key);
        else if (key.equals(node.key)){
            if (node.left == null || node.right == null)
                return (node.left == null)? node.right : node.left;
            else {
                    Node<Integer, String> maxInLeft = findMax(node.left);
                    node.key = maxInLeft.key;
                    node.value = maxInLeft.value;
                    node.left = delete(node.left, maxInLeft.key);
                }
        }
        if (node != null){
            getHeight(node);
            balance(node);
        }
        return node;
    }
    void inOrder (StringBuilder sb, Node node){
        if (node == null) return;
        inOrder(sb, node.left);
        sb.append(node.key.toString() + "=" + node.value.toString() + ", ");
        inOrder(sb, node.right);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (size > 0) {
            inOrder(sb, root);
            sb.setLength(sb.length() - 2);
        }
        return sb.append('}').toString();
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
        Node res = find(root, (Integer)key);
        return (res == null)? false: true;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    Node find(Node<Integer, String> node, Integer key){
        if (node == null) return null;
        if (key.equals(node.key)) return node;
        if (key > node.key) return find(node.right, key);
        return find(node.left, key);
    }
    @Override
    public String get(Object key) {
        Node res = find(root, (Integer)key);
        return (res == null)? null: (String)res.value;
    }

    @Override
    public String put(Integer key, String value) {
        String prev =  insert(root, key, value);
        if (prev == null) size++;
        return prev;
    }

    @Override
    public String remove(Object key) {
        Node toRemove = find(root, (Integer)key);
        if (toRemove != null){
            size--;
            String save = (String)toRemove.value;
            root = delete(root, (Integer)key);
            return save;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    Node deleteByDirectTraversal(Node node){
        if (node == null) return null;
        node.left = deleteByDirectTraversal(node.left);
        node.right = deleteByDirectTraversal(node.right);
        return null;
    }

    @Override
    public void clear() {
        root = deleteByDirectTraversal(root);
        size = 0;
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
