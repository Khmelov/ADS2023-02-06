package by.it.group251002.klimovich.lesson14;

import java.util.*;

public class PointsA {

    private static class DSU {
        int[] parent;
        int[] rank;

        DSU(int size){
            parent = new int[size];
            rank = new int[size];
        }
        public void set(int v) {
            parent[v] = v;
            rank[v] = 0;
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
                if (rank[max] == rank[min])
                    rank[max]++;
            }
        }
    }
    public static void main(String[] args) {
        int k = 0;
        Scanner scanner = new Scanner(System.in);
        int max_dist = scanner.nextInt();
        int max_size = scanner.nextInt();
        int[][] points = new int[max_size][3];
        DSU dsu = new DSU(max_size);
        for(int i = 0; i < max_size; i++){
            for(int j = 0; j < 3; j++)
                points[k][j] = scanner.nextInt();
            dsu.set(k);
            k++;
        }
        for(int i = 0; i < max_size; i++)
            for(int j = i+1; j < max_size; j++)
                if(Math.hypot(Math.hypot(points[i][0] - points[j][0], points[i][1] - points[j][1]), points[i][2] - points[j][2])<=max_dist){
                    dsu.union(i,j);
                }
        int[] SizeArr = new int[max_size];
        for(int i = 0; i < max_size; i++){
            SizeArr[dsu.findSet(i)]++;
        }
        for(int i = 0; i < max_size; i++){
            for(int j = 0; j < max_size-i-1; j++){
                if (SizeArr[j]<=SizeArr[j+1]){
                    int temp=SizeArr[j];
                    SizeArr[j]=SizeArr[j+1];
                    SizeArr[j+1]=temp;
                }
            }
        };
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < max_size; i++){
            if (SizeArr[i]==0)
                break;
            sb.append(SizeArr[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}
