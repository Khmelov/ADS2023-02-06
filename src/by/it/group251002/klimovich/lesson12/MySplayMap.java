package by.it.group251002.klimovich.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {

    private static class Node {
        int key;
        String elem;
        Node parent;
        Node left;
        Node right;

        public Node(Integer key, String elem) {
            this.key = key;
            this.elem = elem;
            this.left = null;
            this.right = null;
            this.parent = null;
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
            if (n==n.parent.left){
                n.parent.left = child;
            }
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
            if (n==n.parent.left){
                n.parent.left = child;
            }
            else n.parent.right = child;
        }
        n.parent = child;
        return child;
    }

    private Node splay(Node n) {
        if (n != null) {
            while (n.parent != null) {
                if (n.parent.parent == null) {
                    if (n==n.parent.left){
                        n = rightRotate(n.parent);
                    }
                    else {
                        n = leftRotate(n.parent);
                    }
                } else if (n==n.parent.right && n.parent.parent.left==n.parent) {
                    n = leftRotate(n.parent);
                    n = rightRotate(n.parent);
                } else if (n==n.parent.left && n.parent.parent.right==n.parent) {
                    n = rightRotate(n.parent);
                    n = leftRotate(n.parent);
                } else if (n==n.parent.left && n.parent.parent.left==n.parent) {
                    n = rightRotate(n.parent);
                    n = rightRotate(n.parent);
                } else {
                    n = leftRotate(n.parent);
                    n = leftRotate(n.parent);
                }
            }
            return n;
        }
        return null;
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
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Integer lowerKey(Integer key) {
        Node lower = null;
        Node n=head;
        while (n != null) {
            if (key <= n.key) {
                n = n.left;
            } else {
                lower = n;
                n = n.right;
            }
        }
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

    @Override
    public Integer floorKey(Integer key) {
        if (find(head, key) != null) {
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
        if (find(head, key) != null) {
            return key;
        }
        return higherKey(key);
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Integer higherKey(Integer key) {
        Node higher = null;
        Node n=head;
        while (n != null) {
            if (key < n.key) {
                higher = n;
                n = n.left;
            } else {
                n = n.right;
            }
        }
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
            newMap.put(n.key, n.elem);
            headToKey(newMap, toKey, n.right);
        }
    }

    private void tailFromKey(SortedMap<Integer, String> newMap, Integer fromKey, Node n) {
        if (n == null) {
            return;
        }

        tailFromKey(newMap, fromKey, n.right);
        if (fromKey <= n.key) {
            newMap.put(n.key, n.elem);
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
        Node n = find(head, (int) key);
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
                if (value.equals(curNode.elem)) {
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
        Node n = find(head, (int) key);
        if (n == null) {
            return null;
        }

        head = splay(n);
        return head.elem;
    }

    @Override
    public String put(Integer key, String value) {
        Node n = find(head, key);
        if (n != null) {
            String prevValue = n.elem;
            n.elem = value;
            head = splay(n);
            return prevValue;
        }
        size++;
        Node prevNode = null;
        n = head;
        while (n != null) {
            prevNode = n;
            if (key < n.key) {
                n = n.left;
            } else if (key > n.key) {
                n = n.right;
            }
        }
        n = new Node(key, value);
        n.parent = prevNode;
        if (prevNode == null) {
            head = n;
        } else if (prevNode.key < n.key) {
            prevNode.right = n;
        } else if (prevNode.key > n.key) {
            prevNode.left = n;
        }
        head = splay(n);
        return null;
    }

    @Override
    public String remove(Object key) {
        Node n = find(head, (int) key);
        if (n != null) {
            size--;
            Node node;
            node=n;
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
            return n.elem;
        }

        return null;
    }

    private Node findMax(Node n) {
        if (n == null) {
            return null;
        }
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {
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