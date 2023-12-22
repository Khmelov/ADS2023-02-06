package by.it.group251002.klimovich.lesson14;

import java.util.*;

public class StatesHanoiTowerC {

    private static class DSU {
        int[] parent;
        int[] rank;

        DSU(int size){
            parent = new int[size];
            rank = new int[size];
        }
        public void set(int v) {
            parent[v] = v;
            rank[v] = 1;
        }
        public int rank(int v){
            return rank[v];
        }
        int findSet(int v) {
            if (v == parent[v])
                return v;
            return findSet(parent[v]);
        }

        void union(int a, int b) {
            int max = findSet(a);
            int min = findSet(b);
            if (max != min) {
                if (rank[max] < rank[min]) {
                    int temp = max;
                    max = min;
                    min = temp;
                }
                parent[min] = max;
                rank[max]+=rank[min];
            }
        }
    }


    static int findMax() {
        int j=0;
        for (int i = 0; i < rodA.length; i++) {
            if (rodA[i] != 0) {
                j++;
            }
        }
        int max = j;
        j=0;
        for (int i = 0; i < rodB.length; i++) {
            if (rodB[i] != 0) {
                j++;
            }
        }
        if (max<j){
            max=j;
        }
        j=0;
        for (int i = 0; i < rodC.length; i++) {
            if (rodC[i] != 0) {
                j++;
            }
        }
        if (max<j){
            max=j;
        }
        return max;
    }

    static int[] rodA, rodB, rodC;
    static int[] heights;
    static DSU dsu;
    static int k=0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        dsu = new DSU(100000000);
        rodA = new int[n];
        rodB = new int[n];
        rodC = new int[n];
        heights = new int[n];
        for (int i = 0; i < n; i++)
            heights[i] = -1;
        for (int i = 0; i < n; i++) {
            rodA[i] = n - i;
        }
        hanoiTowers(n, rodA, rodB, rodC);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (heights[i] != -1) {
                res[i] = dsu.rank(heights[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int max = i;
            for (int j = i + 1; j < n; j++)
                if (res[max] < res[j])
                    max = j;
            if (res[max] == 0)
                break;
            int temp = res[max];
            res[max] = res[i];
            res[i] = temp;
            sb.insert(0, res[i] + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
    static void moveDisk(int disk, int[] fromRod, int[] toRod) {
        int fromIndex = findTopIndex(fromRod);
        int toIndex = findTopIndex(toRod) + 1;

        toRod[toIndex] = fromRod[fromIndex];
        fromRod[fromIndex] = 0;
        int max = findMax()-1;
        dsu.set(k);
        if (heights[max] == -1)
            heights[max] = k;
        else
            dsu.union(heights[max], k);
        k++;
    }
    static int findTopIndex(int[] rod) {
        for (int i = rod.length - 1; i >= 0; i--) {
            if (rod[i] != 0) {
                return i;
            }
        }
        return -1;
    }
    static void hanoiTowers(int n, int[] fromRod, int[] toRod, int[] auxRod) {
        int max = findMax()-1;
        if (n == 1) {
            moveDisk(n, fromRod, toRod);
            return;
        }
        hanoiTowers(n - 1, fromRod, auxRod, toRod);
        moveDisk(n, fromRod, toRod);
        hanoiTowers(n - 1, auxRod, toRod, fromRod);
    }
}