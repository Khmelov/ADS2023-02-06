package by.it.group251002.urbanovich.lesson13;

import java.lang.reflect.Array;
import java.util.*;

public class GraphB {

    public static boolean sortGraph(Map<String, ArrayList<String>> graph) {
        for (ArrayList<String> array : graph.values()) {
            array.sort(Comparator.reverseOrder());
        }
        Map<String, String> colors = new HashMap<>();
        for (String vert : graph.keySet())
            colors.put(vert, "white");
        String predecessor = null;
        List<String> vertices = new ArrayList<>();
        for (String vert : graph.keySet())
            if (!colors.get(vert).equals("grey"))
                depthSearch(graph, colors, vert, predecessor, vertices);
        return vertices.size() != 0;
    }

    public static void depthSearch(Map<String, ArrayList<String>> graph, Map<String, String> colors, String vertex, String predecessor, List<String> vertices) {
        colors.put(vertex, "grey");
        if (graph.get(vertex) != null)
            for (String neighbour : graph.get(vertex)) {
                if (colors.get(neighbour) != null && colors.get(neighbour).equals("white"))
                    depthSearch(graph, colors, neighbour, vertex, vertices);
                else if (colors.get(neighbour) != null && colors.get(neighbour).equals("grey"))
                    vertices.add(neighbour);
            }
        colors.put(vertex, "black");
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
            boolean hasCycles = sortGraph(graph);
            if (hasCycles)
                System.out.println("yes");
            else
                System.out.println("no");
        }
        scanner.close();
    }
}
