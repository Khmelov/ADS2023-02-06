package by.it.group251001.besedin_anton.lesson04;

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

    void merge(int[] arr, int left, int middle, int right) {
        int[] result = new int[right - left];
        int it1 = 0, it2 = 0;
    
        while (left + it1 < middle && middle + it2 < right) {
            if (arr[left + it1] < arr[middle + it2]) {
                result[it1 + it2] = arr[left + it1];
                it1++;
            } else {
                result[it1 + it2] = arr[middle + it2];
                it2++;
            }
        }
        
        while (left + it1 < middle) {
            result[it1 + it2] = arr[left + it1];
            it1++;
        }

        while (middle + it2 < right) {
            result[it1 + it2] = arr[middle + it2];
            it2++;
        }
    
        if (it1 + it2 >= 0){
            System.arraycopy(result, 0, arr, left, it1 + it2);
        }
    }
    
    void mergeSort(int[] arr, int left, int right) {
        if (left + 1 >= right){
            return;
        }
        
        int middle = (left + right) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle, right);
        merge(arr, left, middle, right);
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

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        mergeSort(a, 0, n);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
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
