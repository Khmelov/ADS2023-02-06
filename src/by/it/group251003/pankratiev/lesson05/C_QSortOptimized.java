package by.it.group251003.pankratiev.lesson05;

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
    private class Segment  implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            if (start > stop) {
                int temp = start;
                start = stop;
                stop = temp;
            }
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            if (this.start != o.start) {
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.stop, o.stop);
        }
    }
    void swap(Segment[] arr, int i, int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    Segment medianOfThree(Segment[] arr, int left, int right) {
        int mid = left + (right - left) / 2;
        if (arr[left].compareTo(arr[mid]) > 0)
            swap(arr, left, mid);
        if (arr[left].compareTo(arr[right]) > 0)
            swap(arr, left, right);
        if (arr[mid].compareTo(arr[right]) > 0)
            swap(arr, mid, right);
        return arr[mid];
    }

    void binaryInsertionSort(Segment[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Segment currentSegment = arr[i];

            int low = left;
            int high = i - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                if (currentSegment.compareTo(arr[mid]) < 0)
                    high = mid - 1;
                else
                    low = mid + 1;
            }

            for (int j = i - 1; j >= low; j--)
                arr[j + 1] = arr[j];

            arr[low] = currentSegment;
        }
    }

    void QuickSort(Segment[] arr, int left, int right) {
        while (left < right) {
            if (right - left <= 10) {
                binaryInsertionSort(arr, left, right);
                return;
            }

            Segment pivot = medianOfThree(arr, left, right);
            int low = left;
            int high = right;
            int middle = (left + right) / 2;

            while (low <= high) {
                if (arr[middle].compareTo(pivot) < 0) {
                    swap(arr, middle, low);
                    middle++;
                    low++;
                } else if (arr[middle].compareTo(pivot) > 0) {
                    swap(arr, middle, high);
                    high--;
                } else
                    middle++;
            }

            QuickSort(arr, left, low - 1);
            left = low;
        }
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

        QuickSort(segments, 0, segments.length - 1);

        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();

            int left = 0;
            int right = n;
            int middle;

            while (left < right) {
                middle = (left + right) / 2;

                if (segments[middle].start <= points[i])
                    left = middle + 1;
                else
                    right = middle;
            }

            for (; right > 0; right--)
                if (points[i] >= segments[right - 1].start && points[i] <= segments[right - 1].stop)
                    result[i]++;
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
