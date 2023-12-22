package by.it.group251003.beskosty.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String>{
    static private class MyNode {
        Integer key;
        String value;
        boolean color;
        MyNode left, right, parrent;
        MyNode(Integer k){
            key = k;
            value = null;
            color = false;
            left = right = parrent = null;
        }
    }

    private static class Str{
        String res;
    }

    MyNode root = null;
    int size = 0;

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    private void travleHeadMap(MyNode p, SortedMap<Integer, String> res, Integer toKey){
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

    private void travleTailMap(MyNode p, SortedMap<Integer, String> res, Integer fromKey){
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

    private void travle(MyNode p, Str res){
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
        MyNode p = root;
        while(p != null && p.left != null)
            p = p.left;
        return p != null ? p.key : null;
    }

    @Override
    public Integer lastKey() {
        MyNode p = root;
        while(p != null && p.right != null)
            p = p.right;
        return p != null ? p.key : null;
    }

    @Override
    public int size() {
        return size;
    }

    private boolean getColor(MyNode p){
        return p != null && p.color;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private MyNode FindKey(Object key){
        MyNode p = root;
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
        MyNode p = FindKey(key);
        return p != null ? p.value : null;
    }

    private void leftRotate(MyNode n){
        MyNode b = n.right, c = b.left;

        n.right = c;
        if(c != null)
            c.parrent = n;
        b.parrent = n.parrent;
        if(b.parrent != null)
            if(b.parrent.left == n)
                b.parrent.left = b;
            else
                b.parrent.right = b;
        n.parrent = b;
        b.left = n;
        if(root == n)
            root = b;
    }

    private void rightRotate(MyNode n) {
        MyNode b = n.left, c = b.right;

        n.left = c;
        if(c != null)
            c.parrent = n;
        b.parrent = n.parrent;
        if(b.parrent != null)
            if(b.parrent.left == n)
                b.parrent.left = b;
            else
                b.parrent.right = b;
        n.parrent = b;
        b.right = n;
        if(root == n)
            root = b;
    }

    private MyNode getParent(MyNode n){
        return n.parrent;
    }

    private MyNode getGrandparent(MyNode n){
        if(n.parrent != null)
            return n.parrent.parrent;
        return null;
    }

    private MyNode getUncle(MyNode n){
        if(n.parrent != null && n.parrent.parrent != null)
            if(n.parrent == n.parrent.parrent.left)
                return n.parrent.parrent.right;
            else
                return n.parrent.parrent.left;
        return null;
    }

    private void fixInsertion(MyNode t) {
        if (root == t) {
            t.color = false;
            return;
        }
        while (getColor(t.parrent)) {
            MyNode p = getParent(t), g = getGrandparent(t), u = getUncle(t);
            if(getColor(p) && getColor(u)){
                p.color = false;
                u.color = false;
                g.color = true;
                t = g;
            } else {
                if(p != null && p.right == t){
                    leftRotate(p);
                    p = t;
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
        MyNode t = new MyNode(key);
        t.color = true;
        if(isEmpty())
            root = t;
        else {
            MyNode p = root;
            MyNode q = null;
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

    private void deleteLeaveNode(MyNode del){
        if(del == root) {
            root = null;
        } else {
            if (del.parrent.left == del)
                del.parrent.left = null;
            else
                del.parrent.right = null;
        }
        del = null;
    }

    private MyNode FindFirstGrater(MyNode p){
        p = p.right;
        while(p.left != null)
            p = p.left;
        return p;
    }

    private MyNode getBrother(MyNode t){
        MyNode p = getParent(t);
        if(p != null)
            return p.right == t ? p.left : p.right;
        return null;
    }

    private MyNode getLeftNephew(MyNode t){
        MyNode b = getBrother(t);
        return b != null ? b.left : null;
    }

    private MyNode getRightNephew(MyNode t){
        MyNode b = getBrother(t);
        return b != null ? b.right : null;
    }

    private void fixDeleting(MyNode x){
        MyNode p = getParent(x), b = getBrother(x), ln = getLeftNephew(x), rn = getRightNephew(x);
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

    private String removeNode(MyNode del) {
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
            }
            del = null;
        } else {
            MyNode x = FindFirstGrater(del);
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