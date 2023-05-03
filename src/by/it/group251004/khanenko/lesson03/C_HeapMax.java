package by.it.group251004.khanenko.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C_HeapMax {

    private class MaxHeap {
        private List<Long> heap = new ArrayList<>();
        //
        int siftDown(int i) {
            while (heap.get(i) > heap.get((i - 1) / 2)) {
                swap(i, (i - 1) / 2);
            }
            return i;
        }

        void swap(int index1, int index2) {
            Long buf = heap.get(index1);
            heap.set(index1, heap.get(index2));
            heap.set(index2, buf);
        }
        int siftUp(int i) {
            while(heap.get(i) > heap.get((i - 1) / 2)) {
                swap(i, (i - 1) / 2);
                siftDown((i - 1) / 2);
            }

            
            return i;
        }
        void insert(Long value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() {
            Long result = heap.get(0);
            heap.remove(0);
            return result;
        }
    }
    Long findMaxValue(InputStream stream) {
        Long maxValue=0L;
        MaxHeap heap = new MaxHeap();
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
}
