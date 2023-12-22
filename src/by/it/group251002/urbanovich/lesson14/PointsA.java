package by.it.group251002.urbanovich.lesson14;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PointsA {
    private static class DisjointSetUnion {
        int parent[];
        int rank[];

        public void makeSet(int item) {
            parent[item] = item;
            rank[item] = 0;
        }

        public int findSet(int item) {
            if (parent[item] != item)
                return findSet(parent[item]);
            return item;
        }

        public void combineSets(int a, int b) {
            a = findSet(a);
            b = findSet(b);
            if (a != b) {
                if (rank[a] < rank[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                parent[b] = a;
                if (rank[a] == rank[b])
                    rank[a]++;
            }
        }

        DisjointSetUnion(int size) {
            parent = new int[size];
            rank = new int[size];
        }
    }

    static boolean check(int[][] points, int a, int b, int max_dist) {
        return Math.hypot(Math.hypot(points[a][0] - points[b][0], points[a][1] - points[b][1]), points[a][2] - points[b][2]) <= max_dist;
    }

    public static void main(String[] args) {
        int size = 0;
        Scanner scanner = new Scanner(System.in);
        int max_dist = scanner.nextInt();
        int max_size = scanner.nextInt();
        int[][] points = new int[max_size][3];
        DisjointSetUnion dsu = new DisjointSetUnion(max_size);

        for (int i = 0; i < max_size; i++) {
            for (int j = 0; j < 3; j++)
                points[size][j] = scanner.nextInt();
            dsu.makeSet(size);
            size++;
        }
        scanner.close();
        for (int i = 0; i < max_size; i++)
            for (int j = i + 1; j < max_size; j++)
                if (check(points, i, j, max_dist)) {
                    dsu.combineSets(i, j);
                }

        int[] dsu_size_array = new int[max_size];
        for (int i = 0; i < max_size; i++) {
            dsu_size_array[dsu.findSet(i)]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max_size; i++) {
            int max = i;
            for (int j = i + 1; j < max_size; j++)
                if (dsu_size_array[max] < dsu_size_array[j])
                    max = j;
            if (dsu_size_array[max] == 0)
                break;
            int temp = dsu_size_array[max];
            dsu_size_array[max] = dsu_size_array[i];
            dsu_size_array[i] = temp;
            sb.append(dsu_size_array[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
