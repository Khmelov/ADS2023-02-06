package by.it.group251002.yanucevich.lesson14;

import java.util.Scanner;

public class StatesHanoiTowerC {
    private static class DSU {
        int[] parent;
        int[] size;

        DSU(int size){
            parent = new int[size];
            this.size = new int[size];
        }
        void make_set(int v) {
            parent[v] = v;
            size[v] = 1;
        }
        int size(int v) {
            return size[find_set(v)];
        }
        int find_set(int v) {
            if (v == parent[v])
                return v;
            return find_set(parent[v]);
        }

        void union_sets(int a, int b) {
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

    static int[] movDisc(int N, int step, int k) {
        int poleA = 0, poleB, poleC;
        poleB = (N % 2 == 0) ? 1 : 2;
        poleC = (N % 2 == 0) ? 2 : 1;

        int[] result = new int[3];
        int t = (step / (1 << (k - 1)) - 1) / 2;

        int[] mapping = (k % 2 != 0) ? new int[]{poleA, poleB, poleC} : new int[]{poleA, poleC, poleB};
        int from = mapping[t % 3];
        int to = mapping[(t + 1) % 3];

        result[from] = -1;
        result[to] = 1;
        return result;
    }

    static int countBits(int num) {
        int count = 0;
        while (num % 2 == 0) {
            count++;
            num /= 2;
        }
        return count;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int max_size = (1 << N) - 1;
        int[] stepHeight = new int[N];
        for (int i = 0; i < N; i++)
            stepHeight[i] = -1;
        DSU dsu = new DSU(max_size);
        int[] heights = new int[3];
        heights[0] = N;
        for (int i = 0; i < max_size; i++) {

            int step = i + 1;
            int[] delta = (step % 2 != 0) ? movDisc(N, step, 1) : movDisc(N, step, countBits(step) + 1);

            for (int j = 0; j < 3; j++)
                heights[j] += delta[j];

            int max = Math.max(heights[0], Math.max(heights[1], heights[2]));
            dsu.make_set(i);

            int maxHeightIndex = max - 1;
            if (stepHeight[maxHeightIndex] == -1)
                stepHeight[maxHeightIndex] = i;
            else
                dsu.union_sets(stepHeight[maxHeightIndex], i);
        }

        int[] sizes = new int[N];
        for (int i = 0; i < N; i++)
            if (stepHeight[i] != -1)
                sizes[i] = dsu.size(stepHeight[i]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int max = i;
            for (int j = i + 1; j < N; j++)
                if (sizes[max] < sizes[j])
                    max = j;
            if (sizes[max] == 0)
                break;
            int temp = sizes[max];
            sizes[max] = sizes[i];
            sizes[i] = temp;
            sb.insert(0, sizes[i] + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}