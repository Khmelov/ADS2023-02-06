package by.it.group251002.trubach.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

class MyDLLNode<E> {
    public MyDLLNode next;
    public MyDLLNode prevAdd, nextAdd;
    public E value;

    public MyDLLNode(E value) {
        this.value = value;
        this.next = null;
        this.nextAdd = null;
        this.prevAdd = null;
    }

}

public class MyLinkedHashSet<E> implements Set<E> {

    private int SIZE_OF_LINKED_HASHSET = 0;
    private int CAPACITY_OF_LINKED_HASHSET = 8;
    private MyDLLNode FIRST_ELEMENT = null;
    private MyDLLNode LAST_ELEMENT = null;


    private
    MyDLLNode<E>[] arr = (MyDLLNode<E>[]) new MyDLLNode[CAPACITY_OF_LINKED_HASHSET];

    private int hashIndex(Object o) {
        return o.hashCode() % CAPACITY_OF_LINKED_HASHSET;
    }

    private void resize() {
        CAPACITY_OF_LINKED_HASHSET = CAPACITY_OF_LINKED_HASHSET * 3 / 2 + 1;
        arr = new MyDLLNode[CAPACITY_OF_LINKED_HASHSET];
        SIZE_OF_LINKED_HASHSET = 0;
        MyDLLNode temp = FIRST_ELEMENT;
        while (temp != null) {
            addWithoutList((E) temp.value);
            temp = temp.nextAdd;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String delimiter = "";
        MyDLLNode temp = FIRST_ELEMENT;
        while (temp != null) {
            sb.append(delimiter).append(temp.value.toString());
            delimiter = ", ";
            temp = temp.nextAdd;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return SIZE_OF_LINKED_HASHSET;
    }

    @Override
    public boolean isEmpty() {
        return SIZE_OF_LINKED_HASHSET == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = hashIndex(o);
        MyDLLNode temp = arr[index];
        if (temp != null) {
            while (temp != null) {
                if (Objects.equals(temp.value, o)) {
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

    private MyDLLNode addWithoutList(E e) {
        int index = hashIndex(e);
        MyDLLNode temp = arr[index];
        if (temp != null) {
            while (temp.next != null) {
                if (!Objects.equals(e, temp.value)) {
                    temp = temp.next;
                } else {
                    return null;
                }
            }
            if (!Objects.equals(e, temp.value)) {
                temp.next = new MyDLLNode(e);
                temp = temp.next;
                SIZE_OF_LINKED_HASHSET++;
                return temp;
            } else {
                return null;
            }
        } else {
            arr[index] = new MyDLLNode(e);
            SIZE_OF_LINKED_HASHSET++;
            return arr[index];
        }
    }

    @Override
    public boolean add(E e) {
        if (SIZE_OF_LINKED_HASHSET + 1 >= CAPACITY_OF_LINKED_HASHSET * 3 / 4) {
            resize();
        }
        MyDLLNode temp = addWithoutList(e);
        if (temp == null) {
            return false;
        } else {
            temp.prevAdd = LAST_ELEMENT;
            // as the element was already added, the size's minimum value is 1
            if (SIZE_OF_LINKED_HASHSET != 1) {
                LAST_ELEMENT.nextAdd = temp;
            } else {
                FIRST_ELEMENT = temp;
            }
            LAST_ELEMENT = temp;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = hashIndex(o);
        MyDLLNode temp = arr[index];
        if (temp != null) {
            if (!Objects.equals(temp.value, o)) {
                MyDLLNode previousElem = temp;
                temp = temp.next;
                while (temp != null) {
                    if (!Objects.equals(temp.value, o)) {
                        previousElem = temp;
                        temp = temp.next;
                    } else {
                        previousElem.next = temp.next;
                        if (temp.prevAdd == null) {
                            FIRST_ELEMENT = temp.nextAdd;
                        } else {
                            temp.prevAdd.nextAdd = temp.nextAdd;
                        }
                        if (temp.nextAdd == null) {
                            LAST_ELEMENT = temp.prevAdd;
                        } else {
                            temp.nextAdd.prevAdd = temp.prevAdd;
                        }
                        SIZE_OF_LINKED_HASHSET--;
                        return true;
                    }
                }
                return false;
            } else {
                arr[index] = temp.next;
                if (temp.prevAdd == null) {
                    FIRST_ELEMENT = temp.nextAdd;
                } else {
                    temp.prevAdd.nextAdd = temp.nextAdd;
                }
                if (temp.nextAdd == null) {
                    LAST_ELEMENT = temp.prevAdd;
                } else {
                    temp.nextAdd.prevAdd = temp.prevAdd;
                }
                SIZE_OF_LINKED_HASHSET--;
                return true;
            }
        } else {
            return false;
        }
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
        int prevSize = SIZE_OF_LINKED_HASHSET;
        for (E e : c) {
            add(e);
        }
        return prevSize != SIZE_OF_LINKED_HASHSET;
    }

    private boolean removeRoutine(Collection<?> c, boolean cond) {
        int prevSize = SIZE_OF_LINKED_HASHSET;
        MyDLLNode temp = FIRST_ELEMENT;
        MyDLLNode nextNode;
        while (temp != null) {
            if (cond == c.contains(temp.value)) {
                nextNode = temp.nextAdd;
                remove(temp.value);
                temp = nextNode;
            } else {
                temp = temp.nextAdd;
            }
        }
        return prevSize != SIZE_OF_LINKED_HASHSET;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return removeRoutine(c, false);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return removeRoutine(c, true);
    }

    @Override
    public void clear() {
        for (int i = 0; i < CAPACITY_OF_LINKED_HASHSET; i++) {
            arr[i] = null;
        }
        SIZE_OF_LINKED_HASHSET = 0;
        FIRST_ELEMENT = null;
        LAST_ELEMENT = null;
    }
}
