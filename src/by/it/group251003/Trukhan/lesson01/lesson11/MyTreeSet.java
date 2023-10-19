package by.it.group251003.Trukhan.lesson01.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet<E extends Comparable<E>> implements Set<E> {
    class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;
        TreeNode(E e) {
            data = e;
        }
    }

    private TreeNode map = null;
    private int size = 0;


    void inOrderTraversal(TreeNode node, StringBuilder sb) {
        if (node == null)
            return;

        inOrderTraversal(node.left, sb);

        if (sb.length() > 1)
            sb.append(", ");
        sb.append(node.data);

        inOrderTraversal(node.right, sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        inOrderTraversal(map, sb);
        return sb.append("]").toString();
    }

    private boolean containsElem(Object o, TreeNode node) {
        if (node == null) return false;

        if (node.data.equals(o)) return true;

        if (containsElem(o, node.left)) return true;
        if (containsElem(o, node.right)) return true;

        return false;
    }
    @Override
    public boolean contains(Object o) {
        return containsElem(o, map);
    }

    TreeNode remove(TreeNode node, E element) {
        if (node == null)
            return null;
        int compare = element.compareTo(node.data);

        if (compare < 0) {
            node.left = remove(node.left, element);
        } else if (compare > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.data = min(node.right).data;
            node.right = remove(node.right, node.data);
        }
        return node;
    }

    TreeNode min(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            map = remove(map, (E) o);
            size--;
            return true;
        }
        return false;
    }

    TreeNode addEl(TreeNode node, E e) {
        if (node == null)
            return new TreeNode(e);

        int compare = e.compareTo(node.data);

        if (compare < 0)
            node.left = addEl(node.left, e);
        else if (compare > 0)
            node.right = addEl(node.right, e);

        return node;
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }
        map = addEl(map, e);
        size++;
        return true;
    }
    @Override
    public boolean removeAll(Collection c) {
        boolean changed = false;
        for (Object o: c)
            changed = remove(o) || changed;

        return changed;
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e: c)
            changed = add(e) || changed;

        return changed;
    }
    @Override
    public boolean retainAll(Collection c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean changed = false;
        MyTreeSet<E> tmp = new MyTreeSet<>();
        for (Object o : c)
            if (contains(o))
                changed = tmp.add((E) o) || changed;

        map = tmp.map;
        size = tmp.size;
        return changed;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object obj: c) {
            if (!contains(obj))
                return false;
        }
        return true;
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
    public void clear() {
        map = null;
        size = 0;
    }




    @Override
    public Iterator iterator() {
        return null;
    }
    @Override
    public Object[] toArray() {
        return new Object[0];
    }
    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
