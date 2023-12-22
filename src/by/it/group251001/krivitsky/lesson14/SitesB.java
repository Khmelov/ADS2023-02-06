package by.it.group251001.krivitsky.lesson14;

import java.util.*;
public class SitesB {
    private static class DSU {
        int rank[];
        int parent[];
        int max_size;

        public DSU(int amount) {
            rank = new int[amount];
            parent = new int[amount];
            max_size = amount;
            for (int i = 0; i < amount; i++) {
                rank[i] = 0;
                parent[i] = i;
            }
        }

        public int getParent(int index) {
            if (index == parent[index]) {
                return index;
            }
            return getParent(parent[index]);
        }

        void unite(int a, int b) {
            a = getParent(a);
            b = getParent(b);
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
        public void print(int max_size) {
            int[] dsu_size_array = new int[max_size];
            for (int i = 0; i < max_size; i++) {
                dsu_size_array[getParent(i)]++;
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
    public static void main(String args[]){
        List<String> sites = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String line = scan.next();
        DSU dsu = new DSU(1000);
        int index1;
        int index2;
        while(line.compareTo("end")!=0) {
            String[] pair = line.split("\\+");

            if (sites.contains(pair[0])){
                index1 = sites.indexOf(pair[0]);
            }
            else{
                index1 = sites.size();
                sites.add(pair[0]);
            }

            if (sites.contains(pair[1])){
                index2 = sites.indexOf(pair[1]);
            }
            else{
                index2 = sites.size();
                sites.add(pair[1]);
            }

            dsu.unite(index1, index2);
            line = scan.next();
        }
        dsu.print(sites.size());
    }
}
