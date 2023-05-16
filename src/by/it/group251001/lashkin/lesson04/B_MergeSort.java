package by.it.group251001.lashkin.lesson04;

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
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        a = sortArray(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public int[] sortArray(int[] arrayA) {
        if (arrayA == null)
            return null;
        if (arrayA.length < 2)
            return arrayA;
        int[] arrayB = new int[arrayA.length / 2];
        System.arraycopy(arrayA, 0, arrayB, 0, arrayB.length);
        int[] arrayC = new int[arrayA.length - arrayB.length];
        System.arraycopy(arrayA, arrayB.length, arrayC, 0, arrayA.length - arrayB.length);

        arrayB = sortArray(arrayB);
        arrayC = sortArray(arrayC);

        return mergeArray(arrayB, arrayC);
    }

    public int[] mergeArray(int[] arrayB, int[] arrayC) {
        int[] array = new int[arrayB.length + arrayC.length];
        int posB = 0, posC = 0;
        for (int i = 0; i < array.length; i++) {
            if (posB == arrayB.length) {
                array[i] = arrayC[posC];
                posC++;
            } else if (posC == arrayC.length) {
                array[i] = arrayB[posB];
                posB++;
            } else if (arrayB[posB] < arrayC[posC]) {
                array[i] = arrayB[posB];
                posB++;
            } else {
                array[i] = arrayC[posC];
                posC++;
            }
        }
        return array;
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
