package by.it.group251005.ivanov.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C_HeapMax {

    private class MaxHeap {
        private List<Long> heap = new ArrayList<>();

        void siftDown(int i) {
            int leftChild = 2 * i + 1;
            int rightChild = leftChild + 1;
            int largest = i;

            if (leftChild < heap.size() && heap.get(leftChild) > heap.get(largest))
                largest = leftChild;

            if (rightChild < heap.size() && heap.get(rightChild) > heap.get(largest))
                largest = rightChild;

            if (largest != i) {
                swapElms(largest, i);
                siftDown(largest);
            }
        }

        private void swapElms(int parentElm, int i) {
            Long temp = heap.get(parentElm);

            heap.set(parentElm, heap.get(i));
            heap.set(i, temp);
        }

        void siftUp(int i) {
            if (i < 1) return;

            int parentElem = (i - 1) / 2;

            if (heap.get(parentElem) < heap.get(i))
                swapElms(parentElem, i);
            siftUp(parentElem);
        }

        void insert(Long value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() {
            if (heap.size() == 0)
                return 0L;

            Long result = heap.remove(0);
            siftDown(0);

            return result;
        }
    }

    Long findMaxValue(InputStream stream) {
        Long maxValue = 0L;
        MaxHeap heap = new MaxHeap();
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
                System.out.println();
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson03/heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }
}
