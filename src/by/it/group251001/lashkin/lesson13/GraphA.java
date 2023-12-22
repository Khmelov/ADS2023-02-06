package by.it.group251001.lashkin.lesson13;

import java.util.*;

public class GraphA {

    private static void dfs(String node, Map<String, List<String>> graph, Set<String> visited, Stack<String> stack) {
        visited.add(node);
        if (graph.containsKey(node)) {
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    dfs(next_node, graph, visited, stack);
                }
            }
        }
        stack.push(node);
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        try (Scanner in = new Scanner(System.in)) {
            boolean isEnd = false;

            while (!isEnd) {
                String a = in.next();
                String s = in.next();
                String b = in.next();
                if (b.charAt(b.length() - 1) == ',') {
                    b = b.substring(0, b.length() - 1);
                } else {
                    isEnd = true;
                }
                graph.putIfAbsent(a, new ArrayList<>());
                graph.get(a).add(b);
            }
        }

        for (List<String> list : graph.values()) {
            list.sort(Collections.reverseOrder());
        }

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, graph, visited, stack);
            }
        }

        while (!stack.empty()) {
            System.out.print(stack.pop());
            System.out.print(' ');
        }
    }
}
