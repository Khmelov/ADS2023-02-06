package by.it.group251003.kapinskiy.lesson11;


import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E extends Comparable> implements Set<E> {



    private class Node {
        E item;
        Node right;

        public Node(E item) {
            this.item = item;
        }

        Node left;

    }

    Node root = null;
    private int size = 0;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (root != null) {
            Reverse(root, sb);
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.append(']').toString();
    }

    private void Reverse(Node Node, StringBuilder sb) {
        if (Node == null) return;
        if (Node.left != null) {
            Reverse(Node.left, sb);
        }
        sb.append(Node.item + ", ");
        if (Node.right != null) {
            Reverse(Node.right, sb);
        }

    }

    private Node Search(E e) {
        Node tmp = root, prev = null;
        while (tmp != null) {
            prev = tmp;
            if (tmp.item.compareTo(e) == 0) return tmp;
            if (e.compareTo(tmp.item) > 0) {
                tmp = tmp.right;
            } else {
                tmp = tmp.left;
            }
        }
        return prev;
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
    public boolean contains(Object o) {
        Node node = Search((E) o);
        return node.item.compareTo(o) == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        Node Node = Search(e);
        if (Node == null) {
            root = new Node(e);
        }
        if (Node != null) {
            if (Node.item.compareTo(e) == 0) return false;
            if (e.compareTo(Node.item) < 0) {
                Node.left = new Node(e);
            } else {
                Node.right = new Node(e);
            }
        }
        size++;
        return true;
    }


    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            root = remove(root, (E) o);
            size--;
            return true;
        }
        return false;
    }

    Node remove(Node node, E e) {
        if (node == null)
            return null;
        int cmp = e.compareTo(node.item);

        if (cmp < 0) {
            node.left = remove(node.left, e);
        } else if (cmp > 0) {
            node.right = remove(node.right, e);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.item = min(node.right).item;
            node.right = remove(node.right, node.item);
        }
        return node;
    }

    Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }





    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E o : c)
            add(o);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        MyTreeSet<E> set = new MyTreeSet<>();
        int count = 0;
        for (Object o : c) {
            if (contains(o)) {
                set.add((E) o);
                count++;
            }
        }
        size = count;
        root = set.root;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) return false;
        for (Object o : c)
            if (contains(o))
                remove(o);
        return true;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}
