package by.it.group251003.nasevich.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {

    private class Node{

        public Node right;
        public Node left;
        public int key;
        public String value;
        public int height;
        public Node(int key, String value){
            this.key = key;
            this.value = value;
            this.height = 1;
        }

    }

    private Node root;
    private int size = 0;

    private int getHeight(Node curEl){
        return curEl == null ? -1 : curEl.height;
    }

    private void updateHeight(Node curEl){
        curEl.height = 1 + Math.max(getHeight(curEl.left), getHeight(curEl.right));
    }

    private int getBalance(Node curEl){
        return curEl == null ? 0 : getHeight(curEl.right) - getHeight(curEl.left);
    }

    private Node rightRotate(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;

        updateHeight(node);
        updateHeight(left);
        return left;
    }
    private Node leftRotate(Node node) {

        Node right = node.right;
        node.right = right.left;
        right.left = node;

        updateHeight(node);

        updateHeight(right);
        return right;
    }

    private Node balance(Node node){
        updateHeight(node);
        if(getBalance(node) == 2)
        {
            if( getBalance(node.right) < 0)
                node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        if(getBalance(node)==-2 )
        {
            if(getBalance(node.left) > 0)
                node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        return node;
    }

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

    private Node insert(Node node, int key, String value){

        if(node == null) return new Node(key, value);
        if(key < node.key)
            node.left = insert(node.left, key, value);
        else
            node.right = insert(node.right,key, value);
        return balance(node);

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
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return search((Integer)key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        if (key == null) throw new NullPointerException();

        Node findRes = search((Integer)key);

        return findRes == null ? null : findRes.value;
    }

    @Override
    public String put(Integer key, String value) {

        Node node = search(key);

        if (node == null){
            root = insert(root, key, value);
            size++;
            return  null;
        }
        else{
            var res = node.value;
            node.value = value;
            return res;
        }

    }

    private Node findMin(Node node){
        return node.left == null ? node: findMin(node.left);
    }

    private Node removeMin(Node node){
        if (node.left == null){
            return node.right;
        }
        node.left = removeMin(node.left);
        return balance(node);
    }

    private Node remove(Node node, int key){

        if (node == null) return null;

        if (key < node.key){
            node.left = remove(node.left, key);
        }
        else if (key > node.key){
            node.right = remove(node.right, key);
        }
        else{
            if (node.left == null && node.right == null)
                return null;
            else if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else
            {
                var min = findMin(node.right);
                node.key = min.key;
                node.value = min.value;
                node.right = removeMin(node.right);
            }

        }
        updateHeight(node);
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
