package by.it.group251001.sevamisiul.lesson13;

import java.util.*;

public class GraphB {
    private static boolean dfs(String node, Map<String, ArrayList<String>> graph, Set<String> visited) {
        visited.add(node);
        boolean answ = false;
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    answ = answ || dfs(next_node, graph, new HashSet<>(visited));
                } else
                    answ = true;
            }
        return answ;
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> graph = new HashMap<>();

        Scanner in = new Scanner(System.in);

        boolean isEnd = false;

        while (!isEnd) {
            String a = in.next();
            String s = in.next();
            String b = in.next();
            if (b.charAt(b.length() - 1) == ',') {
                b = b.substring(0, s.length() - 1);
            } else {
                isEnd = true;
            }
            if (!graph.containsKey(a))
                graph.put(a, new ArrayList<>());
            graph.get(a).add(b);
        }

        boolean answ = false;

        for (String node : graph.keySet()) {
            answ = answ || dfs(node, graph, new HashSet<>());

        }

        if (!answ)
            System.out.println("no");
        else
            System.out.println("yes");

        in.close();
    }
}
