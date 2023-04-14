package by.it.group251003.evt.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

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
    public static void mergesort(int[] arr) {
        int len = arr.length;
        if (len > 1){
            int mid = len / 2;
            int[] l = Arrays.copyOfRange(arr, 0, mid);
            int[] r = Arrays.copyOfRange(arr, len - mid, len);
            mergesort(l);
            mergesort(r);

            merge(arr,l,r);

        }
    }

    public static void merge(int[] main, int[] left, int[] right){
        int i = 0, j=0,k=0;
        while(i<left.length&&j<right.length){
            if(left[i]<=right[j]){
                main[k]=left[i];
                k++;
                i++;
            }
            else{
                main[k]=right[j];
                k++;
                j++;
            }
        }
        while (i<left.length){
            main[k]=left[i];
            k++;
            i++;
        }
        while(j<right.length){
            main[k]=right[j];
            k++;
            j++;
        }

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
        if (n>1){
            mergesort(a);
        }





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
