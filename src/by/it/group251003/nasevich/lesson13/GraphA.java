package by.it.group251003.nasevich.lesson13;

import java.util.*;

public class GraphA {
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

    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }

    private static void tSort(String node, Map<String, ArrayList<String>> graph, Set<String> visited, Stack<String> stack) {
        visited.add(node);
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    tSort(next_node, graph, visited, stack);
                }
            }
        stack.push(node);
    }

    public static void main(String[] str) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        getGraph(graph);

        for (ArrayList<String> arr :
                graph.values()) {
            Collections.sort(arr,new StringComparator());
        }

        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                tSort(node, graph, visited, stack);
            }
        }

        while (!stack.empty()) {
            System.out.print(stack.pop());
            System.out.print(' ');
        }
    }
}
