package by.it.group251001.krivitsky.lesson12;

import com.sun.jdi.connect.Connector;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {

    private Node root;
    int size;

    private static class Node{
        Integer key;
        int height;
        Node LeftNode;
        Node RightNode;
        String data;
        public Node(Integer key, String data){
            this.data = data;
            this.height = 1;
            this.key = key;
            this.RightNode = null;
            this.LeftNode = null;
        }
    }

    private int getBalance(Node node) {
        return height(node.RightNode) - height(node.LeftNode);
    }

    private int height(Node node){
        return node == null ? 0 : node.height;
    }
    private void updateHeight(Node node){
        int leftHeight = height(node.LeftNode);
        int rightHeight = height(node.RightNode);
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }
    private Node get(Node node, int key){
        if (node == null){
            return null;
        }
        if (node.key == key){
            return node;
        }
        else if (key < node.key){
            return get(node.LeftNode, key);
        }
        return get(node.RightNode, key);
    }
    private String toStringRecurse(Node node, String Temp){
        if (node.LeftNode != null){
            Temp = toStringRecurse(node.LeftNode, Temp);
        }
        Temp = Temp + node.key + "=" + node.data + ", ";
        if (node.RightNode != null){
            Temp = toStringRecurse(node.RightNode, Temp);
        }
        return Temp;
    }
    private Node rotateLeft(Node node){
        Node temp = node.RightNode;
        node.RightNode = temp.LeftNode;
        temp.LeftNode = node;
        updateHeight(temp);
        updateHeight(node);
        return temp;
    }
    private Node rotateRight(Node node){
        Node temp = node.LeftNode;
        node.LeftNode = temp.RightNode;
        temp.RightNode = node;
        updateHeight(node);
        updateHeight(temp);
        return temp;
    }
    private Node insert(Node node, int key, String data) {
        if (node == null){
            return new Node(key, data);
        }
        if (key < node.key) {
            node.LeftNode = insert(node.LeftNode, key, data);
        }
        else {
            node.RightNode = insert(node.RightNode, key, data);
        }

        return balance(node);
    }
    @Override
    public int size() {
        return size;
    }

    public Node remove(Integer key){
        return null;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public String get(int key){
        Node result = get(root, key);
        if (result != null){
            return result.data;
        }
        return null;
    }
    public String toString(){
        String Temp;
        if (isEmpty()){
            Temp = "{}";
        }
        else{
            Temp = "{";
            Temp = toStringRecurse(root, Temp);
            Temp = Temp.substring(0, Temp.length() - 2);
            Temp += "}";
        }
        System.out.println(Temp);
        return Temp;
    }
    @Override
    public String get(Object key) {
        Node result = get(root, (Integer)key);
        if (result != null){
            return result.data;
        }
        return null;
    }
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public String put(Integer key, String data) {
        Node tryNode = get(root, key);
        if (tryNode != null) {
            String prevVal = tryNode.data;
            tryNode.data = data;
            return prevVal;
        }

        size++;
        root = insert(root, key, data);
        return null;
    }
    @Override
    public String remove(Object key) {

        Node tryNode = get(root, (Integer) key);
        if (tryNode == null) {
            return null;
        }

        String val = tryNode.data;
        root = remove(root, (Integer) key);
        size--;
        return val;
    }

    private Node balance(Node node){
        updateHeight(node);

        if (getBalance(node) == 2) {

            if (getBalance(node.RightNode) < 0) {
                node.RightNode = rotateRight(node.RightNode);
            }

            return rotateLeft(node);
        }

        if (getBalance(node) == -2) {

            if (getBalance(node.LeftNode) > 0) {
                node.LeftNode = rotateLeft(node.LeftNode);
            }

            return rotateRight(node);
        }

        return node;
    }

    private Node removeMin(Node node) {

        if (node.LeftNode == null) {
            return node.RightNode;
        }

        node.LeftNode = removeMin(node.LeftNode);
        return balance(node);
    }

    private Node findMin(Node node){
        return node.LeftNode == null ? node : findMin(node.LeftNode);
    }
    private Node remove(Node node, Integer key) {

        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.LeftNode = remove(node.LeftNode, key);
        }
        else if (key > node.key) {
            node.RightNode = remove(node.RightNode, key);
        }
        else {
            Node q = node.LeftNode;
            Node r = node.RightNode;
            if (r == null) {
                return q;
            }
            Node min = findMin(r);
            min.RightNode = removeMin(r);
            min.LeftNode = q;
            return balance(min);
        }

        return balance(node);
    }



    @Override
    public boolean containsKey(Object key) {
        return get(root, (int)key) != null;
    }

    public boolean containsKey(int key){
        return get(root, key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {}

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
