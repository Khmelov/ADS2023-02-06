package by.it.group251002.trubach.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

class MyNode<E> {
    public E value;
    public MyNode next;

    public MyNode(E value) {
        this.value = value;
        this.next = null;
    }
}

public class MyHashSet<E> implements Set<E> {

    private int SIZE_OF_HASHSET = 0;
    private int HASHSET_CAPACITY = 8;
    private MyNode<E>[] arr = (MyNode<E>[]) new MyNode[HASHSET_CAPACITY];

    private int hashIndex(Object e) {
        return e.hashCode() % HASHSET_CAPACITY;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String delimiter = "";
        MyNode temp;
        for (int i = 0; i < HASHSET_CAPACITY; i++) {
            temp = arr[i];
            while (temp != null) {
                sb.append(delimiter).append(temp.value.toString());
                delimiter = ", ";
                temp = temp.next;
            }
        }

        sb.append("]");
        return sb.toString();
    }

    private void resize() {
        int oldCap = HASHSET_CAPACITY;
        int newCapacity = HASHSET_CAPACITY * 3 / 2 + 1;
        MyNode[] oldArr = arr;
        MyNode[] newArr = new MyNode[newCapacity];
        HASHSET_CAPACITY = newCapacity;
        arr = newArr;
        SIZE_OF_HASHSET = 0;
        for (int i = 0; i < newCapacity; i++) {
            newArr[i] = null;
        }

        MyNode<E> temp;
        for (int i = 0; i < oldCap; i++) {
            temp = oldArr[i];
            while (temp != null) {
                add(temp.value);
                temp = temp.next;
            }
        }
    }

    @Override
    public int size() {
        return SIZE_OF_HASHSET;
    }

    @Override
    public boolean isEmpty() {
        return SIZE_OF_HASHSET == 0;
    }

    @Override
    public boolean contains(Object o) {
        int hash = hashIndex(o);
        MyNode temp = arr[hash];
        if (temp != null) {
            while (temp != null) {
                if (Objects.equals(o, temp.value)) {
                    return true;
                } else {
                    temp = temp.next;
                }
            }
            return false;
        } else {
            return false;
        }

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
        if ((SIZE_OF_HASHSET + 1) >= HASHSET_CAPACITY * 3 / 4) {
            resize();
        }
        int hash = hashIndex(e);
        MyNode temp = arr[hash];
        if (temp != null) {
            while (temp.next != null) {
                if (!Objects.equals(e, temp.value)) {
                    temp = temp.next;
                } else {
                    return false;
                }
            }
            if (!Objects.equals(e, temp.value)) {
                temp.next = new MyNode(e);
                SIZE_OF_HASHSET++;
                return true;
            } else {
                return false;
            }
        } else {
            arr[hash] = new MyNode(e);
            SIZE_OF_HASHSET++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        int hash = hashIndex(o);
        MyNode temp = arr[hash];
        if (temp != null) {
            // if the first one is the man, decapitate him
            if (Objects.equals(o, temp.value)) {
                arr[hash] = temp.next;
                SIZE_OF_HASHSET--;
                return true;
            } else {
                MyNode prev = arr[hash];
                temp = temp.next;
                while (temp != null) {
                    if (Objects.equals(o, temp.value)) {
                        prev.next = temp.next;
                        SIZE_OF_HASHSET--;
                        return true;
                    } else {
                        prev = temp;
                        temp = temp.next;
                    }
                }
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < HASHSET_CAPACITY; i++) {
            arr[i] = null;
        }
        SIZE_OF_HASHSET = 0;
    }
}
