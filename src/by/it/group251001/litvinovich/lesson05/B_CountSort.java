package by.it.group251001.litvinovich.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_CountSort {

    int[] countSort(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Читаем размер массива
        int n = scanner.nextInt();
        int[] points = new int[n];

        // Читаем числа
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }

        // Находим максимальное и минимальное значения
        int min = points[0];
        int max = points[0];
        for (int i = 1; i < n; i++) {
            if (points[i] < min) {
                min = points[i];
            }
            if (points[i] > max) {
                max = points[i];
            }
        }

        // Создаем массив счетчиков
        int[] count = new int[max - min + 1];

        // Считаем количество вхождений каждого числа
        for (int i = 0; i < n; i++) {
            count[points[i] - min]++;
        }

        // Восстанавливаем упорядоченную последовательность
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                points[index] = i + min;
                index++;
                count[i]--;
            }
        }

        return points;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
