package by.it.group251001.dadush.lesson12;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {

    private static class Node {
        Integer key;
        String value;
        byte height;
        Node left, right;

        Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            this.height = 1;
            this.left = this.right = null;
        }
    }

    private Node startNode = null;

    private int size = 0;

    private int getHeight(Node node) {
        return node != null ? node.height : 0;
    }

    private int bfactor(Node node) {
        return getHeight(node.right) - getHeight(node.left);
    }

    private void fixheight(Node node) {
        int hl = getHeight(node.left);
        int hr = getHeight(node.right);
        node.height = (byte)((hl > hr ? hl : hr) + 1);
    }

    private Node rotateLeft(Node node) {
        Node pivot = node.right;
        node.right = pivot.left;
        pivot.left = node;
        fixheight(node);
        fixheight(pivot);
        return pivot;
    }

    private Node rotateRight(Node node) {
        Node pivot = node.left;
        node.left = pivot.right;
        pivot.right = node;
        fixheight(node);
        fixheight(pivot);
        return pivot;
    }

    private Node balance(Node node) {
        fixheight(node);
        if (bfactor(node) == 2) {
            if (bfactor(node.right) < 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        if (bfactor(node) == -2) {
            if (bfactor(node.left) > 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        return node;
    }

    // bomba method

    private void BOMBA(Node node) {
        if (node != null) {
            if (bfactor(node) < -1 || bfactor(node) > 1)
                throw new NullPointerException("AAAAAAAA!!!!!! ВАШ WINDOWS ЗАБЛОКИРОВАН!!!");
            BOMBA(node.left);
            BOMBA(node.right);
        }
    }

    ///////////////////////////

    private StringBuilder getString(Node node, StringBuilder str) {
        if (node.left != null)
            getString(node.left, str);
        str.append(node.key + "=" + node.value + ", ");
        if (node.right != null)
            getString(node.right, str);
        return str;
    }

    public String toString() {
        if (startNode == null)
            return "{}";
        StringBuilder sb = new StringBuilder("{");
        getString(startNode, sb);
        if (sb.length() > 2)
            sb.delete(sb.length() - 2, sb.length());
        return sb.append("}").toString();
    }

    private Node pureAdd(Node node, Integer key, String value, StringBuilder res) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key < node.key)
            node.left = pureAdd(node.left, key, value, res);
        else if (key > node.key)
            node.right = pureAdd(node.right, key, value, res);
        else {
            res.append(node.value);
            node.value = value;
            return node;
        }
        return balance(node);
    }

    public String put(Integer key, String value) {
        if (startNode == null) {
            size++;
            startNode = new Node(key, value);
            return null;
        }
        StringBuilder res = new StringBuilder();
        startNode = pureAdd(startNode, key, value, res);

        BOMBA(startNode);

        return ! res.isEmpty() ? res.toString() : null;
    }

    private Node removeMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = removeMin(node.left);
        return balance(node);
    }

    private Node remove(Node node, Object key, StringBuilder res) {
        if (node == null) return null;
        if ((Integer) key < node.key)
            node.left = remove(node.left, key, res);
        else if ((Integer) key > node.key)
            node.right = remove(node.right, key, res);
        else {
            size--;
            res.append(node.value);
            Node left = node.left;
            Node right = node.right;
            Node min = right;
            if (right == null)
                return left;
            while (min.left != null)
                min = min.left;
            min.right = removeMin(right);
            min.left = left;
            return balance(min);
        }
        return balance(node);
    }

    public String remove(Object key) {
        StringBuilder res = new StringBuilder();
        startNode = remove(startNode, key, res);
        BOMBA(startNode);
        return ! res.isEmpty() ? res.toString() : null;
    }

    public String get(Object key) {
        Node node = startNode;
        Integer k = (Integer) key;
        while (node != null) {
            if (k < node.key)
                node = node.left;
            else if (k > node.key)
                node = node.right;
            else
                return node.value;
        }
        return null;
    }
    public boolean containsKey(Object key) {
        Node node = startNode;
        Integer k = (Integer) key;
        while (node != null) {
            if (k < node.key)
                node = node.left;
            else if (k > node.key)
                node = node.right;
            else
                return true;
        }
        return false;
    }

    public int size() {
        return size;
    }
    public void clear() {
        size = 0;
        startNode = null;
        //////
    }
    public boolean isEmpty() {
        return size == 0;
    }

    ///////////////////////////////////

    private boolean containsValue(Node node, Object value) {
        if (node == null)
            return false;
        if (! node.value.equals(value))
            return containsValue(node.left, value) || containsValue(node.right, value);
        return true;
    }

    public boolean containsValue(Object value) {
        return containsValue(startNode, value);
    }

    public void putAll(Map<? extends Integer, ? extends String> m) {}

    public Set<Integer> keySet() {return null;}

    public Collection<String> values() {return null;}

    public Set<Map.Entry<Integer, String>> entrySet() {return null;}
}
