package by.it.group251001.kulchinskiy.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_Knapsack {

    int getMaxWeight(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int w = scanner.nextInt(); // Knapsack capacity
        int n = scanner.nextInt(); // Number of gold bars
        int[] gold = new int[n]; // Array to store the weights of gold bars

        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        int[] dp = new int[w + 1]; // Array to store the maximum weights

        for (int i = 1; i <= w; i++) {
            for (int j = 0; j < n; j++) {
                if (gold[j] <= i) {
                    int weight = dp[i - gold[j]] + gold[j];
                    if (weight > dp[i]) {
                        dp[i] = weight;
                    }
                }
            }
        }

        return dp[w];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}

