package by.it.group251003.kukhotskovolets.lesson05;

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
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            if (start > stop){
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
            if(this.start == o.start && this.stop == o.stop) {
                return 0;
            }
            if((this.start > o.start) || (this.start == o.start) && (this.stop > o.stop)){
                return 1;
            }
            return -1;
        }
    }

    Segment MedianOfThree(Segment left, Segment mid, Segment  right){
        if (left.compareTo(mid) < 0) {
            if (mid.compareTo(right) < 0) {
                return mid;
            } else if (left.compareTo(right) < 0) {
                return right;
            } else {
                return left;
            }
        }  else {
            if (left.compareTo(right) < 0){
                return left;
            } else if (mid.compareTo(right) < 0){
                return right;
            } else {
                return mid;
            }
        }
    }

    Segment [] CallSortOnPlace(Segment[] arr, int left, int right){
        for (int i = left + 1; i <= right; i++) {
            Segment key = arr[i];
            int j = i - 1;
            while (j >= left && (arr[j].compareTo(key) > 0)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        return arr;
    }

    Segment[] QuickSort(Segment[] arr, int left, int right){
        while(left < right)
        {

            if(right - left <= 10)
            {arr = CallSortOnPlace(arr,left,right); return arr;}

            Segment ideal = MedianOfThree(arr[right], arr[(right + left) / 2], arr[left]);
            int lower_than = left, i = left, greater_than = right;
            while (i <= greater_than) {
                if (arr[i].compareTo(ideal) < 0) {
                    Segment temp = arr[lower_than];
                    arr[lower_than] = arr[i];
                    arr[i] = temp;
                    i++;
                    lower_than++;
                } else if (arr[i].compareTo(ideal) > 0) {
                    Segment temp = arr[greater_than];
                    arr[greater_than] = arr[i];
                    arr[i] = temp;
                    greater_than--;
                } else {
                    i++;
                }
            }

            arr = QuickSort(arr, left, lower_than - 1);
            left = i;

        }
        return arr;
    }
    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];

        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];


        for (int i = 0; i < n; i++) {
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        segments = QuickSort(segments, 0, segments.length - 1);
        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();

            int l = 0, r = n, mid = 0;
            while (l != r) {
                mid = (l + r)/2;
                if (segments[mid].start <= points[i]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            while (r-- > 0) {
                if (points[i] >= segments[r].start && points[i] <= segments[r].stop){
                    result[i]++;
                }

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
