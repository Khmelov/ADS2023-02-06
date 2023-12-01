package by.it.group251002.baranovskaia.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {

    private static class Node {

        Color color;
        Node left, right, parent;
        Integer key;
        String value;

        public Node(Node parent, Integer key, String value, Color color) {
            this.parent = parent;
            this.key = key;
            this.value = value;
            this.color = color;
            left = right = null;
        }
    }

    private enum Color {
        BLACK,
        RED,
        DOUBLE_BLACK
    }

    private Node root;
    private int size;

    @Override
    public String toString() {

        if (isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder("{");
        inorderTraversal(sb, root);
        return sb.delete(sb.length() - 2, sb.length()).append("}").toString();
    }

    @Override
    public String put(Integer key, String value) {

        if (isEmpty()) {
            root = new Node(null, key, value, Color.BLACK);
            size++;
            return null;
        }

        Node parent = root.parent;
        Node curr = root;

        while (curr != null) {
            parent = curr;
            if (key < curr.key) {
                curr = curr.left;
            } else if (key > curr.key) {
                curr = curr.right;
            } else {
                String prevVal = curr.value;
                curr.value = value;
                return prevVal;
            }
        }

        Node newNode = new Node(parent, key, value, Color.RED);

        if (parent.key > key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        balance(newNode);
        size++;
        return null;
    }

    private void balance(Node node) {

        Node parent;
        Node grandparent;

        while (node != root && node.color == Color.RED && node.parent.color == Color.RED) {

            parent = node.parent;
            grandparent = parent.parent;

            if (parent == grandparent.left) {
                Node uncle = grandparent.right;
                if (getColor(uncle) == Color.RED) {
                    setColor(uncle, Color.BLACK);
                    setColor(parent, Color.BLACK);
                    setColor(grandparent, Color.RED);
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateRight(grandparent);
                    swapColors(parent, grandparent);
                    node = parent;
                }
            } else {
                Node uncle = grandparent.left;
                if (getColor(uncle) == Color.RED) {
                    setColor(uncle, Color.BLACK);
                    setColor(parent, Color.BLACK);
                    setColor(grandparent, Color.RED);
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateLeft(grandparent);
                    swapColors(parent, grandparent);
                    node = parent;
                }
            }
        }

        setColor(root, Color.BLACK);
    }

    private void swapColors(Node node1, Node node2) {
        Color tmpColor = node1.color;
        node1.color = node2.color;
        node2.color = tmpColor;
    }

    private void rotateLeft(Node node) {

        Node rChild = node.right;
        node.right = rChild.left;

        if (node.right != null) {
            node.right.parent = node;
        }

        rChild.parent = node.parent;

        if (node.parent == null) {
            root = rChild;
        } else if (node == node.parent.left) {
            node.parent.left = rChild;
        } else {
            node.parent.right = rChild;
        }

        rChild.left = node;
        node.parent = rChild;
    }

    private void rotateRight(Node node) {

        Node lChild = node.left;
        node.left = lChild.right;

        if (node.left != null) {
            node.left.parent = node;
        }

        lChild.parent = node.parent;

        if (node.parent == null) {
            root = lChild;
        } else if (node == node.parent.left) {
            node.parent.left = lChild;
        } else {
            node.parent.right = lChild;
        }

        lChild.right = node;
        node.parent = lChild;
    }

    private void inorderTraversal(StringBuilder sb, Node node) {
        if (node != null) {
            inorderTraversal(sb, node.left);
            sb.append(String.format("%s=%s, ", node.key, node.value));
            inorderTraversal(sb, node.right);
        }
    }

    private Node findByKey(Node node, Object key) {

        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        }

        Comparable<? super Integer> comparable = (Comparable<? super Integer>) key;
        if (comparable.compareTo(node.key) < 0) {
            return findByKey(node.left, key);
        }

        return findByKey(node.right, key);
    }

    private Node findByValue(Node node, Object value) {

        if (node == null) {
            return null;
        }

        if (node.value.equals(value)) {
            return node;
        }

        Node target = findByValue(node.left, value);

        if (target != null) {
            return target;
        }

        return findByValue(node.right, value);
    }

    @Override
    public String remove(Object key) {

        Node tryNode = findByKey(root, key);
        if (tryNode == null) {
            return null;
        }

        String val = tryNode.value;
        Node node = delete(root, key);
        fixDeletion(node);
        size--;
        return val;
    }

    private Node delete(Node root, Object key) {

        if (root == null) {
            return null;
        }

        Comparable<? super Integer> comparable = (Comparable<? super Integer>) key;

        if (comparable.compareTo(root.key) < 0) {
            return delete(root.left, key);
        }

        if (comparable.compareTo(root.key) > 0) {
            return delete(root.right, key);
        }

        if (root.left == null || root.right == null) {
            return root;
        }

        Node temp = minKeyNode(root.right);
        root.key = temp.key;
        root.value = temp.value;
        return delete(root.right, temp.key);
    }

    private void fixDeletion(Node node) {

        if (node == null) {
            return;
        }

        if (node == root) {
            root = null;
            return;
        }

        if (getColor(node) == Color.RED || getColor(node.left) == Color.RED || getColor(node.right) == Color.RED) {

            Node child = node.left != null ? node.left : node.right;

            if (node == node.parent.left) {
                node.parent.left = child;
            }
            else {
                node.parent.right = child;
            }

            if (child != null) {
                child.parent = node.parent;
            }
            setColor(child, Color.BLACK);

        }
        else {

            Node sibling;
            Node parent;
            Node curr = node;
            setColor(curr, Color.DOUBLE_BLACK);

            while (curr != root && getColor(curr) == Color.DOUBLE_BLACK) {
                parent = curr.parent;
                if (curr == parent.left) {
                    sibling = parent.right;
                    if (getColor(sibling) == Color.RED) {
                        setColor(sibling, Color.BLACK);
                        setColor(parent, Color.RED);
                        rotateLeft(parent);
                    }
                    else {
                        if (getColor(sibling.left) == Color.BLACK && getColor(sibling.right) == Color.BLACK) {
                            setColor(sibling, Color.RED);
                            if (getColor(parent) == Color.RED) {
                                setColor(parent, Color.BLACK);
                            }
                            else {
                                setColor(parent, Color.DOUBLE_BLACK);
                            }
                            curr = parent;
                        }
                        else {
                            if (getColor(sibling.right) == Color.BLACK) {
                                setColor(sibling.left, Color.BLACK);
                                setColor(sibling, Color.RED);
                                rotateRight(sibling);
                                sibling = parent.right;
                            }
                            setColor(sibling, parent.color);
                            setColor(parent, Color.BLACK);
                            setColor(sibling.right, Color.BLACK);
                            rotateLeft(parent);
                            break;
                        }
                    }
                }
                else {
                    sibling = parent.left;
                    if (getColor(sibling) == Color.RED) {
                        setColor(sibling, Color.BLACK);
                        setColor(parent, Color.RED);
                        rotateRight(parent);
                    }
                    else {
                        if (getColor(sibling.left) == Color.BLACK && getColor(sibling.right) == Color.BLACK) {
                            setColor(sibling, Color.RED);
                            if (getColor(parent) == Color.RED) {
                                setColor(parent, Color.BLACK);
                            }
                            else {
                                setColor(parent, Color.DOUBLE_BLACK);
                            }
                            curr = parent;
                        }
                        else {
                            if (getColor(sibling.left) == Color.BLACK) {
                                setColor(sibling.right, Color.BLACK);
                                setColor(sibling, Color.RED);
                                rotateLeft(sibling);
                                sibling = parent.left;
                            }
                            setColor(sibling, parent.color);
                            setColor(parent, Color.BLACK);
                            setColor(sibling.left, Color.BLACK);
                            rotateRight(parent);
                            break;
                        }
                    }
                }
            }

            if (node == node.parent.left) {
                node.parent.left = null;
            }
            else {
                node.parent.right = null;
            }

            setColor(root, Color.BLACK);
        }
    }


    @Override
    public String get(Object key) {
        Node target = findByKey(root, key);
        return (target == null) ? null : target.value;
    }

    @Override
    public boolean containsKey(Object key) {
        return findByKey(root, key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return findByValue(root, value) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {

        if (toKey == null)
            throw new NullPointerException("The key cannot be null");

        SortedMap<Integer, String> sortedMap = new MyRbMap();
        setToKey(root, toKey, sortedMap);

        return sortedMap;
    }

    private void setToKey(Node node, int toKey, SortedMap<Integer, String> sortedMap) {

        if (node == null)
            return;

        setToKey(node.left, toKey, sortedMap);

        if (node.key < toKey) {
            sortedMap.put(node.key, node.value);
            setToKey(node.right, toKey, sortedMap);
        }
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {

        if (fromKey == null)
            throw new NullPointerException("The key cannot be null");

        SortedMap<Integer, String> sortedMap = new MyRbMap();
        setFromKey(root, fromKey, sortedMap);

        return sortedMap;
    }

    private void setFromKey(Node node, int fromKey, SortedMap<Integer, String> sortedMap) {
        if (node == null)
            return;

        setFromKey(node.right, fromKey, sortedMap);

        if (node.key >= fromKey) {
            sortedMap.put(node.key, node.value);
            setFromKey(node.left, fromKey, sortedMap);
        }
    }

    @Override
    public Integer firstKey() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return minKeyNode(root).key;
    }

    @Override
    public Integer lastKey() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return maxKeyNode(root).key;
    }

    private Node minKeyNode(Node root) {
        Node node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node maxKeyNode(Node root) {
        Node node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Color getColor(Node node) {

        if (node == null) {
            return Color.BLACK;
        }

        return node.color;
    }

    private void setColor(Node node, Color color) {

        if (node == null) {
            return;
        }

        node.color = color;
    }

    //–––––

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
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
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }
}