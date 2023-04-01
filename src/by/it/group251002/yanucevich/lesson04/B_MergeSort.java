package by.it.group251002.yanucevich.lesson04;

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

    public static int[] merge(int[] a, int[] b){
        int[] arr = new int[a.length+b.length];
        int left=0;
        int right=0;
        for(int i=0;i<arr.length;i++){
            if (left==a.length){
                arr[i]=b[right];
                right++;
            }
            else if(right==b.length){
                arr[i]=a[left];
                left++;
            }
            else {
                if(a[left]<b[right]){
                    arr[i]=a[left];
                    left++;
                }
                else{
                    arr[i]=b[right];
                    right++;
                }
            }
        }
        return arr;
    }
    int[] mergeSort(int[] array,int l, int r){
        int[] arr;
        if(l!=r){
            int m=(l+r)/2;
            arr = merge(mergeSort(array,l,m),mergeSort(array,m+1,r));
        }
        else{
            arr= new int[]{array[l]};
        }
        return arr;
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

        a=mergeSort(a,0, a.length-1);
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
