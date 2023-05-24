package by.it.group251001.churavskiy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {


    int[] countSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        int[] points=new int[n];

        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением сортировки подсчетом


        countSort(points, 10);


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return points;
    }

    int[] countSortImp(int[] arr, int MAX){
        int[] count = new int[MAX];
        //counting;
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        //inc c[i] += c[i - 1]
        for (int i = 1; i < MAX; i++) {
            count[i] += count[i - 1];
        }
        int[] sorted = new int[arr.length];
        for (int i = arr.length - 1; i > -1; i--) {
            count[arr[i]]--;
            sorted[count[arr[i]]] = arr[i];
        }
        return sorted;
    }

    void countSort(int[] arr, int MAX){
        int[] sorted = countSortImp(arr, MAX);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sorted[i];
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
