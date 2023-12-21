package by.it.group251001.voytov.lesson13;

import java.util.*;
import java.util.Map.Entry;

public class GraphC {
    static Integer currTime = 0;

    private static void dfsTime(String node, Map<String, List<String>> graph, Set<String> visited, Map<String, Integer> time) {
        visited.add(node);
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    currTime++;
                    dfsTime(next_node, graph, visited, time);
                }
            }
        time.put(node, currTime++);
    }

    private static void dfs(String node, Map<String, List<String>> graph, Set<String> visited, List<String> path) {
        visited.add(node);
        path.add(node);
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    dfs(next_node, graph, visited, path);
                }
            }
    }

    static class MapEntryComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            int valueComparison = entry1.getValue().compareTo(entry2.getValue());
            if (valueComparison == 0) {
                return entry2.getKey().compareTo(entry1.getKey());
            }
            return valueComparison;
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, List<String>> graphReversed = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> time = new HashMap<>();

        try (Scanner scanner = new Scanner(System.in)) {

            String input = scanner.nextLine();
            String[] nodes = input.split("\\s*,\\s*");

            for (String node : nodes) {
                String[] vertexes = node.split("\\s*->\\s*");
                if (graph.containsKey(vertexes[0])) {
                    graph.get(vertexes[0]).add(vertexes[1]);
                }
                else {
                    List<String> list = new ArrayList<>();
                    list.add(vertexes[1]);
                    graph.put(vertexes[0], list);
                }

                if (graphReversed.containsKey(vertexes[1])) {
                    graphReversed.get(vertexes[1]).add(vertexes[0]);
                    continue;
                }

                List<String> list = new ArrayList<>();
                list.add(vertexes[0]);
                graphReversed.put(vertexes[1], list);
            }
        }

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfsTime(node, graph, visited, time);
            }
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(time.entrySet());
        entryList.sort(new MapEntryComparator());
        String[] vertices = new String[entryList.size()];
        for (int i = entryList.size() - 1; i > -1; i--) {
            vertices[entryList.size() - 1 - i] = entryList.get(i).getKey();
        }

        visited = new HashSet<>();
        for (String node : vertices) {
            if (!visited.contains(node)) {
                List<String> path = new ArrayList<>();
                dfs(node, graphReversed, visited, path);

                path.sort(CharSequence::compare);

                for (String n : path) {
                    System.out.print(n);
                }
                System.out.println();
            }
        }
    }
}
