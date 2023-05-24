package by.it.group251002.makarov.lesson05;

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
            if (start<stop)
            {
                this.start = start;
                this.stop = stop;
            }
            else
            {
                this.start=stop;
                this.stop=start;
            }
        }
        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            if (this.start!=o.start){
                return Integer.compare(this.start,o.start);
            }
            return Integer.compare(this.stop,o.stop);
        }
    }
        private void swap(Segment[] arr,int i1,int i2){
        Segment temp = arr[i1];
        arr[i1]=arr[i2];
        arr[i2]=temp;
        }

        Segment mid(Segment[] arr,int left,int right){
        int centre = left+(right-left)/2;
            if (arr[left].compareTo(arr[right])>0) swap(arr,left,right);
            if (arr[centre].compareTo(arr[right])>0) swap(arr,centre,right);
            if (arr[left].compareTo(arr[centre])>0) swap(arr,left,centre);
        return arr[centre];
        }

        private int razdel(Segment[] arr, int left,int right){
        Segment curr = arr[left];
        int j = left;
        for (int i = left + 1;i<=right;i++ ){
            if (arr[i].compareTo(curr)==0||arr[i].compareTo(curr)==-1){
                j++;
                swap(arr,i,j);
            }
        }
        swap(arr,left,j);
        return j;
        }
    public void quicksort(Segment[] arr,int left, int right){
        while (left<right){
            if (right-left<=12){
                insertionsort(arr,left,right);
                return;
            }
            int base = razdel(arr,left,right);
            quicksort(arr,left,right);
            left=base+1;
        }
    }
    private void insertionsort(Segment[] arr, int left,int right){
        int j;
        for(int i = left+1; i<right; i++){
            Segment curr = arr[i];
            for (j=i;j>0&&curr.compareTo(arr[j-1])>0;j--){
                arr[j]=arr[j-1];
            }
            arr[j]=curr;
        }
    }

    private int binaryS(Segment[] arr, int find, int left,int right){
        int mid;
        while (left<right){
            mid = left+(right-left)>>1;
            if (arr[mid].start<=find) left = mid +1;
            else right=mid;
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
        quicksort(segments,0,n-1);
        for (int i =0;i<n;i++){
            int postofind=binaryS(segments,points[i],0,n);
            while (postofind>0){
                if (points[i]>=segments[postofind-1].start&&points[i]<=segments[postofind-1].stop)
                    result[i]++;
                postofind--;
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
