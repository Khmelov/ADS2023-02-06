package by.it.group251003.beskosty.lesson12;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {

    static private class Node{
        Integer key;
        String value;
        Node parent, left, right;
        Node(Integer k){key = k; value = null; parent = left = right = null;}
    }
    private static class Str {
        String res;
    }
    Node root = null;
    int size = 0;

    private void travleToString(Node p, Str res){
        if(p == null) return;
        if(p.left != null)
            travleToString(p.left, res);
        res.res += p.key.toString() + "=" + p.value + ", ";
        if(p.right != null)
            travleToString(p.right, res);
    }

    @Override
    public String toString(){
        Str res = new Str();
        res.res = "{";
        travleToString(root, res);
        if(res.res.length() > 2)
            res.res = res.res.substring(0, res.res.length() - 2);
        return res.res + "}";
    }

    private Node getParent(Node t){
        return t.parent;
    }

    private Node getGrandparent(Node t){
        Node p = getParent(t);
        return p != null ? getParent(p) : null;
    }

    private void leftRotate(Node a){
        Node b = a.right;
        if(b != null){
            Node c = b.left;
            b.parent = a.parent;
            if(b.parent != null){
                if(b.parent.left == a)
                    b.parent.left = b;
                else
                    b.parent.right = b;
            }
            a.parent = b;
            if(root == a)
                root = b;
            b.left = a;
            if(c != null)
                c.parent = a;
            a.right = c;
        }
    }

    private void rightRotate(Node a){
        Node b = a.left;
        if(b != null){
            Node c = b.right;
            b.parent = a.parent;
            if(b.parent != null){
                if(b.parent.left == a)
                    b.parent.left = b;
                else
                    b.parent.right = b;
            }
            a.parent = b;
            if(root == a)
                root = b;
            b.right = a;
            if(c != null)
                c.parent = a;
            a.left = c;
        }
    }

    private void Zig(Node a){
        if(a.parent.left == a)
            rightRotate(a.parent);
        else
            leftRotate(a.parent);
    }

    private void ZigZig(Node x){
        Node g = getGrandparent(x), p = getParent(x);
        if(p.left == x){
            rightRotate(g);
            rightRotate(p);
        } else {
            leftRotate(g);
            leftRotate(p);
        }
    }

    private void ZigZag(Node x){
        Node g = getGrandparent(x), p = getParent(x);
        if(p.right == x){
            leftRotate(p);
            rightRotate(g);
        } else {
            rightRotate(p);
            leftRotate(g);
        }
    }

    private void Splay(Node x){
        while (root != x) {
            Node p = getParent(x);
            if (p == root)
                Zig(x);
            else {
                Node g = getGrandparent(x);
                if ((g.left == p && p.left == x) || (g.right == p && p.right == x))
                    ZigZig(x);
                else
                    ZigZag(x);
            }
        }
    }

    private Node FindKey(Object key){
        Node t = root;
        while(t != null && !t.key.equals(key))
            if((Integer)key < t.key)
                t = t.left;
            else
                t = t.right;
        return t;
    }

    private Node FindFirstGreater(Node t){
        t = t.right;
        while(t.left != null)
            t = t.left;
        return t;
    }

    private Node putKey(Integer key){
        Node prev = root, t = prev;
        while(t != null){
            prev = t;
            if(key < t.key)
                t = t.left;
            else
                t = t.right;
        }
        t = new Node(key);
        t.parent = prev;
        if(prev != null) {
            if (key < prev.key)
                prev.left = t;
            else
                prev.right = t;
        } else
            root = t;
        return t;
    }

    private void removeLeaveNode(Node x){
        if(x.parent != null) {
            if (x.parent.left == x)
                x.parent.left = null;
            else
                x.parent.right = null;
            x.parent = null;
        } else
            root = null;
        x = null;
    }

    private void removeNode(Node exist){
        if(exist.left == null && exist.right == null) {
            Node p = getParent(exist);
            removeLeaveNode(exist);
            if(p != null)
                Splay(p);
        } else if(exist.left == null || exist.right == null){
            Node p;
            if(exist.left != null){
                p = exist.left;
                exist.left.parent = exist.parent;
                if(exist.parent != null) {
                    if (exist.parent.left == exist)
                        exist.parent.left = exist.left;
                    else
                        exist.parent.right = exist.left;
                } else
                    root = exist.left;
            } else {
                p = exist.right;
                exist.right.parent = exist.parent;
                if (exist.parent != null) {
                    if (exist.parent.left == exist)
                        exist.parent.left = exist.right;
                    else
                        exist.parent.right = exist.right;
                } else
                    root = exist.right;
            }
            Splay(p);
        } else {
            Node del = FindFirstGreater(exist);
            Integer tempKey = exist.key;
            String tempString = exist.value;
            exist.key = del.key;
            exist.value = del.value;
            del.key = tempKey;
            del.value = tempString;
            removeNode(del);
        }
    }

    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    private Integer travelLowerKey(Node p, Integer key, boolean inclusion){
        Integer ans = (p.key < key) || (inclusion && p.key.equals(key)) ? p.key : null;
        Integer l = p.left != null ? travelLowerKey(p.left, key, inclusion) : null;
        Integer r = p.right != null ? travelLowerKey(p.right, key, inclusion) : null;
        if(ans == null)
            if(l != null)
                ans = l;
            else if(r != null)
                ans = r;
        if(ans != null & l != null && l > ans)
            ans = l;
        if(ans != null && r != null && r > ans)
            ans = r;
        return ans;
    }

    @Override
    public Integer lowerKey(Integer key) {
        if(isEmpty())
            return null;
        return travelLowerKey(root, key, false);
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
        if(isEmpty())
            return null;
        return travelLowerKey(root, key, true);
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        if(isEmpty())
            return null;
        return travelHigherKey(root, key, true);
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    private Integer travelHigherKey(Node p, Integer key, boolean inclusion){
        Integer ans = (p.key > key) || (inclusion && p.key.equals(key)) ? p.key : null;
        Integer l = p.left != null ? travelHigherKey(p.left, key, inclusion) : null;
        Integer r = p.right != null ? travelHigherKey(p.right, key, inclusion) : null;
        if(ans == null)
            if(l != null)
                ans = l;
            else if(r != null)
                ans = r;
        if(ans != null & l != null && l < ans)
            ans = l;
        if(ans != null && r != null && r < ans)
            ans = r;
        return ans;
    }

    @Override
    public Integer higherKey(Integer key) {
        if(isEmpty())
            return null;
        return travelHigherKey(root, key, false);
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

    private void travleHeadMap(Node p, NavigableMap<Integer, String> res, Integer toKey){
        if(p != null) {
            if (p.left != null)
                travleHeadMap(p.left, res, toKey);
            if (p.key < toKey)
                res.put(p.key, p.value);
            if (p.right != null)
                travleHeadMap(p.right, res, toKey);
        }
    }

    @Override
    public NavigableMap<Integer, String> headMap(Integer toKey) {
        MySplayMap ans = new MySplayMap();
        travleHeadMap(root, ans, toKey);
        return ans;
    }

    private void travleTailMap(Node p, NavigableMap<Integer, String> res, Integer fromKey){
        if(p == null) return;
        if(p.left != null)
            travleTailMap(p.left, res, fromKey);
        if(p.key >= fromKey)
            res.put(p.key, p.value);
        if(p.right != null)
            travleTailMap(p.right, res, fromKey);
    }

    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey) {
        MySplayMap ans = new MySplayMap();
        travleTailMap(root, ans, fromKey);
        return ans;
    }

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
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
    public Integer firstKey() {
        Node p = root;
        while(p != null && p.left != null)
            p = p.left;
        return p != null ? p.key : null;
    }

    @Override
    public Integer lastKey() {
        Node p = root;
        while(p != null && p.right != null)
            p = p.right;
        return p != null ? p.key : null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
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
        Node x = FindKey(key);
        return x != null ? x.value : null;
    }

    @Override
    public String put(Integer key, String value) {
        Node exist = FindKey(key);
        if(exist != null) {
            String prev = exist.value;
            exist.value = value;
            return prev;
        } else {
            ++size;
            Node x = putKey(key);
            x.value = value;
            Splay(x);
            return null;
        }
    }

    @Override
    public String remove(Object key) {
        Node exist = FindKey(key);
        if(exist == null)
            return null;
        else {
            String prev = exist.value;
            --size;
            removeNode(exist);
            return prev;
        }
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {
    }

    @Override
    public void clear() {
        while(!isEmpty())
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