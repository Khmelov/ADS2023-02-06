package by.it.group251002.urbanovich.lesson13;

import java.util.*;

public class GraphC {
    static Integer depth = 0;

    static class mapComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> set1, Map.Entry<String, Integer> set2) {
            int cmp = set1.getValue().compareTo(set2.getValue());
            if (cmp == 0) {
                return set2.getKey().compareTo(set1.getKey());
            }
            return cmp;
        }
    }

    public static void getStrongComponents(Map<String, ArrayList<String>> graph, Map<String, ArrayList<String>> reversedGraph) {
        List<String> visited = new ArrayList<>();
        Map<String, Integer> depthMap = new HashMap<>();
        for (String vert : graph.keySet())
            if (!visited.contains(vert))
                depthSearch(graph, visited, vert, depthMap);

        List<Map.Entry<String, Integer>> array = new ArrayList<>(depthMap.entrySet());
        array.sort(new mapComparator());
        String[] vertices = new String[array.size()];
        for (int i = array.size() - 1; i > -1; i--) {
            vertices[array.size() - 1 - i] = array.get(i).getKey();
        }

        visited = new ArrayList<>();

        for (String node : vertices) {
            if (!visited.contains(node)) {
                List<String> paths = new ArrayList<>();
                getPath(node, reversedGraph, visited, paths);
                paths.sort(Comparator.naturalOrder());
                for (String path : paths) {
                    System.out.print(path);
                }
                System.out.println();
            }
        }
    }
    private static void getPath(String node, Map<String, ArrayList<String>> graph, List<String> visited, List<String> path){
        visited.add(node);
        path.add(node);
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    getPath(next_node, graph, visited, path);
                }
            }
    }
    public static void depthSearch(Map<String, ArrayList<String>> graph, List<String> visited, String vertex, Map<String, Integer> depthMap) {
        visited.add(vertex);
        if (graph.get(vertex) != null)
            for (String neighbour : graph.get(vertex)) {
                if (!visited.contains(neighbour)) {
                    depth++;
                    depthSearch(graph, visited, neighbour, depthMap);
                }
            }
        depthMap.put(vertex, depth++);
    }

    public static Map<String, ArrayList<String>> makeGraph(String[] edges) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            String[] vertices = edges[i].split("->");
            if (!graph.containsKey(vertices[0])) {
                graph.put(vertices[0], new ArrayList<>());
            }
        }
        for (int i = 0; i < edges.length; i++) {
            String[] vert = edges[i].split("->");
            graph.get(vert[0]).add(vert[1]);
        }
        return graph;
    }

    public static Map<String, ArrayList<String>> makeReversedGraph(String[] edges) {
        Map<String, ArrayList<String>> reversedGraph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            String[] vertices = edges[i].split("->");
            if (!reversedGraph.containsKey(vertices[1])) {
                reversedGraph.put(vertices[1], new ArrayList<>());
            }
        }
        for (int i = 0; i < edges.length; i++) {
            String[] vert = edges[i].split("->");
            reversedGraph.get(vert[1]).add(vert[0]);
        }
        return reversedGraph;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] edges = input.split(", ");
            Map<String, ArrayList<String>> graph = makeGraph(edges);
            Map<String, ArrayList<String>> reversedGraph = makeReversedGraph(edges);
            getStrongComponents(graph, reversedGraph);
        }
        scanner.close();
    }
}
