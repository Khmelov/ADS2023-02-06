package by.it.group251003.skrylev.lesson14;


import java.util.*;

class PointsA {
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
            int rP = find(p);
            int rQ = find(q);

            if (rP == rQ) { return; }

            if (size[rP] < size[rQ]) {
                parent[rP] = rQ;
                size[rQ] += size[rP];
            }
            else {
                parent[rQ] = rP;
                size[rP] += size[rQ];
            }
        }

    }

    private record Point(int x, int y, int z) { }

    private static boolean check(Point p1, Point p2, int distance) {
        return Math.hypot(Math.hypot(p1.x- p2.x, p1.y - p2.y), p1.z - p2.z) <= distance;
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

        DSU dsu = new DSU(pointsAmount);
        for (int i = 0; i < pointsAmount; i++)
            for (int j = i + 1; j < pointsAmount; j++)
                if (check(points[i], points[j], distanceThreshold))
                    dsu.union(i, j);

        List<Integer> sizes = new ArrayList<>();
        boolean[] visit = new boolean[pointsAmount];
        for (int i = 0; i < pointsAmount; i++) {
            int root = dsu.find(i);
            if (!visit[root]){
                visit[root] = true;
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