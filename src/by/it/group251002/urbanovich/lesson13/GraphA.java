package by.it.group251002.urbanovich.lesson13;

import java.lang.reflect.Array;
import java.util.*;

public class GraphA {

    public static List<String> sortGraph(Map<String, ArrayList<String>> graph) {
        Stack<String> stack = new Stack<>();
        List<String> visited = new ArrayList<>();
        List<String> sorted = new ArrayList<>();
        for (ArrayList<String> array : graph.values()) {
            array.sort(Comparator.reverseOrder());
        }
        for (String vert : graph.keySet())
            if (!visited.contains(vert))
                depthSearch(graph, visited, vert, stack);
        while (!stack.empty())
            sorted.add(stack.pop());
        return sorted;
    }

    public static void depthSearch(Map<String, ArrayList<String>> graph, List<String> visited, String vertex, Stack<String> stack) {
        visited.add(vertex);
        if (graph.get(vertex) != null)
            for (String neighbour : graph.get(vertex)) {
                if (!visited.contains(neighbour))
                    depthSearch(graph, visited, neighbour, stack);
            }
        stack.push(vertex);
    }

    public static Map<String, ArrayList<String>> makeGraph(String[] edges) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            String[] vertices = edges[i].split(" -> ");
            if (!graph.containsKey(vertices[0])) {
                graph.put(vertices[0], new ArrayList<>());
            }
        }
        for (int i = 0; i < edges.length; i++) {
            String[] vert = edges[i].split(" -> ");
            graph.get(vert[0]).add(vert[1]);
        }
        return graph;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] edges = input.split(", ");
            Map<String, ArrayList<String>> graph = makeGraph(edges);
            List<String> sorted = sortGraph(graph);
            for (String vert : sorted)
                System.out.print(vert + " ");
            System.out.println();
        }
        scanner.close();
    }
}
