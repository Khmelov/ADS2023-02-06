package by.it.group251001.churavskiy.lesson05;

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
    public interface BiComparator<T, U> {
        int accept(T t, U u);
    }
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;
        int length() {
            return stop - start;
        }

        Segment(int start, int stop) {
            if (start < stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            int comp = Integer.compare(this.start, o.start);
            if (comp == 0)
                comp = Integer.compare(this.length(), o.length());
            return comp;
        }

        boolean inSegment(int statement) {
            return this.start <= statement && statement <= this.stop;
        }

    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        Integer[] points = new Integer[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        sort(segments);
        sort(points);
        BiComparator<Segment, Integer> comparator = (segment, point) -> {
            if (segment.start > point) return 1;
            if (segment.stop < point) return -1;
            return 0;
        };
        for (int i = 0; i < m; i++) {
            int index = binaryFind(segments, points[i], comparator);
            if (index > -1) {
                result[i]++;
                //move to the right
                int j = index + 1;
                while (j < n && segments[j].inSegment(points[i])) {
                    result[i]++;
                    j++;
                }
                //move to the left
                j = index - 1;
                while (j > -1 && segments[j].inSegment(points[i])) {
                    result[i]++;
                    j--;
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    <T extends Comparable<T>, U> int binaryFind(T[] arr, U find, BiComparator<T, U> comparator) {
        return binaryFindImp(arr, 0, arr.length - 1, find, comparator);
    }

    <T extends Comparable<T>, U> int binaryFindImp(
            T[] arr, int l, int r, U find, BiComparator<T, U> comparator) {
        if (r < l) {
            return -1;
        }
        if (comparator.accept(arr[l], find) == 0) {
            return l;
        }
        if (comparator.accept(arr[r], find) == 0) {
            return r;
        }
        int mid = l + ((r - l) / 2);// + 1;
        if (comparator.accept(arr[mid], find) == 0) {
            return mid;
        }
        if (comparator.accept(arr[mid], find) < 0) {
            return binaryFindImp(arr, mid + 1, r - 1, find, comparator);
        } else {
            return binaryFindImp(arr, l + 1, mid - 1, find, comparator);
        }
    }

    <T extends Comparable<T>> void sort(T[] arr) {
        qSortOptimised(arr, 0, arr.length - 1);
    }

    <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    <T extends Comparable<T>> void qSortOptimised(T[] arr, int lo, int hi) {
        int p = lo;
        int g = hi + 1;
        int i = lo + 1;
        int j = hi;
        T sup = arr[lo];
        do{
            while (i < hi && arr[i].compareTo(sup) < 0) {
                i++;
            }
            while (arr[j].compareTo(sup) > 0) {
                j--;
            }
            if (i >= j) {
                if (i == j && arr[i].compareTo(sup) == 0) {
                    p++;
                    swap(arr, i, p);
                }
            } else {
                swap(arr, i, j);
                if (arr[i].compareTo(sup) == 0) {
                    p++;
                    swap(arr, i, p);
                }
                if (arr[j].compareTo(sup) == 0) {
                    g--;
                    swap(arr, j, g);
                }
                i++;
                j--;
            }
        }while (i < j);
        for (int k = lo; k <= p; k++) {
            swap(arr, k, j);
            j--;
        }
        for (int k = hi; k >= g; k--) {
            swap(arr, k, i);
            i++;
        }
        if (lo < j) {
            qSortOptimised(arr, lo, j);
        }
        if (i < hi) {
            qSortOptimised(arr, i, hi);
        }
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
