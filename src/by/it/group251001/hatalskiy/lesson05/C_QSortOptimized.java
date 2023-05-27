package by.it.group251001.hatalskiy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии *
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
        public int compareTo(Segment o){
            return this.stop - o.stop;
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
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        Q_Sort_fix(segments,0, segments.length - 1);

        for (int i = 0; i < m; i++){
            int res_bin_search = BinarySearch(segments,points[i],0, segments.length - 1);
            if (res_bin_search > -1){
                int count = 1;
                int j = res_bin_search + 1;
                while (j < n && points[i] <= segments[j].stop){
                    if (segments[j].start <= points[i])
                        count++;
                    j++;
                }
                j = res_bin_search - 1;
                while (j >= 0 && points[i] <= segments[j].stop){
                    if (segments[j].start <= points[i])
                        count++;
                    j--;
                }
                result[i] = count;
            }
            else
                result[i] = 0;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }
    public static class Partition {
        int left;
        int right;

        public Partition(int lt, int gt) {
        }
    }
    Partition new_Partition(Segment[] A, int left, int right){
        int lt = left;
        int current = left;
        int gt = right;
        Segment value = A[left];
        while(current <= gt){
            if (A[current].compareTo(value) < 0){
                Segment temp = A[current];
                A[current] = A[lt];
                A[lt] = temp;
                lt++;
                current++;}
            else{
                if (A[current].compareTo(value) == 0)
                    current++;
                else{
                    Segment temp = A[current];
                    A[current] = A[gt];
                    A[gt] = temp;
                    gt--;
                }

            }
        }
        Partition res = new Partition(lt,gt);
        return res;
    }
    /*int partition(Segment[] A, int left, int right){
        Segment temp = A[left];
        int left_temp = left;
        for (int i = left + 1;i < right;i++){
            if  (A[i].compareTo(temp) < 0){
                left_temp++;
                Segment q = A[i];
                A[i] = A[left_temp];
                A[left_temp] = q;
            }
        }
        Segment q = A[left];
        A[left] = A[left_temp];
        A[left_temp] = A[left];

        return left_temp;
    }*/
    Segment[] Q_Sort_fix(Segment[] A, int left, int right){
        while (left < right) {
            Partition middlePartition = new_Partition(A, left, right);
            Q_Sort_fix(A, left, middlePartition.left - 1);
            left = middlePartition.right + 1;
        /*while (left < right){
            int m = partition(A,left,right);
            Q_Sort_fix(A,left,m - 1);
            left = m + 1;
        }*/
        }
        return A;
    }
    int BinarySearch(Segment[] A, int key, int left,int right){
        boolean IsFound = false;
        while (left <= right && IsFound != true){
            int mid = (left + right)/2;
            if (A[mid].start > key)
                right = mid - 1;

            else{
                if (A[mid].stop < key)
                    left = mid + 1;
                else{
                    IsFound = true;
                    return mid;
                }
            }
        }
        return -1;
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