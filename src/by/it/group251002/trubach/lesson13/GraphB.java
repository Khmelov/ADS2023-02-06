package by.it.group251002.trubach.lesson13;

import java.util.*;

public class GraphB {
    private static void getGraph(Map<String, ArrayList<String>> graph) {
        Scanner in = new Scanner(System.in);

        boolean isEnd = false;
        while (!isEnd) {
            String vertexOut = in.next();
            if (!graph.containsKey(vertexOut)) {
                graph.put(vertexOut, new ArrayList<>());
            }
            String edge = in.next();
            String vertexIn = in.next();
            if (vertexIn.charAt(vertexIn.length() - 1) == ',') {
                vertexIn = vertexIn.substring(0, vertexIn.length() - 1);
            } else {
                isEnd = true;
            }
            graph.get(vertexOut).add(vertexIn);
        }
    }

    private static boolean isCyclic(String node, Map<String, ArrayList<String>> graph, Set<String> visited) {
        boolean res = false;
        visited.add(node);
        if (graph.get(node) != null) {
            for (String nextNode : graph.get(node)) {
                if (!visited.contains(nextNode)) {
                    res = isCyclic(nextNode, graph, new HashSet<>(visited));
                } else {
                    res = true;
                    return res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        getGraph(graph);
        for (String node :
                graph.keySet()) {
            boolean res = isCyclic(node, graph, new HashSet<>());
            if (res) {
                System.out.println("yes");
                return;
            }
        }
        System.out.println("no");
    }
}
