package by.it.group251004.khanenko.lesson04;

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
        }
        a = cutArray(a);

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public int [] cutArray(int[] array) { // сортировка Массива который передается в функцию
        if (array.length < 2) {
            return array;
        }
        int [] firstArray = new int[array.length / 2];
        System.arraycopy(array, 0, firstArray, 0, array.length / 2);
        int [] secondArray = new int[array.length - array.length / 2];
        System.arraycopy(array, array.length / 2, secondArray, 0, array.length - array.length / 2);
        firstArray = cutArray(firstArray);
        secondArray = cutArray(secondArray);
        return sortArrays(firstArray, secondArray);
    }
    public static int[] sortArrays(int[] firstArray, int[] secondArray) {
        int[] resArray = new int[firstArray.length + secondArray.length];
        int i = 0, j = 0, k = 0;
        while (i < firstArray.length && j < secondArray.length) {
            if (firstArray[i] <= secondArray[j]) {
                resArray[k] = firstArray[i];
                i++;
            }
            else {
                resArray[k] = secondArray[j];
                j++;
            }
            k++;
        }
        while (i < firstArray.length) {
            resArray[k] = firstArray[i];
            i++;
            k++;
        }
        while (j < secondArray.length) {
            resArray[k] = secondArray[j];
            j++;
            k++;
        }
        return resArray;
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
