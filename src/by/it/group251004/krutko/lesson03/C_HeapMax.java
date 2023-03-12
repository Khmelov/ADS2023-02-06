package by.it.group251004.krutko.lesson03;

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
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение.
        //Будет мало? Ну тогда можете его собрать как Generic и/или использовать в варианте B
        private List<Long> heap = new ArrayList<>();

        int siftDown(int i) { //просеивание вниз

            return i;
        }

        int siftUp(int i, Long[] tempArr) { //просеивание вверх
            if (tempArr[i] > tempArr[(i - 1) / 2]) {
                swap(i, (i - 1) / 2, tempArr);
                i = (i - 1) / 2;
                siftUp(i, tempArr);
            }
            return i; //мб и void?
        }

        private void swap(int firstpos, int secondpos, Long[] tempArr)
        {
            Long tmp = tempArr[firstpos];
            tempArr[firstpos] = tempArr[secondpos];
            tempArr[secondpos] = tmp;
        }

        void insert(Long value) { //вставка
            heap.add(value);
            Long[] tempArr = new Long[heap.size()];
            for (int i = 0; i < heap.size(); i++)
                tempArr[i] = heap.get(i);

            heap.clear();

            int current = tempArr.length - 1;
            siftUp(current, tempArr);
            for (int i = 0; i < tempArr.length; i++)
                heap.add(tempArr[i]);
            /*while (tempArr[current] > tempArr[(current - 1) / 2]) {
                swap(current, (current - 1) / 2, tempArr);
                current = (current - 1) / 2;
            }*/

            //Можно убрать лист-глист и просто
            /*Heap[size] = element;

            int current = size;
            while (Heap[current] > Heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
            }
            size++;*/
            // Однако для этого надо сразу знать максимальное значение элементов + хранить в конструкторе size данного массива, показывающий кол-во заполненных элементов
        }

        Long extractMax() { //извлечение и удаление максимума
            Long result = null;
            Long[] tempArr = new Long[heap.size()];
            for (int i = 0; i < heap.size(); i++)
                tempArr[i] = heap.get(i);

            result = tempArr[0];
            tempArr[0] = tempArr[tempArr.length - 1];

            heap.clear();

            for (int i = 0; i < tempArr.length - 1; i++) {
                heap.add(tempArr[i]);
            }

            return result;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    }

    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue=0L;
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        MaxHeap heap = new MaxHeap();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res=heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
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
