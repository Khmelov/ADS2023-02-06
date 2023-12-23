package by.it.group251002.markouskii.lesson12;

import java.util.NavigableMap;
import java.security.Key;
import java.util.*;
import java.util.function.ToIntFunction;
class SNode{
    public int Key;
    public String Value;
    public SNode Prev;
    public SNode Left;
    public SNode Right;
    public SNode(int K, String Val, SNode P) {
        Key = K;
        Value = Val;
        Prev = P;
        Left = null;
        Right = null;
    }
}
public class MySplayMap <Integer, String> implements NavigableMap<Integer, String> {
    private int size=0;
    private SNode head=null;

    @Override
    public java.lang.String toString() {
        StringBuilder sb=new StringBuilder("{");
        java.lang.String delimiter = "";
        SNode pos=GetLeft(head);
        SNode end=GetRight(head);
        boolean FromRight=false;
        while (pos!=end) {
            if (!FromRight) {sb.append(delimiter).append(pos.Key).append("=").append(pos.Value);
                delimiter = ", ";}
            if (pos.Right!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right!=null) && (pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos!=null) sb.append(delimiter).append(pos.Key).append("=").append(pos.Value);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public boolean containsKey(Object key) {
        SNode pos=head;
        int Key=(int)key;
        while (pos!=null && pos.Key!=Key) {
            if (Key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        if (pos!=null) splay(pos);
        return (pos!=null);
    }

    @Override
    public boolean containsValue(Object value) {
        SNode pos=GetLeft(head);
        SNode end=GetRight(head);
        boolean FromRight=false;
        while (pos!=end) {
            if (pos.Value.equals(value)) {splay(pos); return true;}
            if (pos.Right!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right!=null && pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        return false;
    }
    @Override
    public String get(Object key) {
        SNode pos=head;
        int Key=(int)key;
        while (pos!=null && pos.Key!=Key) {
            if ((int)key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        if (pos!=null) {splay(pos);return (String) pos.Value;}
        return null;
    }
    private SNode p(SNode pos){
        return pos.Prev;
    }
    private SNode g(SNode pos){
        pos=p(pos);
        if (pos==null) return null;
        pos=p(pos);
        return pos;
    }
    private void LeftRotate(SNode P){
        SNode R=P.Right;
        if (R!=null && R.Left!=null)  R.Left.Prev=P;
        P.Right=(R!=null?R.Left : null);
        if (R!=null) R.Left=P;
        if (P.Prev!=null) {
            if (P.Prev.Left!=null && P.Prev.Left.equals(P)) P.Prev.Left=R; else P.Prev.Right=R;}
        if (R!=null) R.Prev=P.Prev;
        P.Prev=R;
    }

    private void RightRotate(SNode P){
        SNode L=P.Left;
        if (L!=null && L.Right!=null)L.Right.Prev=P;
        P.Left=(L!=null?L.Right : null);
        if (L!=null) L.Right=P;
        if (P.Prev!=null) {
            if (P.Prev.Left!=null && P.Prev.Left.equals(P)) P.Prev.Left=L; else P.Prev.Right=L;}
        if (L!=null) L.Prev=P.Prev;
        P.Prev=L;
    }
    private void splay(SNode pos){
        while (pos!=null && p(pos)!=null){
            if (pos==p(pos).Left){
                if (g(pos)==null) RightRotate(pos.Prev);
                else if (p(pos)==g(pos).Left) {
                        //zig-zig left
                        RightRotate(g(pos));
                        RightRotate(p(pos));
                } else {
                    //zig-zag left
                    RightRotate(p(pos));
                    LeftRotate(p(pos));
                }
            } else {
                if (g(pos)==null) LeftRotate(pos.Prev);
                else if (p(pos)==g(pos).Right) {
                    //zig-zig right
                    LeftRotate(g(pos));
                    LeftRotate(p(pos));
                } else {
                    //zig-zag right
                    LeftRotate(p(pos));
                    RightRotate(p(pos));
                }
            }
        }
        head=pos;
    }
    public SNode GetLeft(SNode pos) {
        SNode left=pos;
        if (left==null) return null;
        while (left.Left!=null) left=left.Left;
        return left;
    }
    public SNode GetRight(SNode pos) {
        SNode right=pos;
        if (right==null) return null;
        while (right.Right!=null) right=right.Right;
        return right;
    }
    private void split(int key){
        SNode pos=GetLeft(head);
        SNode end=GetRight(head);
        SNode lowestPos=end;
        boolean FromRight=false;
        while (pos!=end) {
            if (!FromRight) {if (pos.Key>=key && lowestPos.Key>pos.Key) lowestPos=pos;}
            if (pos.Right!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right!=null) && (pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos!=null && pos.Key>=key && lowestPos.Key>pos.Key) lowestPos=pos;
        if (lowestPos!=null && lowestPos.Key>=key) splay(lowestPos);
    }
    @Override
    public String put(Integer key, String value) {
        int Key=(int)key;
        String Value=ReplaceValue(key,value);
        if (Value!=null) return Value;

        split((int)key);
        SNode pos = new SNode((int)key,(java.lang.String)value,null);
        if (head!=null) pos.Left=head.Left;
        if (head!=null && head.Key>=Key) pos.Right=head; else pos.Left=head;
        if (pos.Left!=null) pos.Left.Prev=pos;
        if (pos.Right!=null) {pos.Right.Left=null;pos.Right.Prev=pos;}
        head=pos;
        size++;

        return null;
    }

    private String ReplaceValue(Integer key, String value){
        SNode pos=head;
        int Key=(int)key;
        while (pos!=null && pos.Key!=Key) {
            if (Key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        if (pos!=null) {
            String answer=(String) pos.Value;
            pos.Value=(java.lang.String)value;
            splay(pos);
            return answer;
        }
        return null;
    }
    @Override
    public String remove(Object key) {
        int Key=(int)key;
        if (!containsKey(key)) return null;
        SNode pos=head;
        while (pos!=null && pos.Key!=Key) {
            if ((int)key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        splay(pos);
        SNode t2=head.Right;
        head.Right=null;
        String answer=(String)head.Value;
        pos=head.Left;
        while(pos.Right!=null) pos=pos.Right;
        splay(pos);
        size--;
        pos.Right=t2;
        pos.Right.Prev=pos;
        head=pos;
        return answer;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        size=0;
        head=null;
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
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    @Override
    public Integer lowerKey(Integer key) {
        SNode pos=GetLeft(head);
        SNode end=GetRight(head);
        boolean FromRight=false;
        SNode lowestKey=pos;
        int Key=(int)key;
        while (pos!=end) {
            if (pos.Key<Key && pos.Key>lowestKey.Key) lowestKey=pos;
            if (pos.Right!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right!=null) && (pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos.Key<Key && pos.Key>lowestKey.Key) lowestKey=pos;
        java.lang.Integer answer=(java.lang.Integer) lowestKey.Key;
        if (lowestKey.Key<Key) {splay(lowestKey);return (Integer)answer;} else return null;
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }

    @Override
    public Integer floorKey(Integer key) {
        SNode pos=GetLeft(head);
        SNode end=GetRight(head);
        boolean FromRight=false;
        SNode lowestKey=pos;
        int Key=(int)key;
        while (pos!=end) {
            if (pos.Key<=Key && pos.Key>lowestKey.Key) lowestKey=pos;
            if (pos.Right!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right!=null) && (pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos.Key<=Key && pos.Key>lowestKey.Key) lowestKey=pos;
        java.lang.Integer answer=(java.lang.Integer) lowestKey.Key;
        if (lowestKey.Key<=Key) {splay(lowestKey);return (Integer)answer;} else return null;
    }

    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }

    @Override
    public Integer ceilingKey(Integer key) {
        SNode pos=GetLeft(head);
        SNode end=GetRight(head);
        boolean FromRight=false;
        SNode lowestKey=end;
        int Key=(int)key;
        while (pos!=end) {
            if (pos.Key>=Key && pos.Key<lowestKey.Key) lowestKey=pos;
            if (pos.Right!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right!=null) && (pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos.Key>=Key && pos.Key<lowestKey.Key) lowestKey=pos;
        java.lang.Integer answer=(java.lang.Integer) lowestKey.Key;
        if (lowestKey.Key>=Key) {splay(lowestKey);return (Integer)answer;} else return null;
    }

    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
    }

    @Override
    public Integer higherKey(Integer key) {
        SNode pos=GetLeft(head);
        SNode end=GetRight(head);
        boolean FromRight=false;
        SNode lowestKey=end;
        int Key=(int)key;
        while (pos!=end) {
            if (pos.Key>Key && pos.Key<lowestKey.Key) lowestKey=pos;
            if (pos.Right!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right!=null) && (pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos.Key>Key && pos.Key<lowestKey.Key) lowestKey=pos;
        java.lang.Integer answer=(java.lang.Integer) lowestKey.Key;
        if (lowestKey.Key>Key) {splay(lowestKey);return (Integer)answer;} else return null;
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

    @Override
    public NavigableMap<Integer, String> headMap(Integer toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<Integer, String> tailMap(Integer fromKey, boolean inclusive) {
        return null;
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
    public SortedMap<Integer, String> headMap(Integer toKey) {
        MySplayMap<Integer, String> Answer = new MySplayMap<Integer, String>();
        int Key=(int)toKey;
        SNode pos=GetLeft(head);
        SNode end=GetRight(head);
        boolean FromRight=false;
        while (pos!=end) {
            if (!FromRight && Key>pos.Key) {
                java.lang.Integer Key1=(java.lang.Integer)pos.Key;
                Answer.put((Integer)Key1,(String)pos.Value);}
            if (pos.Right!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right!=null && pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos!=null && Key>pos.Key) {
            java.lang.Integer Key1=(java.lang.Integer)pos.Key;
            Answer.put((Integer)Key1,(String)pos.Value);}
        return Answer;
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MySplayMap<Integer, String> Answer = new MySplayMap<Integer, String>();
        int Key=(int)fromKey;
        SNode pos=GetLeft(head);
        SNode end=GetRight(head);
        boolean FromRight=false;
        while (pos!=end) {
            if (!FromRight && Key<=pos.Key) {
                java.lang.Integer Key1=(java.lang.Integer)pos.Key;
                Answer.put((Integer)Key1,(String)pos.Value);}
            if (pos.Right!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right!=null && pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos!=null && Key<=pos.Key) {
            java.lang.Integer Key1=(java.lang.Integer)pos.Key;
            Answer.put((Integer)Key1,(String)pos.Value);}
        return Answer;
    }

    @Override
    public Integer firstKey() {
        java.lang.Integer answer=(java.lang.Integer)GetLeft(head).Key;
        return (Integer)answer;
    }

    @Override
    public Integer lastKey() {
        java.lang.Integer answer=(java.lang.Integer)GetRight(head).Key;
        return (Integer)answer;
    }
}
