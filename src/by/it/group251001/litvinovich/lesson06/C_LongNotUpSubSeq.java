package by.it.group251001.litvinovich.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
        }

        int[] dp = new int[n]; // Массив для хранения длин наибольших невозрастающих подпоследовательностей
        int[] prev = new int[n]; // Массив для хранения предыдущих индексов элементов в подпоследовательности

        for (int i = 0; i < n; i++) {
            dp[i] = 1; // Изначально длина каждой подпоследовательности равна 1
            prev[i] = -1; // Изначально предыдущий индекс не определен
            for (int j = 0; j < i; j++) {
                if (sequence[j] >= sequence[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1; // Обновляем длину подпоследовательности
                    prev[i] = j; // Запоминаем предыдущий индекс
                }
            }
        }

        int maxLen = dp[0];
        int maxLenIndex = 0;

        // Находим индекс последнего элемента наибольшей невозрастающей подпоследовательности
        for (int i = 1; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxLenIndex = i;
            }
        }

        // Восстанавливаем подпоследовательность, используя массив prev
        int[] result = new int[maxLen];
        int currentIndex = maxLenIndex;
        for (int i = maxLen - 1; i >= 0; i--) {
            result[i] = currentIndex + 1;
            currentIndex = prev[currentIndex];
        }

        System.out.println(maxLen);
        for (int i = 0; i < maxLen; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();

        return maxLen;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251001/litvinovich/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }
}
