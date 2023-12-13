package by.it.group251002.baranovskaia.lesson13;

import java.util.*;

public class GraphB {
    private static void getGraph(Map<String, ArrayList<String>> graph) {
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
        }
    }
    private static boolean isGraphCyclic(String node, Map<String, ArrayList<String>> graph, Set<String> visited) {
        boolean res = false;
        visited.add(node);
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    res = isGraphCyclic(next_node, graph, new HashSet<>(visited));
                } else
                {
                    res = true;
                    return res;
                }
            }
        return res;
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
       getGraph(graph);

        boolean res = false;
        for (String node : graph.keySet()) {
            res = isGraphCyclic(node, graph, new HashSet<>());
            if(res)
            {
                System.out.println("yes");
                return;
            }
        }
        System.out.println("no");
    }
}
