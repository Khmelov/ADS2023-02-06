package by.it.group251001.besedinAndrei.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {
    protected static class LNode<E> {
        public E data;
        public LNode<E> next;
        public int pose;

        public LNode(E data, int actPose) {
            this.data = data;
            this.pose = actPose;
            next = null;
        }
    }

    private int maxSize = 100;
    private int actualSize = 0, lastPose = 0;

    protected static class MyList<E> {
        private LNode<E> head, tail;

        public boolean contains(E o) {
            LNode<E> curr = head;
            while (curr != null && !curr.data.equals(o)) {
                curr = curr.next;
            }
            return curr != null;
        }

        public boolean add(E o, int actPose, boolean toCheck) {
            if (toCheck && contains(o)) {
                return false;
            }
            LNode<E> curr = new LNode<E>(o, actPose);
            if (tail == null) {
                head = tail = curr;
            } else {
                tail.next = curr;
                tail = curr;
            }
            return true;
        }

        public boolean remove(E o) {
            if (head == null) {
                return false;
            }
            if (head.data.equals(o)) {
                head = head.next;
                return true;
            }
            LNode<E> prev = head;
            while (prev.next != null && !prev.next.data.equals(o)) {
                prev = prev.next;
            }
            if (prev.next == null) {
                return false;
            }
            if (prev.next == tail) {
                prev.next = null;
                tail = prev;
            } else {
                prev.next = prev.next.next;
            }
            return true;
        }
    }

    private MyList[] map = new MyList[maxSize];
    {
        for (int i = 0; i < maxSize; i++) map[i] = new MyList<E>();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder res = new StringBuilder("[");
        LNode<E>[] nods = new LNode[maxSize];

        for (int i = 0; i < maxSize; i++) {
            nods[i] = (LNode<E>) map[i].head;
        }

        while (true) {
            LNode<E> minInf = new LNode<E>((E) new Object(), Integer.MAX_VALUE);
            int minI = -1;
            for (int i = 0; i < maxSize; i++)
                if (nods[i] != null && nods[i].pose < minInf.pose) {
                    minInf = nods[i];
                    minI = i;
                }
            if (minI == -1) {
                break;
            }
            nods[minI] = nods[minI].next;
            res.append(minInf.data.toString()).append(", ");
        }

        return res.substring(0, res.length() - 2) + "]";
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public void clear() {
        actualSize = 0;
        for (int i = 0; i < maxSize; i++) {
            map[i] = new MyList<E>();
        }
    }

    @Override
    public boolean isEmpty() {
        return actualSize == 0;
    }

    @Override
    public boolean add(E e) {
        if (map[e.hashCode() % maxSize].add(e, lastPose++, true))
            actualSize++;
        else {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (map[o.hashCode() % maxSize].remove(o))
            actualSize--;
        else {
            return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return map[o.hashCode() % maxSize].contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E o : c) {
            if (add(o)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int deleted = 0;

        for (int i = 0; i < maxSize; i++) {

            MyList<E> newMyList = new MyList<>();
            LNode<E> curr = map[i].head;

            while (curr != null) {
                if (!c.contains(curr.data)) {
                    newMyList.add(curr.data, curr.pose, false);
                } else {
                    deleted++;
                }
                curr = curr.next;
            }

            map[i] = newMyList;
        }
        actualSize -= deleted;
        return deleted > 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int deleted = 0;

        for (int i = 0; i < maxSize; i++) {
            MyList<E> newMyList = new MyList<>();

            LNode<E> curr = map[i].head;

            while (curr != null) {
                if (c.contains(curr.data)) {
                    newMyList.add(curr.data, curr.pose, false);
                } else {
                    deleted++;
                }
                curr = curr.next;
            }

            map[i] = newMyList;
        }

        actualSize -= deleted;

        return deleted > 0;
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
