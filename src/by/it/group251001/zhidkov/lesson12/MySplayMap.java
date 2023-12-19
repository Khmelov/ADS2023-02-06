package by.it.group251001.zhidkov.lesson12;
import java.util.*;
public class MySplayMap implements NavigableMap<Integer, String> {
    static private class Node {
        Integer key;
        String value;
        Node left;
        Node right;
        Node parent;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size = 0;
    private Node root = null;

    private Node Max(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node Min(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        return x;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("{");
        if (root != null) {
            toStringHelper(root, res);
            res.replace(res.length() - 2, res.length(), "}");
        }
        else
            res.append("}");
        return res.toString();
    }

    private void toStringHelper(Node node, StringBuilder res) {
        if (node != null) {
            if (node.left != null) {
                toStringHelper(node.left, res);
            }
            res.append(node.key).append("=").append(node.value).append(", ");
            if (node.right != null) {
                toStringHelper(node.right, res);
            }
        }
    }

    @Override
    public Integer lowerKey(Integer key) {
        return LowerKey(root, key).key;
    }

    private Node LowerKey(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            Node lowerInRight = LowerKey(node.right, key);
            return (lowerInRight != null) ? lowerInRight : node;
        } else {
            if (cmp < 0) {
                return LowerKey(node.left, key);
            } else {
                if (node.left != null) {
                    return Max(node.left);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public Integer floorKey(Integer key) {
        if (root == null) {
            return null;
        }
        Node node = FloorKey(root, key);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    private Node FloorKey(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp >= 0) {
            Node floorInLeft = FloorKey(node.right, key);
            return (floorInLeft != null) ? floorInLeft : node;
        } else  {
            return FloorKey(node.left, key);
        }
    }

    @Override
    public Integer ceilingKey(Integer key) {
        return CeilingKey(root, key).key;
    }

    private Node CeilingKey(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return CeilingKey(node.right, key);
        } else  {
            Node ceilingInLeft = CeilingKey(node.left, key);
            return ceilingInLeft != null ? ceilingInLeft : node;
        }
    }

    @Override
    public Integer higherKey(Integer key) {
        return HigherKey(root, key).key;
    }

    private Node HigherKey(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp >= 0) {
            return HigherKey(node.right, key);
        } else {
            Node higherInLeft = HigherKey(node.left, key);
            return higherInLeft != null ? higherInLeft : node;
        }
    }

    private SortedMap<Integer, String> resMap(Node node, Integer key, boolean isHead) {
        MySplayMap Map = new MySplayMap();
        subMap(node, key, Map, isHead);
        return Map;
    }
    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        return resMap(root, toKey, true);
    }

    private void subMap(Node node, Integer key, MySplayMap map, boolean isHead) {
        if (node == null) {
            return;
        }
        if (isHead) {
            if (node.key.compareTo(key) < 0) {
                map.put(node.key, node.value);
                subMap(node.right, key, map, isHead);
            }
            subMap(node.left, key, map, isHead);
        }
        else {
            if (node.key.compareTo(key) >= 0) {
                map.put(node.key, node.value);
                subMap(node.left, key, map, isHead);
            }
            subMap(node.right, key, map, isHead);
        }
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        return resMap(root, fromKey, false);
    }

    @Override
    public Integer firstKey() {
        return Min(root).key;
    }

    @Override
    public Integer lastKey() {
        return Max(root).key;
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
    public void clear() {
        size = 0;
        root = null;

    }

    @Override
    public boolean containsKey(Object key) {
        return get(key)!=null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null || !(value instanceof String)) {
            return false;
        }
        return containsValue(root, (String) value);
    }

    public boolean containsValue(Node node, String value) {
        if (node == null) {
            return false;
        }
        if (node.value.equals(value)) {
            return true;
        }
        return containsValue(root.right, value) || containsValue(root.left, value);
    }


    private Node splay(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (node.left == null) {
                return node;
            }
            //ZIG _ ZIG
            if (key.compareTo(node.left.key) < 0) {
                node.left.left = splay(node.left.left, key);
                node = rotateRight(node);
                //ZIG_ZAG
            } else if (key.compareTo(node.left.key) > 0) {
                node.left.right = splay(node.left.right, key);
                if (node.left.right != null) {
                    node.left = rotateLeft(node.left);
                }
            }
            return (node.left == null) ? node : rotateRight(node);
        } else if (cmp > 0) {
            if (node.right == null) {
                return node;
            }
            //ZAG_ZAG
            if (key.compareTo(node.right.key) < 0) {
                node.right.left = splay(node.right.left, key);
                if (node.right.left != null) {
                    node.right = rotateRight(node.right);
                }
                //ZAG_ZIG
            } else if (key.compareTo(node.right.key) > 0) {
                node.right.right = splay(node.right.right, key);
                node = rotateLeft(node);
            }
            return (node.right == null) ? node : rotateLeft(node);
        } else {
            return node;
        }
    }

    @Override
    public String get(Object key) {
        Node node = search(root, (Integer) key);
        if (node == null) {
            return null;
        }
        root = splay(root, node.key);
        return node.value;
    }

    private Node search(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return search(node.left, key);
        } else if (cmp > 0) {
            return search(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public String put(Integer key, String value) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new Node(key, value);
            size++;
            return null;
        }
        String prev = get(key);
        root = insert(root, key, value);
        if (prev == null) {
            size++;
        }
        return prev;
    }

    private Node insert(Node node, Integer key, String value) {
        if (node == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }
        return splay(node, key);
    }

    @Override
    public String remove(Object key) {
        if (root == null || key == null || !(key instanceof Integer)) {
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
            Node newRoot = root.right;
            if (newRoot != null) {
                newRoot = splay(newRoot, (Integer) key);
                newRoot.left = root.left;
                newRoot.left.parent = newRoot;
                root = newRoot;
            } else {
                root = root.left;
                root.parent = null;
            }
        }
        size--;
        return removedValue;
    }


    private Node remove(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = remove(node.right, key);
        } else if (cmp < 0) {
            node.left = remove(node.left, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            Node minRight = Min(node.right);
            node.key = minRight.key;
            node.value = minRight.value;
            node.right = remove(node.right, minRight.key);
        }
        return node;
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
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
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
}
