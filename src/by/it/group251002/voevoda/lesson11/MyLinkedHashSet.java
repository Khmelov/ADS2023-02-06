package by.it.group251002.voevoda.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {

    private SLL<E>[] set;
    private int size;

    private int indexPointer;

    public MyLinkedHashSet() {
        int defaultCapacity = 32;
        set = (SLL<E>[]) new SLL[defaultCapacity];
    }

    public MyLinkedHashSet(int capacity) {
        set = (SLL<E>[]) new SLL[capacity];
    }

    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////

    public String toString() {

        int checkSum = 0;
        for (int i = 0; i < set.length; ++i) {
            if (set[i] != null) {
                checkSum += set[i].getSize();
            }
        }
        System.out.print("CHECK_SUM_RES: ");
        System.out.println(checkSum == size);
        System.out.printf("CHECK_SUM: %d\n", checkSum);

        Node<E>[] buffer = toBuffer();

        radixSort(buffer);
//        insertSort(buffer);

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        int startIndex = 0;
        for (; startIndex < buffer.length && buffer[startIndex] == null; ) {
            ++startIndex;
        }

        if (buffer.length > startIndex) {
            sb.append(buffer[startIndex].getValue().toString());
        }

        for (int j = startIndex + 1; j < buffer.length; ++j) {
            if (buffer[j] != null) {
                sb.append(", ");
                sb.append(buffer[j].getValue().toString());
            }
        }

        sb.append(']');

        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(set, null);
        size = 0;
        System.out.println("CLEARED");
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {

        int index = e.hashCode() % set.length;

        if (set[index] != null && set[index].contains(e)) {
            System.out.printf("x(%d) ", e);
            return false;
        }

        if (set[index] == null) {
            set[index] = new SLL<>();
        }
        if (e.equals(200)) {
            System.out.printf("{INDEX: %d} ", index);
        }
        set[index].append(e, indexPointer++);
        ++size;

        System.out.printf("e(%d) ", e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = o.hashCode() % set.length;
        if (set[index] == null) {
            return false;
        }

        if (set[index].remove((E) o)) {
            --size;
            if (set[index].getSize() == 0) {
                set[index] = null;
            }
            return true;
        }

        if (set[index].getSize() == 0) {
            set[index].setHead(null);
            set[index].setTail(null);
        }

        return false;
    }

    @Override
    public boolean contains(Object o) {
        int index = o.hashCode() % set.length;
        if (set[index] == null) {
            return false;
        }
        E e = (E) o;
        return set[index].contains(e);
    }

    /////////////////////////////////////////////////////////////////////////
    //////              Необязательные к реализации методы            ///////
    /////////////////////////////////////////////////////////////////////////

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
    public boolean containsAll(Collection<?> c) {
        Object[] elems = (E[]) c.toArray();
        for (Object el: elems) {
            if (!contains(el)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        E[] elems = (E[]) c.toArray();
        for (E el: elems) {
            result |= add(el);
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        Node<E>[] buffer = toBuffer();
        for (Node<E> n: buffer) {
            E e = n.getValue();
            if (!c.contains(e)) {
                result |= remove(e);
            }
        }
        System.out.println("THIS: " +  this);
        System.out.println(Arrays.toString(set));
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        E[] elems = (E[]) c.toArray();
        for (E el: elems) {
            result |= remove(el);
        }
        return result;
    }

    private void insertSort(Node<E>[] buffer) {
        for (int i = 1; i < buffer.length; ++i) {
            Node<E> key = buffer[i];
            int j = i - 1;
            for (; j >= 0 && (key == null || (buffer[j] != null && buffer[j].getIndex() > key.getIndex())); --j) {
                buffer[j+1] = buffer[j];
            }
            buffer[j+1] = key;
        }
    }

    private void radixSort(Node<E>[] buffer) {
        if (buffer.length < 2) {
            return;
        }

        int max = buffer[0].getIndex();
        for (int i = 1; i < buffer.length; ++i) {
            int currNodeInd = buffer[i].getIndex();
            if (currNodeInd > max) {
                max = currNodeInd;
            }
        }

        for (int rank = 1; rank <= max; rank *= 10) {
            countSortSubroutine(buffer, rank);
        }
    }

    private void countSortSubroutine(Node<E>[] buffer, int rank) {
        int max = getDigit(buffer[0].getIndex(), rank);
        for (int i = 1; i < buffer.length; ++i) {
            int val = getDigit(buffer[i].getIndex(), rank);
            if (val > max) {
                max = val;
            }
        }
        ++max;

        int[] countBuffer = new int[max];
        for (Node<E> n: buffer) {
            int el = getDigit(n.getIndex(), rank);
            ++countBuffer[el];
        }

        for (int i = 1; i < max; ++i) {
            countBuffer[i] += countBuffer[i-1];
        }

        Node<E>[] temporaryBuffer = (Node<E>[]) new Node[buffer.length];
        for (int j = buffer.length - 1; j >= 0; --j) {
            int i = getDigit(buffer[j].getIndex(), rank);
            int k = countBuffer[i] - 1;
            temporaryBuffer[k] = buffer[j];
            --countBuffer[i];
        }

        for (int i = 0; i < buffer.length; ++i) {
            buffer[i] = temporaryBuffer[i];
        }
    }

    private int getDigit(int num, int rank) {
        return num / rank % 10;
    }

    private Node<E>[] toBuffer() {

        Node<E>[] buffer = (Node<E>[]) new Node[size];

        int sizeCounter = 0;
        int i = 0;
        for (int index = 0; index < set.length; ++index) {
            if (set[index] == null) {
                continue;
            }
            for (Node<E> ptr = set[index].getHead(); ptr != null; ptr = ptr.getNext()) {
                buffer[i++] = ptr;
                ++sizeCounter;
            }
        }

//        if (sizeCounter < size) {
//            Node<E>[] tmp = (Node<E>[]) new Node[sizeCounter];
//            System.arraycopy(buffer, 0, tmp, 0, sizeCounter);
//            size = sizeCounter;
//            return tmp;
//        }
//        System.out.printf("%d, %d\n", size, sizeCounter);

        return buffer;
    }
}
