package by.it.group251004.a_nediakin.lesson03;

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

    private class MaxHeap {
        private List<Long> heap = new ArrayList<>();

        int siftDown(int i) {
            int parentIndex = (i - 1) / 2;
            while (i > 0 && heap.get(i) > heap.get(parentIndex)) {
                swap(heap, parentIndex, i);
                i = parentIndex;
                parentIndex = (i - 1) / 2;
            }

            return i;
        }

        int siftUp(int i) {

            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;
            int largestChild = i;

            while (true) {

                if (leftChild < heap.size() && heap.get(leftChild) > heap.get(largestChild)) {
                    largestChild = leftChild;
                }

                if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largestChild)) {
                    largestChild = rightChild;
                }

                if (largestChild == i) {
                    break;
                }

                swap(heap, largestChild, i);

                i = largestChild;
                leftChild = 2 * i + 1;
                rightChild = 2 * i + 2;
            }

            return i;
        }

        void insert(Long value) {
            heap.add(value);
            siftDown(heap.size() - 1);
        }

        Long extractMax() {
            long result = heap.get(0);
            heap.set(0, heap.remove(heap.size() - 1));
            siftUp(0);

            return result;
        }

        private static void swap(List<Long> list, int index1, int index2) {
            long temp = list.get(index1);
            list.set(index1, list.get(index2));
            list.set(index2, temp);
        }
    }

    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue=0L;
        MaxHeap heap = new MaxHeap();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res=heap.extractMax();
                if (res!=null && res>maxValue) maxValue=res;
                System.out.println();
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
                //System.out.println(heap); //debug
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX="+instance.findMaxValue(stream));
    }

    // РЕМАРКА. Это задание исключительно учебное.
    // Свои собственные кучи нужны довольно редко.
    // "В реальном бою" все существенно иначе. Изучите и используйте коллекции
    // TreeSet, TreeMap, PriorityQueue и т.д. с нужным CompareTo() для объекта внутри.
}