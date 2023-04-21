package by.it.group251001.brutskaya.lesson04;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class Inversions {

    public static long linearInversions(int[] a) {
        return mergeSort(a, 0, a.length);
    }

    public static long bruteForceInversions(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j])
                    count++;
            }
        }
        return count;
    }

    public static long parallelInversions(int[] a) {
        return new RecursiveSortTask(a, 0, a.length).invoke();
    }

    private static long mergeSort(int[] arr, int left, int right) {
        if (right - left <= 1) return 0;
        int mid = (left + right) >>> 1;
        long inverses = 0;
        inverses += mergeSort(arr, left, mid);
        inverses += mergeSort(arr, mid, right);
        int[] tmp = Arrays.copyOfRange(arr, left, mid);
        int i = left;
        int j = 0;
        int k = mid;
        while (j < tmp.length && k < right) {
            if (tmp[j] <= arr[k]) {
                arr[i++] = tmp[j++];
            } else {
                arr[i++] = arr[k++];
                inverses += tmp.length - j;
            }
        }
        while (j < tmp.length) {
            arr[i++] = tmp[j++];
        }
        while (k < right) {
            arr[i++] = arr[k++];
        }
        return inverses;
    }

    static class RecursiveSortTask extends RecursiveTask<Long> {
        private final int[] a;
        private final int left;
        private final int right;

        public RecursiveSortTask(int[] a, int left, int right) {
            this.a = a;
            this.left = left;
            this.right = right;
        }

        private long merge(int[] arr, int left, int mid, int right) {
            int[] tmp = Arrays.copyOfRange(arr, left, mid);
            int i = left;
            int j = 0;
            int k = mid;
            long inverses = 0;
            while (j < tmp.length && k < right) {
                if (tmp[j] <= arr[k]) {
                    arr[i++] = tmp[j++];
                } else {
                    arr[i++] = arr[k++];
                    inverses += tmp.length - j;
                }
            }
            while (j < tmp.length) {
                arr[i++] = tmp[j++];
            }
            while (k < right) {
                arr[i++] = arr[k++];
            }
            return inverses;
        }

        @Override
        protected Long compute() {
            if ((right - left) <= 1) return 0L;
            int mid = (left + right) >>> 1;
            long inverses = 0;
            RecursiveSortTask left = new RecursiveSortTask(a, this.left, mid);
            left.fork();
            RecursiveSortTask right = new RecursiveSortTask(a, mid, this.right);
            right.fork();
            inverses += left.join();
            inverses += right.join();
            inverses += merge(a, this.left, mid, this.right);
            return inverses;
        }
    }
}
