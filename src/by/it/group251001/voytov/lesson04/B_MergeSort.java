package by.it.group251001.voytov.lesson04;

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
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        mergeSort(a);
        return a;
    }

    public static void mergeSort(int[] array) {

        if (array.length == 1) {
            return;
        }

        int[] leftPart = new int[array.length / 2];
        int[] rightPart = new int[array.length - leftPart .length];

        for (int i = 0; i < leftPart.length; i++) {
            leftPart[i] = array[i];
        }
        for (int i = leftPart.length; i < array.length; i++) {
            rightPart[i - leftPart.length] = array[i];
        }

        mergeSort(leftPart);
        mergeSort(rightPart);

        int i = 0;
        int j = 0;

        while (i < leftPart.length && j < rightPart.length) {

            if (leftPart[i] > rightPart[j]) {
                array[i + j] = rightPart[j];
                j++;
            }
            else {
                array[i + j] = leftPart[i];
                i++;
            }
        }

        while (i < leftPart.length) {
            array[i + j] = leftPart[i];
            i++;
        }

        while (j < rightPart.length) {
            array[i + j] = rightPart[j];
            j++;
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
