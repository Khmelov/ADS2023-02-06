package by.it.group251001.krivitsky.lesson11;

public class MyList<E> {
    private int size = 0;
    private E[] elements = (E[]) new Object[10];

    public void add(E e){
        if (size == elements.length){
            E[] newarr = (E[]) new Object[size*3/2+1];
            System.arraycopy(elements, 0, newarr, 0, size);
            elements = newarr;
        }
        elements[size++] = e;
    }

    public int size(){
        return size;
    }

    public void clear(){
        size = 0;
        elements = (E[]) new Object[10];
    }

    public E remove(int index){
        E temp = elements[index];
        System.arraycopy(elements, index+1 , elements, index, size-index-1);
        size--;
        return temp;
    }

    public boolean remove(Object o){
        for (int i = 0; i < size; i++){
            if (elements[i].equals(o)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    public E get(int index){
        return elements[index];
    }

    public int indexOf(Object o){
        int result = -1;
        for (int i = 0; i < size; i++){
            if (elements[i].equals(o)){
                result = i;
                break;
            }
        }
        return result;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(E o){
        for (int i = 0; i < size; i++){
            if (elements[i].equals(o)){
                return true;
            }
        }
        return false;
    }

}
