package by.it.group251003.palitanski.lesson05;

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

        int min, max = min = points[0];
        // тупо находим максимальное и минимальное значение
        for (int i = 1; i < points.length; i++) {
            if (points[i] < min) {
                min = points[i];
            }
            if (points[i] > max) {
                max = points[i];
            }
        }

        int[] count = new int[max - min + 1];

        for (int i = 0; i < points.length; i++) {
            // подсчитываем сколько раз встречается число,
            // встретилось +1 к счетчику
            count[points[i] - min]++;
        }
        // например. count[0]=1, count[1]=2, count[3]=0,count[4]=0,count[5]=1
        int idx = 0;
        // теперь все готово
        // пробегаем по всему счетчику (от 0 до 5)
        // count[i] - показывает сколько раз встречается то или иное число
        for (int i = 0; i < count.length; i++) {
            // count[0]=1, значит array[0]=0;
            // count[1]=2, значит вставляем два раза array[1]=array[2]=1;
            // count[2]=1, опять только один раз. array[3]=2;
            // count[3]=0, значит ничего не вставляем и т.д.
            for (int j = 0; j < count[i]; j++) {
                points[idx++] = i + min;
            }
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
