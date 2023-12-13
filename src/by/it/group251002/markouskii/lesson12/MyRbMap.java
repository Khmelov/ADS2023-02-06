package by.it.group251002.markouskii.lesson12;

import java.security.Key;
import java.util.*;
import java.util.function.ToIntFunction;

class RBNode{
    public int Key;
    public boolean Exist;
    public String Value;
    public RBNode Prev;
    public RBNode Left;
    public RBNode Right;
    //Color - black-false,red-true
    public boolean Color;
    public RBNode(int K, String Val, RBNode P,RBNode roots) {
        Key = K;
        Value = Val;
        Prev = P;
        Left = roots;
        Right = roots;
        Color=(P!=null);
        Exist=true;
    }
}
public class MyRbMap<Integer, String> implements SortedMap<Integer, String>{
    public static final RBNode EmptyNode=new RBNode(0,null,null,null);
    private RBNode head=EmptyNode;
    private int size=0;
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
        MyRbMap<Integer, String> Answer = new MyRbMap<Integer, String>();
        int Key=(int)toKey;
        RBNode pos=GetLeft(head);
        RBNode end=GetRight(head);
        boolean FromRight=false;
        while (pos!=end) {
            if (!FromRight && pos.Exist && Key>pos.Key) {
                java.lang.Integer Key1=(java.lang.Integer)pos.Key;
                Answer.put((Integer)Key1,(String)pos.Value);}
            if (pos.Right.Value!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos!=null && pos.Value!=null && pos.Exist  && pos.Exist && Key>pos.Key) {
            java.lang.Integer Key1=(java.lang.Integer)pos.Key;
            Answer.put((Integer)Key1,(String)pos.Value);}
        return Answer;
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        MyRbMap<Integer, String> Answer = new MyRbMap<Integer, String>();
        int Key=(int)fromKey;
        RBNode pos=GetLeft(head);
        RBNode end=GetRight(head);
        boolean FromRight=false;
        while (pos!=end) {
            if (!FromRight && pos.Exist && Key<=pos.Key) {
                java.lang.Integer Key1=(java.lang.Integer)pos.Key;
                Answer.put((Integer)Key1,(String)pos.Value);}
            if (pos.Right.Value!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos!=null && pos.Value!=null && pos.Exist  && pos.Exist && Key<=pos.Key) {
            java.lang.Integer Key1=(java.lang.Integer)pos.Key;
            Answer.put((Integer)Key1,(String)pos.Value);}
        return Answer;
    }

    @Override
    public Integer firstKey() {
        RBNode ans=head;
        RBNode left=head;
        if (left.Value==null) return null;
        while (left.Left.Value!=null) {left=left.Left;ans=(left.Key<ans.Key && left.Exist? left: ans);}
        java.lang.Integer answer=(java.lang.Integer)ans.Key;
        return ((Integer)answer);
    }

    @Override
    public Integer lastKey() {
        RBNode ans=head;
        RBNode right=head;
        if (right.Value==null) return null;
        while (right.Right.Value!=null) {right=right.Right;ans=(right.Key>ans.Key && right.Exist? right: ans);}
        java.lang.Integer answer=(java.lang.Integer)ans.Key;
        return ((Integer)answer);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }
    public RBNode GetLeft(RBNode pos) {
        RBNode left=pos;
        if (left.Value==null) return null;
        while (left.Left.Value!=null) left=left.Left;
        return left;
    }
    public RBNode GetRight(RBNode pos) {
        RBNode right=pos;
        if (right.Value==null) return null;
        while (right.Right.Value!=null) right=right.Right;
        return right;
    }
    @Override
    public java.lang.String toString() {
        StringBuilder sb=new StringBuilder("{");
        java.lang.String delimiter = "";
        RBNode pos=GetLeft(head);
        RBNode end=GetRight(head);
        boolean FromRight=false;
        while (pos!=end) {
            if (!FromRight && pos.Exist) {sb.append(delimiter).append(pos.Key).append("=").append(pos.Value);
                delimiter = ", ";}
            if (pos.Right.Value!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        if (pos!=null && pos.Value!=null && pos.Exist) sb.append(delimiter).append(pos.Key).append("=").append(pos.Value);
        sb.append("}");
        return sb.toString();
    }
    @Override
    public boolean containsKey(Object key) {
        RBNode pos=head;
        int Key=(int)key;
        while (pos.Value!=null && pos.Key!=Key) {
            if (Key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        return (pos.Value!=null && pos.Exist);
    }

    @Override
    public boolean containsValue(Object value) {
        RBNode pos=GetLeft(head);
        RBNode end=GetRight(head);
        boolean FromRight=false;
        while (pos!=end) {
            if (pos.Value.equals(value) && pos.Exist) return true;
            if (pos.Right.Value!=null  && !FromRight) {
                pos=pos.Right;
                pos=GetLeft(pos);
            } else {FromRight=(pos.Prev.Right.equals(pos));pos=pos.Prev;}
        }
        return false;
    }

    @Override
    public String get(Object key) {
        RBNode pos=head;
        int Key=(int)key;
        while (pos.Value!=null && pos.Key!=Key) {
            if (Key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        if (pos.Value!=null && pos.Exist) return (String) pos.Value;
        return null;
    }
    private String ReplaceValue(int key,String val){
        RBNode pos=head;
        while (pos.Value!=null && pos.Key!=key) {
            if (key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        if (pos.Value!=null) {
            if (!pos.Exist) {
                pos.Exist=true;
                pos.Value=(java.lang.String)val;
                size++;
                return null;
            }
            String answer=(String)pos.Value;
            pos.Value=(java.lang.String)val;
            return answer;
        }
        return null;
    }
    private void LeftRotate(RBNode P){
        RBNode R=P.Right;
        if (R.Left!=EmptyNode)R.Left.Prev=P;
        P.Right=(R!=EmptyNode?R.Left : EmptyNode);
        if (R!=EmptyNode) R.Left=P;
        if (P.Prev!=null) {
            if (P.Prev.Left!=EmptyNode && P.Prev.Left.equals(P)) P.Prev.Left=R; else P.Prev.Right=R;}
        else head=R;
        if (R!=EmptyNode) R.Prev=P.Prev;
        P.Prev=R;
    }

    private void RightRotate(RBNode P){
        RBNode L=P.Left;
        if (L.Right!=EmptyNode) L.Right.Prev=P;
        P.Left=(L!=EmptyNode?L.Right : EmptyNode);
        if (L!=EmptyNode) L.Right=P;
        if (P.Prev!=null) {
            if (P.Prev.Left!=EmptyNode && P.Prev.Left.equals(P)) P.Prev.Left=L; else P.Prev.Right=L;}
        else head=L;
        if (L!=EmptyNode) L.Prev=P.Prev;
        P.Prev=L;
    }
    private void fixCase1(RBNode pos){
        if (pos.equals(head)) {pos.Color=false;return;}
        else fixCase2(pos);
    }
    private void fixCase2(RBNode pos){
        if (!pos.Prev.Color) return;
        else fixCase3(pos);
    }
    private void fixCase3(RBNode pos){
        RBNode g=Grandparent(pos);
        RBNode u=Uncle(pos);
        if (pos.Prev.Color && u.Value!=null && u.Color) {
            pos.Prev.Color=false;
            u.Color=false;
            g.Color=true;
            fixCase1(g);
        } else fixCase4(pos);
    }
    private void fixCase4(RBNode pos){
        RBNode g=Grandparent(pos);
        RBNode u=Uncle(pos);
            if (pos==pos.Prev.Right && pos.Prev==g.Left) {
                LeftRotate(pos.Prev);
                pos.Color=false;
                g.Color=true;
                pos=pos.Left;

            } else if (pos==pos.Prev.Left && pos.Prev==g.Right) {
                RightRotate(pos.Prev);
                pos.Color=false;
                g.Color=true;
                pos=pos.Right;
            }
            fixCase5(pos);

    }
    private void fixCase5(RBNode pos){
        RBNode g=Grandparent(pos);
        pos.Prev.Color=false;
        g.Color=true;
        if (pos==pos.Prev.Right && pos.Prev==g.Right) {
            LeftRotate(g);
            fixCase1(g);
        } else {RightRotate(g);fixCase1(g);}

    }
    @Override
    public String put(Integer key, String value) {
        int Key=(int)key;
        int oldsize=size;
        String Value=ReplaceValue(Key,value);
        if (size!=oldsize) return null;
        if (Value!=null) return Value;

        RBNode Prev=null;
        RBNode pos=head;
        while (pos.Value!=null) {
            Prev=pos;
            if (Key>pos.Key) pos=pos.Right;
            else pos=pos.Left;
        }
        pos=new RBNode(Key,(java.lang.String)value,Prev,EmptyNode);
        if (head.Value==null) head=pos; else
        {if (Key>Prev.Key) Prev.Right=pos;
        else Prev.Left=pos;}
        fixCase1(pos);
        size++;
        return null;
    }
    private RBNode Grandparent(RBNode pos){
        if ((pos!=null) && (pos.Prev!=null))
            return pos.Prev.Prev;
        else
            return null;
    }

    private RBNode Uncle(RBNode pos){
        RBNode grandp=Grandparent(pos);
        if (grandp==null) return null;
        if (pos.Prev.equals(grandp.Left)) return grandp.Right;
        else return grandp.Left;
    }
    @Override
    public String remove(Object key) {
        RBNode pos=head;
        int Key=(int)key;
        while (pos.Value!=null && pos.Key!=Key) {
            if (Key>pos.Key) pos=pos.Right; else
                pos=pos.Left;
        }
        if (pos.Value!=null && pos.Exist) {size--;pos.Exist=false;return (String) pos.Value;}
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        size=0;
        head=EmptyNode;
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
