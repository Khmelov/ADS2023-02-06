package by.it.group251002.shpitalenkov.lesson14;

import java.util.*;

public class StatesHanoiTowerC {
    private static class DSU {
        private final int[] parent;
        private final int[] size;
        private DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        private int find(int v) {
            if (v != parent[v])
                parent[v] = find(parent[v]);
            return parent[v];
        }
        private void union (int p, int q){
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) { return; }

            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }
    }
    private static int max(int[] heights) {
        return Math.max(Math.max(heights[0], heights[1]), heights[2]);
    }
    private static int[] carryingOver(int N, int step, int k) {
        int t, axisY, axisZ;
        if (N % 2 == 0) {
            axisY = 1;
            axisZ = 2;
        } else {
            axisY = 2;
            axisZ = 1;
        }

        int[] result = new int[3];
        t = (step / (1 << (k - 1)) - 1) / 2;
        int from = 0, to = 0;
        if (k % 2 != 0)
            switch (t % 3) {
                case 0 -> to = axisY;
                case 1 -> {
                    from = axisY;
                    to = axisZ;
                }
                case 2 -> from = axisZ;
            }
        else {
            switch (t % 3) {
                case 0 -> to = axisZ;
                case 1 -> {
                    from = axisZ;
                    to = axisY;
                }
                case 2 -> from = axisY;
            }
        }

        result[from] = -1;
        result[to] = 1;
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int max_size = (1 << n) - 1;
        int[] steps_heights = new int[n];
        Arrays.fill(steps_heights, -1);

        DSU dsu = new DSU(max_size);
        int[] heights = new int[3];

        heights[0] = n;
        for (int i = 0; i < max_size; i++) {
            int step = i + 1;
            int[] delta;
            if (step % 2 != 0) {
                delta = carryingOver(n, step, 1);
            }
            else {
                int count = step;
                int countDisk = 0;

                while (count % 2 == 0) {
                    countDisk++;
                    count /= 2;
                }
                delta = carryingOver(n, step, countDisk + 1);
            }
            for (int j = 0; j < 3; j++)
                heights[j] += delta[j];

            int max = max(heights);
            if (steps_heights[max - 1] == -1)
                steps_heights[max - 1] = i;
            else
                dsu.union(steps_heights[max - 1], i);
        }

        int[] sizes = new int[n];
        for (int i = 0; i < n; i++)
            if (steps_heights[i] != -1)
                sizes[i] = dsu.size[dsu.find(steps_heights[i])];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {

            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (sizes[max] < sizes[j]) {
                    max = j;
                }
            }

            if (sizes[max] == 0) {
                break;
            }

            int temp = sizes[max];
            sizes[max] = sizes[i];
            sizes[i] = temp;
            sb.insert(0, sizes[i] + " ");
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}