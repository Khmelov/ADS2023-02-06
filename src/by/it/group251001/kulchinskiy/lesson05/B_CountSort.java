package by.it.group251001.kulchinskiy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class B_CountSort {

    int[] countSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] points = new int[n];

        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }

        int max = Arrays.stream(points).max().orElse(0);
        int[] counts = new int[max + 1];

        for (int num : points) {
            counts[num]++;
        }

        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                points[index++] = i;
                counts[i]--;
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
