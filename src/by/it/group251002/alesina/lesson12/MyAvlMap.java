package by.it.group251002.alesina.lesson12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MyAvlMap implements Map<Integer, String> {

    private static class Node {
        Integer key;
        String value;
        int height;
        Node left, right;
        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            height = 1;
        }

    }
    int _size = 0;
    Node head = null; //корень дерева

    private Node search(Node n, Integer key) {
        while(n != null) {
            if ((int)key < (int)n.key) {
                n = n.left;
            } else if ((int)key > (int)n.key) {
                n = n.right;
            } else {
                return n;
            }
        }
        return null;
    }

    private Node insert(Node n, Integer key, String value) { //вставка нового элемента
        if (n == null)
            return new Node(key, value);
        if ((int)key < (int)n.key) {  //меньше - влево
            n.left = insert(n.left, key, value);
        } else {   //больше - вправо
            n.right = insert(n.right, key, value);
        }
        return balanceNode(n);
    }
    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private int balanceHeight(Node n) {
        return height(n.right) - height(n.left);
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Node n = search(head, (Integer)key); //ищем элемент с данным ключом
        if (n == null) { //не нашли - такого ключа нет
            return false;
        }
        return true;
    }


    private void getSameHeight(Node n) {  //исправляем ситуацию, когда разница в высоте больше 1
        if (n == null) {
            return;
        }
        int hLeft = height(n.left);
        int hRight = height(n.right);
        n.height = (hLeft > hRight ? hLeft : hRight) + 1;
    }

    private Node rotateToRight(Node parent) {  //поворот вправо
        Node child = parent.left;
        parent.left = child.right;
        child.right = parent;
        getSameHeight(parent);
        getSameHeight(child);
        return child;
    }

    private Node rotateToLeft(Node parent) { //поворот влево
        Node child = parent.right;
        parent.right = child.left;
        child.left = parent;
        getSameHeight(parent);
        getSameHeight(child);
        return child;
    }



    private Node balanceNode(Node parent) {
        if (parent == null) {
            return null;
        }
        getSameHeight(parent);
        if (balanceHeight(parent) == 2) {
            if (balanceHeight(parent.right) < 0) {
                parent.right = rotateToRight(parent.right);
            }
            return rotateToLeft(parent);
        }

        if (balanceHeight(parent) == -2) {
            if (balanceHeight(parent.left) > 0) {
                parent.left = rotateToLeft(parent.left);
            }
            return rotateToRight(parent);
        }
        return parent;
    }

    @Override
    public String get(Object key) {   //получение значения элемента по ключу
        Node n = search(head, (Integer)key);
        if (n == null) {
            return null;
        }
        return n.value;
    }

    private Node findMin(Node n) {
        return n.left == null ? n : findMin(n.left);
    }

    private Node removeMin(Node n) {
        if (n.left == null) {
            return n.right;
        }
        n.left = removeMin(n.left);
        return balanceNode(n);
    }

    private Node remove(Node n, Integer key) {  //удаление элементаа
        if (n == null) return null;
        if ((int)key < (int)n.key) {   //рекурсивно ищем позицию удаляемого элемент в дереве
            n.left = remove(n.left, key);
        } else if ((int)key > (int)n.key) {
            n.right = remove(n.right, key);
        } else {
            Node q = n.left;
            Node r = n.right;
            if (r == null) return q;
            Node min = findMin(r);
            min.right = removeMin(r);
            min.left = q;
            return balanceNode(min);  //восстанавливаем нарушенную часть дерева после удаления элемента
        }

        return balanceNode(n);  //восстанавливаем нарушенную часть дерева после удаления элемента
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        String delimiter = "";
        Stack<Node> s = new Stack<Node>();
        Node curNode = head;
        while (!s.isEmpty() || curNode != null) {
            if (curNode != null) {
                s.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = s.pop();
                sb.append(delimiter).append(curNode.key).append("=").append(curNode.value);
                delimiter = ", ";
                curNode = curNode.right;
            }
        }

        sb.append("}");
        return sb.toString();
    }


    @Override
    public boolean containsValue(Object value) {
        return false;
    }


    @Override
    public String put(Integer key, String value) {
        Node n = search(head, key);
        if (n == null) {
            head = insert(head, key, value);
            _size++;
            return null;
        }
        String prevValue = n.value;
        n.value = value;
        return prevValue;
    }

    @Override
    public String remove(Object key) {
        Node n = search(head, (Integer) key);
        if (n == null) {
            return null;
        }

        head = remove(head, (Integer) key);
        _size--;
        return n.value;
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
        _size = 0;
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
