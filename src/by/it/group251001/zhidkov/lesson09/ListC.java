package by.it.group251001.zhidkov.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {

    private Object[] ListElements; // Массив для хранения элементов списка
    private int Lsize;          // Текущий размер списка

    // Конструктор для создания пустого списка
    public ListC() {
        ListElements = new Object[10]; // Начальный размер списка
        Lsize = 0;
    }
    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < Lsize; i++)
        {
            result.append(ListElements[i]);
            if (i < Lsize - 1)
                result.append(", ");
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public boolean add(E e) {
        if (Lsize == ListElements.length)
        {
            int newCapacity = Lsize * 2;
            //Умножаю на 2, чтобы уменьшить частоту необходимости увеличения массива
            Object[] newListEl = new Object[newCapacity];
            for (int i = 0; i < Lsize; i++)
            {
                newListEl[i] = ListElements[i];
            }
            ListElements = newListEl;
        }
        ListElements[Lsize] = e;
        Lsize++;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= Lsize)
        {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        E removedElement = (E) ListElements[index];

        for (int i = index; i < Lsize - 1; i++)
        {
            ListElements[i] = ListElements[i + 1];
        }
        Lsize--;
        return removedElement;
    }

    @Override
    public int size() {
        return Lsize;
    }


    public void add(int index, E element) {
        int newCapacity = Lsize + 1;
        Object[] newListEl = new Object[newCapacity];

        // Копируем элементы до индекса index
        for (int i = 0; i < index; i++) {
            newListEl[i] = ListElements[i];
        }

// Вставляем новый элемент в позицию index
        newListEl[index] = element;

// Копируем остальные элементы после индекса index
        for (int i = index + 1; i < Lsize + 1; i++) {
            newListEl[i] = ListElements[i - 1];
        }

        ListElements = newListEl;
        Lsize++;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > -1)
        {
            remove(index);
        }
        return (index > -1);
    }
    public E set(int index, E element) {
        if (index < 0 || index >= Lsize)
        {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        E prevEl = (E)ListElements[index];
        ListElements[index] = element;
        return prevEl;
    }

    public boolean isEmpty() {
        return (Lsize == 0);
    }

    public void clear() {
        for (int i = 0; i < Lsize; i++) {
            ListElements[i] = null;
        }
        Lsize = 0;
    }

    public int indexOf(Object o) {
        if (o == null)
        {
            for (int i = 0; i < Lsize; i++)
            {
                if (ListElements[i].equals(null))
                {
                    return  i;
                }
            }
        }
        else
        {
            for (int i = 0; i < Lsize; i++)
            {
                if (o.equals(ListElements[i]))
                {
                    return  i;
                }
            }
        }

        return -1;
    }

    public E get(int index) {
        return (E)ListElements[index];
    }

    public boolean contains(Object o) {
        return (indexOf(o) > -1);
    }

    public int lastIndexOf(Object o) {
        if (o == null)
        {
            for (int i = Lsize - 1; i >= 0; i--)
            {
                if (ListElements[i].equals(null))
                {
                    return i;
                }
            }
        }
        else
        {
            for (int i = Lsize - 1; i >= 0; i--)
            {
                if (o.equals(ListElements[i]))
                {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        //Из-за того что 'c' может быть любым типом использую Object
        for (Object El : c);
        {
            if (!contains(c))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        Object[] newListEl = new Object[Lsize + c.size()];
        for (int i = 0; i < Lsize; i++)
        {
            newListEl[i] = ListElements[i];
        }
        for (E Element : c)
        {
            newListEl[Lsize] = Element;
            Lsize++;
        }
        ListElements = newListEl;
        return !c.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > Lsize) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Object[] newListEl = new Object[Lsize + c.size()];
        int i;
        for (i = 0; i < index; i++)
        {
            newListEl[i] = ListElements[i];
        }

        int j = index;

        for (E Element : c)
        {
            newListEl[j] = Element;
            j++;
        }

        for (j = i; j < Lsize; j++)
        {
            newListEl[j + c.size()] = ListElements[j];
        }
        Lsize += c.size();
        ListElements = newListEl;
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean bool = false;
        int i;
        for (i = 0; i < Lsize; i++) {
            if (c.contains(ListElements[i])) {
                remove(i);
                bool = true;
                i--; // Уменьшаем индекс, так как после удаления элемента все элементы смещаются на одну позицию влево
            }
        }
        Lsize = i;
        return bool;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        boolean bool = false;
        int i;
        for (i = 0; i < Lsize; i++) {
            if (!c.contains(ListElements[i])) {
                remove(i);
                bool = true;
                i--; // Уменьшаем индекс, так как после удаления элемента все элементы смещаются на одну позицию влево
            }
        }
        Lsize = i;
        return bool;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
