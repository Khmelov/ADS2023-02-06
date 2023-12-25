package by.it.group251003.kukhotskovolets.lesson13;

import java.util.*;

public class GraphA {
    private Map<String, List<String>> adjacencyList;

    public GraphA() {
        adjacencyList = new TreeMap<>();
    }

    public void addEdge(String edge) {
        String[] parts = edge.split(" -> ");
        String from = parts[0];
        String to = parts[1];

        if (!adjacencyList.containsKey(from)) {
            adjacencyList.put(from, new ArrayList<>());
        }

        adjacencyList.get(from).add(to);
    }

    public List<String> topologicalSort() {
        Map<String, Integer> inDegree = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>();

        // Calculate in-degree for each vertex
        for (String vertex : adjacencyList.keySet()) {
            inDegree.put(vertex, inDegree.getOrDefault(vertex, 0));
            for (String neighbor : adjacencyList.get(vertex)) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparing((String v) -> inDegree.get(v)).thenComparing(v -> v));

        for (String vertex : inDegree.keySet()) {
            if (inDegree.get(vertex) == 0) {
                priorityQueue.offer(vertex);
            }
        }

        List<String> result = new ArrayList<>();

        while (!priorityQueue.isEmpty()) {
            String current = priorityQueue.poll();
            result.add(current);

            if (adjacencyList.containsKey(current)) {
                for (String neighbor : adjacencyList.get(current)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        priorityQueue.offer(neighbor);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        GraphA graph = new GraphA();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] edges = input.split(", ");

        for (String edge : edges) {
            graph.addEdge(edge);
        }

        List<String> topologicalOrder = graph.topologicalSort();
        System.out.println(String.join(" ", topologicalOrder));
    }
}

