package by.it.group251002.lapus_vitaliy.lesson12;

import java.util.*;

class MySplay<Integer, String>{

    public Integer key;

    public String elem;
    MySplay<Integer, String> left, right, parent;

    public MySplay(Integer k,String e, MySplay<Integer, String> par, MySplay<Integer, String> l, MySplay<Integer, String> r)
    {
        key=k;
        elem=e;
        left=l;
        right=r;
        parent=par;
    }
}

public class MySplayMap implements NavigableMap<Integer, String> {

    MySplay<Integer, String> tree= null;
    int size=0;

    MySplay<Integer, String> nullleaf = new MySplay<Integer, String>(0 ,null, null, null, null);


    private void rotateLeft(MySplay<Integer, String> N)
    {
        N.parent.right=N.left;
        if(N.left!=nullleaf)
        {
            N.left.parent=N.parent;
        }
        N.left=N.parent;
        if(N.parent.parent.left==N.parent) {
            N.parent.parent.left = N;
        }
        else if(N.parent.parent.right==N.parent){
            N.parent.parent.right = N;
        }
        N.parent = N.left.parent;
        N.left.parent=N;
    }

    private void rotateRight(MySplay<Integer, String> N)
    {
        N.parent.left=N.right;
        if(N.right!=nullleaf)
        {
            N.right.parent=N.parent;
        }
        N.right=N.parent;
        if(N.parent.parent.left==N.parent) {
            N.parent.parent.left = N;
        }
        else if(N.parent.parent.right==N.parent){
            N.parent.parent.right = N;
        }
        N.parent=N.right.parent;
        N.right.parent=N;
    }

    private void zig(MySplay<Integer, String> N)
    {
        if(N.parent==nullleaf)
        {return;}
        else{
            if(N.parent.left==N)
            {
                rotateRight(N);
            }
            else{
                rotateLeft(N);
            }
        }
    }

    private void correction(MySplay<Integer, String> N){
        if(N==null || N==nullleaf || N.parent==null || N.parent==nullleaf) {
            return;
        }
        if(N.parent.parent==nullleaf)
        {
            zig(N);
        }
        else{
            if((N.parent.parent.left==N.parent && N.parent.left==N)||(N.parent.parent.right==N.parent && N.parent.right==N))
            {
                zig(N.parent);
                zig(N);
            }
            else{
                zig(N);
                zig(N);
            }
            correction(N);
        }
    }

    private MySplay<Integer, String> goUp(MySplay<Integer, String> head)
    {
        while(head.parent!=nullleaf)
            head=head.parent;
        return head;
    }

    private MySplay<Integer, String> goLeft(MySplay<Integer, String> head)
    {
        while(head.left!=nullleaf)
            head=head.left;
        return head;
    }

    private MySplay<Integer, String> goRight(MySplay<Integer, String> head)
    {
        while(head.right!=nullleaf)
            head=head.right;
        return head;
    }

    private MySplay<Integer, String> getNextKey(Integer key)
    {
        MySplay<Integer, String> buf=tree;
        MySplay<Integer, String> lastbuf=tree;
        if(buf==null)
        {
            return null;
        }
        while(buf!=nullleaf)
        {
            if(((Comparable<Integer>)(buf.key)).compareTo((key))>0)
            {
                lastbuf=buf;
                buf=buf.left;
            } else if (((Comparable<Integer>)(buf.key)).compareTo((key))<0) {
                lastbuf=buf;
                buf=buf.right;
            }
            else{
                return buf;
            }
        }
        buf=lastbuf;
        while(lastbuf!=nullleaf && lastbuf.key<key)
        {
            lastbuf=lastbuf.parent;
        }
        if(lastbuf==nullleaf)
        {
            return buf;
        }
        else{
            return lastbuf;
        }
    }

    private MySplay<Integer, String> getPredKey(Integer key)
    {
        MySplay<Integer, String> buf=tree;
        MySplay<Integer, String> lastbuf=tree;
        if(buf==null)
        {
            return null;
        }
        while(buf!=nullleaf)
        {
            if(((Comparable<Integer>)(buf.key)).compareTo((key))>0)
            {
                lastbuf=buf;
                buf=buf.left;
            } else if (((Comparable<Integer>)(buf.key)).compareTo((key))<0) {
                lastbuf=buf;
                buf=buf.right;
            }
            else{
                return buf;
            }
        }
        buf=lastbuf;
        while(lastbuf!=nullleaf && lastbuf.key>key)
        {
            lastbuf=lastbuf.parent;
        }
        if(lastbuf==nullleaf)
        {
            return buf;
        }
        else{
            return lastbuf;
        }
    }

    private void stringMaker(MySplay<Integer, String> tree, StringBuilder str)
    {
        if((tree!=nullleaf) && (tree!=null)) {
            stringMaker(tree.left, str);
            str.append(tree.key).append("=").append(tree.elem).append(", ");
            stringMaker(tree.right, str);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        stringMaker(tree,str);
        if(str.length()>=3)
            str.delete(str.length()-2, str.length());
        str.append("}");
        return str.toString();
    }

    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Integer lowerKey(Integer key) {
        MySplay<Integer,String> buf;
        buf=getPredKey(key-1);
        if(((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))>0)
        {
            return null;
        }
        else{
            return buf.key;
        }
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
        MySplay<Integer,String> buf;
        buf=getPredKey(key);
        if(((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))>0)
        {
            return null;
        }
        else{
            return buf.key;
        }
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        MySplay<Integer,String> buf;
        buf=getNextKey(key);
        if(((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))<0)
        {
            return null;
        }
        else{
            return buf.key;
        }
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Integer higherKey(Integer key) {
        MySplay<Integer,String> buf;
        buf=getNextKey(key+1);
        if(((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))<0)
        {
            return null;
        }
        else{
            return buf.key;
        }
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

    private void headM(MySplayMap bufMap, MySplay<Integer, String> buf, Integer toKey)
    {
        if(buf==nullleaf || buf==null)
        {
            return;
        }
        if(buf.key>=toKey)
        {
            headM(bufMap, buf.left, toKey);
            return;
        }
        headM(bufMap, buf.left, toKey);
        headM(bufMap, buf.right, toKey);
        bufMap.put(buf.key, buf.elem);
    }

    @Override
    public NavigableMap<Integer, String> headMap(Integer toKey, boolean inclusive) {
        MySplayMap bufMap= new MySplayMap();
        headM(bufMap, tree, toKey);
        return bufMap;
    }

    private void tailM(MySplayMap bufMap, MySplay<Integer, String> buf, Integer fromKey)
    {
        if(buf==nullleaf || buf==null)
        {
            return;
        }
        if(buf.key<fromKey)
        {
            tailM(bufMap, buf.right, fromKey);
            return;
        }
        tailM(bufMap, buf.left, fromKey);
        tailM(bufMap, buf.right, fromKey);
        bufMap.put(buf.key, buf.elem);
    }


    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey, boolean inclusive) {
        MySplayMap bufMap= new MySplayMap();
        tailM(bufMap, tree, fromKey);
        return bufMap;
    }

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    @Override
    public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
        return null;
    }

    private void headM(MyRbMap bufMap, MySplay<Integer, String> buf, Integer toKey)
    {
        if(buf==nullleaf || buf==null)
        {
            return;
        }
        if(buf.key>=toKey)
        {
            headM(bufMap, buf.left, toKey);
            return;
        }
        headM(bufMap, buf.left, toKey);
        headM(bufMap, buf.right, toKey);
        bufMap.put(buf.key, buf.elem);
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MyRbMap bufMap= new MyRbMap();
        headM(bufMap, tree, toKey);
        return bufMap;
    }

    private void tailM(MyRbMap bufMap, MySplay<Integer, String> buf, Integer fromKey)
    {
        if(buf==nullleaf || buf==null)
        {
            return;
        }
        if(buf.key<fromKey)
        {
            tailM(bufMap, buf.right, fromKey);
            return;
        }
        tailM(bufMap, buf.left, fromKey);
        tailM(bufMap, buf.right, fromKey);
        bufMap.put(buf.key, buf.elem);
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap bufMap= new MyRbMap();
        tailM(bufMap, tree, fromKey);
        return bufMap;
    }

    @Override
    public Integer firstKey() {
        MySplay<Integer, String> buf=tree;
        buf=goLeft(buf);
        correction(buf);
        tree=goUp(tree);
        return buf==null ? null :buf.key;
    }

    @Override
    public Integer lastKey() {
        MySplay<Integer, String> buf=tree;
        buf=goRight(buf);
        correction(buf);
        tree=goUp(tree);
        return buf==null ? null :buf.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean containsKey(Object key) {
        MySplay<Integer, String> buf=tree;
        if(buf==null)
        {
            return false;
        }
        while(buf!=nullleaf)
        {
            if(((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))>0)
            {
                buf=buf.left;
            } else if (((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))<0) {
                buf=buf.right;
            }
            else{
                correction(buf);
                tree=goUp(tree);
                return true;
            }
        }
        return false;
    }

    private boolean contValue(MySplay<Integer, String> buf, Object value)
    {
        if((buf!=nullleaf) && (buf!=null)) {
            if((value==null || buf.elem==null) ? value==buf.elem : (buf.elem).equals(value))
                return true;
            return contValue(buf.left, value) || contValue(buf.right, value);
        }
        else{
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        return contValue(tree, value);
    }

    @Override
    public String get(Object key) {
        MySplay<Integer, String> buf=tree;
        if(buf==null)
        {
            return null;
        }
        while(buf!=nullleaf)
        {
            if(((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))>0)
            {
                buf=buf.left;
            } else if (((Comparable<Integer>)(buf.key)).compareTo((Integer)(key))<0) {
                buf=buf.right;
            }
            else{
                correction(buf);
                tree=goUp(tree);
                return buf.elem;
            }
        }
        return null;
    }

    @Override
    public String put(Integer key, String value) {
        if(tree==null)
        {
            nullleaf.right=nullleaf;
            nullleaf.left=nullleaf;
            nullleaf.parent=nullleaf;
            tree= new MySplay<Integer, String>(key, value, nullleaf, nullleaf, nullleaf);
            size++;
            return null;
        }
        else{
              MySplay<Integer, String> buf;
              buf = getNextKey(key);
              MySplay<Integer, String> lastbuf=buf;
              correction(buf);
              if(((Comparable<Integer>)(buf.key)).compareTo((key))<0)
              {
                  buf = new MySplay<Integer, String>(key, value, nullleaf, lastbuf, nullleaf);
                  buf.left.parent = buf;
                  size++;
                  tree = goUp(tree);
                 return null;
              }
              else if(((Comparable<Integer>)(buf.key)).compareTo((key))==0){
                  String strBuf=buf.elem;
                  buf.elem=value;
                  tree=goUp(tree);
                  return strBuf;
              }
              else{
                      buf=new MySplay<Integer, String>(key, value, nullleaf, lastbuf.left, lastbuf);
                      lastbuf.left=nullleaf;
                      buf.left.parent=buf;
                      lastbuf.parent=buf;
                      size++;
                      tree=goUp(tree);
                      return null;
              }
        }
    }

    @Override
    public String remove(Object key) {
        MySplay<Integer, String> buf;
        buf = getNextKey((Integer)key);
        MySplay<Integer, String> bufLeft;
        MySplay<Integer, String> bufRight;
        String str;
        if(((Comparable<Integer>)(buf.key)).compareTo((Integer)key)==0)
        {
            correction(buf);
            str=buf.elem;
            bufLeft=buf.left;
            bufRight=buf.right;
            buf=null;
            bufRight.parent=nullleaf;
            bufLeft.parent=nullleaf;
            if(bufRight==nullleaf)
            {
                tree=bufLeft;
            }
            else if(bufLeft==nullleaf){
                tree=bufRight;
            }
            else{
                tree=bufRight;
                bufRight=getNextKey(bufLeft.key);
                correction(bufRight);
                bufRight.left=bufLeft;
                bufLeft.parent=bufRight;
                tree=bufRight;
            }
            size--;
            return str;
        }
        else {
            return null;
        }
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        size=0;
        tree=null;
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
