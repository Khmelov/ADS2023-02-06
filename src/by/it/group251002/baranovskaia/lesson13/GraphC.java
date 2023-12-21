package by.it.group251002.baranovskaia.lesson13;

import java.util.*;
import java.util.Map.Entry;

public class GraphC {
    static Integer depth = 0;

    private static void getGraph(Map<String, ArrayList<String>> graph, Map<String, ArrayList<String>> graphRev) {
        Scanner scanner = new Scanner(System.in);
        boolean isEnd = false;

        while (!isEnd) {
            String vertexOut = scanner.next();
            if (!graph.containsKey(vertexOut))
                graph.put(vertexOut, new ArrayList<>());

            String edge = scanner.next();

            String vertexIn = scanner.next();
            if (vertexIn.charAt(vertexIn.length() - 1) == ',') {
                vertexIn = vertexIn.substring(0, vertexIn.length() - 1);
            } else {
                isEnd = true;
            }
            graph.get(vertexOut).add(vertexIn);
            if (!graphRev.containsKey(vertexIn))
                graphRev.put(vertexIn, new ArrayList<>());
            graphRev.get(vertexIn).add(vertexOut);
        }
    }
    private static void getDepth(String node, Map<String, ArrayList<String>> graph, Set<String> visited, Map<String, Integer> depthMap) {
        visited.add(node);
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    depth++;
                    getDepth(next_node, graph, visited, depthMap);
                }
            }
        depthMap.put(node, depth++);
    }

    private static void getPath(String node, Map<String, ArrayList<String>> graph, Set<String> visited, List<String> path) {
        visited.add(node);
        path.add(node);
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    getPath(next_node, graph, visited, path);
                }
            }
    }

    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    static class MapEntryComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Entry<String, Integer> set1, Entry<String, Integer> set2) {
            int valueComparison = set1.getValue().compareTo(set2.getValue());
            if (valueComparison == 0) {
                return set2.getKey().compareTo(set1.getKey());
            }
            return valueComparison;
        }
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        Map<String, ArrayList<String>> graphReversed = new HashMap<>();
        getGraph(graph, graphReversed);

        Set<String> visited = new HashSet<>();
        Map<String, Integer> depthMap = new HashMap<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                getDepth(node, graph, visited, depthMap);
            }
        }

        List<Map.Entry<String, Integer>> arr = new ArrayList<>(depthMap.entrySet());
        arr.sort(new MapEntryComparator());
        String[] vertices = new String[arr.size()];
        for (int i = arr.size() - 1; i > -1; i--) {
            vertices[arr.size() - 1 - i] = arr.get(i).getKey();
        }

        visited = new HashSet<>();
        for (String node : vertices) {
            if (!visited.contains(node)) {
                List<String> paths = new ArrayList<>();
                getPath(node, graphReversed, visited, paths);

                paths.sort(new StringComparator());

                for (String path : paths) {
                    System.out.print(path);
                }
                System.out.println();
            }
        }
    }
}
