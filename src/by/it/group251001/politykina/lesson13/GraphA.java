package by.it.group251001.politykina.lesson13;
import java.util.*;
public class GraphA {

    private static void topologicalSortUnit(String node, Map<String, List<String>> graph, Set<String> visited, Stack<String> stack) {
        visited.add(node);

        if (graph.containsKey(node)) {
            for (String neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    topologicalSortUnit(neighbor, graph, visited, stack);
                }
            }
        }
        stack.push(node);
    }

    public static List<String> topologicalSort(Map<String, List<String>> graph) {
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        for (List<String> array : graph.values()) {
            array.sort(Comparator.reverseOrder());
        }

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                topologicalSortUnit(node, graph, visited, stack);
            }
        }

        List<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    public static void main(String[] args) {
        // Пример использования
        Map<String, List<String>> graph = new HashMap<>();

        Scanner in = new Scanner(System.in);
        boolean isEnd = false;
        while (!isEnd) {
            String a = in.next();
            String s = in.next();
            String b = in.next();
            if (b.charAt(b.length() - 1) != ',') {
                isEnd = true;
            }else {
                b = b.substring(0, b.length() - 1);
            }
            if (!graph.containsKey(a))
                graph.put(a, new ArrayList<>());
            graph.get(a).add(b);
        }

        List<String> sortedNodes = topologicalSort(graph);
        String result = "";
        result = String.join(" ", sortedNodes);
        System.out.println(result);
    }
}
