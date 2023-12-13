package by.it.group251002.urbanovich.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String> {
    private class Node {
        Integer key;
        String value;
        boolean color;
        Node left, right, parent;

        Node(Integer k) {
            key = k;
            value = null;
            color = false;
            left = right = parent = null;
        }
    }


    Node root = null;
    int size = 0;

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
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

    @Override
    public int size() {
        return size;
    }

    private boolean getColor(Node p) {
        return p == null ? false : p.color;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private Node FindKey(Object key) {
        Node p = root;
        while (p != null && !p.key.equals(key)) {
            if ((Integer) key < p.key)
                p = p.left;
            else
                p = p.right;
        }
        return p;
    }

    @Override
    public boolean containsKey(Object key) {
        return FindKey(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node p = FindKey(key);
        return p != null ? p.value : null;
    }

    private void rotateLeft(Node n) {
        Node a = n, b = a.right, l = a.left, c = b.left, r = b.right;

        a.right = c;
        if (c != null)
            c.parent = a;
        b.parent = a.parent;
        if (b.parent != null)
            if (b.parent.left == a)
                b.parent.left = b;
            else
                b.parent.right = b;
        a.parent = b;
        b.left = a;
        if (root == a)
            root = b;
    }

    private void rotateRight(Node n) {
        Node a = n, b = a.left, l = b.left, c = b.right, r = a.right;
        a.left = c;
        if (c != null)
            c.parent = a;
        b.parent = a.parent;
        if (b.parent != null)
            if (b.parent.left == a)
                b.parent.left = b;
            else
                b.parent.right = b;
        a.parent = b;
        b.right = a;
        if (root == a)
            root = b;
    }

    private Node getParent(Node n) {
        return n.parent;
    }

    private Node getGrandparent(Node n) {
        if (n.parent != null)
            return n.parent.parent;
        return null;
    }

    private Node getUncle(Node n) {
        Node g = getGrandparent(n);
        if (g != null) {
            if (n.parent == g.left)
                return g.right;
            else
                return g.left;
        }
        return null;
    }

    private void reorient(Node t) {
        if (root == t) {
            t.color = false;
            return;
        }
        while (getColor(t.parent) == true) {
            Node p = getParent(t), g = getGrandparent(t), u = getUncle(t);
            if (getColor(p) == true && getColor(u) == true) {
                p.color = false;
                u.color = false;
                g.color = true;
                t = g;
            } else {
                if (p != null && p.right == t) {
                    rotateLeft(p);
                    Node temp = t;
                    t = p;
                    p = temp;
                }
                if (g != null && g.left == p)
                    rotateRight(g);
                else if (g != null && g.right == p)
                    rotateLeft(g);
                if (p != null)
                    p.color = false;
                if (g != null)
                    g.color = true;
                if (u != null)
                    u.color = false;
                t = root;
            }
        }
        root.color = false;
    }

    private void insertKey(Integer key) {
        Node t = new Node(key);
        t.color = true;
        if (isEmpty())
            root = t;
        else {
            Node p = root;
            Node q = null;
            while (p != null) {
                q = p;
                if (p.key < t.key)
                    p = p.right;
                else
                    p = p.left;
            }
            t.parent = q;
            if (q.key < t.key)
                q.right = t;
            else
                q.left = t;
        }
        reorient(t);
    }

    @Override
    public String put(Integer key, String value) {
        String prev = get(key);
        if (!containsKey(key)) {
            insertKey(key);
            size++;
        }
        FindKey(key).value = value;
        return prev;
    }

    private void deleteLeafNode(Node del) {
        if (del == root) {
            root = null;
            del = null;
        } else {
            if (del.parent.left == del)
                del.parent.left = null;
            else
                del.parent.right = null;
            del = null;
        }
    }

    private Node findFirstGreater(Node p) {
        p = p.right;
        while (p.left != null)
            p = p.left;
        return p;
    }

    private Node getBrother(Node t) {
        Node p = getParent(t);
        if (p != null)
            return p.right == t ? p.left : p.right;
        return null;
    }

    private Node getLeftNephew(Node t) {
        Node b = getBrother(t);
        return b != null ? b.left : null;
    }

    private Node getRightNephew(Node t) {
        Node b = getBrother(t);
        return b != null ? b.right : null;
    }

    private void fixDeleting(Node x) {
        Node p = getParent(x), b = getBrother(x), ln = getLeftNephew(x), rn = getRightNephew(x);
        if (b != null) {
            if (!getColor(x) && !getColor(p) && getColor(b) && !getColor(ln) && !getColor(rn)) {
                rotateLeft(p);
                p = getParent(x);
                b = getBrother(x);
                ln = getLeftNephew(x);
                rn = getRightNephew(x);
                if (!getColor(x) && !getColor(b) && getColor(ln) && !getColor(rn)) {
                    rotateRight(b);
                    b.color = true;
                    ln.color = false;
                    p = getParent(x);
                    b = getBrother(x);
                    ln = getLeftNephew(x);
                    rn = getRightNephew(x);
                }
                if (!getColor(x) && !getColor(b) && getColor(rn)) {
                    rotateLeft(p);
                    p.color = false;
                    rn.color = false;
                    p = getParent(x);
                    b = getBrother(x);
                    ln = getLeftNephew(x);
                    rn = getRightNephew(x);
                }
            }
            if (b != null && !getColor(x) && getColor(p) && !getColor(b) && !getColor(ln) && !getColor(rn)) {
                p.color = false;
                b.color = true;
                fixDeleting(p);
            }
        }
    }

    @Override
    public String remove(Object key) {
        return removeNode(FindKey(key));
    }

    private String removeNode(Node del) {
        if (del == null)
            return null;
        String res = del.value;
        if (del.left == null && del.right == null) {
            deleteLeafNode(del);
            --size;
        } else if (del.left == null || del.right == null) {
            --size;
            if (del.right != null) {
                del.right.parent = del.parent;
                if (del.parent != null) {
                    if (del.parent.left == del)
                        del.parent.left = del.right;
                    else
                        del.parent.right = del.right;
                } else
                    root = del.right;
                if (getColor(del.parent) && del.right.color)
                    del.right.color = false;
                del = null;
            } else {
                del.left.parent = del.parent;
                if (del.parent != null) {
                    if (del.parent.left == del)
                        del.parent.left = del.left;
                    else
                        del.parent.right = del.left;
                } else
                    root = del.left;
                if (getColor(del.parent) && del.left.color)
                    del.left.color = false;
                del = null;
            }
        } else {
            Node x = findFirstGreater(del);
            String tempStr = x.value;
            Integer tempKey = x.key;
            x.value = del.value;
            x.key = del.key;
            del.value = tempStr;
            del.key = tempKey;
            if (!x.color)
                fixDeleting(x);
            removeNode(x);
        }
        return res;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        while (!isEmpty())
            remove(root.key);
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
