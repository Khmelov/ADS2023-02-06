package by.it.group251001.mikhei.lesson13;

import java.util.*;

import static java.util.stream.Collectors.*;

public class GraphB {

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

        Map<String, Integer> c = new HashMap<>();

        for (var x : g.entrySet()) {
            c.put(x.getKey(), 0);

            for (var to : x.getValue()) {
                c.put(to, 0);
            }
        }

        for (String v : c.keySet()) {
            if(c.get(v).equals(2)) continue;

            if (dfs(v, c, g)) {
                System.out.println("yes");
                return;
            }
        }

        System.out.println("no");
    }

    static boolean dfs(String v, Map<String, Integer> c, Map<String, List<String>> g) {
        c.put(v, 1);

        for(var to: g.getOrDefault(v, new ArrayList<>())){
            if(c.get(to).equals(0)){
                if(dfs(to, c, g)) return true;
            }else if(c.get(to).equals(1)){
                return true;
            }
        }

        c.put(v, 2);
        return false;
    }
}
