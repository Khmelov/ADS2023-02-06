package by.it.group251001.lashkin.lesson13;

import java.util.*;

public class GraphB {
    private static boolean dfs(String node, Map<String, List<String>> graph, Set<String> visited, Set<String> recursionStack) {
        if (recursionStack.contains(node)) {
            return true;
        }

        if (!visited.contains(node)) {
            visited.add(node);
            recursionStack.add(node);

            if (graph.containsKey(node)) {
                for (String nextNode : graph.get(node)) {
                    if (dfs(nextNode, graph, visited, recursionStack)) {
                        return true;
                    }
                }
            }

            recursionStack.remove(node);
        }

        return false;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();

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

        boolean hasCycle = false;
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (dfs(node, graph, visited, recursionStack)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        if (hasCycle) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
