package by.it.group251002.klimovich.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {

    private static boolean RED = true;
    private static boolean BLACK = false;
    private static class Node {
        Integer key;
        String elem;
        boolean color;
        Node left;
        Node right;
        boolean toDel;
        public Node(Integer key, String elem, boolean color) {
            this.key = key;
            this.elem = elem;
            this.color = color;
            this.left = null;
            this.right = null;
            this.toDel = false;
        }
    }

    int size = 0;
    Node head = null;

    private boolean isRed(Node n) {
        if (n != null) {
           if (n.color = RED){
               return true;
           }
        }
        return false;
    }

    private void swapColors(Node n) {
        boolean tmp = n.left.color;
        n.left.color = n.color;
        n.right.color = n.color;
        n.color = tmp;
    }
    private Node leftRotate(Node n) {
        Node child = n.right;
        n.right = child.left;
        child.left = n;
        child.color = n.color;
        n.color = RED;
        return child;
    }

    private Node rightRotate(Node n) {
        Node child = n.left;
        n.left = child.right;
        child.right = n;
        child.color = n.color;
        n.color = RED;
        return child;
    }

    private Node balanceNode(Node n) {
        if (isRed(n.right) && !isRed(n.left)) {
            n = leftRotate(n);
        }
        if (isRed(n.left) && isRed(n.left.left)) {
            n = rightRotate(n);
        }
        if (isRed(n.right) && isRed(n.left)) {
            swapColors(n);
        }
        return n;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        OrderPath(sb, head);
        return sb.delete(sb.length() - 2, sb.length()).append("}").toString();
    }

    private void OrderPath(StringBuilder sb, Node node) {
        if (node != null) {
            OrderPath(sb, node.left);
            if (node.toDel!=true){
                sb.append(String.format("%s=%s, ", node.key, node.elem));
            }
            OrderPath(sb, node.right);
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

    private void headToKey(SortedMap<Integer, String> newMap, Integer toKey, Node n) {
        if (n == null) {
            return;
        }

        headToKey(newMap, toKey, n.left);
        if (toKey > n.key && n.toDel!=true) {
            newMap.put(n.key, n.elem);
        }
        if (toKey > n.key){
            headToKey(newMap, toKey, n.right);
        }
    }

    private void tailFromKey(SortedMap<Integer, String> newMap, Integer fromKey, Node n) {
        if (n == null) {
            return;
        }

        tailFromKey(newMap, fromKey, n.right);
        if (fromKey <= n.key && n.toDel!=true) {
            newMap.put(n.key, n.elem);
        }
        if (fromKey <= n.key){
            tailFromKey(newMap, fromKey, n.left);
        }
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        SortedMap<Integer, String> newMap = new MyRbMap();
        headToKey(newMap, toKey, head);
        return newMap;
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        SortedMap<Integer, String> newMap = new MyRbMap();
        tailFromKey(newMap, fromKey, head);
        return newMap;
    }

    @Override
    public Integer firstKey() {
        if (head == null) {
            return null;
        }
        Node n = head;
        while (n.left != null) {
            n = n.left;
        }
        return n.key;
    }

    @Override
    public Integer lastKey() {
        if (head == null) {
            return null;
        }

        Node n = head;
        while (n.right != null) {
            n = n.right;
        }

        return n.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }
    private Node find(Node n, int key) {
        while(n != null) {
            if (key < n.key) {
                n = n.left;
            } else if (key > n.key) {
                n = n.right;
            } else if (n.toDel!=true) {
                return n;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return find(head, (int)key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        Stack<Node> s = new Stack<Node>();
        Node curNode = head;
        while (!s.isEmpty() || curNode != null) {
            if (curNode != null) {
                s.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = s.pop();
                if (value.equals(curNode.elem) && curNode.toDel!=true) {
                    return true;
                }
                curNode = curNode.right;
            }
        }
        return false;
    }

    @Override
    public String get(Object key) {
        Node n = find(head, (int)key);
        return n == null ? null : n.elem;
    }

    private Node add(Node n, int key, String value) {
        if (n == null) {
            return new Node(key, value, RED);
        }

        if (key < n.key) {
            n.left = add(n.left, key, value);
        } else if (key > n.key) {
            n.right = add(n.right, key, value);
        }

        return balanceNode(n);
    }
    @Override
    public String put(Integer key, String value) {
        Node n = find(head, (int)key);
        if (n == null) {
            size++;
            head = add(head, key, value);
            if (head.color == RED) {
                head.color = BLACK;
            }
            return null;
        }

        String oldValue = n.elem;
        n.elem = value;
        return oldValue;
    }
    @Override
    public String remove(Object key) {
        Node n = find(head,(Integer) key);
        if (n!=null){
            n.toDel=true;
            return n.elem;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }
    private void cler(Node n){
        if (n!=null){
            if (n.left!=null){
                cler(n.left);
            }
            if (n.right!=null){
                cler(n.right);
            }
            n=null;
        }
    }

    @Override
    public void clear() {
        cler(head);
        head = null;
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