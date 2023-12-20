package by.it.group251001.brutskaya.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E extends Comparable> implements Set<E> {
    class Node<E> {
        E item;
        Node<E> left, right;

        public Node(E item) {
            this.item = item;
        }
    }

    int size = 0;
    Node<E> root = null;

    Node<E> search(E e) {
        Node<E> temp = root, prev = null;

        while (temp != null) {
            prev = temp;
            if (temp.item.compareTo(e) == 0)
                return temp;
            if (e.compareTo(temp.item) > 0) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        return prev;
    }

    void reverse(Node<E> node, StringBuilder s){
        if (node==null)
            return;
        if (node.left!= null)
            reverse(node.left, s);
        s.append(node.item).append(", ");
        if (node.right!=null)
            reverse(node.right, s);
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        if(root!=null){
            reverse(root, s);
            s.delete(s.length()-2, s.length());
        }
        s.append("]");
        return s.toString();
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
        Node<E> node = search((E) o);
        return node.item.compareTo(o) == 0;
    }


    @Override
    public boolean add(E e) {
        Node<E> node = search(e);
        if (node == null) {
            root = new Node<>(e);
        } else {
            if (node.item.compareTo(e) == 0)
                return false;
            if (e.compareTo(node.item) > 0) {
                node.right = new Node<>(e);
            } else {
                node.left = new Node<>(e);
            }
        }
        size++;
        return true;
    }

    Node<E> remove(Node<E> root, E e) {
        if (root == null) {
            return root;
        }
        if (e.compareTo(root.item) < 0) {
            root.left = remove(root.left, e);
            return root;
        } else if (e.compareTo(root.item) > 0) {
            root.right = remove(root.right, e);
            return root;
        }

        if (root.left == null) {
            Node<E> temp = root.right;
            return temp;
        } else if (root.right == null) {
            Node<E> temp = root.left;
            return temp;
        } else {
            Node<E> succParent = root;

            Node<E> succ = root.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }
            if (succParent != root) {
                succParent.left = succ.right;
            } else {
                succParent.right = succ.right;
            }
            root.item = succ.item;

            return root;
        }
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

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o :
                c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E o :
                c) {
            add(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        MyTreeSet<E> set = new MyTreeSet<>();
        int count = 0;
        for (Object o :
                c) {
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
        if (c.isEmpty())
            return false;
        for (Object o :
                c) {
            if (contains(o))
                remove(o);
        }
        return true;
    }

    @Override
    public void clear() {
        root=null;
        size=0;
    }
///////////////////////////////////////////////////////////////

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
}
