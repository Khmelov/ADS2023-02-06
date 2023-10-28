package by.it.group251002.sazonov.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

class Node<E> {
    E key;
    int height;
    boolean removed;
    Node<E> left, right;
    public Node(E value) {
        this.key = value;
        this.left = this.right = null;
        this.removed = false;
        height = 1;
    }

}
public class MyTreeSet<E extends Comparable> implements Set<E> {

    int _size = 0;
    Node<E> head = null;

    private int height(Node<E> n) {
        return n == null ? 0 : n.height;
    }

    private int balanceHeight(Node<E> n) {
        return height(n.right) - height(n.left);
    }

    private void fixHeight(Node<E> n) {
        int hl = height(n.left);
        int hr = height(n.right);
        n.height = (hl > hr ? hr : hl) + 1;
    }

    private Node<E> rotateRight(Node<E> parent) {
        Node<E> child = parent.left;
        parent.left = child.right;
        child.right = parent;
        fixHeight(parent);
        fixHeight(child);
        return child;
    }

    private Node<E> rotateLeft(Node<E> parent) {
        Node<E> child = parent.right;
        parent.right = child.left;
        child.left = parent;
        fixHeight(parent);
        fixHeight(child);
        return child;
    }

    private Node<E> balanceNode(Node<E> parent) {
        fixHeight(parent);
        if (balanceHeight(parent) == 2) {
            if (balanceHeight(parent.right) < 0) {
                parent.right = rotateRight(parent.right);
            }
            return rotateLeft(parent);
        }

        if (balanceHeight(parent) == -2) {
            if (balanceHeight(parent.left) > 0) {
                parent.left = rotateLeft(parent.left);
            }
            return rotateRight(parent);
        }
        return parent;
    }

    private Node<E> findKey(Node<E> parent, E key) {
        if (parent == null) return null;
        if (key.compareTo(parent.key) == 0 && parent.removed == false) {
            return parent;
        }

        if (key.compareTo(parent.key) < 0) {
            return findKey(parent.left, key);
        } else {
            return findKey(parent.right, key);
        }
    }

    private Node<E> insert(Node<E> parent, E key) {
        if (parent == null) return new Node<E>(key);
        if (key.compareTo(parent.key) < 0) {
            parent.left = insert(parent.left, key);
        } else if (key.compareTo(parent.key) > 0) {
            parent.right = insert(parent.right, key);
        } else {
            parent.removed = false;
        }
        return balanceNode(parent);
    }

    private Node<E> markRemoved(Node<E> parent, E key) {
        if (parent == null) return null;
        if (key.compareTo(parent.key) == 0 && parent.removed == false) {
            parent.removed = true;
            return parent;
        }

        if (key.compareTo(parent.key) < 0) {
            return markRemoved(parent.left, key);
        } else {
            return markRemoved(parent.right, key);
        }
    }

    private void toStringRec(StringBuilder sb, Node<E> parent) {
        if (parent == null) {
            return;
        }

        toStringRec(sb, parent.left);
        if (parent.removed == false) {
            if (sb.length() != 1) {
                sb.append(", ");
            }
            sb.append(parent.key);
        }
        toStringRec(sb, parent.right);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String delimiter = "";

        toStringRec(sb, head);
        sb.append("]");
        return sb.toString();
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
    public boolean contains(Object o) {
        return findKey(head, (E) o) != null;
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
        if (findKey(head, e) == null) {
            head = insert(head, e);
            _size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (markRemoved(head, (E) o) != null) {
            _size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] data =  c.toArray();
        for(int i = 0; i < c.size(); i ++) {
            if (!contains(data[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean ok = false;
        Object[] data = c.toArray();
        for(int i = 0; i < c.size(); i ++) {
            if (add((E) data[i])) {
                ok = true;
            }
        }
        return ok;
    }

    private void retainAllRec(Node<E> parent, Collection<?> c) {
       if (parent == null) return;

       if (!c.contains(parent.key) && parent.removed != true) {
           parent.removed = true;
           _size--;
       }

       retainAllRec(parent.left, c);
       retainAllRec(parent.right, c);
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        int prev_size = _size;
        retainAllRec(head, c);

        return !(prev_size == _size);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ok = false;
        Object[] data = c.toArray();
        for(int i = 0; i < c.size(); i ++) {
            if (remove(data[i])) {
                ok = true;
            }
        }
        return ok;
    }

    private void clearRec(Node<E> parent) {
        if (parent == null) {
            return;
        }

        clearRec(parent.left);
        clearRec(parent.right);
        parent.left = null;
        parent.right = null;
    }
    @Override
    public void clear() {
        clearRec(head);
        _size = 0;
        head = null;
    }
}
