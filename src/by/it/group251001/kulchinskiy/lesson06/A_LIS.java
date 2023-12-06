package by.it.group251001.kulchinskiy.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_LIS {


    int getSeqSize(InputStream stream) throws FileNotFoundException {
        // Prepare to read the input data
        Scanner scanner = new Scanner(stream);

        // Read the total length of the sequence
        int n = scanner.nextInt();

        // Read the sequence into an array
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
        }

        // Initialize an array to store the lengths of the increasing subsequences
        int[] dp = new int[n];

        // Compute the length of the longest increasing subsequence
        int result = computeLIS(sequence, dp);

        return result;
    }

    int computeLIS(int[] sequence, int[] dp) {
        int n = sequence.length;
        int maxLIS = 0;

        // Compute the lengths of increasing subsequences ending at each index
        for (int i = 0; i < n; i++) {
            dp[i] = 1; // Minimum length is 1

            // Find the maximum length of increasing subsequence ending at the current index
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }

            // Update the maximum length if necessary
            if (dp[i] > maxLIS) {
                maxLIS = dp[i];
            }
        }

        return maxLIS;
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}
