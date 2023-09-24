package by.it.group251004.krutko.lesson05;

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

    /*{void Swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void SelectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++)
                minIndex = (arr[j] < arr[minIndex]) ? j : minIndex;
            Swap(arr, i, minIndex);
        }
    }*/

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
        int max = points[0], min = max;
        for (int i = 1; i < points.length; i++) {
            min = (points[i] < min) ? points[i] : min;
            max = (points[i] > max) ? points[i] : max;
        }

        int[] newArr = new int[max - min + 1];
        for (int i = 0; i < points.length; i++)
            newArr[points[i] - min]++;

        int pos = 0;
        for (int i = 0; i < newArr.length; i++) {
            while (newArr[i]-- > 0)
                points[pos++] = min + i;
                //newArr[i]--;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return points;
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
