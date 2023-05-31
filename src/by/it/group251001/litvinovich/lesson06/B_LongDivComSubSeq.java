package by.it.group251001.litvinovich.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_LongDivComSubSeq {

    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Читаем общую длину последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];

        // Читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // Создаем массив dp для хранения результатов
        int[] dp = new int[n];

        // Инициализируем каждый элемент dp как 1 (минимальная длина подпоследовательности равна 1)
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Начинаем считать длину наибольшей кратной подпоследовательности
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Если m[i] делится на m[j], то увеличиваем dp[i] на 1
                if (m[i] % m[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        // Находим максимальное значение в массиве dp
        int maxSeqSize = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxSeqSize) {
                maxSeqSize = dp[i];
            }
        }

        return maxSeqSize;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }
}
