package by.it.group251003.gabrus.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {
    private int size = 0;
    private Node head = null;
    private static class Node {
        int key;
        String value;
        int height = 1;
        Node left;
        Node right;
        Node (int key, String value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
    private int height (Node p) {
        return p != null ? p.height : 0;
    }
    private int bFactor(Node p) {
        return height(p.left) - height(p.right);
    }
    private void fixHeight(Node p) {
        int hl = height(p.left);
        int hr = height(p.right);
        p.height = (Math.max(hl, hr))+1;
    }
    private Node rotateLeft(Node q) // левый поворот вокруг q
    {
        Node p = q.right;
        q.right = p.left;
        p.left = q;
        fixHeight(q);
        fixHeight(p);
        return p;
    }
    private Node rotateRight(Node p) // правый поворот вокруг p
    {
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        fixHeight(p);
        fixHeight(q);
        return q;
    }
    private Node balance(Node p) {
        fixHeight(p);

        if (bFactor(p) <= -2 && p.height > 2) {
            if (p.right != null && p.right.left != null && bFactor(p.right) < 0)
                p.right = rotateRight(p.right);
            return rotateLeft(p);
        }

        if (bFactor(p) >= 2 && p.height > 2) {
            if (p.left != null && p.left.right != null && bFactor(p.left) > 0 )
                p.left = rotateLeft(p.left);
            return rotateRight(p);
        }

        return p;
    }

    private StringBuilder toStringValues(Node p, StringBuilder str) {
        if (p == null) {
            return str;
        } else if (p.height == 1) {
            return str.append(p.key).append("=").append(p.value).append(", ");
        } else {
            str = toStringValues(p.left, str);
            str.append(p.key).append("=").append(p.value).append(", ");
            str = toStringValues(p.right, str);
        }

        return str;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");

        str = toStringValues(head, str);
        if (str.charAt(str.length() - 1) == ' ')
            str.replace(str.length() - 2, str.length(), "");

        str.append("}");

        return str.toString();
    }

    private String returnResult;
    private Node insert(Node p, Integer key, String value) {
        if (p == null) {
            size++;
            returnResult = null;
            return new Node(key, value);
        } else if (key == p.key) {
            returnResult = p.value;
            p.value = value;
            return p;
        }

        if (key < p.key) {
            p.left = insert(p.left, key, value);
        } else {
            p.right = insert(p.right, key, value);
        }

        if (returnResult == null)
            return balance(p);
        else
            return p;
    }

    @Override
    public String put(Integer key, String value) {
        if (key == null || value == null)
            throw new NullPointerException();

        if (head == null) {
            size++;
            head = new Node(key, value);
        } else {
            head = insert(head, key, value);
        }

        return returnResult;
    }

    private Node findMin(Node p)
    {
        return p.left != null ? findMin(p.left) : p;
    }

    private Node removeMin(Node p) // удаление узла с минимальным ключом из дерева p
    {
        if (p.left == null)
            return p.right;
        p.left = removeMin(p.left);
        return balance(p);
    }

    private Node removeWithMin(Node p, int key) // удаление ключа k из дерева p
    {
        if (p == null) {
            returnResult = null;
            return null;
        }

        if (key < p.key) {
            p.left = removeWithMin(p.left, key);
        } else if( key > p.key ) {
            p.right = removeWithMin(p.right, key);
        } else {
            size--;
            returnResult = p.value;
            Node q = p.left;
            Node r = p.right;

            if (r == null) return q;

            Node min = findMin(r);
            min.right = removeMin(r);
            min.left = q;
            return balance(min);
        }

        return balance(p);
    }

    @Override
    public String remove(Object key) {
        head = removeWithMin(head, (Integer)key);

        return returnResult;
    }

    @Override
    public String get(Object k) {
        Node p = head;

        while (p != null) {
            if (k.getClass() == Node.class) {
                if (((Node) k).key < p.key) {
                    p = p.left;
                } else if (((Node) k).key > p.key) {
                    p = p.right;
                } else {
                    return p.value;
                }
            } else {
                if ((Integer)k < p.key) {
                    p = p.left;
                } else if ((Integer)k > p.key) {
                    p = p.right;
                } else {
                    return p.value;
                }
            }
        }

        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        Node p = head;

        while (p != null) {
            if ((Integer)key < p.key) {
                p = p.left;
            } else if ((Integer)key > p.key){
                p = p.right;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() { return size; }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public boolean isEmpty() { return head == null; }

    //----------------------------------------------------------------------------//

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
}
