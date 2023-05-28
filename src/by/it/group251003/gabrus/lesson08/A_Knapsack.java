package by.it.group251003.gabrus.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.


Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:

15 3
2 8 16
Sample Output 2:
14

*/

public class A_Knapsack {

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }

        Arrays.sort(gold);
        int result;
        if (gold[0] < w) {
            int dp[][] = new int[2][w / gold[0] * gold.length];
            int m = 0;
            for (int ingot : gold) {
                int amount = w / ingot;
                while (amount > 0) {
                    dp[0][m] = ingot;
                    amount--;
                    m++;
                }
            }

            dp[1][0] = dp[0][0];
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < i; j++) {
                    int currWeight = dp[1][j] + dp[0][i];
                    if (currWeight <= w && currWeight > dp[1][i]){
                        dp[1][i] = currWeight;
                    }
                }
                if (dp[1][i] == 0) dp[1][i] = dp[1][i - 1];
            }

            result = dp[1][m - 1];
        } else {
            result = 0;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
