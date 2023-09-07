package by.it.group251003.gridusko_bogdan.lesson05;

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
            if (start < stop){
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            if (this.start != o.start) {
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.stop, o.stop);
        }
    }
    private void swap(Segment[] nums, int destination, int source){
        Segment tmp = nums[destination];
        nums[destination] = nums[source];
        nums[source] = tmp;
    }
    Segment median(Segment[] nums, int left, int right) {
        int mid = left + (right - left) / 2;
        if (nums[left].compareTo(nums[right]) > 0)
            swap(nums, left, right);
        if (nums[mid].compareTo(nums[right]) > 0)
            swap(nums, mid, right);
        if (nums[left].compareTo(nums[mid]) > 0)
            swap(nums, left, mid);
        return nums[mid];
    }

    private int partition(Segment[] nums, int left, int right){
        Segment curr = nums[left];
        int j = left;
        for(int i = left + 1; i <= right; i++) {
            if (nums[i].compareTo(curr) == 0 || nums[i].compareTo(curr) == -1) {
                j++;
                swap(nums, i, j);
            }
        }
        swap(nums, left, j);
        return j;
    }
    private void quickSort(Segment[] nums, int left, int right) {
        while (left < right) {
            if (right - left <= 12) {
                insertionSort(nums, left, right);
                return;
            }
            int pivot = partition(nums, left, right);
            quickSort(nums, left, pivot-1);
            left = pivot + 1;
        }
    }

    private void insertionSort(Segment[] nums, int left, int right) {
        int j;
        for (int i = left+1; i < right; i++) {
            Segment curr = nums[i];
            for (j = i; j > 0 && curr.compareTo(nums[j-1]) > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[j] = curr;
        }
    }

    private int binarySearch(Segment[] nums, int toFind, int left, int right){
        int mid;
        while (left < right){
            mid = left + (right - left) >> 1;
            if (nums[mid].start <= toFind)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
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
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quickSort(segments, 0, n-1);
        for (int i = 0; i < n; i++){
            int posFind = binarySearch(segments, points[i],0, n);

            while (posFind > 0){
                if (points[i] >= segments[posFind - 1].start && points[i] <= segments[posFind - 1].stop)
                    result[i]++;
                posFind--;
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
