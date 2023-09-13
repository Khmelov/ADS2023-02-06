package by.it.group251001.politykina.lesson05;

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
        int[] arr1 = new int[n + 1];

        int x = points[0];
        for (int i = 1; i < n; i++) {
            if (points[i] > x)
                x = points[i];
        }
        int[] count_arr = new int[x + 1];

        for (int i = 0; i < x; ++i) {
            count_arr[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            count_arr[points[i]]++;
        }

        for (int i = 1; i <= x; i++) {
            count_arr[i] += count_arr[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            arr1[count_arr[points[i]] - 1] = points[i];
            count_arr[points[i]]--;
        }

        for (int i = 0; i < n; i++) {
            points[i] = arr1[i];
        }
        display(points);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return points;
    }

    void display(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
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
