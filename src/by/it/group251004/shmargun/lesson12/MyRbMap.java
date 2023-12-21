package by.it.group251004.shmargun.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String>{
    private class Node{
        Integer key;
        String value;
        boolean color;
        Node left, right, parrent;
        Node(Integer k){
            key = k;
            value = null;
            color = false;
            left = right = parrent = null;
        }
    }

    private class Str{
        String res;
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

    private void travleHeadMap(Node p, SortedMap<Integer, String> res, Integer toKey){
        if(p == null) return;
        if(p.left != null)
            travleHeadMap(p.left, res, toKey);
        if(p.key < toKey)
            res.put(p.key, p.value);
        if(p.right != null)
            travleHeadMap(p.right, res, toKey);
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap ans = new MyRbMap();
        travleHeadMap(root, ans, toKey);
        return ans;
    }

    private void travleTailMap(Node p, SortedMap<Integer, String> res, Integer fromKey){
        if(p == null) return;
        if(p.left != null)
            travleTailMap(p.left, res, fromKey);
        if(p.key >= fromKey)
            res.put(p.key, p.value);
        if(p.right != null)
            travleTailMap(p.right, res, fromKey);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap ans = new MyRbMap();
        travleTailMap(root, ans, fromKey);
        return ans;
    }

    private void travle(Node p, Str res){
        if(p == null) return;
        if(p.left != null)
            travle(p.left, res);
        res.res += p.key.toString() + "=" + p.value + ", ";
        if(p.right != null)
            travle(p.right, res);
    }

    public String toString(){
        Str res = new Str();
        res.res = "{";
        travle(root, res);
        if(res.res.length() > 2)
            res.res = res.res.substring(0, res.res.length() - 2);
        return res.res + "}";
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

    private boolean getColor(Node p){
        return p == null ? false : p.color;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private Node FindKey(Object key){
        Node p = root;
        while(p != null && !p.key.equals(key)){
            if((Integer)key < p.key)
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

    private void leftRotate(Node n){
        Node a = n, b = a.right, l = a.left, c = b.left, r = b.right;

        a.right = c;
        if(c != null)
            c.parrent = a;
        b.parrent = a.parrent;
        if(b.parrent != null)
            if(b.parrent.left == a)
                b.parrent.left = b;
            else
                b.parrent.right = b;
        a.parrent = b;
        b.left = a;
        if(root == a)
            root = b;
    }

    private void rightRotate(Node n) {
        Node a = n, b = a.left, l = b.left, c = b.right, r = a.right;

        a.left = c;
        if(c != null)
            c.parrent = a;
        b.parrent = a.parrent;
        if(b.parrent != null)
            if(b.parrent.left == a)
                b.parrent.left = b;
            else
                b.parrent.right = b;
        a.parrent = b;
        b.right = a;
        if(root == a)
            root = b;
    }

    private Node getParent(Node n){
        return n.parrent;
    }

    private Node getGrandparent(Node n){
        if(n.parrent != null)
            return n.parrent.parrent;
        return null;
    }

    private Node getUncle(Node n){
        if(n.parrent != null && n.parrent.parrent != null)
            if(n.parrent == n.parrent.parrent.left)
                return n.parrent.parrent.right;
            else
                return n.parrent.parrent.left;
        return null;
    }

    private void fixInsertion(Node t) {
        if (root == t) {
            t.color = false;
            return;
        }
        while (getColor(t.parrent) == true) {
            Node p = getParent(t), g = getGrandparent(t), u = getUncle(t);
            if(getColor(p) == true && getColor(u) == true){
                p.color = false;
                u.color = false;
                g.color = true;
                t = g;
            } else {
                if(p != null && p.right == t){
                    leftRotate(p);
                    Node temp = t;
                    t = p;
                    p = temp;
                }

                if(g != null && g.left == p)
                    rightRotate(g);
                else if(g != null && g.right == p)
                    leftRotate(g);
                if(p != null)
                    p.color = false;
                if(g != null)
                    g.color = true;
                if(u != null)
                    u.color = false;
                t = root;
            }
        }
        root.color = false;
    }

    private void insertKey(Integer key) {
        Node t = new Node(key);
        t.color = true;
        if(isEmpty())
            root = t;
        else {
            Node p = root;
            Node q = null;
            while(p != null) {
                q = p;
                if(p.key < t.key)
                    p = p.right;
                else
                    p = p.left;
            }
            t.parrent = q;
            if(q.key < t.key)
                q.right = t;
            else
                q.left = t;
        }
        fixInsertion(t);
    }

    @Override
    public String put(Integer key, String value) {
        String prev = get(key);
        if(!containsKey(key)) {
            insertKey(key);
            ++size;
        }
        FindKey(key).value = value;
        return prev;
    }

    private void deleteLeaveNode(Node del){
        if(del == root) {
            root = null;
            del = null;
        } else {
            if (del.parrent.left == del)
                del.parrent.left = null;
            else
                del.parrent.right = null;
            del = null;
        }
    }

    private Node FindFirstGrater(Node p){
        p = p.right;
        while(p.left != null)
            p = p.left;
        return p;
    }

    private Node getBrother(Node t){
        Node p = getParent(t);
        if(p != null)
            return p.right == t ? p.left : p.right;
        return null;
    }

    private Node getLeftNephew(Node t){
        Node b = getBrother(t);
        return b != null ? b.left : null;
    }

    private Node getRightNephew(Node t){
        Node b = getBrother(t);
        return b != null ? b.right : null;
    }

    private void fixDeleting(Node x){
        Node p = getParent(x), b = getBrother(x), ln = getLeftNephew(x), rn = getRightNephew(x);
        if(b != null){
            if(!getColor(x) && !getColor(p) && getColor(b) && !getColor(ln) && !getColor(rn)){
                leftRotate(p);
                p = getParent(x);
                b = getBrother(x);
                ln = getLeftNephew(x);
                rn = getRightNephew(x);
                    if(!getColor(x) && !getColor(b) && getColor(ln) && !getColor(rn)){
                        rightRotate(b);
                        b.color = true;
                        ln.color = false;
                        p = getParent(x);
                        b = getBrother(x);
                        ln = getLeftNephew(x);
                        rn = getRightNephew(x);
                    }
                    if(!getColor(x) && !getColor(b) && getColor(rn)){
                        leftRotate(p);
                        p.color = false;
                        rn.color = false;
                        p = getParent(x);
                        b = getBrother(x);
                        ln = getLeftNephew(x);
                        rn = getRightNephew(x);
                    }
                }
            if(b != null && !getColor(x) && getColor(p) && !getColor(b) && !getColor(ln) && !getColor(rn)){
                p.color = false;
                b.color = true;
                fixDeleting(p);
            }
        }
    }

    @Override
    public String remove(Object key){
        return removeNode(FindKey(key));
    }

    private String removeNode(Node del) {
        if(del == null)
            return null;
        String res = del.value;
        if(del.left == null && del.right == null) {
            deleteLeaveNode(del);
            --size;
        }else
            if(del.left == null || del.right == null){
                --size;
                if(del.right != null){
                    del.right.parrent = del.parrent;
                    if(del.parrent != null) {
                        if (del.parrent.left == del)
                            del.parrent.left = del.right;
                        else
                            del.parrent.right = del.right;
                    } else
                        root = del.right;
                    if(getColor(del.parrent) && del.right.color)
                        del.right.color = false;
                    del = null;
                } else {
                    del.left.parrent = del.parrent;
                    if(del.parrent != null) {
                        if (del.parrent.left == del)
                            del.parrent.left = del.left;
                        else
                            del.parrent.right = del.left;
                    } else
                        root = del.left;
                    if(getColor(del.parrent) && del.left.color)
                        del.left.color = false;
                    del = null;
                }
            } else {
                Node x = FindFirstGrater(del);
                String tempStr = x.value;
                Integer tempKey = x.key;
                x.value = del.value;
                x.key = del.key;
                del.value = tempStr;
                del.key = tempKey;
                if(!x.color)
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
