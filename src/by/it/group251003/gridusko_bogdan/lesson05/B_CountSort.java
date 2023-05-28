package by.it.group251003.gridusko_bogdan.lesson05;

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
    private void sort(int[] nums, int min, int max){
        int[] frequency = new int[max - min + 1];
        int n = nums.length;
        for (int i = 0; i < n; i++){
            frequency[nums[i] - min]++;
        }
        int numsIndex = 0;
        for (int i = 0; i < frequency.length; i++){
            for (int j = 0; j < frequency[i]; j++) {
                nums[numsIndex++] = i + min;
            }
        }
    }
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
        int max = points[0];
        int min = points[0];
        for (int i = 1; i < n; i++){
            if (points[i] > max)
                max = points[i];
            if (points[i] < min)
                min = points[i];
        }
        sort(points, min, max);
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
