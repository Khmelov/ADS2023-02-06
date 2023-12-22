package by.it.group251002.makarov.lesson13;

import java.util.*;

public class GraphC {
    private Map<String, List<String>> adjacencyList;

    public GraphC() {
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(String u, String v) {
        adjacencyList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    }

    private void fillOrder(String v, Set<String> visited, Stack<String> stack) {
        visited.add(v);

        List<String> neighbors = adjacencyList.getOrDefault(v, Collections.emptyList());
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                fillOrder(neighbor, visited, stack);
            }
        }

        stack.push(v);
    }

    private void DFSUtil(String v, Set<String> visited, Set<String> component) {
        visited.add(v);
        component.add(v);

        List<String> neighbors = adjacencyList.getOrDefault(v, Collections.emptyList());
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited, component);
            }
        }
    }

    private GraphC getTranspose() {
        GraphC transposedGraph = new GraphC();
        for (String vertex : adjacencyList.keySet()) {
            List<String> neighbors = adjacencyList.getOrDefault(vertex, Collections.emptyList());
            for (String neighbor : neighbors) {
                transposedGraph.addEdge(neighbor, vertex);
            }
        }
        return transposedGraph;
    }

    private void printStronglyConnectedComponents() {
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        // Заполняем стек в порядке завершения вершин
        for (String vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                fillOrder(vertex, visited, stack);
            }
        }

        // Получаем транспонированный граф
        GraphC transposedGraph = getTranspose();

        visited.clear();

        // Обрабатываем вершины в порядке, обратном завершения
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                Set<String> component = new HashSet<>();
                transposedGraph.DFSUtil(vertex, visited, component);

                // Выводим компоненту сильной связности
                System.out.println(String.join("", component));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        GraphC graph = new GraphC();

        // Разбиваем входную строку на ребра и строим граф
        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] vertices = edge.split("->");
            graph.addEdge(vertices[0], vertices[1]);
        }

        // Выводим компоненты сильной связности
        graph.printStronglyConnectedComponents();
    }
}
