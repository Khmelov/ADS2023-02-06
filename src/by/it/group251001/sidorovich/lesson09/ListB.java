package by.it.group251001.sidorovich.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {
    private Object mas[]=new Object[1];
    int pos=0;

    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < pos - 1; i++) {
            sb.append(mas[i] + ", ");
        }
        if (pos!=0){
            sb.append(mas[pos-1]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        mas= Arrays.copyOf(mas,pos+1);
        mas[pos++]=e;
        //  pos++;
        return true;
    }//*/
    @Override
    public E remove(int index) {
        if ((index>=pos)|(index<0))
        {
            return null;
        }
        else
        {
            E result=(E) mas[index];
            for (int i=index;i<pos-1;i++){
                mas[i]=mas[i+1];
            }
            pos-=1;
            mas=Arrays.copyOf(mas,pos);
            return result;
        }
    }//*/

    @Override
    public int size() {
        return pos;
    }




    @Override
    public void add(int index, E element) {
mas=Arrays.copyOf(mas,++pos);
for (int i=pos-1; i>index;i--){
    mas[i]=mas[i-1];
}
mas[index]=element;
    }

    @Override
    public boolean remove(Object o) {
        int st=pos;
        for (int i = 0;i<pos;i++)
        {
            if (mas[i].equals(o)) {
                remove(i);
            }
        }
        if (st!=pos){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public E set(int index, E element) {

       E result = (E)mas[index];
       mas[index]=element;
        return result;


    }


    @Override
    public boolean isEmpty() {
        if (pos==0) return true; else return false;
    }
    @Override
    public void clear() {
mas=Arrays.copyOf(mas,0);
pos=0;
    }

    @Override
    public int indexOf(Object o)
    {
       // System.out.println(pos);
      int result=-1;
      for (int i=0; i<pos; i++)
        {
            if (mas[i].equals(o))
            {
               // System.out.println(i+" "+mas[i]+" "+o+" "+result);
                result=i;
            }
        }
        return result;
    }

    @Override
    public E get(int index) {
        return (E)mas[index];
    }

    @Override
    public boolean contains(Object o) {
        int p=0;
       for (int i = 0; i<pos; i++)
        {
            if (mas[i].equals(o)) p=1;
        }
       if (p==0) return false; else return true;
    }

    @Override
    public int lastIndexOf(Object o) {
        int result=-1;  for (int i=0; i<pos; i++){
            if ((mas[i].equals(o))) result=i;
        }
        return result;
    }


    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }


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
