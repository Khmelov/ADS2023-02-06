package by.it.group251002.alesina.lesson12;

import by.it.group251002.sazonov.lesson12.MyRbMap;

import java.util.*;

public class MySplayMap implements NavigableMap<Integer, String> {

    private static class Node {
        Integer key;
        String value;
        Node parent, left, right;
        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            this.left = this.right = this.parent = null;
        }
    }

    int size = 0;
    Node head = null;

    private Node rotateToLeft(Node n) {
        Node child = n.right;
        if (child != null) {
            n.right = child.left;
            if (child.left != null) {
                child.left.parent = n;
            }
            child.parent = n.parent;
            child.left = n;
        }

        if (n.parent != null) {
            if (n.equals(n.parent.left)) n.parent.left = child;
            else n.parent.right = child;
        }
        n.parent = child;
        return child;
    }

    private Node rotateToRight(Node n) {
        Node child = n.left;
        if (child != null) {
            n.left = child.right;
            if (child.right != null) {
                child.right.parent = n;
            }
            child.parent = n.parent;
            child.right = n;
        }

        if (n.parent != null) {
            if (n.equals(n.parent.left)) n.parent.left = child;
            else n.parent.right = child;
        }
        n.parent = child;
        return child;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("{");
        String comma = "";
        Stack<Node> st = new Stack<Node>();
        Node currNode = head;
        while (!st.isEmpty() || currNode != null) {
            if (currNode != null) {
                st.push(currNode);
                currNode = currNode.left;
            } else {
                currNode = st.pop();
                str.append(comma).append(currNode.key).append("=").append(currNode.value);
                comma = ", ";
                currNode = currNode.right;
            }
        }

        str.append("}");
        return str.toString();
    }

    private Node search(Node n, Integer key) { //поиск значения в дереве
        while (n != null) {
            if (key < n.key) {
                n = n.left;
            } else if (key > n.key) {
                n = n.right;
            } else {
                return n;
            }
        }

        return null;
    }

    @Override
    public String get(Object key) {  //получить элемент по ключу
        Node n = search(head, (int)key); //находим элемент
        if (n == null) {
            return null;
        }

        head = splay(n); //поворачиваем дерево
        return head.value;
    }

    @Override
    public boolean containsKey(Object key) { //проверка, есть ли элемент с таким ключом
        Node n = search(head, (int)key);
        if (n == null) {
            return false;
        }

        head = splay(n);
        return true;
    }

    private Node findMax(Node n) {
        if (n == null) {
            return null;
        }

        while(n.right != null) {
            n = n.right;
        }

        return n;
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
    public String put(Integer key, String value) {  //добавить элемент в дерево
        Node n = search(head, key);
        if (n != null) {  //проверяем, есть ли уже элемент с таким ключом
            String oldValue = n.value;  //меняем его значение, если нашли
            n.value = value;
            head = splay(n);
            return oldValue;
        }

        size++;
        Node preInsert = null;
        n = head;
        while(n != null) {   //ищем позицию, после которой надо вставить новый элемент
            preInsert = n;
            if (key < n.key) {
                n = n.left;
            } else if (key > n.key) {
                n = n.right;
            }
        }

        n = new Node(key, value);
        n.parent = preInsert;

        if (preInsert == null) {  //вставляем элемент на нужную позицию
            head = n;
        } else if (preInsert.key < n.key) {
            preInsert.right = n;
        } else if (preInsert.key > n.key) {
            preInsert.left = n;
        }
        head = splay(n);
        return null;
    }


    private Node splay(Node n) { //процедура разворота дерева
        if (n == null) {
            return null;
        }

        while(n.parent != null) {
            if (n.parent.parent == null) {   //если нет прародителя - делаем узел правым или левым потомком
                if (n.equals(n.parent.left))
                    n = rotateToRight(n.parent);
                else
                    n = rotateToLeft(n.parent);
            } else if (n.equals(n.parent.left) && n.parent.parent.left == n.parent) { //левый-левый
                n = rotateToRight(n.parent);   //и узел, и его родитель - левые потомки=>2 разворота вправо
                n = rotateToRight(n.parent);
            } else if (n.equals(n.parent.left) && n.parent.parent.right == n.parent) {  //левый-правый
                n = rotateToRight(n.parent); //узел - левый потомок, а его родитель - правый=>сначала вправо, потом влево
                n = rotateToLeft(n.parent);
            } else if (n.equals(n.parent.right) && n.parent.parent.left == n.parent) {   //правый-левый
                n = rotateToLeft(n.parent);   //узел - правый потомок, а его родитель - леввый=>сначала влево, потом вправо
                n = rotateToRight(n.parent);
            } else {                            //правый-правый
                n = rotateToLeft(n.parent);  //и узел, и его родитель - правые потомки=>2 разворота влево
                n = rotateToLeft(n.parent);
            }
        }

        return n;
    }


    @Override
    public Entry<Integer, String> lowerEntry(Integer key) {
        return null;
    }

    private Node findLower(Node n, Integer key) {
        Node lower = null;
        while (n != null) {
            if (key <= n.key) {
                n = n.left;
            } else {
                lower = n;
                n = n.right;
            }
        }

        return lower;
    }
    @Override
    public Integer lowerKey(Integer key) {  //вернуть наибольший ключ, который строго меньше заданного
        Node lower = findLower(head, key);
        if (lower == null) {
            return null;
        }
        head = splay(lower);

        return lower.key;
    }

    private Node findHigher(Node n, Integer key) {
        Node higher = null;
        while (n != null) {
            if (key < n.key) {
                higher = n;
                n = n.left;
            } else {
                n = n.right;
            }
        }

        return higher;
    }
    @Override
    public Integer higherKey(Integer key) {  //вернуть наименьший ключ, который строго больше заданного
        Node higher = findHigher(head, key);
        if (higher == null) {
            return null;
        }
        head = splay(higher);

        return higher.key;
    }

    @Override
    public Integer firstKey() {  //вернуть наименьший
        if (head == null) {
            return null;
        }

        Node n = head;
        while (n.left != null) {
            n = n.left;
        }

        head = splay(n);
        return head.key;
    }

    @Override
    public Integer lastKey() {  //вернуть наибольший
        if (head == null) {
            return null;
        }

        Node n = head;
        while (n.right != null) {
            n = n.right;
        }

        head = splay(n);
        return head.key;
    }

    @Override
    public Integer floorKey(Integer key) {
        if (search(head, key) != null) {
            return key;
        }

        return lowerKey(key);
    }

    @Override
    public Integer ceilingKey(Integer key) {
        if (search(head, key) != null) {
            return key;
        }
        return higherKey(key);
    }

    @Override
    public Entry<Integer, String> floorEntry(Integer key) {
        return null;
    }


    @Override
    public Entry<Integer, String> ceilingEntry(Integer key) {
        return null;
    }



    @Override
    public Entry<Integer, String> higherEntry(Integer key) {
        return null;
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

    private void headToKey(SortedMap<Integer, String> newMap, Integer toKey, Node n) {
        if (n == null) {
            return;
        }

        headToKey(newMap, toKey, n.left);
        if (toKey > n.key) {
            newMap.put(n.key, n.value);
            headToKey(newMap, toKey, n.right);
        }
    }

    private void tailFromKey(SortedMap<Integer, String> newMap, Integer fromKey, Node n) {
        if (n == null) {
            return;
        }

        tailFromKey(newMap, fromKey, n.right);
        if (fromKey <= n.key) {
            newMap.put(n.key, n.value);
            tailFromKey(newMap, fromKey, n.left);
        }
    }

    @Override
    public SortedMap<Integer, String> headMap(Integer toKey) {
        SortedMap<Integer, String> newMap = new MyRbMap();
        headToKey(newMap, toKey, head);
        return newMap;
    }

    @Override
    public SortedMap<Integer, String> tailMap(Integer fromKey) {
        SortedMap<Integer, String> newMap = new MyRbMap();
        tailFromKey(newMap, fromKey, head);
        return newMap;
    }






    @Override
    public boolean containsValue(Object value) {
        Stack<Node> st = new Stack<Node>();
        Node curNode = head;
        while (!st.isEmpty() || curNode != null) {
            if (curNode != null) {
                st.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = st.pop();
                if (value.equals(curNode.value)) {
                    head = splay(curNode);
                    return true;
                }
                curNode = curNode.right;
            }
        }
        return false;
    }




    @Override
    public String remove(Object key) {
        Node n = search(head, (int)key);
        if (n != null) {
            size--;
            remove(n);
            return n.value;
        }

        return null;
    }

    private void remove(Node node) {
        if (node == null) {
            return;
        }

        head = splay(node);

        if (node.left == null) {
            head = head.right;
            if (head != null) {
                head.parent = null;
            }
        } else {
            Node tmp = head;
            head = splay(findMax(head.left));
            head.right = tmp.right;
            tmp.right.parent = head;
        }
    }


    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) {

    }

    @Override
    public void clear() {
        Stack<Node> s = new Stack<Node>();
        Node lastVisited = null, curNode = head;
        while (!s.isEmpty() || curNode != null) {
            if (curNode != null) {
                s.push(curNode);
                lastVisited = curNode;
                curNode = curNode.left;
            } else {
                curNode = s.pop();
                if (lastVisited != null && lastVisited != curNode) {
                    lastVisited.right = null;
                }
                lastVisited = curNode;
                curNode.left = null;
                curNode = curNode.right;
            }
        }
        head.left = null;
        head.right = null;
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
