package by.it.group251001.kulchinskiy.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_LongDivComSubSeq {


    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
        // Prepare to read the input data
        Scanner scanner = new Scanner(stream);

        // Read the total length of the sequence
        int n = scanner.nextInt();

        // Read the sequence into an array
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
        }

        // Initialize an array to store the lengths of the divisible subsequences
        int[] dp = new int[n];

        // Compute the length of the longest divisible common subsequence
        int result = computeLDCS(sequence, dp);

        return result;
    }

    int computeLDCS(int[] sequence, int[] dp) {
        int n = sequence.length;
        int maxLDCS = 0;

        // Compute the lengths of divisible common subsequences ending at each index
        for (int i = 0; i < n; i++) {
            dp[i] = 1; // Minimum length is 1

            // Find the maximum length of divisible common subsequence ending at the current index
            for (int j = 0; j < i; j++) {
                if (sequence[i] % sequence[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }

            // Update the maximum length if necessary
            if (dp[i] > maxLDCS) {
                maxLDCS = dp[i];
            }
        }

        return maxLDCS;
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

}