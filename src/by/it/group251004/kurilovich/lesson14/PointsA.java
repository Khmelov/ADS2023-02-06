package by.it.group251004.kurilovich.lesson14;
import java.util.*;

class PointsA{
    private static class DisjointSetUnion {
        private int[] parent;
        private int[] size;

        private DisjointSetUnion(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int v) {
            if (v != parent[v])
                parent[v] = find(parent[v]);
            return parent[v];
        }

        private void union(int p, int q) {
            int rootX = find(p);
            int rootY = find(q);

            if (rootX == rootY) {
                return;
            }

            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
    }

    private record Point(int x, int y, int z) { }

    private static boolean isWithinDistance(Point p1, Point p2, int distance) {
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

        DisjointSetUnion dsu = new DisjointSetUnion(pointsAmount);
        for (int i = 0; i < pointsAmount; i++)
            for (int j = i + 1; j < pointsAmount; j++)
                if (isWithinDistance(points[i], points[j], distanceThreshold))
                    dsu.union(i, j);

        List<Integer> componentSizes = new ArrayList<>();
        boolean[] visited = new boolean[pointsAmount];
        for (int i = 0; i < pointsAmount; i++) {
            int root = dsu.find(i);
            if (!visited[root]) {
                visited[root] = true;
                componentSizes.add(dsu.size[root]);
            }
        }

        componentSizes.sort(Collections.reverseOrder());
        for (int size : componentSizes) {
            System.out.print(size);
            System.out.print(" ");
        }
    }
}
