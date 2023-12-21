package by.it.group251003.nasevich.lesson14;

import java.util.*;

public class SitesB {
    static int[] Par;
    static int[] Siz;
    static int n = 0;
    static String inp, u, v;
    static Map<String, Integer> mp = new HashMap<>();
    static ArrayList<Integer> l = new ArrayList<>(), r = new ArrayList<>();

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
        inp = sc.nextLine();
        while (!inp.equals("end")) {
            int idx = inp.indexOf('+');
            u = inp.substring(0, idx);
            v = inp.substring(idx + 1);
            if (!mp.containsKey(u)) {
                mp.put(u, n);
                l.add(n++);
            } else
                l.add(mp.get(u));
            if (!mp.containsKey(v)) {
                mp.put(v, n);
                r.add(n++);
            } else
                r.add(mp.get(v));
            inp = sc.nextLine();
        }
        Par = new int[n];
        Siz = new int[n];
        for (int i = 0; i < n; i++) {
            Par[i] = i;
            Siz[i] = 1;
        }
        for (int i = 0; i < l.size(); i++)
            if (getParent(l.get(i)) != getParent(r.get(i)))
                uni(l.get(i), r.get(i));
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (Par[i] == i)
                ans.add(Siz[i]);
        ans.sort(Comparator.reverseOrder());
        System.out.println(n);
        for (Integer an : ans) System.out.print(an + " ");
        l.clear();
        r.clear();
        mp.clear();


    }
}