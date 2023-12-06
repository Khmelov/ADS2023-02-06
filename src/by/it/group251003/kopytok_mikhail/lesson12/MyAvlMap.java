package by.it.group251003.kopytok_mikhail.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {
    private static class avltree {
        public int key;
        public String data;
        public avltree left = null;
        public avltree right = null;
        public int height = 1;
        public avltree(int key, String data) {
            this.key = key;
            this.data = data;
        }
    }

    private avltree root = null;
    private int size = 0;

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(avltree node) {
        if (node == null)
            return true;

        int balance = getBalance(node);

        if (Math.abs(balance) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    private int getHeight(avltree node) {
        return node == null ? 0 : node.height;
    }
    private int getBalance(avltree node) {
        return getHeight(node.left) - getHeight(node.right);
    }
    private void setHeight(avltree node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private avltree rightRotate(avltree node) {
        avltree left = node.left;
        node.left = left.right;
        left.right = node;

        setHeight(node);
        setHeight(left);
        return left;
    }

    private avltree leftRotate(avltree node) {
        avltree right = node.right;
        node.right = right.left;
        right.left = node;

        setHeight(node);
        setHeight(right);
        return right;
    }

    private avltree balance(avltree node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0)
                node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        else if (balance < -1) {
            if (getBalance(node.right) > 0)
                node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private boolean isInvalidKeyType(Object o) {
        return !(o instanceof Integer);
    }

    private avltree getMin(avltree node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    private avltree removeMin(avltree node) {
        if (node.left == null)
            return node.right;

        node.left = removeMin(node.left);
        setHeight(node);

        return balance(node);
    }

    private void checkBalance() {
        if (!isBalanced())
            throw new IllegalStateException("The tree is unbalanced");
    }

    @Override
    public String toString() {
        checkBalance();
        if (root == null)
            return "{}";

        StringBuilder sb = new StringBuilder("{");
        toString(root, sb);
        sb.replace(sb.length() - 2, sb.length(), "}");
        return sb.toString();
    }

    private void toString(avltree node, StringBuilder sb) {
        if (node != null) {
            toString(node.left, sb);
            sb.append(node.key).append("=").append(node.data).append(", ");
            toString(node.right, sb);
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

    private avltree search(int key) {
        avltree node = root;
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
    @Override
    public boolean containsKey(Object key) {
        if (isInvalidKeyType(key))
            throw new ClassCastException("The key is not of type Integer");
        return search((int)key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        if (isInvalidKeyType(key))
            throw new ClassCastException("The key is not of type Integer");
        avltree result = search((int)key);
        return result == null ? null : result.data;
    }

    @Override
    public String put(Integer key, String value) {
        var node = search(key);
        if (node == null) {
            size++;
            root = put(root, key, value);
            return null;
        }
        else {
            var oldValue = node.data;
            node.data = value;
            return oldValue;
        }
    }

    private avltree put(avltree node, int key, String value) {
        if (node == null)
            return new avltree(key, value);

        if (key < node.key)
            node.left = put(node.left, key, value);
        else if (key > node.key)
            node.right = put(node.right, key, value);
        else
            node.data = value;
        setHeight(node);

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
    private avltree remove(avltree node, int key) {
        if (node == null) return null;

        if (key < node.key)
            node.left = remove(node.left, key);
        else if (key > node.key)
            node.right = remove(node.right, key);
        else {
            if (node.left == null && node.right == null)
                return null;
            else if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                var min = getMin(node.right);

                node.key = min.key;
                node.data = min.data;

                node.right = removeMin(node.right);
            }
        }
        setHeight(node);

        return balance(node);
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
