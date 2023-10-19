package by.it.group251003.bondarenko.lesson11;
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

    TreeNode root;
    int size;

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
        inOrderTraversal(root, sb);
        return sb.append("]").toString();
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
        return contains(root, (E) o);
    }

    boolean contains(TreeNode node, E element) {
        if (node == null)
            return false;
        int compare = element.compareTo(node.data);

        if (compare == 0)
            return true;

        if (compare < 0)
            return contains(node.left, element);
        return contains(node.right, element);

    }

    TreeNode insert(TreeNode node, E element) {
        if (node == null)
            return new TreeNode(element);

        int compare = element.compareTo(node.data);

        if (compare < 0)
            node.left = insert(node.left, element);
        else if (compare > 0)
            node.right = insert(node.right, element);

        return node;
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }
        root = insert(root, e);
        size++;
        return true;
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
            root = remove(root, (E) o);
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj: c) {
            if (!contains(obj))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean flag = false;
        for (E e: c)
            flag = add(e) || flag;

        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean flag = false;
        MyTreeSet<E> tmp = new MyTreeSet<>();
        for (Object o : c)
            if (contains(o))
                flag = tmp.add((E) o) || flag;

        root = tmp.root;
        size = tmp.size;
        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        for (Object o: c)
            flag = remove(o) || flag;

        return flag;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
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
}
