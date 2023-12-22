package by.it.group251003.kukhotskovolets.lesson13;

import java.util.*;

class GraphC {
    private Map<Character, List<Character>> graph;
    private Map<Character, List<Character>> reversedGraph;
    private Set<Character> visited;
    private Stack<Character> stack;

    public GraphC() {
        graph = new HashMap<>();
        reversedGraph = new HashMap<>();
        visited = new HashSet<>();
        stack = new Stack<>();
    }

    private void addEdge(char from, char to) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        reversedGraph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
    }

    private void fillOrder(char vertex) {
        visited.add(vertex);
        List<Character> neighbors = graph.getOrDefault(vertex, Collections.emptyList());
        for (char neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                fillOrder(neighbor);
            }
        }
        stack.push(vertex);
    }

    private void dfs(char vertex, Set<Character> component) {
        visited.add(vertex);
        component.add(vertex);
        List<Character> neighbors = reversedGraph.getOrDefault(vertex, Collections.emptyList());
        for (char neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, component);
            }
        }
    }

    private List<Set<Character>> kosaraju() {
        for (char vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                fillOrder(vertex);
            }
        }

        List<Set<Character>> components = new ArrayList<>();
        visited.clear();

        while (!stack.isEmpty()) {
            char vertex = stack.pop();
            if (!visited.contains(vertex)) {
                Set<Character> component = new TreeSet<>();
                dfs(vertex, component);
                components.add(component);
            }
        }

        return components;
    }

    private void printStronglyConnectedComponents(List<Set<Character>> components) {
        for (Set<Character> component : components) {
            for (char vertex : component) {
                System.out.print(vertex);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphC graphC = new GraphC();
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String[] edges = input.split(", ");
        for (String edge : edges) {
            char from = edge.charAt(0);
            char to = edge.charAt(3);
            graphC.addEdge(from, to);
        }

        List<Set<Character>> components = graphC.kosaraju();
        graphC.printStronglyConnectedComponents(components);
    }
}

