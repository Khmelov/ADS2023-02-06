package by.it.group251001.smychek.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Lesson 3. C_Heap.
// Задача: построить max-кучу = пирамиду = бинарное сбалансированное дерево на массиве.
// ВАЖНО! НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ НИКАКИЕ КОЛЛЕКЦИИ, КРОМЕ ARRAYLIST (его можно, но только для массива)


public class C_HeapMax {
    Long findMaxValue(InputStream stream) {
        long maxValue = 0L;
        MaxHeap heap = new MaxHeap();

        Scanner scanner = new Scanner(stream);
        int count = scanner.nextInt();

        scanner.nextLine();
        for (int i = 0; i < count; i++) {
            String s = scanner.nextLine();

            if (s.equals("ExtractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
            }else{
                heap.insert(Long.parseLong(s.split(" ")[1]));
            }
        }

        return maxValue;
    }

    private static class MaxHeap {
        private final List<Long> heap = new ArrayList<>();

        void siftDown(int i) {
            int maxIndex = i;
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;

            if (leftChildIndex < heap.size() && heap.get(leftChildIndex) > heap.get(maxIndex)) {
                maxIndex = leftChildIndex;
            }

            if (rightChildIndex < heap.size() && heap.get(rightChildIndex) > heap.get(maxIndex)) {
                maxIndex = rightChildIndex;
            }

            if (maxIndex != i) {
                Collections.swap(heap, i, maxIndex);
                siftDown(maxIndex);
            }
        }

        void siftUp(int i) {
            while (i > 0 && heap.get((i - 1) / 2) < heap.get(i)) {
                Collections.swap(heap, i, (i - 1) / 2);
                i = (i - 1) / 2;
            }

        }

        void insert(Long value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() {
            if (heap.isEmpty()) {
                return null;
            }

            Long result = heap.get(0);
            Long lastElement = heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {
                heap.set(0, lastElement);
                siftDown(0);
            }

            return result;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251001/smychek/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX="+instance.findMaxValue(stream));
    }
}
