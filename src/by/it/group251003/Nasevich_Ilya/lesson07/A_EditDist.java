package by.it.group251003.Nasevich_Ilya.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.min;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {
    private static final int INF = 1_000_000_000;

    int levenshtein(int[][] dp, int i, int j, String s1, String s2){
        if(i == 0) return dp[i][j] = j;
        if(j == 0) return dp[i][j] = i;

        if(dp[i][j] != INF) return dp[i][j];

        int ins = levenshtein(dp, i,j - 1, s1, s2) + 1;
        int del = levenshtein(dp, i - 1, j, s1, s2) + 1;
        int repl = levenshtein(dp, i - 1, j - 1, s1, s2) + (s1.charAt(i - 1) != s2.charAt(j - 1) ? 1 : 0);

        return dp[i][j] = min(ins, min(del, repl));
    }

    int getDistanceEdinting(String one, String two) {
        int[][] dp = new int[one.length() + 1][two.length() + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        return levenshtein(dp, one.length(), two.length(), one, two);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
