package by.it.group251002.shpitalenkov.lesson14;

import java.util.*;

public class SitesB {
    private static class DSU {
        private int[] parent;
        private int[] size;
        private DSU(int n){
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        private int find(int v){
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> indexes = new HashMap<>();
        int curr_index = 0;

        List<String> in = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine()).equals("end")) {
            in.add(line);
            String[] sites = line.split("\\+");
            for (String site : sites)
                if (!indexes.containsKey(site))
                    indexes.put(site, curr_index++);
        }

        DSU dsu = new DSU(curr_index);
        for (String lineInput : in) {
            String[] sites = lineInput.split("\\+");
            dsu.union(indexes.get(sites[0]), indexes.get(sites[1]));
        }

        List<Integer> sizes = new ArrayList<>();
        boolean[] visited = new boolean[curr_index];
        for (int i = 0; i < curr_index; i++) {
            int root = dsu.find(i);
            if (!visited[root]){
                visited[root] = true;
                sizes.add(dsu.size[root]);
            }
        }

        sizes.sort(Collections.reverseOrder());
        for (int size : sizes) {
            System.out.print(size);
            System.out.print(" ");
        }
    }
}