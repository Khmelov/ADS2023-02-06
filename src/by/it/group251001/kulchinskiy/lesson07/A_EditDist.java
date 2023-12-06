package by.it.group251001.kulchinskiy.lesson07;

import java.io.FileNotFoundException;

public class A_EditDist {

    int getDistanceEdinting(String one, String two) {
        int m = one.length();
        int n = two.length();

        // Create a 2D array to store the distances
        int[][] dp = new int[m + 1][n + 1];

        // Initialize the first row and column of the array
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill in the rest of the array using dynamic programming
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // Characters are equal, no operation needed
                } else {
                    int deletion = dp[i - 1][j] + 1; // Delete a character from the first string
                    int insertion = dp[i][j - 1] + 1; // Insert a character into the first string
                    int substitution = dp[i - 1][j - 1] + 1; // Substitute a character in the first string

                    // Choose the minimum of the three operations
                    dp[i][j] = Math.min(deletion, Math.min(insertion, substitution));
                }
            }
        }

        // The result is the value in the bottom-right corner of the array
        return dp[m][n];
    }

    public static void main(String[] args) throws FileNotFoundException {
        A_EditDist instance = new A_EditDist();
        System.out.println(instance.getDistanceEdinting("ab", "ab"));
        System.out.println(instance.getDistanceEdinting("short", "ports"));
        System.out.println(instance.getDistanceEdinting("distance", "editing"));
    }
}
