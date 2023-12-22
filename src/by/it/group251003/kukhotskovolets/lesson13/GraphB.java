package by.it.group251003.kukhotskovolets.lesson13;

import java.util.*;

class GraphB {
    private Map<String, List<String>> graph;

    public GraphB() {
        this.graph = new HashMap<>();
    }

    public void addEdge(String from, String to) {
        if (!graph.containsKey(from)) {
            graph.put(from, new ArrayList<>());
        }
        graph.get(from).add(to);
    }

    public boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        for (String node : graph.keySet()) {
            if (hasCycleUtil(node, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleUtil(String node, Set<String> visited, Set<String> recursionStack) {
        if (recursionStack.contains(node)) {
            return true;
        }

        if (visited.contains(node)) {
            return false;
        }

        visited.add(node);
        recursionStack.add(node);

        if (graph.containsKey(node)) {
            for (String neighbor : graph.get(node)) {
                if (hasCycleUtil(neighbor, visited, recursionStack)) {
                    return true;
                }
            }
        }

        recursionStack.remove(node);

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        GraphB graphB = new GraphB();

        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] nodes = edge.split(" -> ");
            String from = nodes[0];
            String to = nodes[1];
            graphB.addEdge(from, to);
        }

        if (graphB.hasCycle()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}


