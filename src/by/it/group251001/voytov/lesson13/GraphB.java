package by.it.group251001.voytov.lesson13;

import java.util.*;

public class GraphB {

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

        if (checkLoop(graph)) {
            System.out.println("yes");
        }
        else {
            System.out.println("no");
        }

    }

    public static boolean checkLoop(Map<String, List<String>> graph) {

        boolean loop = false;
        for (String vertex : graph.keySet()) {
            loop = loop || topologicalSort(graph, new HashSet<>(), vertex);
        }

        return loop;
    }

    private static boolean topologicalSort(Map<String, List<String>> graph, Set<String> visited, String curr) {

        visited.add(curr);
        boolean loop = false;

        if (graph.get(curr) != null) {
            for (String next : graph.get(curr)) {
                if (!visited.contains(next)) {
                    loop = loop || topologicalSort(graph, new HashSet<>(visited), next);
                }
                else {
                    loop = true;
                }
            }
        }

        return loop;
    }
}
