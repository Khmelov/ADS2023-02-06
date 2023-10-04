package by.it.group251001.hatalskiy.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
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
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        for (int i = 1;i <= n;i *= 2){
            for (int j = 0; j <= n - i; j += 2 * i)
                merge(a,j,j + i,Math.min(j + 2 * i,n));
        }
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    void merge(int[] a,int left,int mid, int right){
        int i1 = 0, i2 = 0;
        int [] result = new int[right - left + 1];
        while (left + i1 < mid && mid + i2 < right){
            if (a[left + i1] < a[mid + i2]){
                result[i1 + i2] = a[left + i1];
                i1++;
            }
            else{
                result[i1 + i2] = a[mid + i2];
                i2++;
            }
        }
        while (left + i1 < mid){
            result[i1 + i2] = a[left + i1];
            i1++;
        }
        while (mid + i2 < right){
            result[i1 + i2] = a[mid + i2];
            i2++;
        }
        for (int i = 0; i < i1 + i2; i++){
            a[left + i] = result[i];
        }

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