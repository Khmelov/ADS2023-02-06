package by.it.group251001.karpekov.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая возрастающая подпоследовательность
см.     https://ru.wikipedia.org/wiki/Задача_поиска_наибольшей_увеличивающейся_подпоследовательности
        https://en.wikipedia.org/wiki/Longest_increasing_subsequence

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]]больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]<A[i[j+1]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    1 3 3 2 6

    Sample Output:
    3
*/

public class A_LIS {



    int getSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        int result = longestIncreasingSubsequenceLength(m);



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    // n^2
    int longestIncSubsequenceLength(int numbers[]) {
        int maxlength = 1;
        int[] d = new int[numbers.length];
        for (int i = 0; i <= numbers.length-1; i++)
        {
            d[i] = 1;
        }


        for (int i = 0; i <= numbers.length-1; i++)
        {
            for (int j = 0; j <= i - 1; j++){
                if (numbers[i] > numbers[j] && d[j]+1 > d[i]){
                    d[i] = d[j] + 1;
                    if (d[i]> maxlength) maxlength = d[i];
                }
            }
        }

        return maxlength;
    }

    // nlogn
    int longestIncreasingSubsequenceLength(int numbers[]) {

        if (numbers.length <= 1) {
            return 1;
        }

        int lis_length = -1;

        int subsequence[] = new int[numbers.length];
        int indexes[] = new int[numbers.length];

        for (int i = 0; i < numbers.length; ++i) {
            subsequence[i] = 2^9+1;
        }

        subsequence[0] = numbers[0];
        indexes[0] = 0;

        for (int i = 1; i < numbers.length; ++i) {
            indexes[i] = ceilIndex(subsequence, 0, i, numbers[i]);

            if (lis_length < indexes[i]) {
                lis_length = indexes[i];
            }
        }

        return lis_length + 1;
    }

    int ceilIndex(int subsequence[],
                  int startLeft,
                  int startRight,
                  int key){

        int mid = 0;
        int left = startLeft;
        int right = startRight;
        int ceilIndex = 0;
        boolean ceilIndexFound = false;

        for (mid = (left + right) / 2; left <= right && !ceilIndexFound; mid = (left + right) / 2) {
            if (subsequence[mid] > key) {
                right = mid - 1;
            }
            else if (subsequence[mid] == key) {
                ceilIndex = mid;
                ceilIndexFound = true;
            }
            else if (mid + 1 <= right && subsequence[mid + 1] >= key) {
                subsequence[mid + 1] = key;
                ceilIndex = mid + 1;
                ceilIndexFound = true;
            } else {
                left = mid + 1;
            }
        }

        if (!ceilIndexFound) {
            if (mid == left) {
                subsequence[mid] = key;
                ceilIndex = mid;
            }
            else {
                subsequence[mid + 1] = key;
                ceilIndex = mid + 1;
            }
        }

        return ceilIndex;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}
