package by.it.group251002.klimovich.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {

    private static class Node {
        int key;
        String elem;
        int height;
        Node left, right;

        public Node(Integer key, String elem) {
            this.key = key;
            this.elem = elem;
            this.left = this.right = null;
            height = 1;
        }

    }

    int size = 0;
    Node head = null;

    private int nodeHeight(Node n) {
        if (n != null) {
            return n.height;
        }
        return 0;
    }

    private int HeightDiff(Node n) {
        return nodeHeight(n.right) - nodeHeight(n.left);
    }

    private void changeHeight(Node n) {
        if (n != null) {
            int heightl = nodeHeight(n.left);
            int heightr = nodeHeight(n.right);
            if (heightl > heightr) {
                n.height=heightl+1;
            }
            else{
                n.height=heightr+1;
            }
        }
    }

    private Node rotateRight(Node elem) {
        Node child = elem.left;
        elem.left = child.right;
        child.right = elem;
        changeHeight(child);
        changeHeight(elem);
        return child;
    }

    private Node rotateLeft(Node elem) {
        Node child = elem.right;
        elem.right = child.left;
        child.left = elem;
        changeHeight(child);
        changeHeight(elem);
        return child;
    }

    private Node balanceNode(Node elem) {
        if (elem != null) {
            changeHeight(elem);
            if (HeightDiff(elem) == 2) {
                if (HeightDiff(elem.right) < 0) {
                    elem.right = rotateRight(elem.right);
                }
                return rotateLeft(elem);
            }
            if (HeightDiff(elem) == -2) {
                if (HeightDiff(elem.left) > 0) {
                    elem.left = rotateLeft(elem.left);
                }
                return rotateRight(elem);
            }
            return elem;
        }
        return null;
    }

    private Node find(Node n, int key) {
        while (n != null) {
            if (key < n.key) {
                n = n.left;
            } else if (key > n.key) {
                n = n.right;
            } else {
                return n;
            }
        }
        return null;
    }

    private Node add(Node n, int key, String value) {
        if (n == null){
            return new Node(key, value);
        }
        if (key < n.key) {
            n.left = add(n.left, key, value);
        } else {
            n.right = add(n.right, key, value);
        }
        return balanceNode(n);
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
            sb.append(String.format("%s=%s, ", node.key, node.elem));
            OrderPath(sb, node.right);
        }
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
        Node n = find(head, (Integer) key);
        if (n != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node n = find(head, (Integer) key);
        if (n != null) {
            return n.elem;
        }
        return null;
    }

    @Override
    public String put(Integer key, String value) {
        Node n = find(head, key);
        if (n == null) {
            head = add(head, key, value);
            size++;
            return null;
        }
        String prevValue = n.elem;
        n.elem = value;
        return prevValue;
    }

    private Node findMin(Node n) {
        if (n.left == null) {
            return n;
        }
        return findMin(n.left);
    }

    private Node removeMin(Node n) {
        if (n.left == null) {
            return n.right;
        }
        n.left = removeMin(n.left);
        return balanceNode(n);
    }

    private Node delete(Node n, int key) {
        if (n != null) {
            if (key <n.key) {
                n.left = delete(n.left, key);
            } else if (key > n.key) {
                n.right = delete(n.right, key);
            } else {
                Node left = n.left;
                Node right = n.right;
                if (right == null){
                    return left;
                }
                Node min = findMin(right);
                min.right = removeMin(right);
                min.left = left;
                return balanceNode(min);
            }
            return balanceNode(n);
        }
        return null;
    }

    @Override
    public String remove(Object key) {
        Node n = find(head, (Integer) key);
        if (n != null) {
            int keey=(Integer) key;
            head = delete(head, keey);
            size--;
            return n.elem;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {
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

