package by.it.group251001.kulchinskiy.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_Knapsack {

    int getMaxWeight(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt(); // Knapsack capacity
        int n = scanner.nextInt(); // Number of gold bars
        int[] weights = new int[n]; // Array to store the weights of gold bars

        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        int[][] dp = new int[n + 1][W + 1]; // Array to store the maximum weights

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                dp[i][j] = dp[i - 1][j];
                if (weights[i - 1] <= j) {
                    int weight = dp[i - 1][j - weights[i - 1]] + weights[i - 1];
                    if (weight > dp[i][j]) {
                        dp[i][j] = weight;
                    }
                }
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
