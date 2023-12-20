package by.it.group251001.brutskaya.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {
    class Node {
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

    private Node getMax(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node getMin(Node node) {
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
        if (isEmpty())
            return "{}";
        StringBuilder sb = new StringBuilder();
        String delimiter = "";
        sb.append("{");
        Stack<Node> nodestack = new Stack<>();
        Node current = root;
        while (!nodestack.isEmpty() || current != null) {
            if (current != null) {
                nodestack.push(current);
                current = current.left;
            } else {
                current = nodestack.pop();
                sb.append(delimiter).append(current.key).append("=").append(current.value);
                delimiter = ", ";
                current = current.right;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public Integer lowerKey(Integer key) {
        return getLowerKey(root, key).key;
    }

    private Node getLowerKey(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            Node lowerInRight = getLowerKey(node.right, key);
            return (lowerInRight != null) ? lowerInRight : node;
        } else {
            if (cmp < 0) {
                return getLowerKey(node.left, key);
            } else {
                if (node.left != null) {
                    return getMax(node.left);
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
        Node node = getFloorKey(root, key);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    private Node getFloorKey(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp >= 0) {
            Node floorInLeft = getFloorKey(node.right, key);
            return (floorInLeft != null) ? floorInLeft : node;
        } else  {
            return getFloorKey(node.left, key);
        }
    }

    @Override
    public Integer ceilingKey(Integer key) {
        return getCeilingKey(root, key).key;
    }

    private Node getCeilingKey(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return getCeilingKey(node.right, key);
        } else  {
            Node ceilingInLeft = getCeilingKey(node.left, key);
            return ceilingInLeft != null ? ceilingInLeft : node;
        }
    }

    @Override
    public Integer higherKey(Integer key) {
        return getHigherKey(root, key).key;
    }

    private Node getHigherKey(Node node, Integer key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp >= 0) {
            return getHigherKey(node.right, key);
        } else {
            Node higherInLeft = getHigherKey(node.left, key);
            return higherInLeft != null ? higherInLeft : node;
        }
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MySplayMap hm = new MySplayMap();
        headMap(root, toKey, hm);
        return hm;
    }

    private void headMap(Node node, Integer key, MySplayMap hm) {
        if (node == null) {
            return;
        }
        if (node.key.compareTo(key) < 0) {
            hm.put(node.key, node.value);
            headMap(node.right, key, hm);
        }
        headMap(node.left, key, hm);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MySplayMap tm = new MySplayMap();
        tailMap(root, fromKey, tm);
        return tm;
    }

    private void tailMap(Node node, Integer key, MySplayMap tm) {
        if (node == null) {
            return;
        }
        if (node.key.compareTo(key) >= 0) {
            tm.put(node.key, node.value);
            tailMap(node.left, key, tm);
        }
        tailMap(node.right, key, tm);
    }

    @Override
    public Integer firstKey() {
        return getMin(root).key;
    }

    @Override
    public Integer lastKey() {
        return getMax(root).key;
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
            Node minRight = getMin(node.right);
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
