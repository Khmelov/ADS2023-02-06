package by.it.group251004.stasevich.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {

    class Node {
        Integer key;
        String value;
        Node parent,left, right;

        Node(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    Node root;

    @Override
    public int size() {
        return size(root);
    }

    int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return containsValue(root, value.toString());
    }

    boolean containsValue(Node node, String value) {
        if (node == null) {
            return false;
        }
        if (node.value.equals(value)) {
            return true;
        }
        return containsValue(node.left, value) || containsValue(node.right, value);
    }

    @Override
    public String get(Object key) {
        Node found = searchNode((Integer) key, root);
        if (found != null) {
            root = splay(root, found.key);
            return found.value;
        }
        return null;
    }

    Node searchNode(Integer key, Node node) {
        if (node == null)
            return null;
        int comparison = key.compareTo(node.key);
        if (comparison == 0)
            return node;

        return searchNode(key, comparison < 0 ? node.left : node.right);
    }

    @Override
    public String put(Integer key, String value) {
        if (root == null) {
            root = new Node(key, value);
            return null;
        }

        root = splay(root, key);
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            String oldValue = root.value;
            root.value = value;
            return oldValue;
        } else if (cmp < 0) {
            Node newNode = new Node(key, value);
            newNode.left = root.left;
            newNode.right = root;
            newNode.right.parent = newNode;
            root.left = null;
            root = newNode;
        } else {
            Node newNode = new Node(key, value);
            newNode.right = root.right;
            newNode.left = root;
            newNode.left.parent = newNode;
            root.right = null;
            root = newNode;
        }
        return null;
    }

    @Override
    public String remove(Object key) {
        if (root == null) {
            return null;
        }

        root = splay(root, (Integer) key);
        int cmp = ((Integer) key).compareTo(root.key);
        if (cmp != 0) {
            return null;
        }

        String removedValue = root.value;

        if (root.left == null) {
            root = root.right;
            if (root != null) {
                root.parent = null;
            }
        } else {
            Node newroot = root.right;
            newroot = splay(newroot, (Integer) key);
            newroot.left = root.left;
            newroot.left.parent = newroot;
            root = newroot;
        }

        return removedValue;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    Node splay(Node node, Integer key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (node.left == null) {
                return node;
            }
            int cmp2 = key.compareTo(node.left.key);
            if (cmp2 < 0) {
                node.left.left = splay(node.left.left, key);
                node = rotateRight(node);
            } else if (cmp2 > 0) {
                node.left.right = splay(node.left.right, key);
                if (node.left.right != null) {
                    node.left = rotateLeft(node.left);
                }
            }
            if (node.left == null) {
                return node;
            } else {
                return rotateRight(node);
            }
        } else if (cmp > 0) {
            if (node.right == null) {
                return node;
            }
            int cmp2 = key.compareTo(node.right.key);
            if (cmp2 < 0) {
                node.right.left = splay(node.right.left, key);
                if (node.right.left != null) {
                    node.right = rotateRight(node.right);
                }
            } else if (cmp2 > 0) {
                node.right.right = splay(node.right.right, key);
                node = rotateLeft(node);
            }
            if (node.right == null) {
                return node;
            } else {
                return rotateLeft(node);
            }
        } else {
            return node;
        }
    }

    Node rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.right = node;
        leftChild.parent = node.parent;
        node.parent = leftChild;
        return leftChild;
    }

    Node rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.left = node;
        rightChild.parent = node.parent;
        node.parent = rightChild;
        return rightChild;
    }

    @Override
    public void clear() {
        root = clear(root);
    }

    Node clear(Node node) {
        if (node == null)
            return null;
        node.left = clear(node.left);
        node.right = clear(node.right);
        return null;
    }

    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Integer lowerKey(Integer key) {
        if (root == null)
            return null;
        Node node = lowerKeyNode(key, root);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    Node lowerKeyNode(Integer key, Node node) {
        if (node == null)
            return null;
        int comparison = key.compareTo(node.key);
        if (comparison <= 0)
            return lowerKeyNode(key, node.left);
        Node rightResult = lowerKeyNode(key, node.right);
        if (rightResult != null)
            return rightResult;
        return node;
    }

    @Override
    public Integer floorKey(Integer key) {
        if (root == null)
            return null;
        Node node = floorKeyNode(key, root);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    Node floorKeyNode(Integer key, Node node) {
        if (node == null)
            return null;
        int comparison = key.compareTo(node.key);
        if (comparison == 0)
            return node;
        if (comparison < 0)
            return floorKeyNode(key, node.left);
        Node rightResult = floorKeyNode(key, node.right);
        if (rightResult != null)
            return rightResult;
        return node;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        if (root == null)
            return null;
        Node node = ceilingKeyNode(key, root);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    Node ceilingKeyNode(Integer key, Node node) {
        if (node == null)
            return null;
        int comparison = key.compareTo(node.key);
        if (comparison == 0)
            return node;
        if (comparison > 0)
            return ceilingKeyNode(key, node.right);
        Node leftResult = ceilingKeyNode(key, node.left);
        if (leftResult != null)
            return leftResult;
        return node;
    }

    @Override
    public Integer higherKey(Integer key) {
        if (root == null)
            return null;
        Node node = higherKeyNode(key, root);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    @Override
    public String toString() {
        if (root == null)
            return "{}";
        StringBuilder sb = new StringBuilder().append("{");
        print(root, sb);
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("}");
        return sb.toString();
    }

    void print(Node node, StringBuilder sb) {
        if (node != null) {
            print(node.left, sb);
            sb.append(node.key + "=" + node.value + ", ");
            print(node.right, sb);
        }
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
    public NavigableMap<Integer, String> headMap(Integer toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey, boolean inclusive) {
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

    Node higherKeyNode(Integer key, Node node) {
        if (node == null)
            return null;
        int comparison = key.compareTo(node.key);
        if (comparison >= 0)
            return higherKeyNode(key, node.right);
        Node leftResult = higherKeyNode(key, node.left);
        if (leftResult != null)
            return leftResult;
        return node;
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MySplayMap subMap = new MySplayMap();
        headMap(root, toKey, subMap);
        return subMap;
    }

    void headMap(Node node, Integer toKey, MySplayMap subMap) {
        if (node == null) {
            return;
        }

        if (node.key.compareTo(toKey) < 0) {
            subMap.put(node.key, node.value);
            headMap(node.right, toKey, subMap);
        }

        headMap(node.left, toKey, subMap);
    }


    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MySplayMap subMap = new MySplayMap();
        tailMap(root, fromKey, subMap);
        return subMap;
    }

    void tailMap(Node node, Integer fromKey,MySplayMap subMap) {
        if (node == null) {
            return;
        }

        if (node.key.compareTo(fromKey) >= 0) {
            subMap.put(node.key, node.value);
            tailMap(node.left, fromKey, subMap);
        }

        tailMap(node.right, fromKey, subMap);
    }

    @Override
    public Integer firstKey() {
        if (root == null)
            return null;
        Node node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    @Override
    public Integer lastKey() {
        if (root == null)
            return null;
        Node node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.key;
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
