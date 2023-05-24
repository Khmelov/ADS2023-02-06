package by.it.group251003.kapinskiy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

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
            this.start = Math.min(start, stop);
            this.stop = Math.max(start, stop);
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            Segment other = (Segment) o;
            return Integer.compare(this.start, other.start);
        }
    }

    void quickSort(Segment[] arr, int left, int right) {
        while (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            left = pivotIndex + 1;
        }
    }

    int partition(Segment[] arr, int l, int r) {
        Segment pivot = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++)
            if (arr[i].compareTo(pivot) <= 0) {
                j++;
                swap(arr, i, j);
            }
        swap(arr, l, j);
        return j;
    }
    void swap(Segment[] arr, int i, int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    int upSearch(Segment[] arr, Segment point) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (arr[mid].compareTo(point) <= 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    int lowSearch(Segment[] arr, Segment point) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(point) >= 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
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
        int min = segments[0].start;
        int max = segments[0].stop;
        quickSort(segments, 0, n - 1);
        for(int i = 0; i < n; i++) {
            min = Math.min(segments[i].start, min);
            max = Math.max(segments[i].stop, max);
        }

        for(int i = 0; i < m; i++) {
            Segment high = new Segment(points[i], max);
            Segment low = new Segment(min, points[i]);
            int right = upSearch(segments, high);
            int left = lowSearch(segments, low);
            for(int j = left; j < right; j++) {
                if (points[i] >= segments[j].start && points[i] <= segments[j].stop)
                    result[i]++;
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
