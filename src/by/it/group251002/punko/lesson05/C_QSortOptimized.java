package by.it.group251002.punko.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Integer;

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
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(this.start, ((Segment) o).start);
        }
    }
    private int findFirstSegmentContainingPoint(Segment[] segments, int point, int left) {
        int l = left + 1;
        int r = segments.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (segments[m].start <= point && point <= segments[m].stop) {
                r = m - 1;
            } else if (segments[m].start > point) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return r;
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
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        Arrays.sort(segments);

        // проходим по всем точкам и ищем отрезки, которые их содержат
        for (int i = 0; i < m; i++) {
            int point = points[i];
            int left = 0;
            int right = n - 1;
            // сначала ищем первый отрезок, содержащий точку
            while (left <= right) {
                int middle = left + (right - left) / 2;
                if (point < segments[middle].start) {
                    right = middle - 1;
                } else if (point > segments[middle].stop) {
                    left = middle + 1;
                } else {
                    // нашли отрезок, содержащий точку, выходим из цикла
                    right = middle - 1;
                }
            }
            if (left == 0 && point < segments[0].start) {
                // точка находится за пределами всех отрезков
                result[i] = 0;
            } else {
                int count = 0;
                // теперь ищем оставшиеся отрезки, содержащие точку
                for (int j = left; j < n && segments[j].start <= point; j++) {
                    if (point <= segments[j].stop) {
                        count++;
                    }
                }
                result[i] = count;
            }

        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


        public static void main(String[] args) throws FileNotFoundException {
            String root = System.getProperty("user.dir") + "/src/";
            InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
            C_QSortOptimized instance = new C_QSortOptimized();
            int[] result = instance.getAccessory2(stream);
            for (int index : result) {
                System.out.print(index + " ");
            }
        }

}
