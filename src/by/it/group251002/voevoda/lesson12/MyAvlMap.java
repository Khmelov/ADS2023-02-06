package by.it.group251002.voevoda.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {
    private static class Node {
        Integer key;
        String value;
        Node p, left, right;
        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            p = left = right = null;
        }
    }
    private int currHeight = 0;
    private Node root;
    private int size;
    public MyAvlMap() {
        root = null;
        size = 0;
    }

    @Override
    public String toString() {

        if (isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder("{");
        toStringUtil(sb, root);
        return sb.delete(sb.length() - 2, sb.length()).append("}").toString();
    }

    private void toStringUtil(StringBuilder sb, Node node) {
        if (node != null) {
            toStringUtil(sb, node.left);
            sb.append(String.format("%s=%s, ", node.key, node.value));
            toStringUtil(sb, node.right);
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

    @Override
    public boolean containsKey(Object key) {
        Node closestNode = findNode(root, (Integer) key);
        return closestNode != null && closestNode.key.equals(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node n = findNode(root, (Integer) key);
        if (n == null || n.key != key) {
            return null;
        }
        return n.value;
    }

    @Override
    public String put(Integer key, String value) {
        Node closestNode = findNode(root, key);
        if (closestNode != null && closestNode.key.equals(key)) {
            String result = closestNode.value;
            closestNode.value = value;
            return result;
        }

        Node ptr = bstInsert(key, value);
        fixBF(ptr);
        return null;
    }

    @Override
    public String remove(Object key) {
        Node z = findNode(root, (Integer) key);
        if (z == null || z.key != key) {
            return null;
        }

        String result = z.value;
        Node y = z;
        Node x = null;

        if (z.left == null) {
            x = z.right;
            transplant(z, x);
        } else if (z.right == null) {
            x = z.left;
            transplant(z, x);
        } else {
            y = findMinNode(z.right);
            x = y.right;
            if (x == null) {
                x = y.p;
            }
            if (y.p != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.p = y;
        }

        fixBF(x);

        --size;

        return result;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        root = null;
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

    private Node findNode(Node ptr, Integer key) {
        if (ptr == null) {
            return null;
        }

        if (ptr.key == key) {
            return ptr;
        } else if (key < ptr.key && ptr.left != null) {
            return findNode(ptr.left, key);
        } else if (key > ptr.key && ptr.right != null) {
            return findNode(ptr.right, key);
        }

        return ptr;
    }

    private Node findMinNode(Node ptr) {
        if (ptr == null) {
            return null;
        }

        while (ptr.left != null) {
            ptr = ptr.left;
        }
        return ptr;
    }

    private Node bstInsert(Integer key, String value) {
        Node second = new Node(key, value);
        Node x = second;

        Node closestNode = findNode(root, key);
        if (closestNode == null) {
            root = second;
        } else if (key == closestNode.key) {
            closestNode.value = value;
            x = closestNode;
        } else if (key < closestNode.key) {
            closestNode.left = second;
            second.p = closestNode;
        } else {
            closestNode.right = second;
            second.p = closestNode;
        }

        ++size;
        return x;
    }

    private void fixBF(Node z) {
        if (z == null) {
            return;
        }

        while (z.p != null && z.p.p != null) {
            if (bfIsValid(z.p.p)) {
                z = z.p;
                continue;
            }

            if (z.p == z.p.p.left) {
                if (z == z.p.right) {
                    z = z.p;
                    leftRotate(z);
                }
                rightRotate(z.p.p);
            } else {
                if (z == z.p.left) {
                    z = z.p;
                    rightRotate(z);
                }
                leftRotate(z.p.p);
            }
        }
    }

    private void leftRotate(Node x) {
        if (x == null || x.right == null) {
            return;
        }

        Node y = x.right;
        Node beta = y.left;

        x.right = beta;
        if (beta != null) {
            beta.p = x;
        }

        y.left = x;

        Node z = x.p;
        if (z == null) {
            root = y;
        } else if (z.left == x) {
            z.left = y;
        } else {
            z.right = y;
        }
        y.p = z;
        x.p = y;
    }

    private void rightRotate(Node y) {
        if (y == null || y.left == null) {
            return;
        }

        Node x = y.left;
        Node beta = x.right;

        y.left = beta;
        if (beta != null) {
            beta.p = y;
        }

        x.right = y;

        Node z = y.p;
        if (z == null) {
            root = x;
        } else if (z.left == y) {
            z.left = x;
        } else {
            z.right = x;
        }
        x.p = z;
        y.p = x;
    }

    // BF - balance factor
    private int calcBF(Node ptr) {
        if (ptr == null) {
            return 0;
        }

        currHeight = 0;
        calcHeight(ptr.left, 0);
        int leftHeight = currHeight;

        currHeight = 0;
        calcHeight(ptr.right, 0);
        int rightHeight = currHeight;

        return leftHeight - rightHeight;
    }

    private boolean bfIsValid(Node ptr) {
        int bf = calcBF(ptr);
        return -1 <= bf && bf <= 1;
    }

    private void calcHeight(Node ptr, int h) {
        if (ptr == null) {
            if (h > currHeight) {
                currHeight = h;
            }
            return;
        }
        ++h;
        calcHeight(ptr.left, h);
        calcHeight(ptr.right, h);
    }

    private void transplant(Node u, Node v) {
        if (u.p == null) {
            root = v;
        } else if (u.p.left == u) {
            u.p.left = v;
        } else {
            u.p.right = v;
        }
        if (v != null) {
            v.p = u.p;
        }
    }

    public static void main(String[] args) {
        MyAvlMap m = new MyAvlMap();
        m.put(40, "");
        m.put(20, "");
        m.put(10, "");
        m.put(30, "");
        m.put(25, "");
        m.put(35, "");
        m.put(37, "");
        m.put(41, "");
        m.put(99, "");
        m.put(100, "");
        m.put(101, "");
        m.put(102, "");
        System.out.println(m);
        return;
    }
}
