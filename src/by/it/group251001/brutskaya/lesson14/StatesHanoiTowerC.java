package by.it.group251001.brutskaya.lesson14;

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
        int size(int v){
            return size[find_set(v)];
        }

        int find_set(int v) {
            if (v == parent[v])
                return v;
            return parent[v] = find_set(parent[v]);
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
                size[a]+=size[b];
            }
        }
    }

    static int max(int[] heights){
        int result = (heights[0]>heights[1])?heights[0]:heights[1];
        return (result>heights[2])?result:heights[2];
    }

    static int[] carryingOver(int N, int step, int k){
        int t, axisX, axisY,axisZ;
        if (N % 2 == 0){
            axisX = 0;
            axisY = 1;
            axisZ = 2;
        } else {
            axisX = 0;
            axisY = 2;
            axisZ = 1;
        }
        int [] result = new int[3];
        t = (step / (1 << (k-1)) -1) / 2;
        int from = 0, to = 0;
        if (k % 2 != 0)
            switch (t % 3) {
                case 0:
                    from = axisX;
                    to = axisY;
                    break;
                case 1:
                    from = axisY;
                    to = axisZ;
                    break;
                case 2:
                    from = axisZ;
                    to = axisX;
                    break;
            }
        else
            switch (t % 3) {
                case 0:
                    from = axisX;
                    to = axisZ;
                    break;
                case 1:
                    from = axisZ;
                    to = axisY;
                    break;
                case 2:
                    from = axisY;
                    to = axisX;
                    break;
            }
        result[from] = -1;
        result[to] = 1;
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int max_size = (1 << N) - 1;
        int[] steps_heights = new int[N];
        for(int i = 0; i < N; i++)
            steps_heights[i] = -1;
        DSU dsu = new DSU(max_size);
        int[] heights = new int[3];
        heights[0] = N;
        for(int i = 0; i < max_size; i++){
            int step = i+1;
            int[] delta;
            if (step % 2 !=0) {
                delta = carryingOver(N, step, 1);
            }else{
                int count = step;
                int countDisk = 0;
                while (count % 2 == 0){
                    countDisk++;
                    count/=2;
                }
                delta = carryingOver(N,step, countDisk+1);
            }
            for(int j = 0; j < 3; j++)
                heights[j]+=delta[j];
            int max = max(heights);
            dsu.make_set(i);
            if (steps_heights[max-1] == -1)
                steps_heights[max-1] = i;
            else
                dsu.union_sets(steps_heights[max-1], i);
        }

        int[] sizes = new int[N];
        for(int i = 0; i < N; i++)
            if (steps_heights[i]!=-1)
                sizes[i] = dsu.size(steps_heights[i]);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            int max = i;
            for(int j = i+1; j < N;j++)
                if(sizes[max]<sizes[j])
                    max = j;
            if (sizes[max]==0)
                break;
            int temp = sizes[max];
            sizes[max] = sizes[i];
            sizes[i] = temp;
            sb.insert(0,sizes[i]+" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}
