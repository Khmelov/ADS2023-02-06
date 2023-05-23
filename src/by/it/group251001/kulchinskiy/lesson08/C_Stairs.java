package by.it.group251001.kulchinskiy.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_Stairs {

    int getMaxSum(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] stairs = new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }

        // Dynamic programming approach
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = stairs[0];

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + stairs[i - 1];
        }

        return dp[n];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        System.out.println(res);
    }
}
