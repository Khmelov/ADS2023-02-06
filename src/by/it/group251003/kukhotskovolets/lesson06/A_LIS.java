package by.it.group251003.kukhotskovolets.lesson06;

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
        // Prepare to read data from the input stream
        Scanner scanner = new Scanner(stream);

        // Read the total length of the sequence
        int n = scanner.nextInt();

        // Create an array to store the sequence
        int[] m = new int[n];

        // Read the entire sequence
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // Create an array to store the dynamic programming values
        int dp[] = new int[n];

        // Initialize the first element of the dp array
        dp[0] = 1;

        // Calculate the longest increasing subsequence using dynamic programming
        for (int i = 1; i < n; i++) {
            int mx = 0;

            // Find the maximum value in dp array for elements before the current element
            for (int j = 0; j < i; j++) {
                if (m[i] > m[j]) {
                    mx = Math.max(mx, dp[j]);
                }
            }

            // Update the dp value for the current element
            dp[i] = mx + 1;
        }

        // The result is the maximum value in the dp array
        int result = dp[n - 1];

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}
