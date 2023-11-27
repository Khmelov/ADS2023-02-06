package by.it.group251005.ubozhenko.lesson12;


import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MyAvlMap implements Map<Integer, String> {

    private static class Node {
        Integer key;
        String value;
        int height;
        Node left, right;
        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            height = 1;
        }

    }
    int _size = 0;
    Node head = null;

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private int balanceHeight(Node n) {
        return height(n.right) - height(n.left);
    }

    private void fixHeight(Node n) {
        if (n == null) {
            return;
        }
        int hl = height(n.left);
        int hr = height(n.right);
        n.height = (hl > hr ? hl : hr) + 1;
    }

    private Node rotateRight(Node parent) {
        Node child = parent.left;
        parent.left = child.right;
        child.right = parent;
        fixHeight(parent);
        fixHeight(child);
        return child;
    }

    private Node rotateLeft(Node parent) {
        Node child = parent.right;
        parent.right = child.left;
        child.left = parent;
        fixHeight(parent);
        fixHeight(child);
        return child;
    }

    private Node balanceNode(Node parent) {
        if (parent == null) {
            return null;
        }
        fixHeight(parent);
        if (balanceHeight(parent) == 2) {
            if (balanceHeight(parent.right) < 0) {
                parent.right = rotateRight(parent.right);
            }
            return rotateLeft(parent);
        }

        if (balanceHeight(parent) == -2) {
            if (balanceHeight(parent.left) > 0) {
                parent.left = rotateLeft(parent.left);
            }
            return rotateRight(parent);
        }
        return parent;
    }

    private Node search(Node n, Integer key) {
        while(n != null) {
            if ((int)key < (int)n.key) {
                n = n.left;
            } else if ((int)key > (int)n.key) {
                n = n.right;
            } else {
                return n;
            }
        }
        return null;
    }

    private Node insert(Node n, Integer key, String value) {
        if (n == null) return new Node(key, value);
        if ((int)key < (int)n.key) {
            n.left = insert(n.left, key, value);
        } else {
            n.right = insert(n.right, key, value);
        }
        return balanceNode(n);
    }

    private Node findMin(Node n) {
        return n.left == null ? n : findMin(n.left);
    }

    private Node removeMin(Node n) {
        if (n.left == null) {
            return n.right;
        }
        n.left = removeMin(n.left);
        return balanceNode(n);
    }

    private Node remove(Node n, Integer key) {
        if (n == null) return null;
        if ((int)key < (int)n.key) {
            n.left = remove(n.left, key);
        } else if ((int)key > (int)n.key) {
            n.right = remove(n.right, key);
        } else {
            Node q = n.left;
            Node r = n.right;
            if (r == null) return q;
            Node min = findMin(r);
            min.right = removeMin(r);
            min.left = q;
            return balanceNode(min);
        }

        return balanceNode(n);
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
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Node n = search(head, (Integer)key);
        if (n == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node n = search(head, (Integer)key);
        if (n == null) {
            return null;
        }
        return n.value;
    }

    @Override
    public String put(Integer key, String value) {
        Node n = search(head, key);
        if (n == null) {
            head = insert(head, key, value);
            _size++;
            return null;
        }
        String prevValue = n.value;
        n.value = value;
        return prevValue;
    }

    @Override
    public String remove(Object key) {
        Node n = search(head, (Integer) key);
        if (n == null) {
            return null;
        }

        head = remove(head, (Integer) key);
        _size--;
        return n.value;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

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
        _size = 0;
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