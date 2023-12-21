package by.it.group251001.brutskaya.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {
    class Node {
        Integer key;
        String value;
        Node left;
        Node right;
        boolean color;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
            this.color = RED;
        }
    }

    int size;
    Node root;

    private final boolean RED = true;
    private final boolean BLACK = false;

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        SortedMap<Integer, String> hm = new MyRbMap();
        headMap(root, toKey, hm);
        return hm;
    }

    private void headMap(Node node, Integer key, SortedMap<Integer, String> hm) {
        if (node == null) {
            return;
        }
        headMap(node.left, key, hm);
        if (node.key.compareTo(key) < 0) {
            hm.put(node.key, node.value);
            headMap(node.right, key, hm);
        }
    }

    private void tailMap(Node node, Integer key, MyRbMap map) {
        if (node == null) {
            return;
        }
        int cmp = key.compareTo(node.key);
        tailMap(node.right, key, map);
        if (cmp <= 0) {
            map.put(node.key, node.value);
            tailMap(node.left, key, map);
        }
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap tm = new MyRbMap();
        tailMap(root, fromKey, tm);
        return tm;
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
    public Integer firstKey() {
        if (root == null) throw new NullPointerException();
        Node node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    @Override
    public Integer lastKey() {
        if (root == null) throw new NullPointerException();
        Node node = root;
        while (node.right != null) {
            node = node.right;
        }
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
        return travel(root, (Integer) key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return travelValue(root, value);
    }

    private String travel(Node node, Integer key) {
        if (node == null) {
            return null;
        }
            int cmp = key.compareTo(node.key);
            if (cmp < 0)
                return travel(node.left, key);
            if (cmp > 0)
                return travel(node.right, key);
            else return node.value;
    }

    private boolean travelValue(Node node, Object value) {
        if (node == null)
            return false;
        if (value.equals(node.value))
            return true;
        return travelValue(node.left, value) || travelValue(node.right, value);
    }

    @Override
    public String get(Object key) {
        return travel(root, (Integer) key);
    }

    public String get(Node root, Integer key) {
        return travel(root, key);
    }

    @Override
    public String put(Integer key, String value) {
        if (key == null) {
            throw new NullPointerException();
        }
        String prev = get(root, key);
        if (prev == null) {
            size++;
        }
        root = put(root, key, value);
        root.color = BLACK;
        return prev;
    }

    private Node put(Node node, Integer key, String value) {
        if (node == null) {
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node search(Node node, Integer key){
        if (node==null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp<0){
            return search(node.left, key);
        } else if (cmp>0) {
            return search(node.right, key);
        } else return node;
    }

    @Override
    public String remove(Object key) {
        if (! (key instanceof Integer)){
            return null;
        }
        Node target = search(root, (Integer)key);
        if (target==null){
            return null;
        }
        size--;
        String removedValue = target.value;
        root = remove(root, (Integer) key);
        if (root!=null){
            root.color=BLACK;
        }
        return removedValue;
    }

    private Node remove(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    return null;
                } else {
                    return temp;
                }
            } else {
                Node successor = findSuccessor(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = remove(node.right, successor.key);
            }
        }
        return balance(node);
    }

    private Node findSuccessor(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public Node balance(Node node) {
        if (isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    ////////////////////////////////////////////
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

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

}
