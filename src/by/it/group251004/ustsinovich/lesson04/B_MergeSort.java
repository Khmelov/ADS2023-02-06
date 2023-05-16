package by.it.group251004.ustsinovich.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    void merge(int[] a, int[] left, int[] right, int left_n, int right_n) {
        int i = 0; int k = 0;
        int j = 0;
        while (i < left_n && j < right_n) {
            if (left[i] <= right[j]){
                a[k++] = left[i++];
            } else {
                a[k++] = right[j++];
            }
        }
        while (i < left_n) {
            a[k++] = left[i++];
        }
        while (j < right_n) {
            a[k++] = right[j++];
        }
    }

    void mergeSort(int[] a, int size) {
        if (size < 2) {
            return;
        }
        int mid = size/2;
        int[] left = new int[mid];
        int[] right = new int[size - mid];
        System.arraycopy(a, 0, left, 0, mid);
        if (size - mid >= 0) System.arraycopy(a, mid, right, mid - mid, size - mid);
        mergeSort(left, mid);
        mergeSort(right, size - mid);

        merge(a, left, right, mid, size - mid);
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.print(a[i] + " ");
        }
        System.out.println();
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        mergeSort(a, n);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group151004/prokopchuk/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}