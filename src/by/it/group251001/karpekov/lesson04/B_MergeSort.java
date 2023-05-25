package by.it.group251001.karpekov.lesson04;

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
@SuppressWarnings("ALL")
public class B_MergeSort {

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
            System.out.print(a[i]+" ");
        }
        int[] res = a.clone();
        myMergeSort(a, res,0, a.length - 1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return res;
    }

    public void myMergeSort(int[] a, int[] res, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;

            myMergeSort(a, res, low, mid);
            myMergeSort(a, res, mid + 1, high);

            int i = low, pos = i, j = mid + 1;

            for (int k = low; k <= high; k++)
                a[k] = res[k];

            while (i <= mid && j <= high) {
                if (a[i] <= a[j])
                    res[pos++] = a[i++];
                else
                    res[pos++] = a[j++];
            }

            while (i <= mid)
                res[pos++] = a[i++];

            while (j <= high)
                res[pos++] = a[j++];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251001/dadush/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.println();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
