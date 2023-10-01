package by.it.group251003.kardychka.lesson05;

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
        //размер массива
        int n = scanner.nextInt();
        int[] points=new int[n];

        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }

        //находим максимальный элемент в массиве
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (points[i] > max) {
                max = points[i];
            }
        }

        //инициализируем массив для подсчета
        int[] count = new int[max+1];
        for (int i = 0; i <= max; i++) {
            count[i] = 0;
        }

        //подсчитываем количество вхождений каждого элемента
        for (int i = 0; i < n; i++) {
            count[points[i]]++;
        }

        //вычисляем префиксную сумму
        for (int i = 1; i <= max; i++) {
            count[i] += count[i-1];
        }

        //заполняем результирующий массив
        int[] result = new int[n];
        for (int i = n-1; i >= 0; i--) {
            result[--count[points[i]]] = points[i];
        }

        return result;
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