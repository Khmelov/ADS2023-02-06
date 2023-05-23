package by.it.group251001.kulchinskiy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_QSortOptimized {

    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            if (this.start < o.start)
                return -1;
            else if (this.start > o.start)
                return 1;
            else
                return 0;
        }
    }

    private int binarySearch(Segment[] segments, int point) {
        int left = 0;
        int right = segments.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (point < segments[mid].start)
                right = mid - 1;
            else if (point > segments[mid].stop)
                left = mid + 1;
            else
                return mid;
        }

        return left;
    }

    private void quickSort(Segment[] segments, int low, int high) {
        if (high <= low)
            return;

        int lt = low;
        int i = low + 1;
        int gt = high;
        Segment pivot = segments[low];

        while (i <= gt) {
            int cmp = segments[i].compareTo(pivot);
            if (cmp < 0)
                swap(segments, lt++, i++);
            else if (cmp > 0)
                swap(segments, i, gt--);
            else
                i++;
        }

        quickSort(segments, low, lt - 1);
        quickSort(segments, gt + 1, high);
    }

    private void swap(Segment[] segments, int i, int j) {
        Segment temp = segments[i];
        segments[i] = segments[j];
        segments[j] = temp;
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        quickSort(segments, 0, n - 1);

        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
            int index = binarySearch(segments, points[i]);
            result[i] = (index >= 0 && index < segments.length && points[i] <= segments[index].stop) ? 1 : 0;
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
