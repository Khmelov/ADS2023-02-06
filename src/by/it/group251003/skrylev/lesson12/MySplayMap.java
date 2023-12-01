package by.it.group251003.skrylev.lesson12;

import java.util.*;

@SuppressWarnings("unchecked")
public class MySplayMap implements NavigableMap<Integer, String> {

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

    private void inorderTraversal(StringBuilder sb, Node node) {
        if (node != null) {
            inorderTraversal(sb, node.left);
            sb.append(String.format("%s=%s, ", node.key, node.value));
            inorderTraversal(sb, node.right);
        }
    }

    @Override
    public String put(Integer key, String value) {

        Node node = search(key);

        if (node == null) {
            size++;
            put((int) key, value);
            return null;
        } else {
            var oldValue = node.value;
            node.value = value;
            return oldValue;
        }
    }

    private void put(int key, String value) {

        if (root == null) {
            root = new Node(key, value, null);
            return;
        }

        Node currentNode = root;
        Node parent = null;

        while (currentNode != null) {
            parent = currentNode;
            if (key < currentNode.key) {
                currentNode = currentNode.left;
            } else if (key > currentNode.key) {
                currentNode = currentNode.right;
            }
        }

        Node newNode = new Node(key, value, parent);
        if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        splay(newNode);
    }

    @Override
    public String remove(Object key) {

        Node node = search(key);
        if (node != null) {
            size--;
            remove(node);
        }

        return node == null ? null : node.value;
    }

    private void remove(Node node) {

        if (node.left == null) {
            root = node.right;
            if (root != null) {
                root.parent = null;
            }
        } else {
            Node rightSubtree = node.right;
            root = node.left;
            root.parent = null;
            Node maxLeft = findMax(root);
            splay(maxLeft);
            root.right = rightSubtree;
            if (rightSubtree != null) {
                rightSubtree.parent = root;
            }
        }
    }

    private Node findMax(Node node) {
        while (node.right != null)
            node = node.right;

        return node;
    }

    @Override
    public String get(Object key) {
        Node result = search(key);
        return result == null ? null : result.value;
    }

    @Override
    public boolean containsKey(Object key) {
        return search(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return containsValue(root, value);
    }

    private boolean containsValue(Node node, Object value) {

        if (node == null) {
            return false;
        }

        if (value.equals(node.value)) {
            splay(node);
            return true;
        }

        return containsValue(node.left, value) || containsValue(node.right, value);
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
    public NavigableMap<Integer, String> headMap(Integer toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey, boolean inclusive) {
        return null;
    }

    @Override
    public Integer firstKey() {

        if (root == null)
            return null;

        Node node = root;
        while (node.left != null)
            node = node.left;
        splay(node);

        return node.key;
    }

    @Override
    public Integer lastKey() {

        if (root == null)
            return null;

        Node node = root;
        while (node.right != null)
            node = node.right;
        splay(node);

        return node.key;
    }

    @Override
    public Integer lowerKey(Integer key) {
        Node lower = findLower(root, key);
        if (lower == null)
            return null;
        splay(lower);

        return lower.key;
    }

    private Node findLower(Node node, Integer key) {

        Node lower = null;

        while (node != null) {
            if (key > node.key) {
                lower = node;
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return lower;
    }

    @Override
    public Integer floorKey(Integer key) {

        if (search(key) != null) {
            return key;
        }

        return lowerKey(key);
    }

    @Override
    public Integer ceilingKey(Integer key) {

        if (search(key) != null)
            return key;

        return higherKey(key);
    }

    @Override
    public Integer higherKey(Integer key) {

        Node higher = findHigher(root, key);
        if (higher == null) {
            return null;
        }
        splay(higher);

        return higher.key;
    }

    private Node findHigher(Node node, int key) {

        Node higher = null;
        while (node != null) {
            if (key < node.key) {
                higher = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return higher;
    }

    private Node search(Object key) {

        Node node = root;
        Comparable<? super Integer> comparable = (Comparable<? super Integer>) key;

        while (node != null) {
            if (comparable.compareTo(node.key) < 0) {
                node = node.left;
            } else if (comparable.compareTo(node.key) > 0) {
                node = node.right;
            } else {
                splay(node);
                return node;
            }
        }

        return null;
    }

    private void splay(Node node) {
        while (node.parent != null) {
            Node parent = node.parent;
            Node grandparent = parent.parent;

            if (node == parent.left) {
                if (grandparent == null) {
                    rightRotate(parent);
                } else {
                    if (parent == grandparent.left) {
                        rightRotate(grandparent);
                        rightRotate(parent);
                    } else {
                        rightRotate(parent);
                        leftRotate(grandparent);
                    }
                }
            } else {
                if (grandparent == null) {
                    leftRotate(parent);
                } else {
                    if (parent == grandparent.right) {
                        leftRotate(grandparent);
                        leftRotate(parent);
                    } else {
                        leftRotate(parent);
                        rightRotate(grandparent);
                    }
                }
            }
        }
    }

    private void rightRotate(Node node) {
        Node left = node.left;
        node.left = left.right;

        if (left.right != null) {
            left.right.parent = node;
        }
        left.parent = node.parent;

        if (node.parent == null) {
            root = left;
        } else if (node == node.parent.right) {
            node.parent.right = left;
        } else {
            node.parent.left = left;
        }

        left.right = node;
        node.parent = left;
    }

    private void leftRotate(Node node) {
        Node right = node.right;
        node.right = right.left;

        if (right.left != null) {
            right.left.parent = node;
        }
        right.parent = node.parent;

        if (node.parent == null) {
            root = right;
        } else if (node == node.parent.left) {
            node.parent.left = right;
        } else {
            node.parent.right = right;
        }

        right.left = node;
        node.parent = right;
    }


    //–––––

    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> firstEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> lastEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> pollFirstEntry() {
        return null;
    }

    @Override
    public Entry<Integer, String> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> descendingMap() {
        return null;
    }

    @Override
    public NavigableSet<Integer> navigableKeySet() {
        return null;
    }

    @Override
    public NavigableSet<Integer> descendingKeySet() {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> subMap(Integer fromKey, boolean fromInclusive, Integer toKey, boolean toInclusive) {
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

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {

        if (toKey == null)
            throw new NullPointerException("The key cannot be null");

        SortedMap<Integer, String> sortedMap = new MySplayMap();
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

        SortedMap<Integer, String> sortedMap = new MySplayMap();
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

    private static class Node {

        int key;
        String value;
        Node left;
        Node right;
        Node parent;

        public Node(int key, String value, Node parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }
}