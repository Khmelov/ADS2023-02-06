package by.it.group251003.bondarenko.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {

    private static class Node {
        Integer key;
        String value;
        Node parent, left, right;
        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            this.left = this.right = this.parent = null;
        }
    }

    int size = 0;
    Node head = null;

    private Node leftRotate(Node n) {
        Node child = n.right;
        if (child != null) {
            n.right = child.left;
            if (child.left != null) {
                child.left.parent = n;
            }
            child.parent = n.parent;
            child.left = n;
        }

        if (n.parent != null) {
            if (n.equals(n.parent.left)) n.parent.left = child;
            else n.parent.right = child;
        }
        n.parent = child;
        return child;
    }

    private Node rightRotate(Node n) {
        Node child = n.left;
        if (child != null) {
            n.left = child.right;
            if (child.right != null) {
                child.right.parent = n;
            }
            child.parent = n.parent;
            child.right = n;
        }

        if (n.parent != null) {
            if (n.equals(n.parent.left)) n.parent.left = child;
            else n.parent.right = child;
        }
        n.parent = child;
        return child;
    }

    private Node search(Node n, Integer key) {
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

    private Node splay(Node n) {
        if (n == null) {
            return null;
        }

        while(n.parent != null) {
            if (n.parent.parent == null) {
                if (n.equals(n.parent.left)) n = rightRotate(n.parent);
                else n = leftRotate(n.parent);
            } else if (n.equals(n.parent.left) && n.parent.parent.left == n.parent) {
                n = rightRotate(n.parent);
                n = rightRotate(n.parent);
            } else if (n.equals(n.parent.left) && n.parent.parent.right == n.parent) {
                n = rightRotate(n.parent);
                n = leftRotate(n.parent);
            } else if (n.equals(n.parent.right) && n.parent.parent.left == n.parent) {
                n = leftRotate(n.parent);
                n = rightRotate(n.parent);
            } else {
                n = leftRotate(n.parent);
                n = leftRotate(n.parent);
            }
        }

        return n;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        String delimiter = "";
        Stack<Node> s = new Stack<Node>();
        Node curNode = head;
        while (!s.isEmpty() || curNode != null) {
            if (curNode != null) {
                s.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = s.pop();
                sb.append(delimiter).append(curNode.key).append("=").append(curNode.value);
                delimiter = ", ";
                curNode = curNode.right;
            }
        }

        sb.append("}");
        return sb.toString();
    }
    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    private Node findLower(Node n, Integer key) {
        Node lower = null;
        while (n != null) {
            if (key <= n.key) {
                n = n.left;
            } else {
                lower = n;
                n = n.right;
            }
        }

        return lower;
    }
    @Override
    public Integer lowerKey(Integer key) {
        Node lower = findLower(head, key);
        if (lower == null) {
            return null;
        }
        head = splay(lower);

        return lower.key;
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
        if (search(head, key) != null) {
            return key;
        }

        return lowerKey(key);
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        if (search(head, key) != null) {
            return key;
        }
        return higherKey(key);
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    private Node findHigher(Node n, Integer key) {
        Node higher = null;
        while (n != null) {
            if (key < n.key) {
                higher = n;
                n = n.left;
            } else {
                n = n.right;
            }
        }

        return higher;
    }
    @Override
    public Integer higherKey(Integer key) {
        Node higher = findHigher(head, key);
        if (higher == null) {
            return null;
        }
        head = splay(higher);

        return higher.key;
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

    private void headToKey(SortedMap<Integer, String> newMap, Integer toKey, Node n) {
        if (n == null) {
            return;
        }

        headToKey(newMap, toKey, n.left);
        if (toKey > n.key) {
            newMap.put(n.key, n.value);
            headToKey(newMap, toKey, n.right);
        }
    }

    private void tailFromKey(SortedMap<Integer, String> newMap, Integer fromKey, Node n) {
        if (n == null) {
            return;
        }

        tailFromKey(newMap, fromKey, n.right);
        if (fromKey <= n.key) {
            newMap.put(n.key, n.value);
            tailFromKey(newMap, fromKey, n.left);
        }
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        SortedMap<Integer, String> newMap = new MyRbMap();
        headToKey(newMap, toKey, head);
        return newMap;
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        SortedMap<Integer, String> newMap = new MyRbMap();
        tailFromKey(newMap, fromKey, head);
        return newMap;
    }

    @Override
    public Integer firstKey() {
        if (head == null) {
            return null;
        }

        Node n = head;
        while (n.left != null) {
            n = n.left;
        }

        head = splay(n);
        return head.key;
    }

    @Override
    public Integer lastKey() {
        if (head == null) {
            return null;
        }

        Node n = head;
        while (n.right != null) {
            n = n.right;
        }

        head = splay(n);
        return head.key;
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
        Node n = search(head, (int)key);
        if (n == null) {
            return false;
        }

        head = splay(n);
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        Stack<Node> s = new Stack<Node>();
        Node curNode = head;
        while (!s.isEmpty() || curNode != null) {
            if (curNode != null) {
                s.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = s.pop();
                if (value.equals(curNode.value)) {
                    head = splay(curNode);
                    return true;
                }
                curNode = curNode.right;
            }
        }
        return false;
    }

    @Override
    public String get(Object key) {
        Node n = search(head, (int)key);
        if (n == null) {
            return null;
        }

        head = splay(n);
        return head.value;
    }

    @Override
    public String put(Integer key, String value) {
        Node n = search(head, key);
        if (n != null) {
            String oldValue = n.value;
            n.value = value;
            head = splay(n);
            return oldValue;
        }

        size++;
        Node preInsert = null;
        n = head;
        while(n != null) {
            preInsert = n;
            if (key < n.key) {
                n = n.left;
            } else if (key > n.key) {
                n = n.right;
            }
        }

        n = new Node(key, value);
        n.parent = preInsert;

        if (preInsert == null) {
            head = n;
        } else if (preInsert.key < n.key) {
            preInsert.right = n;
        } else if (preInsert.key > n.key) {
            preInsert.left = n;
        }
        head = splay(n);
        return null;
    }

    @Override
    public void clear() {
        Stack<Node> s = new Stack<Node>();
        Node lastVisited = null, curNode = head;
        while (!s.isEmpty() || curNode != null) {
            if (curNode != null) {
                s.push(curNode);
                lastVisited = curNode;
                curNode = curNode.left;
            } else {
                curNode = s.pop();
                if (lastVisited != null && lastVisited != curNode) {
                    lastVisited.right = null;
                }
                lastVisited = curNode;
                curNode.left = null;
                curNode = curNode.right;
            }
        }
        head.left = null;
        head.right = null;
        head = null;
        size = 0;
    }

    @Override
    public String remove(Object key) {
        Node n = search(head, (int)key);
        if (n != null) {
            size--;
            remove(n);
            return n.value;
        }

        return null;
    }

    private void remove(Node node) {
        if (node == null) {
            return;
        }

        head = splay(node);

        if (node.left == null) {
            head = head.right;
            if (head != null) {
                head.parent = null;
            }
        } else {
            Node tmp = head;
            head = splay(findMax(head.left));
            head.right = tmp.right;
            tmp.right.parent = head;
        }
    }

    private Node findMax(Node n) {
        if (n == null) {
            return null;
        }

        while(n.right != null) {
            n = n.right;
        }

        return n;
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
}
