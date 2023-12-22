package by.it.group251003.Trukhan.lesson14;

import java.util.*;
import java.util.*;

import java.util.*;

class PointsA {
    private static class UnionFind {
        private int[] parent;
        private int[] size;

        private UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        private int find(int v) {
            return (v == parent[v]) ? v : (parent[v] = find(parent[v]));
        }

        private void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) {
                return;
            }

            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }
    }

    private static record Point(int x, int y, int z) { }

    private static boolean areWithinDistance(Point p1, Point p2, int distance) {
        return Math.hypot(Math.hypot(p1.x - p2.x, p1.y - p2.y), p1.z - p2.z) <= distance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int distanceThreshold = scanner.nextInt();
        int pointsAmount = scanner.nextInt();

        Point[] points = new Point[pointsAmount];
        for (int i = 0; i < pointsAmount; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            points[i] = new Point(x, y, z);
        }

        UnionFind uf = new UnionFind(pointsAmount);
        for (int i = 0; i < pointsAmount; i++) {
            for (int j = i + 1; j < pointsAmount; j++) {
                if (areWithinDistance(points[i], points[j], distanceThreshold)) {
                    uf.union(i, j);
                }
            }
        }

        List<Integer> componentSizes = new ArrayList<>();
        boolean[] visited = new boolean[pointsAmount];
        for (int i = 0; i < pointsAmount; i++) {
            int root = uf.find(i);
            if (!visited[root]) {
                visited[root] = true;
                componentSizes.add(uf.size[root]);
            }
        }

        componentSizes.sort(Collections.reverseOrder());
        componentSizes.forEach(size -> System.out.print(size + " "));
    }
}