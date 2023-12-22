package by.it.group251004.kurilovich.lesson14;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SitesB {
    static class DSU {
        private final Map<String, String> parent;
        private final Map<String, Integer> rank;

        public DSU() {
            parent = new HashMap<>();
            rank = new HashMap<>();
        }

        public String find(String x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                rank.put(x, 0);
                return x;
            }

            if (!parent.get(x).equals(x)) {
                parent.put(x, find(parent.get(x)));
            }

            return parent.get(x);
        }

        public void union(String x, String y) {
            String rootX = find(x);
            String rootY = find(y);

            if (rootX.equals(rootY)) {
                return;
            }

            if (rank.get(rootX) < rank.get(rootY)) {
                parent.put(rootX, rootY);
            } else if (rank.get(rootX) > rank.get(rootY)) {
                parent.put(rootY, rootX);
            } else {
                parent.put(rootX, rootY);
                rank.put(rootY, rank.get(rootY) + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DSU dsu = new DSU();

        String input;
        while (!(input = scanner.nextLine()).equals("end")) {
            String[] sites = input.split("\\+");
            dsu.union(sites[0], sites[1]);
        }

        Map<String, Integer> clusters = new HashMap<>();

        for (String site : dsu.parent.keySet()) {
            String root = dsu.find(site);
            clusters.put(root, clusters.getOrDefault(root, 0) + 1);
        }

        clusters.values()
                .stream()
                .sorted((a, b) -> Integer.compare(b, a))
                .forEachOrdered(value -> System.out.print(value + " "));
    }
}