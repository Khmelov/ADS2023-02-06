package by.it.group251002.punko.lesson14;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class SitesB {
    static class DSU {
        Map<String, String> parent;
        Map<String, Integer> rank;

        public DSU() {
            parent = new HashMap<>();
            rank = new HashMap<>();
        }

        public void makeSet(String x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                rank.put(x, 1);
            }
        }

        public String find(String x) {
            if (!parent.get(x).equals(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(String x, String y) {
            String rootX = find(x);
            String rootY = find(y);

            if (!rootX.equals(rootY)) {
                if (rank.get(rootX) < rank.get(rootY)) {
                    String temp = rootX;
                    rootX = rootY;
                    rootY = temp;
                }
                parent.put(rootY, rootX);
                rank.put(rootX, rank.get(rootX) + rank.get(rootY));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DSU dsu = new DSU();

        String input;
        while (!(input = scanner.nextLine()).equals("end")) {
            String[] sites = input.split("\\+");
            dsu.makeSet(sites[0]);
            dsu.makeSet(sites[1]);
            dsu.union(sites[0], sites[1]);
        }

        Map<String, Integer> clusterSizes = new HashMap<>();
        for (String site : dsu.parent.keySet()) {
            String root = dsu.find(site);
            clusterSizes.put(root, clusterSizes.getOrDefault(root, 0) + 1);
        }

        clusterSizes.values().stream()
                .sorted((a, b) -> b - a)
                .forEach(size -> System.out.print(size + " "));
    }
}