package by.it.group251004.ryabchikov.lesson14;

import java.util.Scanner;

public class StatesHanoiTowerC {
    public static void main(String[] args) {

        int N;

        try (Scanner scanner = new Scanner(System.in)) {
            N = scanner.nextInt();
        }

        int max_size = (1 << N) - 1;
        int[] steps_heights = new int[N];

        for (int i = 0; i < N; i++) {
            steps_heights[i] = -1;
        }

        DSU dsu = new DSU(max_size);
        int[] heights = new int[3];

        heights[0] = N;
        for (int i = 0; i < max_size; i++) {
            int step = i + 1;
            int[] delta;
            if (step % 2 != 0) {
                delta = carryingOver(N, step, 1);
            }
            else {

                int count = step;
                int countDisk = 0;

                while (count % 2 == 0) {
                    countDisk++;
                    count /= 2;
                }

                delta = carryingOver(N, step, countDisk + 1);
            }

            for (int j = 0; j < 3; j++) {
                heights[j] += delta[j];
            }

            int max = max(heights);
            dsu.make_set(i);
            if (steps_heights[max - 1] == -1) {
                steps_heights[max - 1] = i;
            }
            else {
                dsu.union_sets(steps_heights[max - 1], i);
            }
        }

        int[] sizes = new int[N];
        for (int i = 0; i < N; i++) {
            if (steps_heights[i] != -1) {
                sizes[i] = dsu.size(steps_heights[i]);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {

            int max = i;
            for (int j = i + 1; j < N; j++) {
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

    private static int max(int[] heights) {
        return Math.max(Math.max(heights[0], heights[1]), heights[2]);
    }

    static int[] carryingOver(int N, int step, int k) {

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
                case 0:
                    to = axisY;
                    break;
                case 1:
                    from = axisY;
                    to = axisZ;
                    break;
                case 2:
                    from = axisZ;
                    break;
            }
        else {
            switch (t % 3) {
                case 0:
                    to = axisZ;
                    break;
                case 1:
                    from = axisZ;
                    to = axisY;
                    break;
                case 2:
                    from = axisY;
                    break;
            }
        }

        result[from] = -1;
        result[to] = 1;

        return result;
    }

    private static class DSU {

        private final int[] parent;
        private final int[] size;

        public DSU(int size) {
            parent = new int[size];
            this.size = new int[size];
        }

        public void make_set(int v) {
            parent[v] = v;
            size[v] = 1;
        }

        public int size(int v) {
            return size[find_set(v)];
        }

        public int find_set(int v) {
            if (v == parent[v])
                return v;
            return parent[v] = find_set(parent[v]);
        }

        public void union_sets(int a, int b) {
            a = find_set(a);
            b = find_set(b);
            if (a != b) {
                if (size[a] < size[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                parent[b] = a;
                size[a] += size[b];
            }
        }
    }
}
