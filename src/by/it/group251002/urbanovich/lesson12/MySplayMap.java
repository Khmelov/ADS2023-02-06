package by.it.group251002.urbanovich.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {
    private class Node {
        Integer key;
        String value;
        Node left, right, parent;

        Node(Integer _key) {
            key = _key;
            value = null;
            left = right = parent = null;
        }
    }

    private Node root = null;
    private int size = 0;

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node nl = node.left;
        if (parent != null) {
            node.parent = parent.parent;
            if (parent.parent != null) {
                if (parent.parent.left == parent)
                    parent.parent.left = node;
                else
                    parent.parent.right = node;
            }
            parent.right = nl;
            if (nl != null) {
                nl.parent = parent;
            }
            node.left = parent;
            parent.parent = node;
        }
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node nr = node.right;
        if (parent != null) {
            node.parent = parent.parent;
            if (parent.parent != null) {
                if (parent.parent.left == parent)
                    parent.parent.left = node;
                else
                    parent.parent.right = node;
            }
            parent.left = nr;
            if (nr != null) {
                nr.parent = parent;
            }
            node.right = parent;
            parent.parent = node;
        }

    }

    private Node splay(Node node) {
        while (node.parent != null) {
            if (node.parent.parent == null) {
                if (node.parent.left == node)
                    rotateRight(node);
                else
                    rotateLeft(node);
            } else {
                if (node.parent == node.parent.parent.left) {
                    if (node == node.parent.left) {
                        rotateRight(node.parent);
                        rotateRight(node);
                    } else {
                        rotateLeft(node);
                        rotateRight(node);
                    }
                } else {
                    if (node.parent == node.parent.parent.right)
                        if (node == node.parent.right) {
                            rotateLeft(node.parent);
                            rotateLeft(node);
                        } else {
                            rotateRight(node);
                            rotateLeft(node);
                        }
                }
            }
        }
        return node;
    }

    private Node findNode(Node root, Integer key) {
        Node currNode = root;
        Node prevNode = null;
        while (currNode != null) {
            prevNode = currNode;
            if (currNode.key > key)
                currNode = currNode.left;
            else if (currNode.key < key)
                currNode = currNode.right;
            else if ((currNode.key.equals(key)))
                break;
        }
        if (currNode != null)
            return splay(currNode);
        else
            return splay(prevNode);
    }

    private Node findKey(Integer key) {
        Node currNode = root;
        while (currNode != null && !currNode.key.equals(key)) {
            if (currNode.key > key)
                currNode = currNode.left;
            else
                currNode = currNode.right;
        }
        return currNode;
    }

    private Node[] split(Integer key) {
        if (root != null) {
            root = findNode(root, key);
            if (root.key == key) {
                root.left.parent = null;
                root.right.parent = null;
                return new Node[]{root.left, root.right};
            } else if (root.key < key) {
                Node right = root.right;
                if (right != null) right.parent = null;
                root.right = null;
                return new Node[]{root, right};
            } else {
                Node left = root.left;
                if (left != null) left.parent = null;
                root.left = null;
                return new Node[]{left, root};
            }
        }
        return new Node[]{null, null};
    }

    private Node insert(Integer key) {
        Node[] parts = split(key);
        Node right = parts[1];
        Node left = parts[0];
        Node newNode = new Node(key);
        newNode.left = left;
        newNode.right = right;
        if (left != null) left.parent = newNode;
        if (right != null) right.parent = newNode;
        root = newNode;
        return newNode;
    }

    private Node merge(Node left, Node right) {
        if (right == null)
            return left;
        if (left == null)
            return right;
        right = findNode(right, left.key);
        right.left = left;
        left.parent = right;
        return right;
    }

    private Node remove(Node root, Integer key) {
        root = findNode(root, key);
        size--;
        if (root.left != null)
            root.left.parent = null;
        if (root.right != null)
            root.right.parent = null;
        return merge(root.left, root.right);
    }

    private void inOrder(Node node, StringBuilder s) {
        if (node == null) return;
        inOrder(node.left, s);
        s.append(node.key).append("=").append(node.value).append(", ");
        inOrder(node.right, s);
    }

    private void inOrderHeadMap(Node p, SortedMap<Integer, String> res, Integer toKey) {
        if (p == null) return;
        inOrderHeadMap(p.left, res, toKey);
        if (p.key < toKey)
            res.put(p.key, p.value);
        inOrderHeadMap(p.right, res, toKey);
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap ans = new MyRbMap();
        inOrderHeadMap(root, ans, toKey);
        return ans;
    }

    private void inOrderTailMap(Node p, SortedMap<Integer, String> res, Integer fromKey) {
        if (p == null) return;
        inOrderTailMap(p.left, res, fromKey);
        if (p.key >= fromKey)
            res.put(p.key, p.value);
        inOrderTailMap(p.right, res, fromKey);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap ans = new MyRbMap();
        inOrderTailMap(root, ans, fromKey);
        return ans;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        String res = "{";
        inOrder(root, sb);
        if (sb.length() > 2)
            res = sb.toString().substring(0, sb.length() - 2);
        return res + "}";
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String put(Integer key, String value) {
        Node exist = findKey(key);
        if (exist != null) {
            String prev = exist.value;
            exist.value = value;
            return prev;
        } else {
            size++;
            Node x = insert(key);
            x.value = value;
            return null;
        }
    }

    @Override
    public String remove(Object key) {
        Node exist = findKey((Integer) key);
        if (exist == null)
            return null;
        else {
            String prev = exist.value;
            root = remove(root, (Integer) key);
            return prev;
        }
    }

    @Override
    public String get(Object key) {
        Node x = findKey((Integer) key);
        return x != null ? x.value : null;
    }

    @Override
    public boolean containsKey(Object key) {
        return findKey((Integer) key) != null;
    }

    @Override
    public Integer firstKey() {
        Node p = root;
        while (p != null && p.left != null)
            p = p.left;
        return p != null ? p.key : null;
    }

    @Override
    public Integer lastKey() {
        Node p = root;
        while (p != null && p.right != null)
            p = p.right;
        return p != null ? p.key : null;
    }

    private Integer travelHigherKey(Node p, Integer key, boolean inclusion) {
        Integer ans = (p.key > key) || (inclusion && p.key.equals(key)) ? p.key : null;
        Integer l = p.left != null ? travelHigherKey(p.left, key, inclusion) : null;
        Integer r = p.right != null ? travelHigherKey(p.right, key, inclusion) : null;
        if (ans == null)
            if (l != null)
                ans = l;
            else if (r != null)
                ans = r;
        if (ans != null & l != null && l < ans)
            ans = l;
        if (ans != null && r != null && r < ans)
            ans = r;
        return ans;
    }

    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    private Integer travelLowerKey(Node p, Integer key, boolean inclusion) {
        Integer ans = (p.key < key) || (inclusion && p.key.equals(key)) ? p.key : null;
        Integer l = p.left != null ? travelLowerKey(p.left, key, inclusion) : null;
        Integer r = p.right != null ? travelLowerKey(p.right, key, inclusion) : null;
        if (ans == null)
            if (l != null)
                ans = l;
            else if (r != null)
                ans = r;
        if (ans != null & l != null && l > ans)
            ans = l;
        if (ans != null && r != null && r > ans)
            ans = r;
        return ans;
    }

    @Override
    public Integer lowerKey(Integer key) {
        if (isEmpty())
            return null;
        return travelLowerKey(root, key, false);
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
        if (isEmpty())
            return null;
        return travelLowerKey(root, key, true);
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        if (isEmpty())
            return null;
        return travelHigherKey(root, key, true);
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Integer higherKey(Integer key) {
        if (isEmpty())
            return null;
        return travelHigherKey(root, key, false);
    }

    @Override
    public void clear() {
        while (!isEmpty())
            root = remove(root, root.key);
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
    public NavigableMap<Integer, String> headMap(Integer toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey, boolean inclusive) {
        return null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
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
}
