package by.it.group251001.dadush.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            return this.start - o.start;
        }
    }

    void QSortOptimized(Segment[] arr, int low, int high) {
        int i = low, j = high, mid;
        mid = (i + j) / 2;
        Segment x = arr[mid];

        while (i <= j) {
            while (arr[i].compareTo(x) < 0) i++;
            while (arr[j].compareTo(x) > 0) j--;
            if (i <= j) {
                Segment temp = arr[i];
                arr[i++] = arr[j];
                arr[j--] = temp;
            }
        }
        if (low < j) QSortOptimized(arr, low, j);
        if (high > i) QSortOptimized(arr, i, high);
    }

    private int binSearchStart(Segment[] array, Segment key) {
        int i = 0, j = array.length - 1;
        while (i < j) {
            int c = i + (j - i) / 2;
            if (array[c].compareTo(key) >= 0) {j = c - 1;}
            else {i = c + 1;}
        }
        return i;
    }

    private int binSearchStop(Segment[] array, Segment key) {
        int i = 0, j = array.length - 1;
        while (i <= j) {
            int c = i + (j - i) / 2;
            if (array[c].compareTo(key) <= 0) {j = c - 1;}
            else {i = c + 1;}
        }
        return i;
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        int min = segments[0].start;
        int max = segments[0].stop;
        for (int i = 0; i < n; i++) {
            min = Math.min(segments[i].start, min);
            max = Math.max(segments[i].stop, max);
        }
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        QSortOptimized(segments, 0, n - 1);

        for (int res = 0; res < m; res++) {
            result[res] = 0;
            Segment low = new Segment(min, points[res]);
            Segment high = new Segment(points[res], max);
            int l = binSearchStart(segments, low);
            int r = binSearchStop(segments, high);
            for (int i = l; i < r ; i++) {
                if (points[res] >= segments[i].start && points[res] <= segments[i].stop)
                    result[res]++;
            }

        }
        for (int res = 0; res < m; res++) {
            result[res] = 0;
            for (int i = 0; i < n && segments[i].start <= points[res]; i++) {
                if (segments[i].stop >= points[res]) result[res]++;
            }
        }



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
