package by.it.group251003.Stelmakh.lesson14;

import java.util.*;
import java.util.stream.Collectors;

public class PointsA {

    public static void main(String[] args) {

        List<Set<Point>> dsu = new ArrayList<>();
        int distance, dotsAmount;

        try (Scanner scanner = new Scanner(System.in)) {

            distance = scanner.nextInt();
            dotsAmount = scanner.nextInt();

            for (int i = 0; i < dotsAmount; i++) {
                Point point = new Point(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                Set<Point> set = new HashSet<>();
                set.add(point);
                dsu.add(set);
            }
        }

        for (int i = 0; i < dsu.size(); i++) {
            for (Set<Point> set : dsu) {
                boolean union = false;
                ok:
                if (dsu.get(i) != set) {
                    for (Point p1 : dsu.get(i)) {
                        for (Point p2 : set) {
                            if (p1 != p2 && checkDistance(p1, p2, distance)) {
                                union = true;
                                break ok;
                            }
                        }
                    }
                }

                if (union) {
                    dsu.get(i).addAll(set);
                    set.clear();
                    i = 0;
                }
            }
        }

        dsu.removeIf(Set::isEmpty);
        String output = dsu.stream()
                .map(Set::size)
                .sorted((n, m) -> m - n)
                .map(String::valueOf)
                .collect(Collectors.joining(" "))
                .trim();

        System.out.println(output);
    }

    private static boolean checkDistance(Point p1, Point p2, int distance) {
        return Math.hypot(Math.hypot(p1.getX() - p2.getX(), p1.getY() - p2.getY()), p1.getZ() - p2.getZ()) <= distance;
    }

    private static class Point {

        private final int x;
        private final int y;
        private final int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }
    }
}
