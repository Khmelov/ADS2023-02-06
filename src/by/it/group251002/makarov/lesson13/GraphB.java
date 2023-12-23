package by.it.group251002.makarov.lesson13;

import java.util.*;

public class GraphB {
    private Map<String, List<String>> adjacencyList;

    public GraphB() {
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(String u, String v) {
        adjacencyList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    }

    private boolean hasCycleUtil(String v, Set<String> visited, Set<String> recursionStack) {
        visited.add(v);
        recursionStack.add(v);

        List<String> neighbors = adjacencyList.getOrDefault(v, Collections.emptyList());
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                if (hasCycleUtil(neighbor, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbor)) {
                return true; // Найден цикл
            }
        }

        recursionStack.remove(v);
        return false;
    }

    public boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        for (String vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                if (hasCycleUtil(vertex, visited, recursionStack)) {
                    return true; // Есть цикл
                }
            }
        }

        return false; // Нет цикла
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        GraphB graph = new GraphB();

        // Разбиваем входную строку на ребра и строим граф
        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] vertices = edge.split(" -> ");
            graph.addEdge(vertices[0], vertices[1]);
        }

        // Проверяем наличие циклов и выводим результат
        if (graph.hasCycle()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
