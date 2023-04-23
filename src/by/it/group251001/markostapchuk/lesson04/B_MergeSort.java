package by.it.group251001.markostapchuk.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        mergeSort(a);

        return a;
    }
    void mergeSort(int[] a) {
        if (a.length <= 1) return;

        int m = (a.length - 1) / 2;
        int[] x = Arrays.copyOfRange(a, 0, m + 1);
        int[] y = Arrays.copyOfRange(a, m + 1, a.length);

        mergeSort(x);
        mergeSort(y);

        int i = 0, j = 0, k = 0;

        while (i < x.length && j < y.length) a[k++] = x[i] < y[j] ? x[i++] : y[j++];

        while (i < x.length) a[k++] = x[i++];
        while (j < y.length) a[k++] = y[j++];
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
