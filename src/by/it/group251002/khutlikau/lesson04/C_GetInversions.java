package by.it.group251002.khutlikau.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        int[] b = new int[n/2+2];
        int[] c = new int[n/2];
        int ib = 0;
        int ic = 0;
        int degree = 2;
        while (degree < 2*n){
            ib = 0;
            ic = 0;
            int i = 0;
            while (i<n){
                if(i%degree < degree/2){
                    b[ib] = a[i];
                    ib++;
                } else{
                    c[ic] = a[i];
                    ic++;
                }
                i++;
            }
            int ia = degree / 2;
            i = 0;
            int j = 0;
            while(ia<n) {
                while (i < ia && j < ia && i < ib && j < ic) {

                    if (b[i] <= c[j]) {
                        a[i+j] = b[i];
                        if (i/(degree/2)*degree+i%(degree/2)-(i+j) > 0){
                            result+=i/(degree/2)*degree+i%(degree/2)-(i+j);
                        }
                        i += 1;
                    }
                    else {
                        a[i+j] = c[j];
                        if (degree/2+j/(degree/2)*degree+j%(degree/2)-(i+j) > 0){
                            result+=degree/2+j/(degree/2)*degree+j%(degree/2)-(i+j);
                        }
                        j += 1;
                    }
                }
                while (i < ia && i < ib) {
                    a[i+j] = b[i];
                    i += 1;
                }
                while (j < ia && j < ic) {
                    a[i+j] = c[j];
                    j += 1;
                }
                ia += degree / 2;
            }
            degree *= 2;
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251002/khutlikau/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
