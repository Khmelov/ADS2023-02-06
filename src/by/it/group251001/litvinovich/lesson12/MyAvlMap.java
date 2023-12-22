package by.it.group251001.litvinovich.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyAvlMap implements Map<Integer, String> {

    private static class Node {
        public int key;
        public int height;
        public String data;
        public Node l, r;

        public Node(int key, String data) {
            this.key = key;
            this.data = data;
            l = r = null;
            height = 1;
        }

        public int height(Node n) {
            return n != null ? n.height : 0;
        }

        public int getBFactor(Node n) {
            if (n == null)
                return 0;
            return height(n.r) - height(n.l);
        }

        public void fixHeight() {
            int hl = height(this.l);
            int hr = height(this.r);
            this.height = Math.max(hl, hr) + 1;
        }

        public Node rotateRight() {
            Node tmpLeft = l;
            l = tmpLeft.r;
            tmpLeft.r = this;
            fixHeight();
            tmpLeft.fixHeight();
            return tmpLeft;
        }

        public Node rotateLeft() {
            Node tmpRight = r;
            r = tmpRight.l;
            tmpRight.l = this;
            fixHeight();
            tmpRight.fixHeight();
            return tmpRight;
        }

        public Node balance() {
            fixHeight();
            if (getBFactor(this) == 2) {
                if (getBFactor(r) < 0)
                    r = r.rotateRight();
                return rotateLeft();
            }
            if (getBFactor(this) == -2) {
                if (getBFactor(l) > 0)
                    l = l.rotateLeft();
                return rotateRight();
            }
            return this;
        }

        public Node findMin() {
            if (l == null)
                return this;
            else
                return l.findMin();
        }

        public Node removeMin() {
            if (l == null)
                return r;
            l = l.removeMin();
            return balance();
        }
    }

    private Node recGet(Node n, Integer key) {
        if (n == null)
            return null;
        else if (key < n.key)
            return recGet(n.l, key);
        else if (key > n.key)
            return recGet(n.r, key);
        else
            return n;
    }

    private Node recInsert(Node n, Integer key, String data) {
        if (n == null)
            return new Node(key, data);
        if (key < n.key)
            n.l = recInsert(n.l, key, data);
        else
            n.r = recInsert(n.r, key, data);
        return n.balance();
    }

    private Node recRemove(Node n, Object key) {
        if (n == null)
            return null;
        if ((Integer) key < n.key)
            n.l = recRemove(n.l, key);
        else if ((Integer) key > n.key)
            n.r = recRemove(n.r, key);
        else {
            Node l = n.l;
            Node r = n.r;
            n = null;
            if (r == null)
                return l;
            Node min = r.findMin();
            min.r = r.removeMin();
            min.l = l;
            min.balance();
            return min;
        }
        return n.balance();
    }

    private Node tree = null;
    private int size = 0;

    private void toStr(Node n, StringBuilder res) {
        if (n == null)
            return;
        toStr(n.l, res);
        res.append(n.key).append("=").append(n.data).append(", ");
        toStr(n.r, res);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("{");
        toStr(tree, res);
        if (tree != null)
            res = new StringBuilder(res.substring(0, res.length() - 2));
        res.append("}");
        return String.valueOf(res);
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
        Node res = recGet(tree, (Integer) key);
        return res != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        Node tmp = recGet(tree, (Integer) key);
        if (tmp == null)
            return null;
        return tmp.data;
    }

    @Override
    public String put(Integer key, String value) {
        Node retVal = recGet(tree, key);
        if (retVal == null) {
            size++;
            tree = recInsert(tree, key, value);
            return null;
        }
        String pr = retVal.data;
        retVal.data = value;
        return pr;
    }


    @Override
    public String remove(Object key) {
        String res = get(key);
        if (res != null) {
            size--;
            tree = recRemove(tree, key);
        }
        return res;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        tree = null;
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
