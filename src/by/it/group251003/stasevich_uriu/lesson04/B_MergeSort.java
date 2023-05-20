package by.it.group251003.stasevich_uriu.lesson04;

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

    public static void MergeSort (int[] a, int n){
        if (n==1){
            return;
        }

        int mid = n/2;
        int[] leftArr = new int[mid];
        int[] rightArr = new int[n-mid];

        for (int i = 0; i < mid; i++){
            leftArr[i]=a[i];
        }

        for (int i = mid; i < n; i++){
            rightArr[i - mid]=a[i];
        }

        MergeSort(leftArr, mid);
        MergeSort(rightArr, n-mid);
        Merge(a, leftArr, rightArr, mid, n-mid);
    }

    public static void Merge(int[] a, int[] leftA, int[] rightA, int leftLen, int rightLen){
        int i = 0, j = 0, k = 0;
        while(i<leftLen && j<rightLen){
            if(leftA[i] < rightA[j]){
                a[k++] = leftA[i++];
            } else {
                a[k++]=rightA[j++];
            }
        }
        while (i<leftLen){
            a[k++]=leftA[i++];
        }

        while (j<rightLen){
            a[k++]=rightA[j++];
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

        MergeSort(a, n);

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
