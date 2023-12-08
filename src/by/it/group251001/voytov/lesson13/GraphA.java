package by.it.group251001.voytov.lesson13;

import java.util.*;

public class GraphA {

    public static void main(String[] args) {

        Map<String, List<String>> graph = new HashMap<>();

        try (Scanner scanner = new Scanner(System.in)) {

            String input = scanner.nextLine();
            String[] nodes = input.split("\\s*,\\s*");

            for (String node : nodes) {
                String[] vertexes = node.split("\\s*->\\s*");
                if (graph.containsKey(vertexes[0])) {
                    graph.get(vertexes[0]).add(vertexes[1]);
                    continue;
                }

                List<String> list = new ArrayList<>();
                list.add(vertexes[1]);
                graph.put(vertexes[0], list);
            }
        }

        for (List<String> list : graph.values()) {
            list.sort(Comparator.reverseOrder());
        }

        Stack<String> stack = topologicalSort(graph);

        while (!stack.empty()) {
            System.out.printf("%s ", stack.pop());
        }

    }

    public static Stack<String> topologicalSort(Map<String, List<String>> graph) {

        Stack<String> result = new Stack<>();
        Set<String> visited = new HashSet<>();
        for (String vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                topologicalSort(graph, visited, vertex, result);
            }
        }

        return result;
    }

    private static void topologicalSort(Map<String, List<String>> graph, Set<String> visited, String curr, Stack<String> result) {

        visited.add(curr);

        if (graph.get(curr) != null) {
            for (String next : graph.get(curr)) {
                if (!visited.contains(next)) {
                    topologicalSort(graph, visited, next, result);
                }
            }
        }

        result.push(curr);
    }
}
