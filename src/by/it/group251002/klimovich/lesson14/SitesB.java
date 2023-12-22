package by.it.group251002.klimovich.lesson14;

import java.util.*;
import by.it.group251002.klimovich.lesson09.ListC;
public class SitesB {

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
        Scanner scanner = new Scanner(System.in);
        DSU dsu = new DSU(1000);
        String line = scanner.next();
        ListC<String> points = new ListC<>();
        while(line.compareTo("end")!=0) {
            String[] pair = line.split("\\+");
            int x1 = points.indexOf(pair[0]);
            if (x1 == -1) {
                x1 = points.size();
                points.add(pair[0]);
                dsu.set(x1);
            }
            int x2 = points.indexOf(pair[1]);
            if (x2 == -1) {
                x2 = points.size();
                points.add(pair[1]);
                dsu.set(x2);
            }
            dsu.union(x1, x2);
            line = scanner.next();
        }
        int max_size = points.size();
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
