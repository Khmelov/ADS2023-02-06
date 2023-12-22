package by.it.group251005.ivanov.lesson10;

import java.util.*;

public class MyPriorityQueue <E extends Comparable<E>> implements Queue<E> {

    private int size=0;
    private int capacity=30;
    private E[] heap=(E[]) new Comparable[capacity];


    @Override
    public String toString() {      //возращает стринг [e1, e2, e3, ... , en]
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    void siftDown(int index) { //просеивание вверх
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = left;
        if (right < size && heap[right].compareTo(heap[largest]) < 0)
            largest = right;
        if (largest < size && heap[largest].compareTo(heap[index]) < 0) {
            E temp = heap[index];
            heap[index] = heap[largest];
            heap[largest] = temp;
            siftDown(largest);
        }
    }


    void siftUp(int index) { //просеивание вниз
        int parent = (index - 1) / 2;
        if (parent >= 0 && heap[index].compareTo(heap[parent]) < 0) {
            E temp = heap[index];
            heap[index] = heap[parent];
            heap[parent] = temp;
            siftUp(parent);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;     //возвращает тру если он пуст
    }

    @Override
    public boolean contains(Object o) {     //Returns true if this deque contains the specified element.
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(o)) {
                return true;
            }
        }
        return false;
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
    public boolean add(E e) {       //Добавляет элемент в конец очереди
        if (size == capacity) {
            capacity *= 2;
            E[] newItems = (E[]) new Comparable[capacity];
            System.arraycopy(heap, 0, newItems, 0, size);
            heap = newItems;
        }
        heap[size++] = e;
        siftUp(size - 1);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {   //The containsAll() method of Java Collection Interface returns a Boolean value
        for (Object item : c) {                     //'true', if this collection contains all the elements in the invoked collection.
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
                                                    //The addAll() method of Java Collection Interface appends or inserts all the
                                                    //elements of the specified collection to this collection.
        boolean f = false;
        for (E item : c) {
            if (add(item)) {
                f = true;
            }
        }
        return f;
    }

    @Override
    public boolean removeAll(Collection<?> c) {     //The removeAll() method of Java Collection Interface only removes those
                                                    //elements of the Collection that are contained in the specified collection.
        boolean f = false;
        for (Object item : c) {
            if (remove(item)) {
                f = true;
            }
        }
        return f;
    }

    @Override
    public boolean retainAll(Collection<?> c) {     //Метод retainAll() интерфейса Java Collection сохраняет в этой очереди
        boolean f= false;                           //только те элементы, которые присутствуют в указанной коллекции.
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(heap[i])) {
                remove(heap[i]);
                f = true;
            }
        }
        return f;
    }

    @Override
    public boolean remove(Object o) {       //Удаляет первое вхождение указанного элемента из этого списка.
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, heap[i])) {
                heap[i] = heap[--size];
                siftDown(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean offer(E e) {     //Inserts the specified element into the queue represented
        return add(e);              //by this deque (in other words, at the tail of this deque)
    }

    @Override
    public E remove() {             //Retrieves and removes the head of the queue represented by this deque (in other words, the first element of this deque).
        if (isEmpty()) {
            return null;
        }
        E root = heap[0];
        heap[0] = heap[--size];
        siftDown(0);
        return root;
    }

    @Override
    public E poll() {           //Извлекает и удаляет начало очереди, представленное этим списком (другими
        if (isEmpty()) {        //словами, первый элемент этого списка), или возвращает null, если этот список пуст.
            return null;
        }
        return remove();
    }

    @Override
    public E element() {       // Извлекает, но не удаляет начало очереди, представленное этим списком (другими словами, первый элемент
        if (isEmpty()) {       // этого списка). Этот метод отличается от peek только тем, что он генерирует исключение, если этот deque пуст.
            return null;
        }
        return heap[0];
    }

    @Override
    public E peek() {       //Retrieves, but does not remove, the head of the queue represented by this deque
                            //(in other words, the first element of this deque), or returns null if this deque is empty.
        if (isEmpty())
            return null;
        return element();
    }

    @Override
    public void clear() {
        size = 0;
    }
}
