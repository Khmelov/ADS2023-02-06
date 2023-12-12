package by.it.group251002.koryakova.lesson12;

import java.util.*;

public class MyRbMap implements SortedMap<Integer, String>{
    private enum colors {RED, BLACK}
    private class Node {
        public int key;
        public String data;
        public Node left, right, parent;
        public colors color;

        public Node(int key, String data) {
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.color = colors.RED;
        }
        public void rotateLeft(){
            Node righ = this.right;
            Node par = this.parent;
            if(par != null){
                if(par.left == this){
                    par.left = righ;
                }
                else{
                    par.right = righ;
                }
            }
            this.right = righ.left;
            if(righ.left != null){
                righ.left.parent = this;
            }
            righ.left = this;
            this.parent = righ;
            righ.parent = par;
        }
        public void rotateRight(){
            Node lef = this.left;
            Node par = this.parent;
            if(par != null){
                if(par.left == this){
                    par.left = lef;
                }
                else{
                    par.right = lef;
                }
            }

            this.left = lef.right;
            if(lef.right != null){
                lef.right.parent = this;
            }
            lef.right = this;
            this.parent = lef;
            lef.parent = par;
        }
        public Node getMin(){
            if(this.left == null){
                return this;
            }
            else{
                return this.left.getMin();
            }
        }
    }
    private Node head = null;
    private int size = 0;
    private boolean isRed(Node nod){
        return ((nod != null) && (nod.color == colors.RED));
    }
    private boolean isBlack(Node nod){
        return ((nod == null) || (nod.color == colors.BLACK));
    }
    private Node getSibling(Node nod){
        if(nod == nod.parent.left){
            return nod.parent.right;
        }
        else{
            return nod.parent.left;
        }
    }
    private Node getGrandfather(Node nod){
        if(nod != null && nod.parent != null){
            return nod.parent.parent;
        }
        else {
            return null;
        }
    }
    private Node getUncle(Node nod){
        Node gf = getGrandfather(nod);
        if(gf != null){
            if(gf.left == nod.parent){
                return gf.right;
            }
            else{
                return gf.left;
            }
        }
        else{
            return null;
        }
    }
    private Node getChild(Node nod){
        return (nod.right == null ? nod.left : nod.right);
    }
    private void balanceInsert(Node nod){
        if (nod.parent == null){
            nod.color = colors.BLACK;
            return;
        }
        while ((nod.parent != null) && (nod.parent.color == colors.RED)){
            Node gf = getGrandfather(nod);
            Node unc = getUncle(nod);
            if ((gf != null) && (gf.left == nod.parent)){
                if ((unc != null) && (unc.color == colors.RED)) {
                    nod.parent.color = colors.BLACK;
                    unc.color = colors.BLACK;
                    gf.color = colors.RED;
                    nod = gf;
                }
                else {
                    if (nod.parent.right == nod) {
                        nod = nod.parent;
                        nod.rotateLeft();
                    }
                    nod.parent.color = colors.BLACK;
                    getGrandfather(nod).color = colors.RED;
                    getGrandfather(nod).rotateRight();
                }
            } else
                if ((gf != null) && (gf.right == nod.parent)) {
                if ((unc != null) && (unc.color == colors.RED)) {
                    nod.parent.color = colors.BLACK;
                    unc.color = colors.BLACK;
                    gf.color = colors.RED;
                    nod = gf;
                }
                else {
                    if (nod.parent.left == nod) {
                        nod = nod.parent;
                        nod.rotateRight();
                    }
                    nod.parent.color = colors.BLACK;
                    getGrandfather(nod).color = colors.RED;
                    getGrandfather(nod).rotateLeft();
                }
            }
            while (head.parent != null)
                head = head.parent;
        }
        head.color = colors.BLACK;
    }
    private void insert(Integer key, String data){
        Node newnode = new Node(key, data);
        if(head == null){
            head = newnode;
        }
        else{
            Node temp = head;
            Node temp_par = head;
            while(temp != null){
                temp_par = temp;
                if(key < temp.key){
                    temp = temp.left;
                }
                else{
                    temp = temp.right;
                }
            }
            newnode.parent = temp_par;
            if(key < temp_par.key){
                temp_par.left = newnode;
            }
            else{
                temp_par.right = newnode;
            }
        }
        balanceInsert(newnode);
    }
    private void removeChild(Node nod){
        if (nod.right == null){
            if (nod.parent.left == nod) {
                nod.parent.left = nod.left;
            }
            else{
                nod.parent.right = nod.left;
            }
        }
        else{
            if (nod.parent.left == nod) {
                nod.parent.left = nod.right;
            }
            else{
                nod.parent.right = nod.right;
            }
        }
    }

    private Node getNode(Node nod, Integer key) {
        if (nod == null)
            return null;
        else if (key < nod.key)
            return getNode(nod.left, key);
        else if (key > nod.key)
            return getNode(nod.right, key);
        else
            return nod;
    }

    private void delete(Integer key){
        Node deleted = getNode(head, key);
        if ((deleted.right == null) && (deleted.left == null)){
            if (deleted == head){
                head = null;
            }
            else {
                if (deleted.parent.left == deleted){
                    deleted.parent.left = null;
                }
                else{
                    deleted.parent.right = null;
                }
            }
            return;
        }
        Node swapped = null;
        if ((deleted.right != null) && (deleted.left != null)){
            swapped = deleted.right.getMin();
        }
        else{
            swapped = getChild(deleted);
        }
        deleted.data = swapped.data;
        deleted.key = swapped.key;
        removeChild(swapped);
        if (isBlack(swapped) || isBlack(deleted)){
            balanceDelete(getChild(swapped));
        }
        head.color = colors.BLACK;
    }

    private void balanceDelete(Node ch) {
        while (ch != head && ch != null) {
            if (ch.parent.left == ch) {
                Node b = getSibling(ch);
                if (isRed(b)) {
                    b.color = colors.BLACK;
                    ch.parent.color = colors.RED;
                    ch.parent.rotateLeft();
                }
                if (isBlack(b.right) && isBlack(b.left))
                    b.color = colors.RED;
                else {
                    if (isBlack(b.right)) {
                        b.left.color = colors.BLACK;
                        b.color = colors.RED;
                        b.rotateRight();
                    }
                    b.color = ch.parent.color;
                    ch.parent.color = colors.BLACK;
                    b.right.color = colors.BLACK;
                    ch.parent.rotateLeft();
                    ch = head;
                }
            }
            else {
                Node b = getSibling(ch);
                if (isRed(b)) {
                    b.color = colors.BLACK;
                    ch.parent.color = colors.RED;
                    ch.parent.rotateLeft();
                }
                if (isBlack(b.right) && isBlack(b.left))
                    b.color = colors.RED;
                else {
                    if (isBlack(b.left)) {
                        b.right.color = colors.BLACK;
                        b.color = colors.RED;
                        b.rotateLeft();
                    }
                    b.color = ch.parent.color;
                    ch.parent.color = colors.BLACK;
                    b.left.color = colors.BLACK;
                    ch.parent.rotateRight();
                    ch = head;
                }
            }
        }
        while (head.parent != null)
            head = head.parent;
        head.color = colors.BLACK;
    }

    private void toStr(Node nod, StringBuilder rez) {
        if (nod == null)
            return;
        toStr(nod.left, rez);
        rez.append(nod.key).append("=").append(nod.data).append(", ");
        toStr(nod.right, rez);
    }

    @Override
    public String toString() {
        StringBuilder rez = new StringBuilder("{");
        toStr(head, rez);
        if (head != null)
            rez = new StringBuilder(rez.substring(0, rez.length() - 2));
        rez.append("}");
        return String.valueOf(rez);
    }

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    public void fillLower(Node n, Integer key) {
        if (n == null)
            return;
        fillLower(n.left, key);
        if (n.key < key)
            this.put(n.key, n.data);
        fillLower(n.right, key);
    }

    public void fillUpper(Node n, Integer key) {
        if (n == null)
            return;
        fillUpper(n.left, key);
        if (n.key >= key)
            this.put(n.key, n.data);
        fillUpper(n.right, key);
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap tmp = new MyRbMap();
        tmp.fillLower(this.head, toKey);
        return tmp;
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap tmp = new MyRbMap();
        tmp.fillUpper(this.head, fromKey);
        return tmp;
    }

    @Override
    public Integer firstKey() {
        Node tmp = head;
        Node tmp_p = null;
        while (tmp != null) {
            tmp_p = tmp;
            tmp = tmp.left;
        }
        return tmp_p.key;
    }

    @Override
    public Integer lastKey() {
        Node tmp = head;
        Node tmp_p = null;
        while (tmp != null) {
            tmp_p = tmp;
            tmp = tmp.right;
        }
        return tmp_p.key;
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
        Node res = getNode(head, (Integer) key);
        return res != null;
    }

    private Boolean ContainsValueF;

    private void recGetByValue(Node n, Object data) {
        if (n == null)
            return;
        recGetByValue(n.left, data);
        if (n.data.equals(data))
            ContainsValueF = true;
        recGetByValue(n.right, data);
    }

    @Override
    public boolean containsValue(Object value) {
        ContainsValueF = false;
        recGetByValue(head, value);
        return ContainsValueF;
    }

    @Override
    public String get(Object key) {
        Node tmp = getNode(head, (Integer) key);
        if (tmp == null)
            return null;
        return tmp.data;
    }

    @Override
    public String put(Integer key, String value) {
        Node retVal = getNode(head, key);
        if (retVal == null) {
            size++;
            insert(key, value);
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
            delete((Integer) key);
        }
        return res;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
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