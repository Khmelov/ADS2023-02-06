package by.it.group251001.krivitsky.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String>{
    private static class Node{
        Integer key;
        String value;
        boolean color;
        Node LeftNode, RightNode, parent;
        Node(Integer k){
            key = k;
            value = null;
            color = false;
            LeftNode = RightNode = parent = null;
        }
    }

    private class Str{
        String data;
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

    private void travleHeadMap(Node p, SortedMap<Integer, String> result, Integer toKey){
        if(p == null) return;
        if(p.LeftNode != null)
            travleHeadMap(p.LeftNode, result, toKey);
        if(p.key < toKey)
            result.put(p.key, p.value);
        if(p.RightNode != null)
            travleHeadMap(p.RightNode, result, toKey);
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap ans = new MyRbMap();
        travleHeadMap(root, ans, toKey);
        return ans;
    }

    private void travleTailMap(Node p, SortedMap<Integer, String> result, Integer fromKey){
        if(p == null) return;
        if(p.LeftNode != null)
            travleTailMap(p.LeftNode, result, fromKey);
        if(p.key >= fromKey)
            result.put(p.key, p.value);
        if(p.RightNode != null)
            travleTailMap(p.RightNode, result, fromKey);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap ans = new MyRbMap();
        travleTailMap(root, ans, fromKey);
        return ans;
    }

    private void travle(Node p, Str result){
        if(p == null) return;
        if(p.LeftNode != null)
            travle(p.LeftNode, result);
        result.data += p.key.toString() + "=" + p.value + ", ";
        if(p.RightNode != null)
            travle(p.RightNode, result);
    }

    public String toString(){
        Str res = new Str();
        res.data = "{";
        travle(root, res);
        if(res.data.length() > 2)
            res.data = res.data.substring(0, res.data.length() - 2);
        return res.data + "}";
    }

    @Override
    public Integer firstKey() {
        Node p = root;
        while(p != null && p.LeftNode != null)
            p = p.LeftNode;
        return p != null ? p.key : null;
    }

    @Override
    public Integer lastKey() {
        Node p = root;
        while(p != null && p.RightNode != null)
            p = p.RightNode;
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
                p = p.LeftNode;
            else
                p = p.RightNode;
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
        Node a = n, b = a.RightNode, l = a.LeftNode, c = b.LeftNode, r = b.RightNode;

        a.RightNode = c;
        if(c != null)
            c.parent = a;
        b.parent = a.parent;
        if(b.parent != null)
            if(b.parent.LeftNode == a)
                b.parent.LeftNode = b;
            else
                b.parent.RightNode = b;
        a.parent = b;
        b.LeftNode = a;
        if(root == a)
            root = b;
    }

    private void rightRotate(Node n) {
        Node a = n, b = a.LeftNode, l = b.LeftNode, c = b.RightNode, r = a.RightNode;

        a.LeftNode = c;
        if(c != null)
            c.parent = a;
        b.parent = a.parent;
        if(b.parent != null)
            if(b.parent.LeftNode == a)
                b.parent.LeftNode = b;
            else
                b.parent.RightNode = b;
        a.parent = b;
        b.RightNode = a;
        if(root == a)
            root = b;
    }

    private Node getParent(Node n){
        return n.parent;
    }

    private Node getGrandparent(Node n){
        if(n.parent != null)
            return n.parent.parent;
        return null;
    }

    private Node getUncle(Node n){
        if(n.parent != null && n.parent.parent != null)
            if(n.parent == n.parent.parent.LeftNode)
                return n.parent.parent.RightNode;
            else
                return n.parent.parent.LeftNode;
        return null;
    }

    private void fixInsertion(Node t) {
        if (root == t) {
            t.color = false;
            return;
        }
        while (getColor(t.parent) == true) {
            Node p = getParent(t), g = getGrandparent(t), u = getUncle(t);
            if(getColor(p) == true && getColor(u) == true){
                p.color = false;
                u.color = false;
                g.color = true;
                t = g;
            } else {
                if(p != null && p.RightNode == t){
                    leftRotate(p);
                    Node temp = t;
                    t = p;
                    p = temp;
                }

                if(g != null && g.LeftNode == p)
                    rightRotate(g);
                else if(g != null && g.RightNode == p)
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
                    p = p.RightNode;
                else
                    p = p.LeftNode;
            }
            t.parent = q;
            if(q.key < t.key)
                q.RightNode = t;
            else
                q.LeftNode = t;
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
            if (del.parent.LeftNode == del)
                del.parent.LeftNode = null;
            else
                del.parent.RightNode = null;
            del = null;
        }
    }

    private Node FindFirstGrater(Node p){
        p = p.RightNode;
        while(p.LeftNode != null)
            p = p.LeftNode;
        return p;
    }

    private Node getBrother(Node t){
        Node p = getParent(t);
        if(p != null)
            return p.RightNode == t ? p.LeftNode : p.RightNode;
        return null;
    }

    private Node getLeftNephew(Node t){
        Node b = getBrother(t);
        return b != null ? b.LeftNode : null;
    }

    private Node getRightNephew(Node t){
        Node b = getBrother(t);
        return b != null ? b.RightNode : null;
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
        if(del.LeftNode == null && del.RightNode == null) {
            deleteLeaveNode(del);
            --size;
        }else
        if(del.LeftNode == null || del.RightNode == null){
            --size;
            if(del.RightNode != null){
                del.RightNode.parent = del.parent;
                if(del.parent != null) {
                    if (del.parent.LeftNode == del)
                        del.parent.LeftNode = del.RightNode;
                    else
                        del.parent.RightNode = del.RightNode;
                } else
                    root = del.RightNode;
                if(getColor(del.parent) && del.RightNode.color)
                    del.RightNode.color = false;
                del = null;
            } else {
                del.LeftNode.parent = del.parent;
                if(del.parent != null) {
                    if (del.parent.LeftNode == del)
                        del.parent.LeftNode = del.LeftNode;
                    else
                        del.parent.RightNode = del.LeftNode;
                } else
                    root = del.LeftNode;
                if(getColor(del.parent) && del.LeftNode.color)
                    del.LeftNode.color = false;
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