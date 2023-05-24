package by.it.group251003.Trukhan.lesson01.lesson04;

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
    public static void MergeSort (int[] a, int n) {
        if (n == 1) return;

        int mid = n / 2;
        int[] leftArr = new int[mid];
        int[] rightArr = new int[n-mid];

        System.arraycopy(a, 0, leftArr, 0, mid);
        System.arraycopy(a, mid, rightArr, 0, n-mid);

        MergeSort(leftArr, mid);
        MergeSort(rightArr, n-mid);
        Merge(a, leftArr, rightArr, mid, n-mid);
    }

    public static void Merge(int[] a, int[] leftArr, int[] rightArr, int leftLen, int rightLen){
        int i = 0, j = 0, k = 0;
        while (i < leftLen && j < rightLen){
            if(leftArr[i] < rightArr[j]) a[k++] = leftArr[i++];
            else a[k++] = rightArr[j++];
        }
        while (i < leftLen) a[k++] = leftArr[i++];
        while (j < rightLen) a[k++] = rightArr[j++];
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
            System.out.println(a[i]);
        }

        MergeSort(a, n);

        return a;
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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
