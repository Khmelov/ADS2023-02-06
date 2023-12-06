package by.it.group251001.kulchinskiy.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
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

        // Trace back the edit operations
        StringBuilder result = new StringBuilder();
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (one.charAt(i - 1) == two.charAt(j - 1)) {
                result.insert(0, "#,"); // Match, no operation needed
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j] + 1) {
                result.insert(0, "-").insert(0, one.charAt(i - 1)).insert(0, ","); // Deletion
                i--;
            } else if (dp[i][j] == dp[i][j - 1] + 1) {
                result.insert(0, "+").insert(0, two.charAt(j - 1)).insert(0, ","); // Insertion
                j--;
            } else {
                result.insert(0, "~").insert(0, two.charAt(j - 1)).insert(0, ","); // Substitution
                i--;
                j--;
            }
        }

        // If there are remaining characters in the first string, delete them
        while (i > 0) {
            result.insert(0, "-").insert(0, one.charAt(i - 1)).insert(0, ",");
            i--;
        }

        // If there are remaining characters in the second string, insert them
        while (j > 0) {
            result.insert(0, "+").insert(0, two.charAt(j - 1)).insert(0, ",");
            j--;
        }

        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
