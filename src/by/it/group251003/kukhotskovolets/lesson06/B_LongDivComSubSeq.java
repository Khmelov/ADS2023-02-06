package by.it.group251003.kukhotskovolets.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая кратная подпоследовательность

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] делится на предыдущий
    т.е. для всех 1<=j<k, A[i[j+1]] делится на A[i[j]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    4
    3 6 7 12

    Sample Output:
    3
*/

public class B_LongDivComSubSeq {


    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
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

        // Calculate the longest divisible subsequence using dynamic programming
        for (int i = 1; i < n; i++) {
            int mx = 0;

            // Find the maximum value in dp array for elements before the current element
            // that are divisible by the current element
            for (int j = 0; j < i; j++) {
                if (m[i] % m[j] == 0) {
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
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

}