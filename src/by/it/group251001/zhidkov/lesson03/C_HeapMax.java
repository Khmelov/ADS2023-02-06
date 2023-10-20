package by.it.group251001.zhidkov.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lesson 3. C_Heap.
// Задача: построить max-кучу = пирамиду = бинарное сбалансированное дерево на массиве.
// ВАЖНО! НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ НИКАКИЕ КОЛЛЕКЦИИ, КРОМЕ ARRAYLIST (его можно, но только для массива)

//      Проверка проводится по данным файла
//      Первая строка входа содержит число операций 1 ≤ n ≤ 100000.
//      Каждая из последующих nn строк задают операцию одного из следующих двух типов:

//      Insert x, где 0 ≤ x ≤ 1000000000 — целое число;
//      ExtractMax.

//      Первая операция добавляет число x в очередь с приоритетами,
//      вторая — извлекает максимальное число и выводит его.

//      Sample Input:
//      6
//      Insert 200
//      Insert 10
//      ExtractMax
//      Insert 5
//      Insert 500
//      ExtractMax
//
//      Sample Output:
//      200
//      500


public class C_HeapMax {

    private static class MaxHeap {
        private final List<Long> heap = new ArrayList<>(); // создание списка для хранения элементов кучи

        void siftDown(int i) { // просеивание вверх
            int parentIndex = (i - 1) / 2; // индекс родительского элемента
            while (i > 0 && heap.get(i) > heap.get(parentIndex)) { // пока текущий элемент больше родительского
                long temp = heap.get(parentIndex); // обмен значениями текущего элемента и родительского
                heap.set(parentIndex, heap.get(i));
                heap.set(i, temp);

                i = parentIndex; // переход к родительскому элементу
                parentIndex = (i - 1) / 2;
            }
        }

        void siftUp(int i) { // просеивание вниз
            int leftChild = 2 * i + 1; // индекс левого потомка
            int rightChild = 2 * i + 2; // индекс правого потомка
            int largestChild = i; // индекс наибольшего потомка
            boolean flg = true;
            while (flg) {
                if (leftChild < heap.size() && heap.get(leftChild) > heap.get(largestChild)) // если левый потомок больше наибольшего
                    largestChild = leftChild;
                if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largestChild)) // если правый потомок больше наибольшего
                    largestChild = rightChild;
                if (largestChild == i) // если наибольший потомок совпадает с текущим элементом, останавливаем просеивание
                    flg = false;

                long temp = heap.get(largestChild); // обмен значениями текущего элемента и наибольшего потомка
                heap.set(largestChild, heap.get(i));
                heap.set(i, temp);

                i = largestChild; // переход к наибольшему потомку
                leftChild = 2 * i + 1;
                rightChild = 2 * i + 2;
            }
        }

        void insert(Long value) { // вставка элемента в кучу
            heap.add(value);
            siftDown(heap.size() - 1);

        }
        Long extractMax() { // извлечение и удаление максимального элемента

            long result = heap.get(0); // сохраняем значение максимального элемента
            heap.set(0, heap.remove(heap.size() - 1)); // заменяем корень кучи последним элементом и удаляем его
            siftUp(0); // просеиваем корень вниз
            return result; // возвращаем максимальный элемент
        }
    }
    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        long maxValue=0L;
        MaxHeap heap = new MaxHeap(); // создание экземпляра класса MaxHeap

        Scanner scanner = new Scanner(stream); // создание объекта Scanner для чтения данных из потока
        int count = scanner.nextInt(); // считываем количество операций

        for (int i = 0; i < count; ) {
            String s = scanner.nextLine(); // считываем очередную строку

            if (s.equalsIgnoreCase("extractMax")) { // если строка содержит операцию "extractMax"
                Long res=heap.extractMax(); // извлекаем и удаляем максимальный элемент из кучи
                if (res!=null && res>maxValue) maxValue=res; // обновляем максимальное значение, если оно больше текущего
                System.out.println(); // выводим пустую строку (для целей отладки)
                i++;
            }

            if (s.contains(" ")) { // если строка содержит пробел (или другой разделитель)
                String[] p = s.split(" "); // разделяем строку на части по пробелу
                if (p[0].equalsIgnoreCase("insert")) // если первая часть равна "insert"
                    heap.insert(Long.parseLong(p[1])); // вставляем элемент в кучу
                i++;
                //System.out.println(heap); //debug
            }
        }

        return maxValue; // возвращаем максимальное значение
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX="+instance.findMaxValue(stream)); // выводим максимальное значение
    }

    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта внутри.
}

