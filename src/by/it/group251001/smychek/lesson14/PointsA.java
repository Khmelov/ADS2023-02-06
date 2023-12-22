package by.it.group251001.smychek.lesson14;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PointsA{
    public static class DSU{
        int[] parent;

        void inicialize_arr(int n){
            parent = new int[n];
        }

        void make_set (int v) {
            parent[v] = v;
        }

        int find_set(int v) {
            if (v == parent[v])
                return v;
            parent[v] = find_set(parent[v]);
            return parent[v];
        }

        void union_sets(int a, int b) {
            a = find_set(a);
            b = find_set(b);
            if (a != b)
                parent[b] = a;
        }
    }

    public static int dist(int[] f, int[]s){
        return (f[0] - s[0])*(f[0] - s[0]) + (f[1] - s[1])*(f[1] - s[1]) + (f[2] - s[2])*(f[2] - s[2]);
    }

    public static boolean less_dist(int d, int[] f, int[] s){
        return dist(f, s) <= d * d;
    }

    public static void main(String[] args) {
        int n;
        int d;
        Scanner in = new Scanner(System.in);
        DSU myDSU = new DSU();
        d = in.nextInt();
        n = in.nextInt();
        myDSU.inicialize_arr(n);
        int[][] pts = new int[n][3];
        for(int i = 0; i < n; ++i){
            pts[i][0] = in.nextInt();
            pts[i][1] = in.nextInt();
            pts[i][2] = in.nextInt();
            myDSU.make_set(i);
        }

        for(int i = 0; i < n - 1; ++i)
            for(int j = i + 1; j < n; ++j)
                if(less_dist(d, pts[i], pts[j]))
                    myDSU.union_sets(i, j);
        Map<Integer, Integer> cnt = new HashMap<>();
        int free = 0;
        int[] ans = new int[n];
        for(int i = 0; i < n; ++i) {
            int p = myDSU.find_set(i);
            if(cnt.containsKey(p))
                ans[cnt.get(p)]++;
            else {
                ans[free] = 1;
                cnt.put(p, free++);
            }
        }
        System.out.println();
        ans = Arrays.copyOf(ans, cnt.size());
        Arrays.sort(ans);
        for(int i = ans.length - 1; i >= 0;) {
            System.out.print(ans[i--]);
            System.out.print(' ');
        }
    }
}
