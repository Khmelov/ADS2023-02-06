package by.it.group251002.urbanovich.lesson14;

import java.util.Scanner;
import java.util.*;

public class SitesB {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DisjointSetUnion dsu = new DisjointSetUnion(100);
        List<String> sites = new ArrayList<>();
        String line = scanner.next();
        while (line.compareTo("end") != 0) {
            String[] pair = line.split("\\+");
            int x1 = sites.indexOf(pair[0]);
            if (x1 == -1) {
                x1 = sites.size();
                sites.add(pair[0]);
                dsu.makeSet(x1);
            }
            int x2 = sites.indexOf(pair[1]);
            if (x2 == -1) {
                x2 = sites.size();
                sites.add(pair[1]);
                dsu.makeSet(x2);
            }
            dsu.combineSets(x1, x2);
            line = scanner.next();
        }
        int max_size = sites.size();
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

