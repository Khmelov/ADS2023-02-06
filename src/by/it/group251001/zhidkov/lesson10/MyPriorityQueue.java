package by.it.group251001.zhidkov.lesson10;

import java.util.*;

public class MyPriorityQueue<E> implements Deque<E> {
    private Object[] Queue;
    private int size;
    public MyPriorityQueue(){
        size = 0;
        Queue = (new Object[10]);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++)
        {
            result.append(Queue[i]);
            if (i < size - 1)
            {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public void addFirst(E e) {
    }

    @Override
    public void addLast(E e) {
    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        if (size >= Queue.length)
        {
            if (size != 0)
            {
                E[] newQueue = (E[]) (new Object[size * 2]);
                for (int i = 0; i < size; i++)
                {
                    newQueue[i] = (E)Queue[i];
                }
                Queue = newQueue;
            }
            else
            {
                E[] newQueue = (E[]) (new Object[10]);
                Queue = newQueue;
            }
        }
        Queue[size] = e;
        int Child = size;
        size++;
        boolean bool = true;
        while (Child != 0 && bool)
        {
            int Parent = (Child - 1) / 2;
            if ((int)Queue[Parent] >= (int)Queue[Child])
            {
                E temp = (E) Queue[Child];
                Queue[Child] = Queue[Parent];
                Queue[Parent] = temp;
                Child = Parent;
            }
            else
            {
                bool = false;
            }
        }
        return true;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        if (size == 0)
        {
            E[] newQueue = (E[]) (new Object[10]);
            Queue = newQueue;
            return null;
        }
        int Parent = 0;
        E remEl = (E)Queue[0];
        size--;
        Queue[0] = Queue[size];
        Queue[size] = null;
        boolean bool = true;
        while (((Parent * 2) + 1 < size) && bool)
        {
            int ChildRight = (Parent * 2) + 2;
            int ChildLeft = (Parent * 2) + 1;
            if ((Parent * 2) + 2 >= size)
            {
                ChildRight = ChildLeft;
            }

            if (((int)Queue[Parent] >= (int)Queue[ChildLeft]) || ((int)Queue[Parent] >= (int)Queue[ChildRight]))
            {
                if ((int)Queue[ChildRight] < (int)Queue[ChildLeft])
                {
                    E temp = (E) Queue[ChildRight];
                    Queue[ChildRight] = Queue[Parent];
                    Queue[Parent] = temp;
                    Parent = ChildRight;
                }
                else
                {
                    E temp = (E) Queue[ChildLeft];
                    Queue[ChildLeft] = Queue[Parent];
                    Queue[Parent] = temp;
                    Parent = ChildLeft;
                }
            }
            else
            {
                bool = false;
            }
        }
        return remEl;
    }

    @Override
    public E poll() {
        return remove();
    }

    @Override
    public E element() {
        return peek();
    }

    @Override
    public E peek()
    {
        if (size > 0)
        {
            return (E) Queue[0];
        }
        else
        {
            return null;
        }

    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean b = false;
        for (E element : c)
        {
            if (add(element))
            {
                b = true;
            }
        }
        return b;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (size == 0)
        {
            E[] newQueue = (E[]) (new Object[10]);
            Queue = newQueue;
            return false;
        }
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (!c.contains(Queue[i])) {
                Queue[j] = Queue[i];
                j++;
            }
        }
        boolean b = false;
        if (j < size) {
            for (int i = j; i < size; i++) {
                Queue[i] = null; // Очищаем оставшиеся элементы
                b = true;
            }
            size = j;
        }
        for (int i = size / 2 - 1; i >= 0; i--) {
            int LChild = (2 * i) + 1;
            int RChild = (2 * i) + 2;
            int Parent = i;
            while ((Parent * 2 + 1) < size)
            {
                if (RChild >= size)
                {
                    RChild = LChild;
                }
                if ((int)Queue[RChild] < (int)Queue[LChild])
                {
                    if ((int)Queue[Parent] >= (int)Queue[RChild])
                    {
                        E temp = (E)Queue[Parent];
                        Queue[Parent] = Queue[RChild];
                        Queue[RChild] = temp;
                        Parent = RChild;
                        RChild = (Parent * 2 + 2);
                        LChild = (Parent * 2 + 1);
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    if ((int)Queue[Parent] >= (int)Queue[LChild])
                    {
                        E temp = (E) Queue[Parent];
                        Queue[Parent] = Queue[LChild];
                        Queue[LChild] = temp;
                        Parent = LChild;
                        RChild = (Parent * 2 + 2);
                        LChild = (Parent * 2 + 1);
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
        return b;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (size == 0)
        {
            E[] newQueue = (E[]) (new Object[10]);
            Queue = newQueue;
            return false;
        }
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(Queue[i])) {
                Queue[j] = Queue[i];
                j++;
            }
        }
        boolean b = false;
        if (j < size) {
            for (int i = j; i < size; i++) {
                Queue[i] = null; // Очищаем оставшиеся элементы
                b = true;
            }
            size = j;
        }
        for (int i = size / 2 - 1; i >= 0; i--) {
            int LChild = (2 * i) + 1;
            int RChild = (2 * i) + 2;
            int Parent = i;
            while ((Parent * 2 + 1) < size)
            {
                if (RChild >= size)
                {
                    RChild = LChild;
                }
                if ((int)Queue[RChild] < (int)Queue[LChild])
                {
                    if ((int)Queue[Parent] >= (int)Queue[RChild])
                    {
                        E temp = (E)Queue[Parent];
                        Queue[Parent] = Queue[RChild];
                        Queue[RChild] = temp;
                        Parent = RChild;
                        RChild = (Parent * 2 + 2);
                        LChild = (Parent * 2 + 1);
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    if ((int)Queue[Parent] >= (int)Queue[LChild])
                    {
                        E temp = (E) Queue[Parent];
                        Queue[Parent] = Queue[LChild];
                        Queue[LChild] = temp;
                        Parent = LChild;
                        RChild = (Parent * 2 + 2);
                        LChild = (Parent * 2 + 1);
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
        return b;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
        {
            Queue[i] = null;
        }
        size = 0;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0)
        {
            E[] newQueue = (E[]) (new Object[10]);
            Queue = newQueue;
            return false;
        }
        int i = 0;
        boolean b = false;
        while (!b && i < size)
        {
            if (contains(o))
            {
                b = true;
            }
            else
            {
                i++;
            }
        }
        if (!b)
        {
            return false;
        }
        E remEl = (E)Queue[i];
        Queue[i] = Queue[size - 1];
        size--;
        Queue[size] = null;
        int Parent = i;
        int Child;
        while ((Parent + 1) * 2 < size)
        {
            Child = (Parent + 1) * 2;
            E temp = (E)Queue[Child];
            Queue[Child] = Queue[Parent];
            Queue[Parent] = temp;
            Parent = Child;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        boolean bool = false;
        int i = 0;
        while (!bool && i < size)
        {
            if (o.equals(Queue[i]))
            {
                bool = true;
            }
            i++;
        }
        return bool;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
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
    public Iterator<E> descendingIterator() {
        return null;
    }
}
