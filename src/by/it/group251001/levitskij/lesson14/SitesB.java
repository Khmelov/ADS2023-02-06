package by.it.group251001.levitskij.lesson14;

import java.util.Scanner;
import by.it.group251001.levitskij.lesson09.ListC;
public class SitesB {

    private static class DSU {
        int[] parent;
        int[] rank;

        DSU(int size){
            parent = new int[size];
            rank = new int[size];
        }
        public void make_set(int v) {
            parent[v] = v;
            rank[v] = 0;
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
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListC<String> points = new ListC<>();
        DSU dsu = new DSU(1000);
        String line = scanner.next();
        while(line.compareTo("end")!=0) {
            String[] pair = line.split("\\+");
            int x1 = points.indexOf(pair[0]);
            if (x1 == -1) {
                x1 = points.size();
                points.add(pair[0]);
                dsu.make_set(x1);
            }
            int x2 = points.indexOf(pair[1]);
            if (x2 == -1) {
                x2 = points.size();
                points.add(pair[1]);
                dsu.make_set(x2);
            }
            dsu.union_sets(x1, x2);
            line = scanner.next();
        }
        int max_size = points.size();
        int[] dsu_size_array = new int[max_size];
        for(int i = 0; i < max_size; i++){
            dsu_size_array[dsu.find_set(i)]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < max_size; i++){
            int max = i;
            for(int j = i+1; j < max_size;j++)
                if(dsu_size_array[max]<dsu_size_array[j])
                    max = j;
            if (dsu_size_array[max]==0)
                break;
            int temp = dsu_size_array[max];
            dsu_size_array[max] = dsu_size_array[i];
            dsu_size_array[i] = temp;
            sb.append(dsu_size_array[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}
