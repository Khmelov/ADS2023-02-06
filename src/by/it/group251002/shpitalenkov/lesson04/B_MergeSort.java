package by.it.group251002.shpitalenkov.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
    void merge(int[] a, int[] b,int[] result){
        int left=0,right=0,i=0;
        while(left<a.length && right<b.length){
            if(a[left]<=b[right]){
                result[i]=a[left];
                left++;
            }
            else{
                result[i]=b[right];
                right++;
            }
            i++;
        }
        while (left<a.length){
            result[i]=a[left];
            i++;
            left++;
        }
        while (right<b.length){
            result[i]=b[right];
            right++;
            i++;
        }
    }
    void mergeSort(int[] array){
        if (array.length>1) {
            int l = 0, r = array.length-1;
            int m = (l + r) / 2;
            int[] leftPart= Arrays.copyOfRange(array,l,m+1);
            int[] rightPart= Arrays.copyOfRange(array,m+1,array.length);
            mergeSort(leftPart);
            mergeSort(rightPart);
            merge(leftPart,rightPart,array);
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

        mergeSort(a);
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
