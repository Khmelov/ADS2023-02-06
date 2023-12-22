package by.it.group251001.zhidkov.lesson14;

import java.util.*;

class PointsA {
    static class Point {
        int x, y, z;

        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        int distanceTo(Point other) {
            return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
        }
    }

    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int distanceThreshold = scanner.nextInt();
        int numPoints = scanner.nextInt();

        Point[] points = new Point[numPoints];
        DSU dsu = new DSU(numPoints);

        for (int i = 0; i < numPoints; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();

            points[i] = new Point(x, y, z);
        }

        for (int i = 0; i < numPoints; i++) {
            for (int j = i + 1; j < numPoints; j++) {
                int distance = points[i].distanceTo(points[j]);
                if (distance <= distanceThreshold) {
                    dsu.union(i, j);
                }
            }
        }

        Map<Integer, List<Integer>> clusters = new HashMap<>();

        for (int i = 0; i < numPoints; i++) {
            int root = dsu.find(i);
            clusters.computeIfAbsent(root, k -> new ArrayList<>()).add(i + 1);
        }

        for (List<Integer> cluster : clusters.values()) {
            Collections.sort(cluster);
            System.out.print(cluster.size() + " ");
        }
    }
}
