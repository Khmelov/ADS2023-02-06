package by.it.group251001.mikhei.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.max;

public class MyAvlMap implements Map<Integer, String> {

    private void toString(Node v, StringBuilder sb){
        if(v == null) return;

        toString(v.left, sb);
        sb.append(v.key).append("=").append(v.value).append(", ");
        toString(v.right, sb);
    }

    @Override
    public String toString() {
        if(isEmpty()) return "{}";

        StringBuilder sb = new StringBuilder("{");

        toString(root, sb);

        sb.delete(sb.length() - 2, sb.length());

        return sb.append("}").toString();
    }

    class Node {
        int key;
        String value;
        Node left, right;
        int height;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        private static int height(Node n) {
            return n == null ? 0 : n.height;
        }

        private static int getBFactor(Node n) {
            return n == null ? 0 : (height(n.right) - height(n.left));
        }

        private void fixHeight() {
            int hl = height(left);
            int hr = height(right);

            height = max(hl, hr) + 1;
        }

        private Node rotateRight() {
            Node q = left;
            left = q.right;
            q.right = this;
            fixHeight();
            q.fixHeight();
            return q;
        }

        private Node rotateLeft() {
            Node p = right;
            right = p.left;
            p.left = this;
            fixHeight();
            p.fixHeight();
            return p;
        }

        Node balance() {
            this.fixHeight();

            if (getBFactor(this) == 2) {
                if (getBFactor(right) < 0)
                    right = right.rotateRight();
                return rotateLeft();
            }

            if (getBFactor(this) == -2) {
                if (getBFactor(left) > 0)
                    left = left.rotateLeft();
                return rotateRight();
            }



            return this;
        }
    }

    private Node root = null;
    private int size = 0;

    Node put(Node v, int key, String value){
        if(v == null) return new Node(key, value);

        if(key < v.key)
            v.left = put(v.left, key, value);
        else
            v.right = put(v.right, key, value);

        return v.balance();
    }

    Node get(Node v, int key){
        if(v == null) return null;

        if(key < v.key) return get(v.left, key);
        if(key > v.key) return get(v.right, key);

        return v;
    }

    Node getMin(Node v){
        return v.left == null ? v : getMin(v.left);
    }

    Node removeMin(Node v){
        if(v.left == null) return v.right;
        v.left = removeMin(v.left);
        return v.balance();
    }

    Node removeRec(Node v, int key){
        if(v == null) return null;

        if(key < v.key){
            v.left = removeRec(v.left, key);
        }else if (key > v.key){
            v.right = removeRec(v.right, key);
        }else{
            Node l = v.left;
            Node r = v.right;

            if(r == null) return l;

            Node min = getMin(r);
            min.right = removeMin(r);
            min.left = l;

            return min.balance();
        }

        return v.balance();
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
        return get(root, (Integer) key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node node = get(root, (Integer) key);

        return node == null ? null : node.value;
    }

    @Override
    public String put(Integer key, String value) {
        Node node = get(root, key);

        if(node != null){
            String prev = node.value;
            node.value = value;
            return prev;
        }

        size++;
        root = put(root, key, value);

        return null;
    }

    @Override
    public String remove(Object key) {
        String prev = get(key);

        if(prev == null) return null;

        size--;
        root = removeRec(root, (Integer) key);

        return prev;
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
