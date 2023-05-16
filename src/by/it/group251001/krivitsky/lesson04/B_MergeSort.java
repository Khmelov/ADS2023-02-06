package by.it.group251001.krivitsky.lesson04;

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

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }
        mergeSort(a);
        return a;
    }


    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // массив уже отсортирован или пустой
        }

        int[] tmp = new int[arr.length];
        mergeSortHelper(arr, tmp, 0, arr.length - 1);
    }

        private static void mergeSortHelper(int[] a, int[] tmp, int left, int right) {
            if (left >= right) {
                return;
            }

            int mid = (left + right) / 2;
            mergeSortHelper(a, tmp, left, mid); // сортируем левую половину
            mergeSortHelper(a, tmp, mid + 1, right); // сортируем правую половину

            // слияние двух отсортированных половин
            int i = left;
            int j = mid + 1;
            int k = 0;
            while (i <= mid && j <= right) {
                if (a[i] <= a[j]) {
                    tmp[k++] = a[i++];
                } else {
                    tmp[k++] = a[j++];
                }
            }
            while (i <= mid) {
                tmp[k++] = a[i++];
            }
            while (j <= right) {
                tmp[k++] = a[j++];
            }

            // копирование временного массива обратно в оригинальный
            k = 0;
            for (i = left; i <= right; i++) {
                a[i] = tmp[k++];
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

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
