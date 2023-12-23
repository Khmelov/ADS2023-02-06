package by.it.group251001.mikhei.lesson13;

import java.util.*;
import java.util.function.Function;

import static java.util.Comparator.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class GraphC {
    public static void main(String[] args) {
        new GraphC().solve();
    }

    Map<String, List<String>> g, invG = new HashMap<>();
    Set<String> used = new HashSet<>();
    List<String> order = new ArrayList<>();
    Map<String, Integer> c = new HashMap<>();

    void dfs1(String v) {
        used.add(v);

        for (var to : g.get(v)) {
            if (!used.contains(to)) dfs1(to);
        }

        order.add(v);
    }

    void dfs2(String v, int ind) {
        c.put(v, ind);

        for (var to : invG.get(v)) {
            if (!c.containsKey(to)) dfs2(to, ind);
        }
    }

    void solve() {
        Scanner scanner = new Scanner(System.in);

        g = Arrays.stream(scanner.nextLine().split(", "))
                .map(s -> s.split("->"))
                .collect(
                        groupingBy(
                                (a -> a[0]),
                                mapping(a -> a[1], toList())
                        )
                );

        for (var x : g.entrySet()) {
            if (!invG.containsKey(x.getKey())) invG.put(x.getKey(), new ArrayList<>());

            for (var to : x.getValue()) {
                if (!invG.containsKey(to)) invG.put(to, new ArrayList<>());

                invG.get(to).add(x.getKey());
            }
        }

        for (var x : invG.keySet()) {
            if (!g.containsKey(x)) g.put(x, new ArrayList<>());
        }

        for (var v : g.keySet()) {
            if (!used.contains(v)) dfs1(v);
        }

        Collections.reverse(order);

        int cnt = 0;
        for (var v : order) {
            if (!c.containsKey(v)) dfs2(v, cnt++);
        }

        List<String>[] comps = new ArrayList[cnt];
        for (int i = 0; i < cnt; i++) {
            comps[i] = new ArrayList<>();
        }

        for (var x : c.entrySet()) {
            comps[x.getValue()].add(x.getKey());
        }

        for (var x : comps) {
            x.sort(comparing(identity()));
        }

        for (var x : comps) {
            for (String s : x) {
                System.out.print(s);
            }
            System.out.println();
        }
    }
}
