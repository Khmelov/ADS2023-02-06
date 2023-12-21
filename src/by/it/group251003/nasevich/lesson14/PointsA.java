package by.it.group251003.nasevich.lesson14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;



public class PointsA {
    static int[] Par;
    static int[] Siz;
    static int maxDst, n;

    private static class point {
        public int x, y, z;

        public point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static point[] Points;

    private static boolean seen(point a, point b) {
        return Math.hypot(Math.hypot(a.x - b.x, a.y - b.y), a.z - b.z) <= maxDst;
    }

    private static int getParent(int x) {
        return (Par[x] == x) ? x : (Par[x] = getParent(Par[x]));
    }

    private static void uni(int x, int y) {
        int parX = getParent(x), parY = getParent(y);
        if (Siz[parX] < Siz[parY]) {
            Par[parX] = getParent(parY);
            Siz[parY] += Siz[parX];
        } else {
            Par[parY] = getParent(parX);
            Siz[parX] += Siz[parY];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        maxDst = sc.nextInt();
        n = sc.nextInt();
        Par = new int[n];
        Siz = new int[n];
        Points = new point[n];
        for (int i = 0; i < n; i++) {
            Par[i] = i;
            Siz[i] = 1;
            Points[i] = new point(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (getParent(i) != getParent(j) && seen(Points[i], Points[j]))
                    uni(i, j);
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (Par[i] == i)
                ans.add(Siz[i]);
        ans.sort(Comparator.reverseOrder());
        for (Integer an : ans) System.out.print(an + " ");
    }
}
