package by.it.group251001.mikhei.lesson13;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class GraphA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> g = Arrays.stream(scanner.nextLine().split(", "))
                .map(s -> s.split(" -> "))
                .collect(
                        groupingBy(
                                (a -> a[0]),
                                mapping(a -> a[1], toList())
                        )
                );

        TreeMap<String, Integer> count = new TreeMap<>();
        for (var x : g.entrySet()) {
            count.put(x.getKey(), count.getOrDefault(x.getKey(), 0));

            for (String to : x.getValue()) {
                count.put(to, count.getOrDefault(to, 0) + 1);
            }
        }

        PriorityQueue<String> q = new PriorityQueue<>();

        for (var x : count.entrySet()) {
            if (x.getValue().equals(0)) q.add(x.getKey());
        }

        List<String> res = new ArrayList<>();
        while (!q.isEmpty()) {
            String v = q.poll();

            count.remove(v);
            for (var to : g.getOrDefault(v, new ArrayList<>())) {
                if (!count.containsKey(to)) continue;

                count.put(to, count.get(to) - 1);
                if (count.get(to).equals(0)) {
                    q.add(to);
                }
            }

            res.add(v);
        }

        for (String v : res) {
            System.out.print(v);
            System.out.print(" ");
        }
        System.out.println();
    }
}
