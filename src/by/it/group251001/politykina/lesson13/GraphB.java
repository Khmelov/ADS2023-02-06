package by.it.group251001.politykina.lesson13;

import java.util.*;

public class GraphB {
    private static boolean hasCycle(Map<String, List<String>> graph, String start, Set<String> visited, Set<String> recursionStack) {
        visited.add(start);
        recursionStack.add(start);

        if (graph.containsKey(start)) {
            for (String neighbor : graph.get(start)) {
                if (!visited.contains(neighbor)) {
                    if (hasCycle(graph, neighbor, visited, recursionStack)) {
                        return true;
                    }
                } else if (recursionStack.contains(neighbor)) {
                    // Вершина уже была посещена в текущем обходе, и это указывает на цикл
                    return true;
                }
            }
        }
        recursionStack.remove(start);
        return false;
    }
    public static boolean hasCycle(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (hasCycle(graph, node, visited, recursionStack)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        Scanner in = new Scanner(System.in);
        boolean isEnd = false;
        while (!isEnd) {
            String a = in.next();
            String s = in.next();
            String b = in.next();
            if (b.charAt(b.length() - 1) != ',') {
                isEnd = true;
            }else{
                b = b.substring(0, b.length() - 1);
            }
            if (!graph.containsKey(a))
                graph.put(a, new ArrayList<>());
            graph.get(a).add(b);
        }

        System.out.println(hasCycle(graph) ? "yes" : "no");
    }
}
